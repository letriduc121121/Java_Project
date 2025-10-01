package com.javaweb.service;

import java.util.List;

import com.javaweb.model.DTO.BuildingRequestDTO;
import com.javaweb.model.DTO.BuildingResponseDTO;

public interface BuildingService {
	List<BuildingResponseDTO> searchBuildings(String name, Long floorArea, Long districtId, String ward,
			String street, Long numberOfBasement, String direction, String level, Long areaFrom, Long areaTo,
			Long rentPriceFrom, Long rentPriceTo, String managerName, String managerPhoneNumber, Long staffId,
			List<String> rentTypes);
//	 List<BuildingResponseDTO> searchBuildings(BuildingRequestDTO requestDTO);

}
