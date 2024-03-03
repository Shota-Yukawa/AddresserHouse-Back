package com.ah.apartowner.listener.tablesync;

import com.ah.apartowner.datasource.entity.ApartownersEntity;
import com.ah.commonlib.EntityUtil;
import com.ah.tablesynclib.bean.TableSyncRequestBean;
import com.ah.tablesynclib.rest.services.ApartownerSyncRequestService;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;

/**
 * {@linkplain ApartownersEntity}のEntityListerクラス<br>
 * tableSyncサービスに同期リクエストを投げる
 */
@RequiredArgsConstructor
public class ApartownerListener {

	private final ApartownerSyncRequestService requestService;

	/**
	 * [登録] apartownerテーブルを、queryDBへのテーブル同期リクエストを行う。
	 * @param entity
	 */
	@PostPersist
	public void afterInsert(ApartownersEntity entity) {
		// エンティティが挿入された後に実行したい処理を記述
		String tableName = EntityUtil.getEntityTalbeName(entity.getClass());
		TableSyncRequestBean reqData = new TableSyncRequestBean(tableName, EntityUtil.methodEnum.insert, entity);
		requestService.syncApartownerInsert(reqData);
	}

	/**
	 * [更新] apartownerテーブルを、queryDBへのテーブル同期リクエストを行う。
	 * @param entity
	 */
	@PostUpdate
	public void afterUpdate(ApartownersEntity entity) {
		// エンティティが更新された後に実行したい処理を記述
		String tableName = EntityUtil.getEntityTalbeName(entity.getClass());
		TableSyncRequestBean reqData = new TableSyncRequestBean(tableName, EntityUtil.methodEnum.update, entity);
		requestService.syncApartownerUpdate(reqData);
	}

	/**
	 * [削除] apartownerテーブルを、queryDBへのテーブル同期リクエストを行う。
	 * @param entity
	 */
	@PostRemove
	public void afterDelete(ApartownersEntity entity) {
		// エンティティが削除された後に実行したい処理を記述
		requestService.syncApartownerDelete(entity.getApartownerId());
	}
}
