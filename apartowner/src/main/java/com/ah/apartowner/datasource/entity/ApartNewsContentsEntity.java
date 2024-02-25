package com.ah.apartowner.datasource.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "apart_news_contents")
@Data
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "pkey_seq", sequenceName = "apart_news_contents_apart_news_content_id_seq", allocationSize = 1)
public class ApartNewsContentsEntity {

	@Id
	@Column(name = "apart_news_content_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pkey_seq")
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
