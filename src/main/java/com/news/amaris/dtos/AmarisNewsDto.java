package com.news.amaris.dtos;

import java.util.Date;

import com.google.gson.Gson;

public class AmarisNewsDto {

	private Integer id;
	private String title;
	private String url;
	private String imageSite;
	private String summary;
	private Date publishedAt;
	private Date updatedAt;
	private boolean featured;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getImageSite() {
		return imageSite;
	}
	public void setImageSite(String imageSite) {
		this.imageSite = imageSite;
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
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
