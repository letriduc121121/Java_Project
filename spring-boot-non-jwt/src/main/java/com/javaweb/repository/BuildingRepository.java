package com.javaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.javaweb.repository.custome.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {

	List<BuildingEntity> findByNameContaining(String name);

	List<BuildingEntity> findByNameContainingAndWardContaining(String name, String ward);

	List<BuildingEntity> findByNameContainingAndDistrictEntity_Code(String name, String districtCode);

//	List<BuildingEntity> findByStructureNotNull(String name, String ward);

	void deleteByIdIn(List<Long> ids);

}
