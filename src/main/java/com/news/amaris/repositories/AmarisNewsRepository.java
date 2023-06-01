package com.news.amaris.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.news.amaris.entities.AmarisNewsEntity;

@Repository
public interface AmarisNewsRepository extends CrudRepository<AmarisNewsEntity, Long> {

	Optional<AmarisNewsEntity> findByOriginalId(Long amarisNewsEntityId);

	List<AmarisNewsEntity> findByIdIn(List<Long> amarisNewsIds);

	@Query(value = "SELECT id FROM (VALUES (:ids)) AS ids (id) WHERE ids.id NOT IN (SELECT original_id FROM amaris_news)", nativeQuery = true)
	List<Long> findIdsNotExist(@Param("ids") List<Long>  ids);
	
	

}