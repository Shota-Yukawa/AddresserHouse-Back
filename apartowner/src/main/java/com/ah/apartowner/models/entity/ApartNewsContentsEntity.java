package com.ah.apartowner.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "apart_news_contents")
@Data
@EqualsAndHashCode(callSuper = false)
public class ApartNewsContentsEntity {

	@Id
	@Column(name = "apart_news_content_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer apartNewsContentId;

	@OneToOne
	@JoinColumn(name = "apart_news_id", nullable = false)
	private ApartNewsEntity apartNews;

	@Column(name = "news_file")
	private String newsFile;

	@Column(name = "cover_image")
	private String coverImage;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

}
