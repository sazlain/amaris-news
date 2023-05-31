package com.news.amaris.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.news.amaris.commons.ErrorCodes;
import com.news.amaris.commons.SupportMessages;
import com.news.amaris.entities.AmarisNewsEntity;
import com.news.amaris.entities.BookmarkEntity;
import com.news.amaris.exceptions.AmarisNewsException;
import com.news.amaris.repositories.AmarisNewsBookmarksRepository;
import com.news.amaris.repositories.AmarisNewsRepository;
import com.news.amaris.services.AmarisNewsService;

@Component
public class AmarisNewsServiceImpl implements AmarisNewsService {
	
	@Autowired
	private AmarisNewsRepository amarisNewsRepository;
	
	@Autowired
	private AmarisNewsBookmarksRepository bookmarksRepository;
	
	@Autowired
	private SupportMessages supportMessages; 

	@Override
	public List<AmarisNewsEntity> getAll() throws AmarisNewsException {
		try {
			return (List<AmarisNewsEntity>) amarisNewsRepository.findAll();
		} catch (Exception e) {
			throw new AmarisNewsException(ErrorCodes.ERR001_INTERNAL_ERROR, supportMessages, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		
	}

	@Override
	public boolean removeBookmarkNews(Long amarisNewsEntityId) throws AmarisNewsException {
		boolean ret = false;
		
		try {
			Optional<AmarisNewsEntity> amarisNewsEntity = amarisNewsRepository.findById(amarisNewsEntityId);
			if(!amarisNewsEntity.isEmpty()) {
				amarisNewsEntity.get().setBookmark(false);
				amarisNewsRepository.save(amarisNewsEntity.get());
				Optional<BookmarkEntity> bookmarkEntity = bookmarksRepository.findByAmarisNewsIdAndAmarisNewsUsername(amarisNewsEntityId, "default");
				bookmarksRepository.delete(bookmarkEntity.get());
				
				ret = true;
			}
		} catch (Exception e) {
			throw new AmarisNewsException(ErrorCodes.ERR001_INTERNAL_ERROR, supportMessages, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}

		return ret;
	}

	@Override
	public boolean addBookmarkNews(Long amarisNewsEntityId) throws AmarisNewsException {
		boolean ret = false;
		
		try {
			Optional<AmarisNewsEntity> amarisNewsEntity = amarisNewsRepository.findById(amarisNewsEntityId);
			if(!amarisNewsEntity.isEmpty()) {
				amarisNewsEntity.get().setBookmark(true);
				amarisNewsRepository.save(amarisNewsEntity.get());
				BookmarkEntity bookmarkEntity = new BookmarkEntity();
				bookmarkEntity.setAmarisNewsId(amarisNewsEntityId);
				bookmarkEntity.setAmarisNewsUsername("default");
				bookmarkEntity.setSavedAt(new Date());
				bookmarksRepository.save(bookmarkEntity);
				ret = true;
			}
		} catch (Exception e) {
			throw new AmarisNewsException(ErrorCodes.ERR001_INTERNAL_ERROR, supportMessages, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		
		return ret;
	}

	@Override
	public List<BookmarkEntity> getAllBookmark() {
		return (List<BookmarkEntity>) bookmarksRepository.findAll();
	}

}
