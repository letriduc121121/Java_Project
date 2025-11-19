package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.*;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.convert.BuildingConvertor;
import com.javaweb.convert.BuildingSearchBuilderConvertor;
import com.javaweb.exception.InvalidBuildingException;
import com.javaweb.model.DTO.BuildingRequestDTO;
import com.javaweb.model.DTO.BuildingResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private RentAreaRepository areaRepository;

	@Autowired
	private BuildingConvertor buildingConvertor;

	@Autowired
	// dug cai nay ko can khoi tao
	private BuildingSearchBuilderConvertor buildingSearchBuilderConvertor;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BuildingResponseDTO> searchBuildings(Map<String, String> requestParams, List<String> typeCode) {

		BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConvertor
				.toBuildingSearchBuilder(requestParams, typeCode);

		List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);

		List<BuildingResponseDTO> buildingResponseDTOs = new ArrayList<>();

		for (BuildingEntity entity : buildingEntities) {
			BuildingResponseDTO buildingResponseDTO = buildingConvertor.convertToBuildingResponseDTO(entity);
			// BuildingResponseDTO buildingResponseDTO =
			// buildingSearchMapper.toBuildingResponseDTO(entity);
			buildingResponseDTOs.add(buildingResponseDTO);
		}

		return buildingResponseDTOs;
	}

	@Override
	@Transactional
	public BuildingEntity create(BuildingRequestDTO building) {
		// chuyen DTO SANG ENTITY
		BuildingEntity buildingEntity = buildingConvertor.toBuildingEntity(building);
		entityManager.persist(buildingEntity);
		entityManager.flush();// cap nhat DB ngay lap tuc
		Query q = entityManager.createNativeQuery("SELECT b.* FROM building b", BuildingEntity.class);
		int cout = q.getResultList().size();

		List<RentAreaEntity> list = new ArrayList<RentAreaEntity>();

		if (building.getRentAreas() != null) {
			for (Long rentAreaValue : building.getRentAreas()) {
				RentAreaEntity areaEntity = new RentAreaEntity();
				areaEntity.setBuilding(buildingEntity);
				areaEntity.setValue(rentAreaValue);
				list.add(areaEntity);
			}
		}
		areaRepository.saveAll(list);
		return buildingEntity;
	}

	@Override
	@Transactional
	public BuildingEntity update(BuildingRequestDTO building) {
		// chuyen DTO SANG ENTITY
		BuildingEntity buildingEntity = buildingConvertor.toBuildingEntity(building);
		entityManager.merge(buildingEntity);
		entityManager.createQuery("DELETE FROM RentAreaEntity r WHERE r.building.id = :id")
				.setParameter("id", buildingEntity.getId()).executeUpdate();
		for (Long rentAreaValue : building.getRentAreas()) {
			RentAreaEntity areaEntity = new RentAreaEntity();
			areaEntity.setBuilding(buildingEntity);
			areaEntity.setValue(rentAreaValue);
			entityManager.persist(areaEntity);
		}

		return buildingEntity;
	}

	@Override
	@Transactional
	public void delete(List<Long> ids) {
		areaRepository.deleteAllByBuilding_IdIn(ids);
		buildingRepository.deleteByIdIn(ids);
	}
}
