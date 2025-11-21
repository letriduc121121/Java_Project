package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.service.BuildingService;

@RestController
@RequestMapping(value = "/buildings")

public class BuildingAPI {

	@Autowired
	private BuildingService buildingService;

	@GetMapping()
	public Object getBuilding(@RequestParam Map<String, String> requestParams,
			@RequestParam(name = "typeCode", required = false) List<String> typeCode) {
		Object responseDTO = buildingService.searchBuildings(requestParams, typeCode);
		return responseDTO;
	}

}
