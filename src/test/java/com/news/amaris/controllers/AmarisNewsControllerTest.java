package com.news.amaris.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.news.amaris.AmarisNewsApplication;
import com.news.amaris.entities.AmarisNewsEntity;
import com.news.amaris.exceptions.AmarisNewsException;
import com.news.amaris.services.impl.AmarisNewsBookmarksServiceImpl;
import com.news.amaris.services.impl.AmarisNewsServiceImpl;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AmarisNewsApplication.class)
class AmarisNewsControllerTest {

	@Mock
	AmarisNewsServiceImpl amarisNewsService;
	
	@Mock
	AmarisNewsBookmarksServiceImpl bookmarksService;
	
	@InjectMocks
	@Spy
	AmarisNewsController amarisNewsController;
	
	@Test
	void ifCallHealthcheckReturnString() {
		ResponseEntity<String> response = amarisNewsController.healthcheck();
		assertEquals("Status Success", response.getBody());
	}
	
	@Test
	void ifCallGetAllReturnList() throws AmarisNewsException {
		List<AmarisNewsEntity> amarisNewsEntityList = new ArrayList<>();
		AmarisNewsEntity amarisNewsEntity = new AmarisNewsEntity();
		amarisNewsEntity.setId((long) 1);
		amarisNewsEntity.setBookmark(true);
		amarisNewsEntity.setFeatured(false);
		amarisNewsEntity.setImageUrl("");
		amarisNewsEntity.setOriginalId((long) 123);
		amarisNewsEntity.setPublishedAt(new Date());
		amarisNewsEntity.setSummary("Example");
		amarisNewsEntity.setTitle("Title example");
		amarisNewsEntity.setUrl("");
		
		amarisNewsEntityList.add(amarisNewsEntity);
		
		
		when(amarisNewsService.getAll()).thenReturn(amarisNewsEntityList);
		ResponseEntity<Object> response = amarisNewsController.getAll();
		assertEquals(amarisNewsEntityList, response.getBody());
	}
	
	@Test
	void ifCallGetAllBookmarkReturnList() throws AmarisNewsException {
		List<AmarisNewsEntity> amarisNewsEntityList = new ArrayList<>();
		AmarisNewsEntity amarisNewsEntity = new AmarisNewsEntity();
		amarisNewsEntity.setId((long) 1);
		amarisNewsEntity.setBookmark(true);
		amarisNewsEntity.setFeatured(false);
		amarisNewsEntity.setImageUrl("");
		amarisNewsEntity.setOriginalId((long) 123);
		amarisNewsEntity.setPublishedAt(new Date());
		amarisNewsEntity.setSummary("Example");
		amarisNewsEntity.setTitle("Title example");
		amarisNewsEntity.setUrl("");
		
		amarisNewsEntityList.add(amarisNewsEntity);
		
		
		when(bookmarksService.getAll()).thenReturn(amarisNewsEntityList);
		ResponseEntity<List<AmarisNewsEntity>> response = amarisNewsController.getAllBookmark();
		assertEquals(amarisNewsEntityList, response.getBody());
	}
	
	@Test
	void ifCallAddBookmarkReturnJson() throws AmarisNewsException {
		List<AmarisNewsEntity> amarisNewsEntityList = new ArrayList<>();
		AmarisNewsEntity amarisNewsEntity = new AmarisNewsEntity();
		amarisNewsEntity.setId((long) 1);
		amarisNewsEntity.setBookmark(true);
		amarisNewsEntity.setFeatured(false);
		amarisNewsEntity.setImageUrl("");
		amarisNewsEntity.setOriginalId((long) 123);
		amarisNewsEntity.setPublishedAt(new Date());
		amarisNewsEntity.setSummary("Example");
		amarisNewsEntity.setTitle("Title example");
		amarisNewsEntity.setUrl("");
		
		amarisNewsEntityList.add(amarisNewsEntity);
		
		when(amarisNewsService.addBookmarkNews((long) 1)).thenReturn(true);
		ResponseEntity<Object> response = amarisNewsController.addBookmarkById((long) 1);
		assertEquals("{\"response\": \"The bookmark news has been added\"}", response.getBody());
	}
	
	@Test
	void ifCallDeleteBookmarkReturnJson() throws AmarisNewsException {
		List<AmarisNewsEntity> amarisNewsEntityList = new ArrayList<>();
		AmarisNewsEntity amarisNewsEntity = new AmarisNewsEntity();
		amarisNewsEntity.setId((long) 1);
		amarisNewsEntity.setBookmark(true);
		amarisNewsEntity.setFeatured(false);
		amarisNewsEntity.setImageUrl("");
		amarisNewsEntity.setOriginalId((long) 123);
		amarisNewsEntity.setPublishedAt(new Date());
		amarisNewsEntity.setSummary("Example");
		amarisNewsEntity.setTitle("Title example");
		amarisNewsEntity.setUrl("");
		
		amarisNewsEntityList.add(amarisNewsEntity);
		
		when(amarisNewsService.removeBookmarkNews((long) 1)).thenReturn(true);
		ResponseEntity<Object> response = amarisNewsController.deleteBookmark((long) 1);
		assertEquals("{\"response\": \"The bookmark news has been deleted\"}", response.getBody());
	}

}
