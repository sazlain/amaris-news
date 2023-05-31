package com.news.amaris.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "bookmarks",uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"amaris_news_username","amaris_news_id"})
	})
public class BookmarkEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="amaris_news_id")
	private Long amarisNewsId;
	
	@Column(name="amaris_news_username")
	private String amarisNewsUsername;
	
	@Column(name="savedAt")
	private Date savedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmarisNewsId() {
		return amarisNewsId;
	}

	public void setAmarisNewsId(Long amarisNewsId) {
		this.amarisNewsId = amarisNewsId;
	}

	public String getAmarisNewsUsername() {
		return amarisNewsUsername;
	}

	public void setAmarisNewsUsername(String amarisNewsUsername) {
		this.amarisNewsUsername = amarisNewsUsername;
	}

	public Date getSavedAt() {
		return savedAt;
	}

	public void setSavedAt(Date savedAt) {
		this.savedAt = savedAt;
	}
	
}
