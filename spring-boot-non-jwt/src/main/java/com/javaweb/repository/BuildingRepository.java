package com.javaweb.repository;

import java.util.List;

import com.javaweb.model.DTO.BuildingRequestDTO;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository {

	
	List<BuildingEntity> searchBuildings(BuildingRequestDTO requestDTO);
}
