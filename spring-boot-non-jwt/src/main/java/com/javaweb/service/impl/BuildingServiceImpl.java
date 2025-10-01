package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.DTO.BuildingRequestDTO;
import com.javaweb.model.DTO.BuildingResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	BuildingRepository buildingRepository;
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	RentAreaRepository rentAreaRepository;

	@Override
	public List<BuildingResponseDTO> searchBuildings(BuildingRequestDTO requestDTO) {
		List<BuildingEntity> buildingEntities = buildingRepository.searchBuildings(requestDTO);

		List<BuildingResponseDTO> buildingResponseDTOs = new ArrayList<>();

		for (BuildingEntity entity : buildingEntities) {
			BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();

			// 1 id
			buildingResponseDTO.setId(entity.getId());

			// 2 name
			buildingResponseDTO.setName(entity.getName());

			// 3 address (street + ward + districtName)
			String districtName = districtRepository.findDistrictNameById(entity.getDistrictId());
			String address = entity.getStreet() + ", " + entity.getWard() + ", " + districtName;
			buildingResponseDTO.setAddress(address);

			// 4 numberofbasement
			buildingResponseDTO.setNumberOfBasement(entity.getNumberOfBasement());

			// 5+6 name and number phone manager
			buildingResponseDTO.setManagerName(entity.getManagerName());
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

//	@Override
//	public List<BuildingResponseDTO> searchBuildings(String name, Long floorArea, Long districtId, String ward,
//			String street, Long numberOfBasement, String direction, String level, Long areaFrom, Long areaTo,
//			Long rentPriceFrom, Long rentPriceTo, String managerName, String managerPhoneNumber, Long staffId,
//			List<String> rentTypes) {
//
//		List<BuildingEntity> buildingEntities = buildingRepository.searchBuildings(name, floorArea, districtId, ward,
//				street, numberOfBasement, direction, level, areaFrom, areaTo, rentPriceFrom, rentPriceTo, managerName,
//				managerPhoneNumber, staffId, rentTypes);
//
//		List<BuildingResponseDTO> buildingResponseDTOs = new ArrayList<BuildingResponseDTO>();
//
//		for (BuildingEntity entity : buildingEntities) {
//
//			BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();
//
//			buildingResponseDTO.setId(entity.getId());// id
//
//			buildingResponseDTO.setName(entity.getName());// name
//
//			// address
//			String districtName = districtRepository.findDistrictNameById(entity.getDistrictId());
//			buildingResponseDTO.setAddress(  entity.getStreet() +","+entity.getWard()+ "," + districtName);
//
//			// number of basement
//			buildingResponseDTO.setNumberOfBasement(entity.getNumberOfBasement());
//
//			// name and number phone
//			buildingResponseDTO.setManagerName(entity.getManagerName());
//			buildingResponseDTO.setManagerPhoneNumber(entity.getManagerPhoneNumber());
//
//			// floor are
//			buildingResponseDTO.setFloorArea(entity.getFloorArea());
//
//			// dien tich thue
//			List<Long> rentAreas = rentAreaRepository.findByBuildingId(entity.getId());
//			buildingResponseDTO.setRentArea(rentAreas);
//			// dien tich trong
//			buildingResponseDTO.setEmptyArea(rentAreaRepository.getTotalEmptyAreaByBuildingId(entity.getId()));
//			// buildingResponseDTO.setEmptyArea(entity.getA);
//
//			// rent price
//			buildingResponseDTO.setRentPrice(entity.getRentPrice());
//
//			// sevice price
//			buildingResponseDTO.setServiceFee(entity.getServiceFee());
//
//			// brokerageFee
//			buildingResponseDTO.setBrokerageFee(entity.getBrokerageFee());
//
//			buildingResponseDTOs.add(buildingResponseDTO);
//		}
//		return buildingResponseDTOs;
//	}

}
