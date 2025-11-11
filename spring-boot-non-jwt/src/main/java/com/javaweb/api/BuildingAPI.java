package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.exception.InvalidBuildingException;
import com.javaweb.model.DTO.BuildingRequestDTO;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.StringUtil;

@RestController
@RequestMapping(value = "/buildings")

public class BuildingAPI {

	@Autowired
	private BuildingService buildingService;

	@GetMapping()
	 public Object getBuilding(
	            @RequestParam Map<String, String> requestParams,
	            @RequestParam(name = "typeCode", required = false) List<String> typeCode) {
		Object responseDTO = buildingService.searchBuildings(requestParams,typeCode);
		return responseDTO;
	}
	@PostMapping()
	public ResponseEntity<?> createBuilding(@RequestBody BuildingRequestDTO building){
		validateData(building);
		buildingService.create(building);
		return ResponseEntity.ok(building);
	}
	private void validateData(BuildingRequestDTO building) {
		// TODO Auto-generated method stub
		if(!StringUtil.check(building.getName())|| building.getNumberOfBasement()==null) {
			throw new InvalidBuildingException("name and numberofBasement not null!");
		}
	}
	@PutMapping()
	public ResponseEntity<?> updateBuilding(@RequestBody BuildingRequestDTO building){
		validateData(building);
		buildingService.update(building);
		return ResponseEntity.ok(building);
	}
	@DeleteMapping("{ids}")
	public ResponseEntity<?> deleteBuilding(@PathVariable List<Long> ids){
		buildingService.delete(ids);
		return ResponseEntity.ok("Delete success");
	}

}
