package com.news.amaris.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.news.amaris.entities.AmarisNewsEntity;
import com.news.amaris.entities.BookmarkEntity;
import com.news.amaris.repositories.AmarisNewsBookmarksRepository;
import com.news.amaris.repositories.AmarisNewsRepository;
import com.news.amaris.services.AmarisNewsBookmarksService;

@Component
public class AmarisNewsBookmarksServiceImpl implements AmarisNewsBookmarksService {
	
	private static final Logger logger = LoggerFactory.getLogger(AmarisNewsBookmarksServiceImpl.class);
	
	@Autowired
	private AmarisNewsBookmarksRepository bookmarksRepository;
	
	@Autowired
	private AmarisNewsRepository amarisNewsRepository;

	@Override
	public List<AmarisNewsEntity> getAll() {
		List<BookmarkEntity> bookmarkEntityList = bookmarksRepository.findByAmarisNewsUsername("default");
		List<Long> amarisNewsIds = new ArrayList<>();
		
		for(BookmarkEntity bookmark: bookmarkEntityList) {
			amarisNewsIds.add(bookmark.getAmarisNewsId());
		}
		
		logger.info("Bookmarks has been returned");
		
		return amarisNewsRepository.findByIdIn(amarisNewsIds);
	}
	
}
