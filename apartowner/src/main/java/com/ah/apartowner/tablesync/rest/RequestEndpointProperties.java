package com.ah.apartowner.tablesync.rest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.ah.apartowner.tablesync.util.EntityUtil;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "tablesync")
public class RequestEndpointProperties {

	private String url;

	private final String APARTROWNER = "/apartowner/";

	private final EntityUtil.methodEnum INSERT = EntityUtil.methodEnum.insert;
	private final EntityUtil.methodEnum UPDATE = EntityUtil.methodEnum.update;
	private final EntityUtil.methodEnum DELETE = EntityUtil.methodEnum.delete;

	/**
	 * aparowner/insert の取得
	 * 
	 * @return
	 */
	public String getApartownerInsert() {
		return url + APARTROWNER + INSERT;
	}

	/**
	 * aparowner/update の取得
	 * 
	 * @return
	 */
	public String getApartownerUpdate() {
		return url + APARTROWNER + UPDATE;
	}

	/**
	 * aparowner/delete の取得
	 * 
	 * @return
	 */
	public String getApartownerDelete() {
		return url + APARTROWNER + DELETE;
	}

}
