package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.model.DTO.BuildingResponseDTO;

public interface BuildingService {
	 List<BuildingResponseDTO> searchBuildings(Map<String, String> requestParams,List<String> typeCode);

}
