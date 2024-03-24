package com.ah.residence.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ah.commonlib.JsonConverter;
import com.ah.residence.datasource.entity.ApartResidencesEntity;
import com.ah.residence.datasource.readimpl.ApartResidenceReadRepositoryImpl;
import com.ah.residence.datasource.repository.ApartResidencesRepository;
import com.ah.residence.exception.ResidenceException;
import com.ah.residence.message.ValidationMessageEnum;
import com.ah.residence.models.req.ApartResidenceReq;
import com.ah.residence.models.req.CommonReq;
import com.ah.residence.models.res.ApartResidenceRes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApartResidenceService {

	private final ApartResidencesRepository rep;
	private final ModelMapper modelMapper;
	private final ApartResidenceReadRepositoryImpl readImpl;
	private final JsonConverter jsonConverter;

	/**
	 * apart_residenceテーブルへの登録用メソッド
	 * 
	 * @param reqBody ApartResidenceReq型の登録データ
	 * @return
	 */
	public ApartResidenceRes create(CommonReq reqBody) {
		ApartResidenceReq reqData = jsonConverter.deserializeJson(reqBody.getData(), ApartResidenceReq.class);
		
		if(readImpl.isExistsByUniqueCol(reqData)) {
			//一意チェックですでにある場合
			throw new ResidenceException(ValidationMessageEnum.ApartResidenceUniqueError.getM());
		}
		
		// リクエストをEntityにマッピング
		ApartResidencesEntity reqEntity = modelMapper.map(reqData, ApartResidencesEntity.class);
		// 登録処理
		reqEntity = rep.save(reqEntity);
		// レスポンスの定義
		ApartResidenceRes res = new ApartResidenceRes();
		res.setApartResidienceId(reqEntity.getApartResidenceId());
		return res;
	}

	/**
	 * apart_residenceテーブルへの更新用メソッド
	 * 
	 * @param targetId 更新対象のapart_residence_id(主キー)
	 * @param reqbody  ApartResidenceReq型の更新データ
	 * @return
	 * @throws ResidenceException 該当データなし
	 */
	public ApartResidenceRes update(CommonReq reqBody){
		
		ApartResidenceReq reqData = jsonConverter.deserializeJson(reqBody.getData(), ApartResidenceReq.class);
		
		if(readImpl.isExistsByUniqueColNotEqId(reqData, reqBody.getId())) {
			//一意チェックですでにある場合
			throw new ResidenceException(ValidationMessageEnum.ApartResidenceUniqueError.getM());
		}
		
		ApartResidencesEntity select = readImpl.existCheckAndGetById(reqBody.getId());
		LocalDateTime registAt = select.getRegistAt();
		
		// リクエストをEntityにマッピング
		ApartResidencesEntity reqEntity = modelMapper.map(reqData, ApartResidencesEntity.class);
		// IDをセット
		reqEntity.setApartResidenceId(reqBody.getId());
		
		//登録日時だけ独自セット
		reqEntity.setRegistAt(registAt);
		
		// 更新処理
		reqEntity = rep.save(reqEntity);
		// レスポンスの定義
		return modelMapper.map(reqEntity, ApartResidenceRes.class);
	}

	/**
	 * apart_residencesテーブルへの削除用メソッド
	 * 
	 * @param targetId
	 * @return 
	 * @throws ResidenceException
	 */
	public ApartResidenceRes delete(Integer reqId){
		ApartResidencesEntity select = readImpl.existCheckAndGetById(reqId);
		rep.delete(select);
		
		// レスポンスの定義
		ApartResidenceRes res = new ApartResidenceRes();
		res.setApartResidienceId(reqId);
		return res;
	}
}
