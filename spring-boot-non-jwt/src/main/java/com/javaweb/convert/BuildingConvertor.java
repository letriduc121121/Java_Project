package com.javaweb.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.exception.InvalidBuildingException;
import com.javaweb.model.DTO.BuildingRequestDTO;
import com.javaweb.model.DTO.BuildingResponseDTO;
//import com.javaweb.repository.DistrictRepository;
//import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingConvertor {

//	@Autowired
//	private DistrictRepository districtRepository;

//	@Autowired
//	private RentAreaRepository rentAreaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	public BuildingResponseDTO convertToBuildingResponseDTO(BuildingEntity entity) {
		// dùng model mapper để chuyển Entity sang DTO
		BuildingResponseDTO dto = modelMapper.map(entity, BuildingResponseDTO.class);
		DistrictEntity district = entity.getDistrictEntity();// tu building entity chi can lay getDisstrict ma ko can qr
		dto.setAddress(entity.getStreet() + ", " + entity.getWard() + ", " + district.getName());
		List<RentAreaEntity> rentAreaEntities = entity.getRentAreaEntities();
		List<Long> rentAreas = rentAreaEntities.stream().map(RentAreaEntity::getValue).collect(Collectors.toList());
		dto.setRentArea(rentAreas);

		return dto;
	}

	public BuildingEntity toBuildingEntity(BuildingRequestDTO buildingRequestDTO) {
		// tim ID
		BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, buildingRequestDTO.getId());
		// dùng model mapper để chuyển DTO sang Entity
		buildingEntity = modelMapper.map(buildingRequestDTO, BuildingEntity.class);
		DistrictEntity district = entityManager.find(DistrictEntity.class, buildingRequestDTO.getDistrictId());
		if (district != null) {
			buildingEntity.setDistrictEntity(district);
		} else {
			throw new InvalidBuildingException("Not found District by Id");
		}

		return buildingEntity;
	}
}
