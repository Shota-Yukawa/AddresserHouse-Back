package com.ah.apartowner.tablesync.rest;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ah.apartowner.tablesync.bean.TableSyncRequestBean;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestService {

	private final RequestEntityFactory entityFactory;
	private final RequestEndpointProperties endpointProperties;

	private final RestTemplate rest = new RestTemplate();

	public String syncApartownerInsert(TableSyncRequestBean body) {
		String url = endpointProperties.getApartownerInsert();
		RequestEntity<TableSyncRequestBean> reqEntity = entityFactory.getPostEntity(url, body);

		ResponseEntity<String> res = rest.exchange(reqEntity, String.class);
		return res.getBody();
	}
}
