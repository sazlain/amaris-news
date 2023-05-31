package com.news.amaris.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.news.amaris.entities.AmarisNewsEntity;
import com.news.amaris.entities.BookmarkEntity;
import com.news.amaris.exceptions.AmarisNewsException;

@Service
@Transactional
public interface AmarisNewsService {
	
	public List<AmarisNewsEntity> getAll() throws AmarisNewsException;
	
	public boolean addBookmarkNews(Long id) throws AmarisNewsException;

	public boolean removeBookmarkNews(Long id) throws AmarisNewsException;
	
	public List<BookmarkEntity> getAllBookmark() throws AmarisNewsException;
	
}
