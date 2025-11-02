package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.convert.BuildingConvertor;
import com.javaweb.convert.BuildingSearchBuilderConvertor;
import com.javaweb.model.DTO.BuildingResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private BuildingConvertor buildingConvertor;

	@Autowired
	// dug cai nay ko can khoi tao
	private BuildingSearchBuilderConvertor buildingSearchBuilderConvertor;

	@Override
	public List<BuildingResponseDTO> searchBuildings(Map<String, String> requestParams, List<String> typeCode) {
		
		BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConvertor
				.toBuildingSearchBuilder(requestParams, typeCode);
		
		List<BuildingEntity> buildingEntities = buildingRepository.searchBuildings(buildingSearchBuilder);
		
		List<BuildingResponseDTO> buildingResponseDTOs = new ArrayList<>();
		
		for (BuildingEntity entity : buildingEntities) {
			 BuildingResponseDTO buildingResponseDTO =buildingConvertor.convertToBuildingResponseDTO(entity);
			//BuildingResponseDTO buildingResponseDTO = buildingSearchMapper.toBuildingResponseDTO(entity);
			buildingResponseDTOs.add(buildingResponseDTO);
		}

		return buildingResponseDTOs;
	}
}
