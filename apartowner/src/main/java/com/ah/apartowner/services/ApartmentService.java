package com.ah.apartowner.services;

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
}
