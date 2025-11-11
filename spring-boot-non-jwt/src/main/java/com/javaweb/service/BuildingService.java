package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.model.DTO.BuildingRequestDTO;
import com.javaweb.model.DTO.BuildingResponseDTO;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingService {
	 List<BuildingResponseDTO> searchBuildings(Map<String, String> requestParams,List<String> typeCode);

		BuildingEntity create(BuildingRequestDTO building);

		BuildingEntity update(BuildingRequestDTO building);
		void delete(List<Long > ids);

}
