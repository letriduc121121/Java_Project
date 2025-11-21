package com.javaweb.model.DTO;

import java.util.List;

public class BuildingRequestDTO {
	private String name;
	private Long floorArea;
	private Long districtId;
	private String ward;
	private String street;
	private Long numberOfBasement;
	private String direction;
	private String level;
	private Long areaFrom;
	private Long areaTo;
	private Long rentPriceFrom;
	private Long rentPriceTo;
	private String managerName;
	private String managerPhoneNumber;
	private Long staffId;
	private List<String> typeCode;

	public BuildingRequestDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Long numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getAreaFrom() {
		return areaFrom;
	}

	public void setAreaFrom(Long areaFrom) {
		this.areaFrom = areaFrom;
	}

	public Long getAreaTo() {
		return areaTo;
	}

	public void setAreaTo(Long areaTo) {
		this.areaTo = areaTo;
	}

	public Long getRentPriceFrom() {
		return rentPriceFrom;
	}

	public void setRentPriceFrom(Long rentPriceFrom) {
		this.rentPriceFrom = rentPriceFrom;
	}

	public Long getRentPriceTo() {
		return rentPriceTo;
	}

	public void setRentPriceTo(Long rentPriceTo) {
		this.rentPriceTo = rentPriceTo;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}

	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public List<String> getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(List<String> typeCode) {
		this.typeCode = typeCode;
	}

}
