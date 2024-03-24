package com.ah.apartowner.listener.tablesync;

import com.ah.apartowner.datasource.entity.ApartmentsEntity;
import com.ah.commonlib.EntityUtil;
import com.ah.tablesynclib.bean.TableSyncRequestBean;
import com.ah.tablesynclib.rest.services.ApartownerSyncRequestService;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;

/**
 * {@linkplain ApartmentsEntity}のEntityListerクラス<br>
 * tableSyncサービスに同期リクエストを投げる
 */
@RequiredArgsConstructor
public class ApartmentListener {

	private final ApartownerSyncRequestService requestService;
	
	/**
	 * [登録] apartmentsテーブルを、queryDBへのテーブル同期リクエストを行う。
	 * @param entity
	 */
	@PostPersist
	public void afterInsert(ApartmentsEntity entity) {
		// エンティティが挿入された後に実行したい処理を記述
		String tableName = EntityUtil.getEntityTalbeName(entity.getClass());
		TableSyncRequestBean reqData = new TableSyncRequestBean(tableName, EntityUtil.methodEnum.insert, entity);
		requestService.syncApartmentInsert(reqData);
	}

	/**
	 * [更新] apartmentsテーブルを、queryDBへのテーブル同期リクエストを行う。
	 * @param entity
	 */
	@PostUpdate
	public void afterUpdate(ApartmentsEntity entity) {
		// エンティティが更新された後に実行したい処理を記述
		String tableName = EntityUtil.getEntityTalbeName(entity.getClass());
		TableSyncRequestBean reqData = new TableSyncRequestBean(tableName, EntityUtil.methodEnum.update, entity);
		requestService.syncApartmentUpdate(reqData);
	}

	/**
	 * [削除] apartmentsテーブルを、queryDBへのテーブル同期リクエストを行う。
	 * @param entity
	 */
	@PostRemove
	public void afterDelete(ApartmentsEntity entity) {
		// エンティティが削除された後に実行したい処理を記述
		requestService.syncApartmentDelete(entity.getApartmentId());
	}
}
