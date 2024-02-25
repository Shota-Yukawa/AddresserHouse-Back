package com.ah.residence.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ah.commonlib.JsonConverter;
import com.ah.residence.datasource.entity.AddresserResidencesEntity;
import com.ah.residence.datasource.repository.AddresserResidencesRepository;
import com.ah.residence.exception.ResidenceException;
import com.ah.residence.models.req.AddresserResidenceReq;
import com.ah.residence.models.req.CommonReq;
import com.ah.residence.models.res.AddresserResidenceRes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddresserResidenceService {
	private final AddresserResidencesRepository rep;
	private final ModelMapper modelMapper;
	private final JsonConverter jsonConverter;

	/**
	 * addresserResidenceテーブルへの登録用メソッド
	 * 
	 * @param reqBody AddresserResidenceReq型の登録データ
	 */
	public AddresserResidenceRes create(CommonReq reqBody) {
		AddresserResidenceReq reqData = jsonConverter.deserializeJson(reqBody.getData(), AddresserResidenceReq.class);
		
		// リクエストをEntityにマッピング
		AddresserResidencesEntity reqEntity = modelMapper.map(reqData, AddresserResidencesEntity.class);
		// 登録処理
		reqEntity = rep.save(reqEntity);
		// レスポンスの定義
		AddresserResidenceRes resDto = new AddresserResidenceRes();
		resDto.setAddressserResidenceId(reqEntity.getAddresserResidenceId());
		return resDto;
	}

	/**
	 * addresserResidenceテーブルの更新用メソッド
	 * 
	 * @param targetId 更新対象のaddresser_residence_id(主キー)
	 * @param reqBody  AddresserResidenceReq型の更新データ
	 * @return
	 * @throws ResidenceException 該当データなし
	 */
	public AddresserResidenceRes update(CommonReq reqBody) {
		
		AddresserResidenceReq reqData = jsonConverter.deserializeJson(reqBody.getData(), AddresserResidenceReq.class);
		
		// リクエストをEntityにマッピング
		AddresserResidencesEntity reqEntity = modelMapper.map(reqData, AddresserResidencesEntity.class);
		reqEntity.setAddresserResidenceId(reqBody.getId());
		// 登録処理
		reqEntity = rep.save(reqEntity);
		// レスポンスの定義
		return modelMapper.map(reqEntity, AddresserResidenceRes.class);
	}

	/**
	 * addresser_residencesテーブルへの削除メソッド
	 * 
	 * @param targetId
	 * @throws ResidenceException
	 */
	public void delete(Integer targetId){
		// 削除処理
		rep.deleteById(targetId);
	}
}
