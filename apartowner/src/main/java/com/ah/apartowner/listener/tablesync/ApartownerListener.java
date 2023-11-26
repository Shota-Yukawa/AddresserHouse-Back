package com.ah.apartowner.listener.tablesync;

import com.ah.apartowner.datasource.entity.ApartownersEntity;
import com.ah.commonlib.EntityUtil;
import com.ah.tablesynclib.bean.TableSyncRequestBean;
import com.ah.tablesynclib.rest.RequestService;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApartownerListener {

	private final RequestService requestService;

	@PostPersist
	public void afterInsert(ApartownersEntity entity) {
		// エンティティが挿入された後に実行したい処理を記述
		String tableName = EntityUtil.getEntityTalbeName(entity.getClass());
		TableSyncRequestBean reqData = new TableSyncRequestBean(tableName, EntityUtil.methodEnum.insert, entity);
		requestService.syncApartownerInsert(reqData);
	}

	@PostUpdate
	public void afterUpdate(ApartownersEntity entity) {
		// エンティティが更新された後に実行したい処理を記述
	}

	@PostRemove
	public void afterDelete(ApartownersEntity entity) {
		// エンティティが削除された後に実行したい処理を記述
	}
}
