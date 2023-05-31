package com.news.amaris.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public class SpaceFlightNewsDto {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("image_url")
	private String imageUrl;
	
	@JsonProperty("summary")
	private String summary;
	
	@JsonProperty("published_at")
	private Date publishedAt;
	
	@JsonProperty("updated_at")
	private Date updatedAt;
	
	@JsonProperty("featured")
	private boolean featured;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
