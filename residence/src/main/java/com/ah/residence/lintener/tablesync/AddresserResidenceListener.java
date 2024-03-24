package com.ah.residence.lintener.tablesync;

import com.ah.commonlib.EntityUtil;
import com.ah.residence.datasource.entity.AddresserResidencesEntity;
import com.ah.tablesynclib.bean.TableSyncRequestBean;
import com.ah.tablesynclib.rest.services.ResidenceSyncRequestService;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;

/**
 * {@linkplain AddresserResidencesEntity}のEntityListenerクラス<br>
 * tableSyncサービスに同期リクエストを投げる
 */
@RequiredArgsConstructor
public class AddresserResidenceListener {

	private ResidenceSyncRequestService requestService;
	
	/**
	 * [登録] addresser_residencesテーブルを、queryDBへのテーブル同期リクエストを行う。
	 * @param entity
	 */
	@PostPersist
	public void afterInsert(AddresserResidencesEntity entity) {
		// エンティティが挿入された後に実行したい処理を記述
		String tableName = EntityUtil.getEntityTalbeName(entity.getClass());
		TableSyncRequestBean reqData = new TableSyncRequestBean(tableName, EntityUtil.methodEnum.insert, entity);
		requestService.syncAddresserResidenceInsert(reqData);
	}

	/**
	 * [更新] addresser_residencesテーブルを、queryDBへのテーブル同期リクエストを行う。
	 * @param entity
	 */
	@PostUpdate
	public void afterUpdate(AddresserResidencesEntity entity) {
		// エンティティが更新された後に実行したい処理を記述
		String tableName = EntityUtil.getEntityTalbeName(entity.getClass());
		TableSyncRequestBean reqData = new TableSyncRequestBean(tableName, EntityUtil.methodEnum.update, entity);
		requestService.syncAddresserResidenceUpdate(reqData);
	}

	/**
	 * [削除] addresser_residencesテーブルを、queryDBへのテーブル同期リクエストを行う。
	 * @param entity
	 */
	@PostRemove
	public void afterDelete(AddresserResidencesEntity entity) {
		// エンティティが削除された後に実行したい処理を記述
		requestService.syncAddresserResidenceDelete(entity.getAddresserResidenceId());
	}
}
