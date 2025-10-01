package com.javaweb.model.DTO;

import java.math.BigDecimal;
import java.util.List;

public class BuildingResponseDTO {
	private Long id;//id
	private String name;//ten toa
	private String address;//dia cjhi
	private Long numberOfBasement;// so tang ham
	private String managerName;// ten quan ly
	private String managerPhoneNumber;//sdt quan ly
	private Long floorArea;// dien tich san
	private List<Long>rentArea;//gia dien tich thue
	private Long emptyArea;//dien tich trong
	private Long rentPrice;//gia thue
	private String serviceFee;//phi thue
	private Long brokerageFee;//phi moi gioi
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Long numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
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
	public Long getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}
	
	public List<Long> getRentArea() {
		return rentArea;
	}
	public void setRentArea(List<Long> rentArea) {
		this.rentArea = rentArea;
	}
	public Long getEmptyArea() {
		return emptyArea;
	}
	public void setEmptyArea(Long long1) {
		this.emptyArea = long1;
	}
	public Long getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public Long getBrokerageFee() {
		return brokerageFee;
	}
	public void setBrokerageFee(Long brokerageFee) {
		this.brokerageFee = brokerageFee;
	}


}
