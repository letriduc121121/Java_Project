package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.DTO.BuildingResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	BuildingRepository buildingRepository;

	@Override
	public List<BuildingResponseDTO> findAll(String name, Long numberOfBasement) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(name, numberOfBasement); // hung ketqua tu
		// repo
		List<BuildingResponseDTO> buildingResponseDTOs = new ArrayList<BuildingResponseDTO>();
     // xu ly tra ve phia controller;
		for (BuildingEntity it : buildingEntities) {// filer loc data sao phu hoc vs yeu cau cliebnt(FILTER DATA)
			BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();
			buildingResponseDTO.setId(it.getId());
			buildingResponseDTO.setName(it.getName());
			buildingResponseDTO.setRentPrice(null);
			buildingResponseDTO.setAddress(it.getStreet() + "," + it.getDistrictId() + " ," + it.getDistrictId());
			buildingResponseDTOs.add(buildingResponseDTO);
   // ....
		}
		return buildingResponseDTOs;
	}

}
