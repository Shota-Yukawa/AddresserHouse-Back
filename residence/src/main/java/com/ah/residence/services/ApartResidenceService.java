package com.ah.residence.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ah.residence.datasource.entity.ApartResidencesEntity;
import com.ah.residence.datasource.repository.ApartResidencesRepository;
import com.ah.residence.exception.ResidenceException;
import com.ah.residence.models.req.ApartResidenceReq;
import com.ah.residence.models.res.ApartResidenceRes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApartResidenceService {

	private final ApartResidencesRepository rep;
	private final ModelMapper modelMapper;

	/**
	 * apart_residenceテーブルへの登録用メソッド
	 * 
	 * @param reqBody ApartResidenceReq型の登録データ
	 * @return
	 */
	public ApartResidenceRes create(ApartResidenceReq reqBody) {
		// リクエストをEntityにマッピング
		ApartResidencesEntity reqEntity = modelMapper.map(reqBody, ApartResidencesEntity.class);
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
	public ApartResidenceRes update(Integer targetId, ApartResidenceReq reqbody) throws ResidenceException {
		// targetIdをもとに検索をかける
		Optional<ApartResidencesEntity> entityOpt = rep.findById(targetId);

		if (entityOpt.isPresent()) {
			// 結果があれば、reqBodyをマッピング
			modelMapper.map(reqbody, entityOpt.get());
			ApartResidencesEntity resEntity = rep.save(entityOpt.get());
			return modelMapper.map(resEntity, ApartResidenceRes.class);
		} else {
			throw new ResidenceException("該当の居住データがありません");
		}
	}

	public void delete(Integer targetId) throws ResidenceException {
		// targetIdをもとに検索をかける
		Optional<ApartResidencesEntity> entityOpt = rep.findById(targetId);
		if (entityOpt.isPresent()) {
			// 結果があれば削除
			rep.deleteById(targetId);
		} else {
			throw new ResidenceException("該当の居住データがありません。");
		}
	}
}
