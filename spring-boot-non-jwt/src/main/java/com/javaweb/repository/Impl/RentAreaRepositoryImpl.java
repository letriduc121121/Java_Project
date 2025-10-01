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

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";

	@Override
	public List<Long> findByBuildingId(Long buildingId) {
		String sql = "SELECT value FROM rentarea WHERE 1=1";

		if (buildingId != null) {
			sql += " AND buildingid = " + buildingId;
		}

		List<Long> values = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				Long value = rs.getLong("value");
				values.add(value);
			}

		} catch (SQLException ex) {

			ex.printStackTrace();
		}

		return values;
	}

	@Override
	public Long getTotalEmptyAreaByBuildingId(Long buildingId) {
		String sql = "SELECT SUM(value) FROM rentarea WHERE 1=1";

		if (buildingId != null) {
			sql += " AND buildingid = " + buildingId;
		}

		Long total = null ;

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			if (rs.next()) {
				total = rs.getLong(1);  
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return total;
	}
	
}