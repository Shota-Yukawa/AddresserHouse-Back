package com.ah.residence.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ah.commonlib.JsonConverter;
import com.ah.residence.datasource.entity.AddresserResidencesEntity;
import com.ah.residence.datasource.readimpl.AddresserResidenceReadRepositoryImpl;
import com.ah.residence.datasource.repository.AddresserResidencesRepository;
import com.ah.residence.exception.ResidenceException;
import com.ah.residence.message.ValidationMessageEnum;
import com.ah.residence.models.req.AddresserResidenceReq;
import com.ah.residence.models.req.CommonReq;
import com.ah.residence.models.res.AddresserResidenceRes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddresserResidenceService {
	private final AddresserResidencesRepository rep;
	private final ModelMapper modelMapper;
	private final AddresserResidenceReadRepositoryImpl readImpl;
	private final JsonConverter jsonConverter;

	/**
	 * addresserResidenceテーブルへの登録用メソッド
	 * 
	 * @param reqBody AddresserResidenceReq型の登録データ
	 */
	public AddresserResidenceRes create(CommonReq reqBody) {
		AddresserResidenceReq reqData = jsonConverter.deserializeJson(reqBody.getData(), AddresserResidenceReq.class);
		
		if(readImpl.isExistsByUniqueCol(reqData)) {
			//一意チェックですでにある場合
			throw new ResidenceException(ValidationMessageEnum.AddresserResidenceUniqueError.getM());
		}
		
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
		
		if(readImpl.isExistsByUniqueColNotEqId(reqData, reqBody.getId())) {
			//一意チェックですでにある場合
			throw new ResidenceException(ValidationMessageEnum.AddresserResidenceUniqueError.getM());
		}
		
		AddresserResidencesEntity select = readImpl.existCheckAndGetById(reqBody.getId());
		LocalDateTime registAt = select.getRegistAt();
		
		// リクエストをEntityにマッピング
		AddresserResidencesEntity reqEntity = modelMapper.map(reqData, AddresserResidencesEntity.class);
		reqEntity.setAddresserResidenceId(reqBody.getId());
		
		//登録日時だけ独自セット
		reqEntity.setRegistAt(registAt);
		
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
	public AddresserResidenceRes delete(Integer reqId){
		AddresserResidencesEntity select = readImpl.existCheckAndGetById(reqId);
		rep.delete(select);
		
		// レスポンスの定義
		AddresserResidenceRes res = new AddresserResidenceRes();
		res.setAddressserResidenceId(reqId);
		return res;
	}
}
