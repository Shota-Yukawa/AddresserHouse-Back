package com.ah.apartowner.services;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ah.apartowner.controllers.ApartownerController;
import com.ah.apartowner.datasource.entity.ApartownersEntity;
import com.ah.apartowner.datasource.repository.ApartownerRepository;
import com.ah.apartowner.exception.AapartownerException;
import com.ah.apartowner.models.request.ApartownerReq;
import com.ah.apartowner.models.response.ApartownerRes;
import com.ah.apartowner.util.StringConverter;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//@Transactional // メソッド開始時にトランザクションを開始、終了時にコミットする
public class AapartownerService {

	private final ApartownerRepository rep;
	private final ModelMapper modelMapper;


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

	/**
	 * 特定項目のみの更新用メソッド<br>
	 * リクエストにあるjson propertyから、カラム名を作り、Eintyにセットして更新する。<br>
	 * 上記のupdate()と同じにしてもいいかも。
	 * @param reqBody
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> updatePart(List<Map<String, Object>> reqBody) {

		//リクエストjsonのプロパティを項目（カラム）名だけにして、値とMapにする。
		List<Map<String, Object>> updateDataList = reqBody
				.stream()
				.map(reqMap -> reqMap.entrySet().stream()
						.filter(entry -> entry.getKey().contains("."))
						.collect(Collectors.toMap(
								entry -> StringConverter.convertSnakeToCamel(entry.getKey().split("\\.")[1]), //絡む名だけ取得し、キャメルケースに変換
								Map.Entry::getValue)))
				.collect(Collectors.toList());

		//PK項目かの判別用に、idの項目名をキャメルケースに変換
		String camelPkColumn = StringConverter.convertSnakeToCamel(ApartownerController.PKCOLUMNNAME);
		// 更新リクエストごとに処理
		for (Map<String, Object> updateData : updateDataList) {
			//idの取得
			//TODO queryへ検索
			Integer id = Integer.valueOf(updateData.get(camelPkColumn).toString());
			//更新データMAPから削除
			updateData.remove(camelPkColumn);
			//idで検索
			Optional<ApartownersEntity> updateEntityOpt = rep.findById(id);
			if (updateEntityOpt.isEmpty()) {
				//データがない場合
				throw new AapartownerException("指定されたidの更新対象のデータが存在しません。");
			}
			//更新する項目と値をentityにをセットする
			for (Entry<String, Object> entry : updateData.entrySet()) {
				try {
					PropertyUtils.setProperty(updateEntityOpt.get(), entry.getKey(), entry.getValue());
				} catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
					e.printStackTrace();
					throw new AapartownerException("更新リクエスト内容に誤りがあります。");
				}
			}
			rep.save(updateEntityOpt.get());
		}
		return null;
	}

	/**
	 * apartownerテーブルへの削除処理
	 * @param reqid
	 * @return
	 */
	public ApartownerRes delete(Integer reqid) {
		//TODO queryへ検索
		Optional<ApartownersEntity> select = rep.findById(reqid);

		if (select.isEmpty()) {
			throw new AapartownerException("指定されたidの削除対象のデータが存在しません。");
		}
		rep.delete(select.get());

		// レスポンス
		ApartownerRes res = new ApartownerRes();
		res.setApartownerId(reqid);
		return res;
	}
}
