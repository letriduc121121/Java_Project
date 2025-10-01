package com.javaweb.repository;


import java.util.List;
import com.javaweb.repository.entity.RentAreaEntity;

public interface RentAreaRepository {
	// find List value area by buidlingId
    List<Long> findByBuildingId(Long buildingId);
    //find sum empty area by buildingId
    Long getTotalEmptyAreaByBuildingId(Long buildingId);
}