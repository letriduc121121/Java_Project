package com.javaweb.repository.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";


	@Override
	public String findDistrictNameById(Long id) {
		String sql = "SELECT name FROM district WHERE id = " + id;
		String districtName = null;
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			if (rs.next()) {
				districtName = rs.getString("name");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return districtName;
	}
}