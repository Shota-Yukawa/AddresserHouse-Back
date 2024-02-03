package com.ah.apartowner.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
import com.ah.commonlib.JsonConverter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApartmentService {
	
	private final ApartmentsRepository rep;
	private final ModelMapper modelMapper;
	private final ApartmentReadRepositoryImpl readImpl;
	private final ApartownerReadRepositoryImpl apartownerReadImpl;

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
}
