package com.ah.apartowner.util;

import org.springframework.util.StringUtils;

public class StringConverter {

	/**
	 * スネークケースのStringをキャメルケースに変換する。
	 * @param snakeCaseStr
	 * @return
	 */
	public static String convertSnakeToCamel(String snakeCaseStr) {
		String[] parts = snakeCaseStr.split("_");
		StringBuilder camelCaseString = new StringBuilder(parts[0]);
		for (int i = 1; i < parts.length; i++) {
			camelCaseString.append(StringUtils.capitalize(parts[i]));
		}
		return camelCaseString.toString();
	}
}
