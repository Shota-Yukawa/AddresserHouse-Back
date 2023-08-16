package com.ah.residence.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ah.residence.datasource.entity.AddresserResidencesEntity;
import com.ah.residence.datasource.repository.AddresserResidencesRepository;
import com.ah.residence.exception.ResidenceException;
import com.ah.residence.models.req.AddresserResidenceReq;
import com.ah.residence.models.res.AddresserResidenceIdRes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddresserResidenceService {
	private final AddresserResidencesRepository rep;
	private final ModelMapper modelMapper;

	/**
	 * addresserResidenceテーブルへの登録用メソッド
	 * 
	 * @param reqBody AddresserResidenceReq型の登録データ
	 */
	public AddresserResidenceIdRes create(AddresserResidenceReq reqBody) {
		// リクエストをEntityにマッピング
		AddresserResidencesEntity reqEntity = modelMapper.map(reqBody, AddresserResidencesEntity.class);
		// 登録処理
		reqEntity = rep.save(reqEntity);
		// レスポンスの定義
		AddresserResidenceIdRes resDto = new AddresserResidenceIdRes();
		resDto.setAddressserResidenceId(reqEntity.getAddresserResidenceId());
		return resDto;
	}

	/**
	 * addresserResidenceテーブルの更新用メソッド
	 * 
	 * @param targetId addresser_residence_id(主キー)
	 * @param reqBody  AddresserResidenceReq型の更新データ
	 * @return
	 * @throws ResidenceException
	 */
	public AddresserResidenceIdRes update(Integer targetId, AddresserResidenceReq reqBody) throws ResidenceException {

		Optional<AddresserResidencesEntity> entityOpt = rep.findById(targetId);
		if (entityOpt.isPresent()) {
			modelMapper.map(reqBody, entityOpt.get());
			AddresserResidencesEntity resEntity = rep.save(entityOpt.get());
			return modelMapper.map(resEntity, AddresserResidenceIdRes.class);
		} else {
			throw new ResidenceException("該当のデータがありません。");
		}

	}
}
