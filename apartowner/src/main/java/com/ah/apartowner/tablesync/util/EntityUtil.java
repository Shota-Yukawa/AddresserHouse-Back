package com.ah.apartowner.tablesync.util;

import jakarta.persistence.Entity;

public class EntityUtil {

	public static String getEntityTalbeName(Class<?> entityClass) {
		if (entityClass.isAnnotationPresent(Entity.class)) {
			Entity entityAnnotation = entityClass.getAnnotation(Entity.class);
			String tableName = entityAnnotation.name();
			if (!tableName.isEmpty()) {
				return tableName;
			} else {
				// エンティティクラス名をスネークケースに変換するなど、適切なロジックを追加できます。
				return entityClass.getSimpleName();
			}
		}
		return null;
	}

	public enum methodEnum {
		insert, update, delete;
	}
}
