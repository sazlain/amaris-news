package com.news.amaris.mappers;

import java.util.Date;

import com.news.amaris.dtos.SpaceFlightNewsDto;
import com.news.amaris.entities.AmarisNewsEntity;

public class AmarisNewsMapper {
	
	private AmarisNewsMapper() {}
	
	public static AmarisNewsEntity spaceFlightNewsDtoToAmarisNewsEntity(SpaceFlightNewsDto response) {
		AmarisNewsEntity amarisNewsDto = new AmarisNewsEntity();
		
		amarisNewsDto.setFeatured(response.isFeatured());
		amarisNewsDto.setImageUrl(response.getImageUrl());
		amarisNewsDto.setPublishedAt(new Date());
		amarisNewsDto.setSummary(response.getSummary());
		amarisNewsDto.setTitle(response.getTitle());
		amarisNewsDto.setUpdatedAt(new Date());
		amarisNewsDto.setUrl(response.getUrl());
		amarisNewsDto.setOriginalId(response.getId());

		return amarisNewsDto;
	}
}
