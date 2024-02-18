package com.ah.apartowner.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ah.apartowner.controllers.ApartmentController;
import com.ah.apartowner.controllers.ApartownerController;
import com.ah.apartowner.datasource.entity.ApartmentsEntity;
import com.ah.apartowner.datasource.entity.ApartownersEntity;
import com.ah.apartowner.datasource.readimpl.ApartmentReadRepositoryImpl;
import com.ah.apartowner.datasource.readimpl.ApartownerReadRepositoryImpl;
import com.ah.apartowner.datasource.repository.ApartmentsRepository;
import com.ah.apartowner.exception.AapartownerException;
import com.ah.apartowner.messages.ValidationMessageEnum;
import com.ah.apartowner.models.request.ApartmentReq;
import com.ah.apartowner.models.request.CommonReq;
import com.ah.apartowner.models.response.ApartmentRes;
import com.ah.commonlib.BackUtil;
import com.ah.commonlib.EntityUtil;
import com.ah.commonlib.JsonConverter;
import com.ah.commonlib.StringConverter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApartmentService {
	
	private final ApartmentsRepository rep;
	private final ModelMapper modelMapper;
	private final ApartmentReadRepositoryImpl readImpl;
	private final ApartownerReadRepositoryImpl apartownerReadImpl;
	private final EntityManager entityManager;

	/**
	 * apartmentsテーブルへの登録処理
	 * @param reqBody
	 * @return
	 */
	public ApartmentRes regist(CommonReq reqBody) {
		//リクエストBeanにデシリアライズ
		ApartmentReq reqData = JsonConverter.deserializeJson(reqBody.getData(), ApartmentReq.class);
		if (readImpl.isExsitsByUniqueCol(reqData)) {
			//一意チェックですでにある場合
			throw new AapartownerException(ValidationMessageEnum.ApartmentUniqueError.getM());
		}

		//アパートオーナーのチェックと取得
		ApartownersEntity apartowner = apartownerReadImpl.existCheckAndGetById(reqData.getApartownerId());

		//Entity作成
		ApartmentsEntity reqEntity = modelMapper.map(reqData, ApartmentsEntity.class);
		//アパートオーナーのセット
		reqEntity.setApartowner(apartowner);
		
		//登録処理
		reqEntity = rep.save(reqEntity);
		
		ApartmentRes res = modelMapper.map(reqEntity, ApartmentRes.class);
		//紐付きのアパートオーナーのIDをセット
		res.setApartownerId(apartowner.getApartownerId());
		
		return res;
	}
	
	/**
	 * apartmentsテーブルへの更新処理<br>
	 * 前カラムの更新
	 * @param reqBody
	 * @return
	 */
	public ApartmentRes update(CommonReq reqBody) {
		
		//リクエストBeanにデシリアライズ
		ApartmentReq reqData = JsonConverter.deserializeJson(reqBody.getData(), ApartmentReq.class);
		
		if (readImpl.isExsitsByUniqueColNotEqId(reqData, reqBody.getId())) {
			//一意チェックですでにある場合
			throw new AapartownerException(ValidationMessageEnum.ApartmentUniqueError.getM());
		}
		//登録日時だけ更新しないため取得
		LocalDateTime registAt = readImpl.existCheckAndGetById(reqBody.getId()).getRegistAt();

		ApartmentsEntity reqEntity = modelMapper.map(reqData, ApartmentsEntity.class);
		reqEntity.setApartmentId(reqBody.getId());
		//登録日時だけ独自セット
		reqEntity.setRegistAt(registAt);
		
		//紐付きの設定
		reqEntity.setApartowner(apartownerReadImpl.existCheckAndGetById(reqData.getApartownerId()));

		// 登録処理
		reqEntity = rep.save(reqEntity);
		
		//レスポンス
		ApartmentRes res = modelMapper.map(reqEntity, ApartmentRes.class);

		return res;
	}

	/**
	 * 特定項目のみの更新用メソッド<br>
	 * リクエストにある jsonからCriteriaUpdateを作成し、Query実行する。<br>
	 * 他テーブルへのリレーションは個別に取得してセットする
	 * @param reqBody
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> updatePart(List<Map<String, Object>> reqBody) {

		//リクエストjsonのプロパティを項目（カラム）名だけにして、値とMapにする。
		List<Map<String, Object>> updateDataList = BackUtil.shapeReqKeyToColumnCamel(reqBody);
		if(Objects.isNull(updateDataList)) {
			throw new AapartownerException(ValidationMessageEnum.RequestBodyError.getM());
		}
		//PK項目かの判別用に、idの項目名をキャメルケースに変換
		String camelPkColumn = StringConverter.convertSnakeToCamel(ApartmentController.PK_COLUMN_NAME);
		String camelApartownerId = StringConverter.convertSnakeToCamel(ApartownerController.PK_COLUMN_NAME);
		
		for (Map<String, Object> updateData : updateDataList) {
			//idの取得
			Integer id = Integer.valueOf(updateData.get(camelPkColumn).toString());
			//更新データMAPから削除
			updateData.remove(camelPkColumn);

			//クリテリアで更新クエリを作成
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaUpdate<ApartmentsEntity> update = criteriaBuilder.createCriteriaUpdate(ApartmentsEntity.class);
			Root<ApartmentsEntity> root = update.getRoot();

			//更新日は手動でセット
			update.set(root.get(EntityUtil.UPDATEATCAMEL), LocalDateTime.now());
			
			//各カラムのセット
			for (Entry<String, Object> entry : updateData.entrySet()) {
				if (Objects.equals(entry.getKey(), camelApartownerId)) {
					//apartownerへのrelation
					if (Objects.isNull(entry.getValue())) {// nullチェック
						throw new AapartownerException(ValidationMessageEnum.RequestUnacceptedValueError
								.getMWithParam(ApartownerController.PK_COLUMN_NAME));
					}
					ApartownersEntity apartowner = apartownerReadImpl
							.existCheckAndGetById(Integer.valueOf(entry.getValue().toString()));
					update.set(root.get(ApartownerController.ENTITY_REL_FIELD_NAME), apartowner);
				} else {
					//その他のカラム
					update.set(root.get(entry.getKey()), entry.getValue());
				}
			}
			//id指定のwhere句
			update.where(criteriaBuilder.equal(root.get(camelPkColumn), id));
			//更新実行
			try {
				entityManager.createQuery(update).executeUpdate();
			} catch (ConstraintViolationException cve) {
				cve.printStackTrace();
				throw new AapartownerException(ValidationMessageEnum.RequestUnacceptedValueError
						.getMWithParam(cve.getConstraintName()));
			}
		}
		return reqBody;
	}
}
