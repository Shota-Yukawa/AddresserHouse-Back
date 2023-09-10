package com.ah.apartowner.datasource.entity.parts;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@MappedSuperclass
@Data
public class SystemDateEntityParts implements Serializable {

	@Column(name = "regist_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime registAt;

	@Column(name = "update_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime updateAt;

	@PrePersist
	protected void afterRegist() {
		LocalDateTime date = LocalDateTime.now();
		setRegistAt(date);
		setUpdateAt(date);
	}

	@PreUpdate
	protected void afterUpdate() {
		LocalDateTime date = LocalDateTime.now();
		setUpdateAt(date);
	}
}
