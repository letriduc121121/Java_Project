package com.javaweb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.DTO.BuildingRequestDTO;
import com.javaweb.service.BuildingService;

@RestController
@RequestMapping(value = "/search")

public class BuildingAPI {

	@Autowired
	private BuildingService buildingService;

	@GetMapping()
	public Object searchBuilding(BuildingRequestDTO requestDTO) {
		Object responseDTO = buildingService.searchBuildings(requestDTO);
		return responseDTO;
	}


}
