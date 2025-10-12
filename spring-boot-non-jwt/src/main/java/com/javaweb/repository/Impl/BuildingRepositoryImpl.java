package com.javaweb.repository.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

import java.util.Map;

@Repository // bean String
public class BuildingRepositoryImpl implements BuildingRepository {

	// connect jdbc
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";

	@Override
	public List<BuildingEntity> searchBuildings(Map<String, String> requestParams, List<String> typeCode) {
		String sql = "SELECT DISTINCT b.* FROM building b ";

		// func join
		sql += buildJoin(requestParams, typeCode);

		sql += " WHERE 1=1 ";

		// func where
		sql += buildWhere(requestParams, typeCode);
		List<BuildingEntity> buildingEntites = executeQuery(sql);
		return buildingEntites;
	}

	private String buildJoin(Map<String, String> requestParams, List<String> typeCode) {
		String sql = "";
		if (requestParams.get("areaFrom") != null || requestParams.get("areaTo") != null) {
			sql += " INNER JOIN rentarea r ON r.buildingid = b.id ";
		}
		if (requestParams.get("staffId") != null) {
			sql += " INNER JOIN assignmentbuilding ab ON ab.buildingid = b.id ";
		}
		if (typeCode != null && !typeCode.isEmpty()) {
			sql += " INNER JOIN buildingrenttype brt ON brt.buildingid = b.id ";
			sql += " INNER JOIN renttype rt ON brt.renttypeid = rt.id ";
		}
		return sql;
	}

	private String buildWhere(Map<String, String> requestParams, List<String> typeCode) {
		String sql = "";
		// 1
		if (requestParams.get("name") != null && !requestParams.get("name").isEmpty()) {
			sql += " AND b.name LIKE '%" + requestParams.get("name") + "%' ";
		}
		// 2
		if (requestParams.get("floorArea") != null) {
			sql += " AND b.floorarea = " + requestParams.get("floorArea");
		}
		// 3
		if (requestParams.get("districtId") != null) {
			sql += " AND b.districtid = " + requestParams.get("districtId");
		}
		// 4
		if (requestParams.get("ward") != null && !requestParams.get("ward").isEmpty()) {
			sql += " AND b.ward LIKE '%" + requestParams.get("ward") + "%'";
		}
		// 5
		if (requestParams.get("street") != null && !requestParams.get("street").isEmpty()) {
			sql += " AND b.street LIKE '%" + requestParams.get("street") + "%'";
		}
		// 6
		if (requestParams.get("numberOfBasement") != null) {
			sql += " AND b.numberofbasement = " + requestParams.get("numberOfBasement");
		}
		// 7
		if (requestParams.get("direction") != null && !requestParams.get("direction").isEmpty()) {
			sql += " AND b.direction LIKE '%" + requestParams.get("direction") + "%'";
		}
		// 8
		if (requestParams.get("level") != null && !requestParams.get("level").isEmpty()) {
			sql += " AND b.level LIKE '%" + requestParams.get("level") + "%'";
		}
		// 9
		if (requestParams.get("areaFrom") != null) {
			sql += " AND r.value >= " + requestParams.get("areaFrom");
		}
		// 10
		if (requestParams.get("areaTo") != null) {
			sql += " AND r.value <= " + requestParams.get("areaTo");
		}
		// 11
		if (requestParams.get("rentPriceFrom") != null) {
			sql += " AND b.rentprice >= " + requestParams.get("rentPriceFrom");
		}
		// 12
		if (requestParams.get("rentPriceTo") != null) {
			sql += " AND b.rentprice <= " + requestParams.get("rentPriceTo");
		}
		// 13
		if (requestParams.get("managerName") != null && !requestParams.get("managerName").isEmpty()) {
			sql += " AND b.managername LIKE '%" + requestParams.get("managerName").trim() + "%' ";
		}
		// 14
		if (requestParams.get("managerPhoneNumber") != null && !requestParams.get("managerPhoneNumber").isEmpty()) {
			sql += " AND b.managerphonenumber LIKE '%" + requestParams.get("managerPhoneNumber").trim() + "%' ";
		}
		// 15
		if (requestParams.get("staffId") != null) {
			sql += " AND ab.staffid = " + requestParams.get("staffId");
		}
		// 16
		if (typeCode != null && !typeCode.isEmpty()) {
			sql += " AND rt.code IN ('" + String.join("','", typeCode) + "')";
		}

		return sql;
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


}
