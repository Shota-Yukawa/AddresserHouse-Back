package com.ah.apartowner.services;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ah.apartowner.controllers.ApartownerController;
import com.ah.apartowner.datasource.entity.ApartownersEntity;
import com.ah.apartowner.datasource.readimpl.ApartownerReadRepositoryImpl;
import com.ah.apartowner.datasource.repository.ApartownerRepository;
import com.ah.apartowner.exception.AapartownerException;
import com.ah.apartowner.messages.ValidationMessageEnum;
import com.ah.apartowner.models.request.ApartownerReq;
import com.ah.apartowner.models.request.CommonReq;
import com.ah.apartowner.models.response.ApartownerRes;
import com.ah.commonlib.BackUtil;
import com.ah.commonlib.JsonConverter;
import com.ah.commonlib.StringConverter;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//@Transactional // メソッド開始時にトランザクションを開始、終了時にコミットする
public class ApartownerService {

	private final ApartownerRepository rep;
	private final ModelMapper modelMapper;
	private final ApartownerReadRepositoryImpl readImpl;


	/**
	 * aprtownersテーブルへの登録処理
	 * @param reqBody 
	 * @param reqData
	 * @return
	 */
	public ApartownerRes regist(CommonReq reqBody) {
		
		ApartownerReq reqData = JsonConverter.deserializeJson(reqBody.getData(), ApartownerReq.class);
		
		if(readImpl.isExsitsByUniqueCol(reqData)) {
			//一意チェックですでにある場合
			throw new AapartownerException(ValidationMessageEnum.ApartownerUniqueError.getM());
		}
		
		// entityにマッピング
		ApartownersEntity reqEntity = modelMapper.map(reqData, ApartownersEntity.class);

		// 登録処理
		reqEntity = rep.save(reqEntity);

		// レスポンス
		ApartownerRes res = new ApartownerRes();
		res.setApartownerId(reqEntity.getApartownerId());
		return res;
	}

	/**
	 * aprtownersテーブルへの更新処理<br>
	 * 全カラムの更新
	 * @param reqData
	 * @return
	 */
	public ApartownerRes update(CommonReq reqBody) {
		
		ApartownerReq reqData = JsonConverter.deserializeJson(reqBody.getData(), ApartownerReq.class);

		if (readImpl.isExsitsByUniqueColNotEqId(reqData, reqBody.getId())) {
			//一意チェックですでにある場合
			throw new AapartownerException(ValidationMessageEnum.ApartownerUniqueError.getM());
		}
		
		//登録日時だけ更新しないため取得
		ApartownersEntity select  = readImpl.existCheckAndGetById(reqBody.getId());
		LocalDateTime registAt = select.getRegistAt();

		// entityにマッピングして、idセット
		ApartownersEntity reqEntity = modelMapper.map(reqData, ApartownersEntity.class);
		reqEntity.setApartownerId(reqBody.getId());
		
		//登録日時だけ独自セット
		reqEntity.setRegistAt(registAt);

		// 登録処理
		reqEntity = rep.save(reqEntity);

		// レスポンス
		ApartownerRes res = modelMapper.map(reqEntity, ApartownerRes.class);
		return res;
	}

	/**
	 * 特定項目のみの更新用メソッド<br>
	 * リクエストにあるjson propertyから、カラム名を作り、Eintyにセットして更新する。<br>
	 * 上記のupdate()と同じにしてもいいかも。
	 * @param reqBody
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> updatePart(List<Map<String, Object>> reqBody) {

		//リクエストjsonのプロパティを項目（カラム）名だけにして、値とMapにする。
		List<Map<String, Object>> updateDataList = BackUtil.shapeReqKeyToColumnCamel(reqBody);

		//PK項目かの判別用に、idの項目名をキャメルケースに変換
		String camelPkColumn = StringConverter.convertSnakeToCamel(ApartownerController.PKCOLUMNNAME);
		// 更新リクエストごとに処理
		for (Map<String, Object> updateData : updateDataList) {
			//idの取得
			Integer id = Integer.valueOf(updateData.get(camelPkColumn).toString());
			//更新データMAPから削除
			updateData.remove(camelPkColumn);
			//idで検索
			ApartownersEntity select  = readImpl.existCheckAndGetById(id);
			//更新する項目と値をentityにをセットする
			for (Entry<String, Object> entry : updateData.entrySet()) {
				try {
					PropertyUtils.setProperty(select, entry.getKey(), entry.getValue());
				} catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
					e.printStackTrace();
					throw new AapartownerException(ValidationMessageEnum.RequestPartBodyError.getM());
				}
			}
			rep.save(select);
		}
		return null;
	}

	/**
	 * apartownerテーブルへの削除処理
	 * @param reqid
	 * @return
	 */
	public ApartownerRes delete(Integer reqId) {
		ApartownersEntity select  = readImpl.existCheckAndGetById(reqId);
		rep.delete(select);

		// レスポンス
		ApartownerRes res = new ApartownerRes();
		res.setApartownerId(reqId);
		return res;
	}
	
}
