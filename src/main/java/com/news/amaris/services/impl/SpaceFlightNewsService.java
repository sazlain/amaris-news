package com.news.amaris.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.news.amaris.commons.ErrorCodes;
import com.news.amaris.commons.SupportMessages;
import com.news.amaris.dtos.SpaceFlightNewsDto;
import com.news.amaris.entities.AmarisNewsEntity;
import com.news.amaris.exceptions.AmarisNewsException;
import com.news.amaris.mappers.AmarisNewsMapper;
import com.news.amaris.repositories.AmarisNewsRepository;
import com.news.amaris.responses.SpaceFlightNewsResponse;

@Service
public class SpaceFlightNewsService {
	
	private static final Logger logger = LoggerFactory.getLogger(SpaceFlightNewsService.class);
	
	@Autowired
	private AmarisNewsRepository amarisNewsRepository;
	
	@Autowired
	private SupportMessages supportMessages; 
	
	private SpaceFlightNewsResponse spaceFlightNewsResponse;
	
	@Value("${spaceflightnewsapi.url}")
	private String spaceflightnewsapiUrl;

	public SpaceFlightNewsResponse getNews() throws AmarisNewsException {
		try {
			RestTemplate rt = new RestTemplate();
			
			ResponseEntity<SpaceFlightNewsResponse> response = rt.getForEntity(spaceflightnewsapiUrl, SpaceFlightNewsResponse.class);
			spaceFlightNewsResponse = response.getBody();
			updateNews();
			return response.getBody();
		} catch (Exception e) {
			throw new AmarisNewsException(ErrorCodes.ERR002_EXTERNAL_CLIENT_ACCESS_ERROR, supportMessages, HttpStatus.BAD_GATEWAY, e.getMessage());
		}
		
	}
	
	private void updateNews() {
		
		for(SpaceFlightNewsDto spaceFlightNews: spaceFlightNewsResponse.getResults()) {
			Optional<AmarisNewsEntity> amarisNewsEntity = amarisNewsRepository.findByOriginalId(spaceFlightNews.getId());
			if(amarisNewsEntity.isEmpty()) {
				AmarisNewsEntity  newAmarisNewsEntity = AmarisNewsMapper.spaceFlightNewsDtoToAmarisNewsEntity(spaceFlightNews);
				amarisNewsRepository.save(newAmarisNewsEntity);
				logger.info("Success News was added to database");
			}
			
		}
		
	}
	
}