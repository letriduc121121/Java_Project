package com.javaweb.repository.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectionJDBCUtil;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {



	@Override
	public DistrictEntity findDistrictById(Long districtId) {
		// func1
		String sql ="SELECT * FROM district WHERE id = " + districtId;
		DistrictEntity district=new DistrictEntity();
		try (Connection conn=ConnectionJDBCUtil.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while(rs.next()) {
				district.setId(rs.getLong("id"));
				district.setCode(rs.getString("code"));
				district.setName(rs.getString("name"));
			}
			
		}
		catch(SQLException e) {
		 e.printStackTrace();
		}
		return district;
		

	
	}
}