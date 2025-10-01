package com.javaweb.repository.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";

	@Override
	public List<RentAreaEntity> findByBuildingId(Long buildingId) {
		// func1
		String sql = buildQuery(buildingId);

		// func2
		List<RentAreaEntity> rentAreaEntities = executeQuery(sql);

		return rentAreaEntities;
	}

	private String buildQuery(Long buildingId) {
		String sql = "SELECT * FROM rentarea WHERE buildingid = " + buildingId;
		return sql;
	}

	private List<RentAreaEntity> executeQuery(String sql) {
		List<RentAreaEntity> rentAreaEntities = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				RentAreaEntity rentAreaEntity = new RentAreaEntity();

				rentAreaEntity.setId(rs.getLong("id"));
				rentAreaEntity.setValue(rs.getLong("value"));
				rentAreaEntity.setBuildingId(rs.getLong("buildingid"));
				rentAreaEntity.setCreatedDate(rs.getDate("createddate"));
				rentAreaEntity.setModifiedDate(rs.getDate("modifieddate"));
				rentAreaEntity.setCreatedBy(rs.getString("createdby"));
				rentAreaEntity.setModifiedBy(rs.getString("modifiedby"));

				rentAreaEntities.add(rentAreaEntity);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return rentAreaEntities;
	}
}