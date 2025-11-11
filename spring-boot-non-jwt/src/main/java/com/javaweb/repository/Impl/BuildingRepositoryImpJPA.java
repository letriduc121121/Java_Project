package com.javaweb.repository.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
//@Primary
public class BuildingRepositoryImpJPA implements BuildingRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BuildingEntity> searchBuildings(BuildingSearchBuilder buildingSearchBuilder) {
		String jpql = "SELECT b  FROM BuildingEntity b  WHERE 1=1  AND b.name LIKE '%Tower%' AND b.districtEntity.code='Q1' ";
		Query query = entityManager.createQuery(jpql, BuildingEntity.class);
		return query.getResultList();
		// CÁCH 2: Dùng Native SQL với Query
//		String sqlNative = "SELECT b.* FROM building b  WHERE b.name LIKE '%Tower%'";
//		Query query = entityManager.createNativeQuery(sqlNative, BuildingEntity.class);
//		return query.getResultList();
	}
}
