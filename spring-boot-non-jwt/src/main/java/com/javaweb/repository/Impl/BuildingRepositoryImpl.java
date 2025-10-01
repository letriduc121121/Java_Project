package com.javaweb.repository.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.model.DTO.BuildingRequestDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository // bean String
public class BuildingRepositoryImpl implements BuildingRepository {

	// connect jdbc
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";

	@Override
	public List<BuildingEntity> searchBuildings(BuildingRequestDTO requestDTO) {

		// func1
		String sql = buildQuery(requestDTO);
//		System.out.println(sql);
		// func2
		List<BuildingEntity> buildingEntities = executeQuery(sql);

		return buildingEntities;
	}

	private String buildQuery(BuildingRequestDTO requestDTO) {

		String sql = "SELECT  b.* FROM building b ";

		// wwith rentare
		if (requestDTO.getAreaFrom() != null || requestDTO.getAreaTo() != null) {
			sql += " INNER JOIN rentarea r ON r.buildingid = b.id ";
		}

		// with assignmentbuilding
		if (requestDTO.getStaffId() != null) {
			sql += " INNER JOIN assignmentbuilding ab ON ab.buildingid = b.id ";
		}

		// wwith buildingrenttype
		if (requestDTO.getTypeCode() != null && !requestDTO.getTypeCode().isEmpty()) {
			sql += " INNER JOIN buildingrenttype brt ON brt.buildingid = b.id ";
			sql += " INNER JOIN renttype rt ON brt.renttypeid = rt.id ";
		}

		sql += " WHERE 1=1 ";

		// 1
		if (requestDTO.getName() != null && !requestDTO.getName().isEmpty()) {
			sql += " AND b.name LIKE '%" + requestDTO.getName() + "%' ";
		}
		// 2
		if (requestDTO.getFloorArea() != null) {
			sql += " AND b.floorarea = " + requestDTO.getFloorArea();
		}
		// 3
		if (requestDTO.getDistrictId() != null) {
			sql += " AND b.districtid = " + requestDTO.getDistrictId();
		}
		// 4
		if (requestDTO.getWard() != null && !requestDTO.getWard().isEmpty()) {
			sql += " AND b.ward LIKE '%" + requestDTO.getWard() + "%'";
		}
		// 5
		if (requestDTO.getStreet() != null && !requestDTO.getStreet().isEmpty()) {
			sql += " AND b.street LIKE '%" + requestDTO.getStreet() + "%'";
		}
		// 6
		if (requestDTO.getNumberOfBasement() != null) {
			sql += " AND b.numberofbasement = " + requestDTO.getNumberOfBasement();
		}
		// 7
		if (requestDTO.getDirection() != null && !requestDTO.getDirection().isEmpty()) {
			sql += " AND b.direction LIKE '%" + requestDTO.getDirection() + "%'";
		}
		// 8
		if (requestDTO.getLevel() != null && !requestDTO.getLevel().isEmpty()) {
			sql += " AND b.level LIKE '%" + requestDTO.getLevel() + "%'";
		}
		// 9
		if (requestDTO.getAreaFrom() != null) {
			sql += " AND r.value >= " + requestDTO.getAreaFrom();
		}
		// 10
		if (requestDTO.getAreaTo() != null) {
			sql += " AND r.value <= " + requestDTO.getAreaTo();
		}
		// 11
		if (requestDTO.getRentPriceFrom() != null) {
			sql += " AND b.rentprice >= " + requestDTO.getRentPriceFrom();
		}
		// 12
		if (requestDTO.getRentPriceTo() != null) {
			sql += " AND b.rentprice <= " + requestDTO.getRentPriceTo();
		}
		// 13
		if (requestDTO.getManagerName() != null && !requestDTO.getManagerName().isEmpty()) {
			String name = requestDTO.getManagerName().trim();  
		    sql += " AND b.managername LIKE '%" + name + "%' ";
		}
		// 14
		if (requestDTO.getManagerPhoneNumber() != null && !requestDTO.getManagerPhoneNumber().isEmpty()) {
			String phone = requestDTO.getManagerPhoneNumber().trim();
		    sql += " AND b.managerphonenumber LIKE '%" + phone + "%' ";
		}
		// 15
		if (requestDTO.getStaffId() != null) {
			sql += " AND ab.staffid = " + requestDTO.getStaffId();
		}
		// 16
		if (requestDTO.getTypeCode() != null && !requestDTO.getTypeCode().isEmpty()) {
			sql += " AND rt.code IN ('" + String.join("','", requestDTO.getTypeCode()) + "')";
		}
		return sql.toString();
	}

	private List<BuildingEntity> executeQuery(String sql) {

		List<BuildingEntity> buildingEntities = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			System.out.println("Connection SQL success!");
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();

				building.setId(rs.getLong("id"));
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setDistrictId(rs.getLong("districtid"));
				building.setStructure(rs.getString("structure"));
				building.setNumberOfBasement(rs.getLong("numberofbasement"));
				building.setFloorArea(rs.getLong("floorarea"));
				building.setDirection(rs.getString("direction"));
				building.setLevel(rs.getString("level"));
				building.setRentPrice(rs.getLong("rentprice"));
				building.setRentPriceDescription(rs.getString("rentpricedescription"));
				building.setServiceFee(rs.getString("servicefee"));
				building.setCarFee(rs.getString("carfee"));
				building.setMotorbikeFee(rs.getString("motorbikefee"));
				building.setOvertimeFee(rs.getString("overtimefee"));
				building.setWaterFee(rs.getString("waterfee"));
				building.setElectricityFee(rs.getString("electricityfee"));
				building.setDeposit(rs.getString("deposit"));
				building.setPayment(rs.getString("payment"));
				building.setRentTime(rs.getString("renttime"));
				building.setDecorationTime(rs.getString("decorationtime"));
				building.setBrokerageFee(rs.getLong("brokeragefee"));
				building.setNote(rs.getString("note"));
				building.setLinkOfBuilding(rs.getString("linkofbuilding"));
				building.setMap(rs.getString("map"));
				building.setImage(rs.getString("image"));
				building.setCreatedDate(rs.getDate("createddate"));
				building.setModifiedDate(rs.getDate("modifieddate"));
				building.setCreatedBy(rs.getString("createdby"));
				building.setModifiedBy(rs.getString("modifiedby"));
				building.setManagerName(rs.getString("managername"));
				building.setManagerPhoneNumber(rs.getString("managerphonenumber"));

				buildingEntities.add(building);
				System.out.println("Added building: " + building.getName());
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return buildingEntities;
	}

//	@Override
//	public List<BuildingEntity> searchBuildings(String name, Long floorArea, Long districtId, String ward,
//			String street, Long numberOfBasement, String direction, String level, Long areaFrom, Long areaTo,
//			Long rentPriceFrom, Long rentPriceTo, String managerName, String managerPhoneNumber, Long staffId,
//			List<String> rentTypes) {
//
//		String sql = "SELECT  b.* FROM building b where 1=1 ";
//		// 1
//		if (name != null && !name.trim().isEmpty()) {
//			sql += "and b.name like '%" + name + "%'";
//		}
//		// 2
//		if (floorArea != null) {
//			sql += "and b.floorarea = " + floorArea;
//		}
//		// 3
//		if (districtId != null) {
//			sql += "and b.districtid = " +districtId;
//		}
//		// 4
//		if (ward != null && !ward.isEmpty()) {
//			sql += " AND b.ward LIKE '%" + ward + "%'";
//		}
//		// 5
//		if (street != null && !street.isEmpty()) {
//			sql += " AND b.street LIKE '%" + street + "%'";
//		}
//		// 6
//		if (numberOfBasement != null) {
//			sql += " AND b.numberofbasement = " + numberOfBasement;
//		}
//		// 7
//		if (direction != null && !direction.isEmpty()) {
//			sql += " AND b.direction LIKE '%" + direction + "%'";
//		}
//		// 8
//		if (level != null && !level.isEmpty()) {
//			sql += " AND b.level LIKE '%" + level + "%'";
//		}
//		// 9
//
//		if (areaFrom != null) {
//			sql += " AND EXISTS (SELECT 1 FROM rentarea r WHERE r.buildingid = b.id AND r.value >= " + areaFrom +" )";
//		}
//		// 10
//		if (areaTo != null) {
//			sql += " AND EXISTS (SELECT 1 FROM rentarea r WHERE r.buildingid = b.id AND r.value <=  " + areaTo +" )";
//		}
//		// 11
//		// rentPrice between
//		if (rentPriceFrom != null) {
//			sql += " AND b.rentprice >= " + rentPriceFrom;
//		}
//		// 12
//		if (rentPriceTo != null) {
//			sql += " AND b.rentprice <= " + rentPriceTo;
//		}
//		// 13
//		if (managerName != null && !managerName.isEmpty()) {
//			sql += "and b.managername like '%" + managerName + "%'";
//		}
//		// 14
//		if (managerPhoneNumber != null && !managerPhoneNumber.isEmpty()) {
//			sql += "and b.managerphonenumber like '%" + managerPhoneNumber + "%'";
//		}
//		// 15
//		if (staffId != null) {
//			sql += " AND EXISTS (SELECT 1 FROM assignmentbuilding ab WHERE ab.buildingid = b.id AND ab.staffid = "
//					+ staffId +" )";
//		}
//		// 16
////		if (rentTypes != null && !rentTypes.isEmpty()) {
////			sql += " AND EXISTS (SELECT 1 FROM buildingrenttype brt " + "JOIN renttype rt ON brt.renttypeid = rt.id "
////					+ "WHERE brt.buildingid = b.id AND rt.code IN ('" + String.join("','", rentTypes) + "'))";
////		}
//		List<BuildingEntity> buildingEntities = new ArrayList<>();
//
//		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//				Statement st = conn.createStatement();
//				ResultSet rs = st.executeQuery(sql)) {
//
//			System.out.println("Connection SQL success!");
//
//			while (rs.next()) {
//
//				BuildingEntity building = new BuildingEntity();
//				
//				building.setId(rs.getLong("id"));
//				building.setName(rs.getString("name"));
//				building.setStreet(rs.getString("street"));
//				building.setWard(rs.getString("ward"));
//				building.setDistrictId(rs.getLong("districtid"));
//				building.setStructure(rs.getString("structure"));
//				building.setNumberOfBasement(rs.getLong("numberofbasement"));
//				building.setFloorArea(rs.getLong("floorarea"));
//				building.setDirection(rs.getString("direction"));
//				building.setLevel(rs.getString("level"));
//				building.setRentPrice(rs.getLong("rentprice"));
//				building.setRentPriceDescription(rs.getString("rentpricedescription"));
//				building.setServiceFee(rs.getString("servicefee"));
//				building.setCarFee(rs.getString("carfee"));
//				building.setMotorbikeFee(rs.getString("motorbikefee"));
//				building.setOvertimeFee(rs.getString("overtimefee"));
//				building.setWaterFee(rs.getString("waterfee"));
//				building.setElectricityFee(rs.getString("electricityfee"));
//				building.setDeposit(rs.getString("deposit"));
//				building.setPayment(rs.getString("payment"));
//				building.setRentTime(rs.getString("renttime"));
//				building.setDecorationTime(rs.getString("decorationtime"));
//				building.setBrokerageFee(rs.getLong("brokeragefee"));
//				building.setNote(rs.getString("note"));
//				building.setLinkOfBuilding(rs.getString("linkofbuilding"));
//				building.setMap(rs.getString("map"));
//				building.setImage(rs.getString("image"));
//				building.setCreatedDate(rs.getDate("createddate"));
//				building.setModifiedDate(rs.getDate("modifieddate"));
//				building.setCreatedBy(rs.getString("createdby"));
//				building.setModifiedBy(rs.getString("modifiedby"));
//				building.setManagerName(rs.getString("managername"));
//				building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
//
//
//				buildingEntities.add(building);
//				System.out.println("Added building: " + building.getName());
//			}
//
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//
//		return buildingEntities;
//	}

}
