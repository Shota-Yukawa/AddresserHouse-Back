package com.ah.residence.lintener.tablesync;

import com.ah.commonlib.EntityUtil;
import com.ah.residence.datasource.entity.ApartResidencesEntity;
import com.ah.tablesynclib.bean.TableSyncRequestBean;
import com.ah.tablesynclib.rest.services.ResidenceSyncRequestService;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;

/**
 * {@linkplain ApartResidencesEntity}のEntityListenerクラス<br>
 * tableSyncサービスに同期リクエストを投げる
 */
@RequiredArgsConstructor
public class ApartResidenceListener {

	private final ResidenceSyncRequestService requestService;

	/**
	 * [登録] apart_residencesテーブルを、queryDBへのテーブル同期リクエストを行う。
	 * @param entity
	 */
	@PostPersist
	public void afterInsert(ApartResidencesEntity entity) {
		// エンティティが挿入された後に実行したい処理を記述
		String tableName = EntityUtil.getEntityTalbeName(entity.getClass());
		TableSyncRequestBean reqData = new TableSyncRequestBean(tableName, EntityUtil.methodEnum.insert, entity);
		requestService.syncApartResidenceInsert(reqData);
	}

	/**
	 * [更新] apart_residencesテーブルを、queryDBへのテーブル同期リクエストを行う。
	 * @param entity
	 */
	@PostUpdate
	public void afterUpdate(ApartResidencesEntity entity) {
		// エンティティが更新された後に実行したい処理を記述
		String tableName = EntityUtil.getEntityTalbeName(entity.getClass());
		TableSyncRequestBean reqData = new TableSyncRequestBean(tableName, EntityUtil.methodEnum.update, entity);
		requestService.syncApartResidenceUpdate(reqData);
	}

	/**
	 * [削除] apart_residencesテーブルを、queryDBへのテーブル同期リクエストを行う。
	 * @param entity
	 */
	@PostRemove
	public void afterDelete(ApartResidencesEntity entity) {
		// エンティティが削除された後に実行したい処理を記述
		requestService.syncApartResidenceDelete(entity.getApartResidenceId());
	}
}
