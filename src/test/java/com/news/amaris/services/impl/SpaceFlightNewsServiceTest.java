package com.news.amaris.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.news.amaris.AmarisNewsApplication;
import com.news.amaris.exceptions.AmarisNewsException;
import com.news.amaris.repositories.AmarisNewsRepository;
import com.news.amaris.responses.SpaceFlightNewsResponse;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AmarisNewsApplication.class, properties = { "spaceflightnewsapi.url=https://api.spaceflightnewsapi.net/v4/articles" })
class SpaceFlightNewsServiceTest {
	
	@Mock
	AmarisNewsRepository amarisNewsRepository;
	
	@Mock
	RestTemplate rt = new RestTemplate();

	@InjectMocks
	@Spy
	@Autowired
	SpaceFlightNewsService spaceFlightNewsService;
	
	@Test
	void test() throws AmarisNewsException {

		SpaceFlightNewsResponse response = spaceFlightNewsService.getNews();
		
		assertTrue(response != null);
	}

}
