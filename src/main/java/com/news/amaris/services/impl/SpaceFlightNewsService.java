package com.news.amaris.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
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
	@Qualifier("jdbcTemplateAmarisNews")
	public JdbcTemplate jdbcTemplate;
	
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
		List<SpaceFlightNewsDto> spaceFlightNewsList = spaceFlightNewsResponse.getResults();
		
		for(Long idNotExist : getIdsNotExist(spaceFlightNewsList)) {
			Optional<SpaceFlightNewsDto> spaceFlightNewsFiltered = spaceFlightNewsList.stream().filter(el -> el.getId().equals(idNotExist)).findAny();
			AmarisNewsEntity  newAmarisNewsEntity = AmarisNewsMapper.spaceFlightNewsDtoToAmarisNewsEntity(spaceFlightNewsFiltered.get());
			amarisNewsRepository.save(newAmarisNewsEntity);
			logger.info("Success News was added to database");
		}
		
	}
	
	
	private List<Long> getIdsNotExist(List<SpaceFlightNewsDto> spaceFlightNewsList) {
		
		List<Long> ids = new ArrayList<>();
		
		for(SpaceFlightNewsDto spaceFlightNews: spaceFlightNewsList) {
			ids.add(spaceFlightNews.getId());
		}
		
		StringBuilder sb = new StringBuilder();
		for(long id:ids) {
			if(sb.toString().isBlank()) {
				sb.append("("+ id + ")");
			}else {
				sb.append(", ("+ id + ")");
			}
		}
		
		String query = "SELECT id FROM (VALUES " + sb.toString() + ") AS ids (id) WHERE ids.id NOT IN (SELECT original_id FROM amaris_news)";
		return jdbcTemplate.queryForList(query, Long.class );
	}
	

	
}