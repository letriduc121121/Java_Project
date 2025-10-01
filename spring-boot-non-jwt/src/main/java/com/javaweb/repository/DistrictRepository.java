package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.DistrictEntity;

public interface DistrictRepository {
	//method find name district by districtId
	//tra han 1 entity 
	DistrictEntity findDistrictById(Long buildingId);

}
