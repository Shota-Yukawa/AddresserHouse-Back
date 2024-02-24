package com.ah.apartowner.services;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.apache.commons.beanutils.PropertyUtils;
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
import com.ah.commonlib.JsonConverter;
import com.ah.commonlib.StringConverter;

import jakarta.persistence.EntityManager;
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
	private final JsonConverter jsonConverter;

	/**
	 * apartmentsテーブルへの登録処理
	 * @param reqBody
	 * @return
	 */
	public ApartmentRes regist(CommonReq reqBody) {
		//リクエストBeanにデシリアライズ
		ApartmentReq reqData = jsonConverter.deserializeJson(reqBody.getData(), ApartmentReq.class);
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
		ApartmentReq reqData = jsonConverter.deserializeJson(reqBody.getData(), ApartmentReq.class);
		
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
		
		for (Map<String, Object> updateData : updateDataList) {
			//idの取得
			Integer id = Integer.valueOf(updateData.get(camelPkColumn).toString());
			//更新データMAPから削除
			updateData.remove(camelPkColumn);

			ApartmentsEntity entity = readImpl.existCheckAndGetById(id);
			
			//各カラムのセット
			for (Entry<String, Object> entry : updateData.entrySet()) {
				try {
				if (Objects.equals(entry.getKey(), ApartownerController.ENTITY_REL_FIELD_NAME)) {
					//apartownerへのrelation
					if (Objects.isNull(entry.getValue())) {// nullチェック
						throw new AapartownerException(ValidationMessageEnum.RequestUnacceptedValueError
								.getMWithParam(ApartownerController.ENTITY_REL_FIELD_NAME));
					}
					ApartownersEntity apartowner = apartownerReadImpl
							.existCheckAndGetById(Integer.valueOf(entry.getValue().toString()));
					PropertyUtils.setProperty(entity, entry.getKey(), apartowner);
				} else {
					//その他のカラム
					PropertyUtils.setProperty(entity, entry.getKey(), entry.getValue());
				}
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					e.printStackTrace();
					throw new AapartownerException(ValidationMessageEnum.RequestUnacceptedValueError
							.getMWithParam(entry.getKey()));
				}
			}
			rep.save(entity);
		}
		return reqBody;
	}
}
