package com.javaweb.service.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.DTO.BuildingResponseDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingConverter {

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private RentAreaRepository rentAreaRepository;

	public BuildingResponseDTO convertToBuildingResponseDTO(BuildingEntity entity) {
		BuildingResponseDTO dto = new BuildingResponseDTO();

		// 1. ID
		dto.setId(entity.getId());

		// 2. Name
		dto.setName(entity.getName());

		// 3. Address (street + ward + district name)
		DistrictEntity districtEntity = districtRepository.findDistrictById(entity.getDistrictId());
		String districtName = (districtEntity != null) ? districtEntity.getName() : "";
		dto.setAddress(entity.getStreet() + ", " + entity.getWard() + ", " + districtName);

		// 4. Number of basement
		dto.setNumberOfBasement(entity.getNumberOfBasement());

		// 5. Manager name
		dto.setManagerName(entity.getManagerName());

		// 6. Manager phone number
		dto.setManagerPhoneNumber(entity.getManagerPhoneNumber());

		// 7. Floor area
		dto.setFloorArea(entity.getFloorArea());

		// 8. List rent area
		List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(entity.getId());
		List<Long> rentAreas = new ArrayList<>();
		for (RentAreaEntity rentArea : rentAreaEntities) {
			rentAreas.add(rentArea.getValue());
		}
		dto.setRentArea(rentAreas);

		// 9. Empty area (giả sử = số lượng ô thuê trống)
		dto.setEmptyArea((long) rentAreaEntities.size());

		// 10. Rent price
		dto.setRentPrice(entity.getRentPrice());

		// 11. Service fee
		dto.setServiceFee(entity.getServiceFee());

		// 12. Brokerage fee
		dto.setBrokerageFee(entity.getBrokerageFee());

		return dto;
	}
}
