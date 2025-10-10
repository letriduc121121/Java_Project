package com.javaweb.service;

import java.util.List;

import com.javaweb.model.DTO.BuildingRequestDTO;
import com.javaweb.model.DTO.BuildingResponseDTO;

public interface BuildingService {
	 List<BuildingResponseDTO> searchBuildings(BuildingRequestDTO requestDTO);

}
