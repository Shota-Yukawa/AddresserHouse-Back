package com.ah.apartowner.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ah.apartowner.datasource.entity.ApartownersEntity;
import com.ah.apartowner.datasource.repository.ApartownerRepository;
import com.ah.apartowner.models.request.ApartownerReq;
import com.ah.apartowner.models.response.ApartownerRes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional // メソッド開始時にトランザクションを開始、終了時にコミットする
public class AapartownerService {

	private final ApartownerRepository rep;
	private final ModelMapper modelMapper;

	public ApartownerRes regist(ApartownerReq reqData) {
		// entityにマッピング
		ApartownersEntity reqEntity = modelMapper.map(reqData, ApartownersEntity.class);

		// 登録処理
		reqEntity = rep.save(reqEntity);

		// レスポンス
		ApartownerRes res = new ApartownerRes();
		res.setApartownerId(reqEntity.getApartownerId());
		return res;
	}
}
