package com.ah.residence.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ah.commonlib.JsonConverter;
import com.ah.residence.datasource.entity.ApartResidencesEntity;
import com.ah.residence.datasource.repository.ApartResidencesRepository;
import com.ah.residence.exception.ResidenceException;
import com.ah.residence.models.req.ApartResidenceReq;
import com.ah.residence.models.req.CommonReq;
import com.ah.residence.models.res.ApartResidenceRes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApartResidenceService {

	private final ApartResidencesRepository rep;
	private final ModelMapper modelMapper;
	private final JsonConverter jsonConverter;

	/**
	 * apart_residenceテーブルへの登録用メソッド
	 * 
	 * @param reqBody ApartResidenceReq型の登録データ
	 * @return
	 */
	public ApartResidenceRes create(CommonReq reqBody) {
		ApartResidenceReq reqData = jsonConverter.deserializeJson(reqBody.getData(), ApartResidenceReq.class);
		
		// リクエストをEntityにマッピング
		ApartResidencesEntity reqEntity = modelMapper.map(reqData, ApartResidencesEntity.class);
		// 登録処理
		reqEntity = rep.save(reqEntity);
		// レスポンスの定義
		ApartResidenceRes resDto = new ApartResidenceRes();
		resDto.setApartResidienceId(reqEntity.getApartResidenceId());
		return resDto;
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
		// リクエストをEntityにマッピング
		ApartResidencesEntity reqEntity = modelMapper.map(reqData, ApartResidencesEntity.class);
		// IDをセット
		reqEntity.setApartResidenceId(reqBody.getId());
		// 更新処理
		reqEntity = rep.save(reqEntity);
		// レスポンスの定義
		return modelMapper.map(reqEntity, ApartResidenceRes.class);
	}

	/**
	 * apart_residencesテーブルへの削除用メソッド
	 * 
	 * @param targetId
	 * @throws ResidenceException
	 */
	public void delete(Integer targetId){
		// 削除処理
		rep.deleteById(targetId);
	}
}
