package com.javaweb.repository.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";

	@Override
	public DistrictEntity findDistrictById(Long districtId) {
		// func1
		String sql = buildQuery(districtId);

		// func2
		DistrictEntity entity = executeQuery(sql);

		return entity;
	}

	private String buildQuery(Long districtId) {
		return "SELECT * FROM district WHERE id = " + districtId;
	}

	private DistrictEntity executeQuery(String sql) {
		DistrictEntity districtEntity = null;

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			if (rs.next()) {
				districtEntity = new DistrictEntity();
				districtEntity.setId(rs.getLong("id"));
				districtEntity.setCode(rs.getString("code"));
				districtEntity.setName(rs.getString("name"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return districtEntity;
	}
}