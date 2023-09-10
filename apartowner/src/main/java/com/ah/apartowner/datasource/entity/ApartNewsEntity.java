package com.ah.apartowner.datasource.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.ah.apartowner.datasource.entity.parts.SystemDateEntityParts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "apart_news")
@Data
@EqualsAndHashCode(callSuper = false)
public class ApartNewsEntity extends SystemDateEntityParts {

	@Id
	@Column(name = "apart_news_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer apartNewsId;

	@ManyToOne
	@JoinColumn(name = "apartowner_id", referencedColumnName = "apartowner_id", nullable = false)
	private ApartownersEntity apartowner;

	@Column(name = "tag", nullable = false)
	private String tag;

	@Column(name = "is_published", nullable = false)
	private boolean ispublished = false;

	@Column(name = "published_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime publishedAt;

	// リレーションマッピング
	@OneToMany(mappedBy = "apartNews")
	private List<ApartNewsRangeEntity> apartNewsranges;
	@OneToOne(mappedBy = "apartNews")
	private ApartNewsContentsEntity apartNewsContent;

}
