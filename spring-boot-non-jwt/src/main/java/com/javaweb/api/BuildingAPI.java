package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.DTO.BuildingRequestDTO;
import com.javaweb.model.DTO.BuildingResponseDTO;
import com.javaweb.service.BuildingService;

@RestController
@RequestMapping(value = "/search")

public class BuildingAPI {

	private static final BuildingRequestDTO BuildingRequestDTO = null;
	@Autowired
	private BuildingService buildingService;
	// @GetMapping()
	// public List<BuildingResponseDTO> searchBuilding(@RequestBody
	// BuildingRequestDTO requestDTO) {
	// return buildingService.searchBuildings(requestDTO);

//		}
	@GetMapping()
	public Object searchBuilding(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "floorArea", required = false) Long floorArea,
			@RequestParam(name = "districtId", required = false) Long districtId,
			@RequestParam(name = "ward", required = false) String ward,
			@RequestParam(name = "street", required = false) String street,
			@RequestParam(name = "numberOfBasement", required = false) Long numberOfBasement,
			@RequestParam(name = "direction", required = false) String direction,
			@RequestParam(name = "level", required = false) String level,
			@RequestParam(name = "areaFrom", required = false) Long areaFrom,
			@RequestParam(name = "areaTo", required = false) Long areaTo,
			@RequestParam(name = "rentPriceFrom", required = false) Long rentPriceFrom,
			@RequestParam(name = "rentPriceTo", required = false) Long rentPriceTo,
			@RequestParam(name = "managerName", required = false) String managerName,
			@RequestParam(name = "managerPhoneNumber", required = false) String managerPhoneNumber,
			@RequestParam(name = "staffId", required = false) Long staffId,
			@RequestParam(name = "typeCode", required = false) List<String> typeCode) {

		BuildingRequestDTO requestDTO = new BuildingRequestDTO();
		requestDTO.setName(name);
		requestDTO.setFloorArea(floorArea);
		requestDTO.setDistrictId(districtId);
		requestDTO.setWard(ward);
		requestDTO.setStreet(street);
		requestDTO.setNumberOfBasement(numberOfBasement);
		requestDTO.setDirection(direction);
		requestDTO.setLevel(level);
		requestDTO.setAreaFrom(areaFrom);
		requestDTO.setAreaTo(areaTo);
		requestDTO.setRentPriceFrom(rentPriceFrom);
		requestDTO.setRentPriceTo(rentPriceTo);
		requestDTO.setManagerName(managerName);
		requestDTO.setManagerPhoneNumber(managerPhoneNumber);
		requestDTO.setStaffId(staffId);
		requestDTO.setTypeCode(typeCode);
//		System.out.println(requestDTO.getManagerName() + " "+requestDTO.getManagerPhoneNumber());
		List<BuildingResponseDTO> buildingResponseDTOs = buildingService.searchBuildings(requestDTO);
		
		
		return buildingResponseDTOs;
	}

}
