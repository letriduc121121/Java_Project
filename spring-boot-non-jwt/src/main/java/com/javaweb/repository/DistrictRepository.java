package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.DistrictEntity;

public interface DistrictRepository {
	//method find name district by districtId
	String findDistrictNameById(Long id);

}
