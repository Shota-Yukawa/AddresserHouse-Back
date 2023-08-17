package com.ah.residence.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ah.residence.datasource.entity.AddresserResidencesEntity;
import com.ah.residence.datasource.repository.AddresserResidencesRepository;
import com.ah.residence.exception.ResidenceException;
import com.ah.residence.models.req.AddresserResidenceReq;
import com.ah.residence.models.res.AddresserResidenceRes;

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
	public AddresserResidenceRes create(AddresserResidenceReq reqBody) {
		// リクエストをEntityにマッピング
		AddresserResidencesEntity reqEntity = modelMapper.map(reqBody, AddresserResidencesEntity.class);
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
	public AddresserResidenceRes update(Integer targetId, AddresserResidenceReq reqBody) throws ResidenceException {
		// targetIdをもとに検索をかける
		Optional<AddresserResidencesEntity> entityOpt = rep.findById(targetId);

		if (entityOpt.isPresent()) {
			// 結果があれば、reqBodyをマッピング
			modelMapper.map(reqBody, entityOpt.get());
			AddresserResidencesEntity resEntity = rep.save(entityOpt.get());
			return modelMapper.map(resEntity, AddresserResidenceRes.class);
		} else {
			throw new ResidenceException("該当の居住データがありません。");
		}
	}

	public void delete(Integer targetId) throws ResidenceException {
		// targetIdをもとに検索をかける
		Optional<AddresserResidencesEntity> entityOpt = rep.findById(targetId);
		if (entityOpt.isPresent()) {
			// 結果があれば削除
			rep.deleteById(targetId);
		} else {
			throw new ResidenceException("該当の居住データがありません。");
		}
	}
}
