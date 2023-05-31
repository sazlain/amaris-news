package com.news.amaris.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.news.amaris.entities.AmarisNewsEntity;

@Service
public interface AmarisNewsBookmarksService {

	public List<AmarisNewsEntity> getAll();
	
}
