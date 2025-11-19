package com.javaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.repository.entity.RentAreaEntity;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {

	List<RentAreaEntity> findRentAreaByBuildingId(Long buildingId);
	void deleteAllByBuilding_IdIn(List<Long> idBuilding);
}