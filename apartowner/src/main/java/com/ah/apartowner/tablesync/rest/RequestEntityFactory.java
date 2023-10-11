package com.ah.apartowner.tablesync.rest;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

import com.ah.apartowner.tablesync.bean.TableSyncRequestBean;

@Component
public class RequestEntityFactory {

	/**
	 * Post メソッドのRequestEntityの作成メソッド
	 * 
	 * @param url  String リクエストエンドポイント
	 * @param body TableSyncRequestBean リクエストボディ
	 * @return RequestEntity
	 */
	public RequestEntity<TableSyncRequestBean> getPostEntity(String url, TableSyncRequestBean body) {
		HttpHeaders header = new HttpHeaders();
		RequestEntity<TableSyncRequestBean> restEntity = RequestEntity.post(URI.create(url)).headers(header).body(body);

		return restEntity;
	}
}
