package com.ah.apartowner.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T deserializeJson(Object jsonObject, Class<T> targetClass) {
		return objectMapper.convertValue(jsonObject, targetClass);
	}
}
