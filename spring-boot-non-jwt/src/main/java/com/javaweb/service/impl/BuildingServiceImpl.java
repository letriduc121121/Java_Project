package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.DTO.BuildingResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private RentAreaRepository rentAreaRepository;

	@Override
	public List<BuildingResponseDTO> searchBuildings(String name, Long floorArea, Long districtId, String ward,
			String street, Long numberOfBasement, String direction, String level, Long areaFrom, Long areaTo,
			Long rentPriceFrom, Long rentPriceTo, String managerName, String managerPhoneNumber, Long staffId,
			List<String> rentTypes) {

		// Lấy danh sách BuildingEntity từ repository
		List<BuildingEntity> buildingEntities = buildingRepository.searchBuildings(name, floorArea, districtId, ward,
				street, numberOfBasement, direction, level, areaFrom, areaTo, rentPriceFrom, rentPriceTo, managerName,
				managerPhoneNumber, staffId, rentTypes);

		List<BuildingResponseDTO> buildingResponseDTOs = new ArrayList<>();

		for (BuildingEntity entity : buildingEntities) {
			BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();

			//1 ID
			buildingResponseDTO.setId(entity.getId());

			//2 Name
			buildingResponseDTO.setName(entity.getName());

			//3 Address (street+ward+name district)
			DistrictEntity districtEntity = districtRepository.findDistrictById(entity.getDistrictId());
			String districtName = districtEntity.getName();
			buildingResponseDTO.setAddress(entity.getStreet() + ", " + entity.getWard() + ", " + districtName);
			//4
			buildingResponseDTO.setNumberOfBasement(entity.getNumberOfBasement());
			//5
			buildingResponseDTO.setManagerName(entity.getManagerName());
			//6
			buildingResponseDTO.setManagerPhoneNumber(entity.getManagerPhoneNumber());

			// 7 floor area
			buildingResponseDTO.setFloorArea(entity.getFloorArea());

			// 8 list rent area
			List<Long> rentAreas = rentAreaRepository.findByBuildingId(entity.getId());
			buildingResponseDTO.setRentArea(rentAreas);

			// 9 emty area
			Long emptyArea = rentAreaRepository.getTotalEmptyAreaByBuildingId(entity.getId());
			buildingResponseDTO.setEmptyArea(emptyArea);

			// 10 rent price
			buildingResponseDTO.setRentPrice(entity.getRentPrice());

			// 11 service fee
			buildingResponseDTO.setServiceFee(entity.getServiceFee());

			// brokerage fee
			buildingResponseDTO.setBrokerageFee(entity.getBrokerageFee());

			

			buildingResponseDTOs.add(buildingResponseDTO);
		}

		return buildingResponseDTOs;
	}
}