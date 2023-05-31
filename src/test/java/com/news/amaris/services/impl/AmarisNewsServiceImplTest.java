package com.news.amaris.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.news.amaris.AmarisNewsApplication;
import com.news.amaris.commons.ErrorCodes;
import com.news.amaris.commons.SupportMessages;
import com.news.amaris.entities.AmarisNewsEntity;
import com.news.amaris.entities.BookmarkEntity;
import com.news.amaris.exceptions.AmarisNewsException;
import com.news.amaris.repositories.AmarisNewsBookmarksRepository;
import com.news.amaris.repositories.AmarisNewsRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AmarisNewsApplication.class)
class AmarisNewsServiceImplTest {
	
	@Mock
	AmarisNewsRepository amarisNewsRepository;
	
	@Mock
	AmarisNewsBookmarksRepository bookmarksRepository;
	
	@Mock
	SupportMessages messages;
	
	@InjectMocks
	@Spy
	AmarisNewsServiceImpl amarisNewsService;
	
	/*
	
	public List<BookmarkEntity> getAllBookmark() throws AmarisNewsException;
	 * */

	@Test
	void ifCallGetAllShouldReturnAmarisNewsEntityList() throws AmarisNewsException {
		
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
		
		when(amarisNewsRepository.findAll()).thenReturn(amarisNewsEntityList);
		
		List<AmarisNewsEntity> response = amarisNewsService.getAll();
		assertEquals(amarisNewsEntityList, response);
	}
	
	@Test
	void throwAmarisNewsExceptionWhenCallGetAll() {
		
		when(amarisNewsRepository.findAll())
		.thenThrow(NullPointerException.class);
		
		try {
			amarisNewsService.getAll();
		} catch (AmarisNewsException e) {
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getError().getHttpStatus().value());
			assertEquals(ErrorCodes.ERR001_INTERNAL_ERROR.getId(), e.getError().getId());
		}
		
	}
	
	@Test
	void ifCallAddBookmarkNewsShouldReturnJsonString() throws AmarisNewsException {
		
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
		
		when(amarisNewsRepository.findById((long) 1)).thenReturn(Optional.of(amarisNewsEntity));
		
		when(amarisNewsRepository.save(amarisNewsEntity)).thenReturn(null);
		
		BookmarkEntity bookmarkEntity = new BookmarkEntity();
		bookmarkEntity.setAmarisNewsId(amarisNewsEntity.getId());
		bookmarkEntity.setAmarisNewsUsername("default");
		bookmarkEntity.setSavedAt(new Date());
		
		when(bookmarksRepository.save(bookmarkEntity)).thenReturn(null);

		boolean response = amarisNewsService.addBookmarkNews((long) 1);
		assertTrue(response);
	}
	
	@Test
	void throwAmarisNewsExceptionWhenCallAddBookmarkNews() {
		
		when(amarisNewsRepository.findById((long) 1))
		.thenThrow(NullPointerException.class);
		
		try {
			amarisNewsService.addBookmarkNews((long) 1);
		} catch (AmarisNewsException e) {
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getError().getHttpStatus().value());
			assertEquals(ErrorCodes.ERR001_INTERNAL_ERROR.getId(), e.getError().getId());
		}
		
	}
	
	@Test
	void ifCallRemoveBookmarkNewsShouldReturnJsonString() throws AmarisNewsException {
		
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
		
		when(amarisNewsRepository.findById((long) 1)).thenReturn(Optional.of(amarisNewsEntity));
		
		when(amarisNewsRepository.save(amarisNewsEntity)).thenReturn(null);
		
		BookmarkEntity bookmarkEntity = new BookmarkEntity();
		bookmarkEntity.setAmarisNewsId(amarisNewsEntity.getId());
		bookmarkEntity.setAmarisNewsUsername("default");
		bookmarkEntity.setSavedAt(new Date());
		
		when(bookmarksRepository.findByAmarisNewsIdAndAmarisNewsUsername((long) 1, "default")).thenReturn(Optional.of(bookmarkEntity));
		
		boolean response = amarisNewsService.removeBookmarkNews((long) 1);
		assertTrue(response);
	}
	
	@Test
	void throwAmarisNewsExceptionWhenCallRemoveBookmarkNews() {
		
		when(amarisNewsRepository.findById((long) 1))
		.thenThrow(NullPointerException.class);
		
		try {
			amarisNewsService.removeBookmarkNews((long) 1);
		} catch (AmarisNewsException e) {
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getError().getHttpStatus().value());
			assertEquals(ErrorCodes.ERR001_INTERNAL_ERROR.getId(), e.getError().getId());
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	void ifCallGetAllBookmarkShouldReturnJsonString() throws AmarisNewsException {
		
		
		List<BookmarkEntity> bookmarkEntityList = new ArrayList<>();
		
		BookmarkEntity bookmarkEntity = new BookmarkEntity();
		bookmarkEntity.setId((long) 1);
		bookmarkEntity.setAmarisNewsId((long) 1);
		bookmarkEntity.setAmarisNewsUsername("default");
		bookmarkEntity.setSavedAt(new Date());
		
		bookmarkEntityList.add(bookmarkEntity);
		
		when(bookmarksRepository.findAll()).thenReturn((Iterable) bookmarkEntityList);

		List<BookmarkEntity> response = amarisNewsService.getAllBookmark();
		assertEquals(bookmarkEntityList, response);
	}

}
