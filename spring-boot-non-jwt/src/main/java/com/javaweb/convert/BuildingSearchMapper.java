package com.javaweb.convert;

import org.mapstruct.Mapper;

import com.javaweb.model.DTO.BuildingResponseDTO;
import com.javaweb.repository.entity.BuildingEntity;

@Mapper(componentModel = "Spring")
//giup chbuyen doi tu entity sang buiildingresponse thanh java bean
//componeModel ap dung cho spring
public interface BuildingSearchMapper {
	BuildingResponseDTO toBuildingResponseDTO(BuildingEntity buildingEntity);
}
