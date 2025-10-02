package com.javaweb.service.impl;

import com.javaweb.model.DTO.BuildingRequestDTO;
import com.javaweb.model.DTO.BuildingResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.service.convert.BuildingConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Override
    public List<BuildingResponseDTO> searchBuildings(BuildingRequestDTO requestDTO) {
    	
        List<BuildingEntity> buildingEntities = buildingRepository.searchBuildings(requestDTO);
        List<BuildingResponseDTO> buildingResponseDTOs = new ArrayList<>();

        for (BuildingEntity entity : buildingEntities) {
            BuildingResponseDTO buildingResponseDTO = buildingConverter.convertToBuildingResponseDTO(entity);
            buildingResponseDTOs.add(buildingResponseDTO);
        }

        return buildingResponseDTOs;
    }
}
