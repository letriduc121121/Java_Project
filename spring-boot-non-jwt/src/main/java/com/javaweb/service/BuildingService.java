package com.javaweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaweb.model.DTO.BuildingResponseDTO;

public interface BuildingService {
	List<BuildingResponseDTO> findAll(String name,Long numberOfBasement);
	
}
