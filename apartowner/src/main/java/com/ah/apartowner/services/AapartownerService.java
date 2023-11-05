package com.ah.apartowner.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ah.apartowner.datasource.entity.ApartownersEntity;
import com.ah.apartowner.datasource.repository.ApartownerRepository;
import com.ah.apartowner.exception.AapartownerException;
import com.ah.apartowner.models.request.ApartownerReq;
import com.ah.apartowner.models.response.ApartownerRes;

@Service
//@Transactional // メソッド開始時にトランザクションを開始、終了時にコミットする
public class AapartownerService {

	private final ApartownerRepository rep;
	private final ModelMapper modelMapper;
	private final ModelMapper modelMapperSkipNull;
	
	@Autowired
	public AapartownerService(ApartownerRepository rep, ModelMapper modelMapper, @Qualifier("modelMapperSkipNull") ModelMapper modelMapperSkipNull) {
		this.rep = rep;
		this.modelMapper = modelMapper;
		this.modelMapperSkipNull = modelMapperSkipNull;
	}

	/**
	 * aprtownersテーブルへの登録処理
	 * @param reqData
	 * @return
	 */
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
	
	/**
	 * aprtownersテーブルへの登録処理<br>
	 * 全カラムの更新
	 * @param reqData
	 * @return
	 */
	public ApartownerRes update(ApartownerReq reqData) {
		
		//TODO queryへ検索
		Optional<ApartownersEntity> select = rep.findById(reqData.getApartownerId());
		
		if (select.isEmpty()) {
			throw new AapartownerException("指定されたidの更新対象のデータが存在しません。");
		}
		
		//登録日時だけ更新しないため取得
		LocalDateTime registAt = select.get().getRegistAt();
		
		// entityにマッピング
		ApartownersEntity reqEntity = modelMapper.map(reqData, ApartownersEntity.class);
		modelMapper.map(reqEntity, select.get());
		
		//登録日時だけ独自セット
		select.get().setRegistAt(registAt);

		// 登録処理
		reqEntity = rep.save(select.get());

		// レスポンス
		ApartownerRes res = modelMapper.map(reqEntity, ApartownerRes.class);
		return res;
	}

}
