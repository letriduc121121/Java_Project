package com.javaweb.service.impl;

import com.javaweb.model.DTO.BuildingResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.convert.BuildingConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Override
    public List<BuildingResponseDTO> searchBuildings(Map<String, String> requestParams, List<String> typeCode) {
    	
        List<BuildingEntity> buildingEntities = buildingRepository.searchBuildings(requestParams, typeCode);
        List<BuildingResponseDTO> buildingResponseDTOs = new ArrayList<>();

        for (BuildingEntity entity : buildingEntities) {
            BuildingResponseDTO buildingResponseDTO = buildingConverter.convertToBuildingResponseDTO(entity);
            buildingResponseDTOs.add(buildingResponseDTO);
        }

        return buildingResponseDTOs;
    }
}
