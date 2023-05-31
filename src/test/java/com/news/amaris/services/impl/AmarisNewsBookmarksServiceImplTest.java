package com.news.amaris.services.impl;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.news.amaris.AmarisNewsApplication;
import com.news.amaris.entities.AmarisNewsEntity;
import com.news.amaris.entities.BookmarkEntity;
import com.news.amaris.repositories.AmarisNewsBookmarksRepository;
import com.news.amaris.repositories.AmarisNewsRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AmarisNewsApplication.class)
class AmarisNewsBookmarksServiceImplTest {

	@Mock
	AmarisNewsBookmarksRepository bookmarksRepository;
	
	@Mock
	AmarisNewsRepository amarisNewsRepository;
	
	@InjectMocks
	@Spy
	AmarisNewsBookmarksServiceImpl amarisNewsBookmarksService;
	
	@Test
	void ifCallGetAllShouldReturnAmarisNewsEntityList() {
		
		List<BookmarkEntity> bookmarkEntityList = new ArrayList<>();
		BookmarkEntity bookmarkEntity = new BookmarkEntity();
		bookmarkEntity.setId((long) 1);
		bookmarkEntity.setAmarisNewsId((long) 1);
		bookmarkEntity.setSavedAt(new Date());
		bookmarkEntity.setAmarisNewsUsername("default");

		bookmarkEntityList.add(bookmarkEntity);
		
		when(bookmarksRepository.findByAmarisNewsUsername("default")).thenReturn(bookmarkEntityList);
		
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
		
		List<Long> ids = new ArrayList<>();
		ids.add(bookmarkEntity.getAmarisNewsId());
		
		when(amarisNewsRepository.findByIdIn(ids)).thenReturn(amarisNewsEntityList);
		
		List<AmarisNewsEntity> response = amarisNewsBookmarksService.getAll();
		assertEquals(amarisNewsEntityList, response);
	}

}
