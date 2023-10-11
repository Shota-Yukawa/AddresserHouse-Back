package com.ah.apartowner.tablesync.bean;

import com.ah.apartowner.datasource.entity.ApartownersEntity;
import com.ah.apartowner.tablesync.util.EntityUtil;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableSyncRequestBean {

//	public TableSyncRequestBean(String tableName, EntityUtil.methodEnum method) {
//		this.talbeName = tableName;
//		this.method = method;
//	}

	private String talbeName;

	private EntityUtil.methodEnum method;

	private ApartownersEntity data;

}
