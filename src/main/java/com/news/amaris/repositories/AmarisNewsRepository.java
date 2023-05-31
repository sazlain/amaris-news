package com.news.amaris.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.news.amaris.entities.AmarisNewsEntity;

@Repository
public interface AmarisNewsRepository extends CrudRepository<AmarisNewsEntity, Long> {

	Optional<AmarisNewsEntity> findByOriginalId(Long amarisNewsEntityId);
	
	List<AmarisNewsEntity> findByIdIn(List<Long> amarisNewsIds);
	
}