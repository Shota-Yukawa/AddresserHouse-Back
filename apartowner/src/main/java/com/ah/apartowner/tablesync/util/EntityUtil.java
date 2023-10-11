package com.ah.apartowner.tablesync.util;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public class EntityUtil {

	/**
	 * 引数のentityインスタンスから、テーブル名を取得する<br>
	 * Tableアノテーションからname属性を取得
	 * 
	 * @param entityClass
	 * @return
	 */
	public static String getEntityTalbeName(Class<?> entityClass) {
		if (entityClass.isAnnotationPresent(Entity.class)) {
			Table table = entityClass.getAnnotation(Table.class);
			if (table != null) {
				return table.name();
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
