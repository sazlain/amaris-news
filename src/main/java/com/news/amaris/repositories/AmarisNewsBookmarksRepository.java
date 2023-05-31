package com.news.amaris.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.news.amaris.entities.BookmarkEntity;

@Repository
public interface AmarisNewsBookmarksRepository extends CrudRepository<BookmarkEntity, Long> {
	
	Optional<BookmarkEntity> findByAmarisNewsIdAndAmarisNewsUsername(Long id, String username);
	
	List<BookmarkEntity> findByAmarisNewsUsername(String username);
	
}