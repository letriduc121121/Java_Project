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
@Repository //bean String
public class BuildingRepositoryImpl  implements BuildingRepository{
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";

	@Override
	public List<BuildingEntity> findAll(String name, Long rentPrice) {
		String sql="Select b.* FROM Building b where 1=1";
		if(name !=null && !name.isEmpty()) {
			sql+=" and b.name LIKE '%" + name + "%'";
		}

	    if (rentPrice != null) {
		        sql += " AND b.rentprice = " + rentPrice;
		   }
	    List<BuildingEntity> buildingEntities=new ArrayList<>();
		System.out.println("🔍 SQL Query: " + sql);
		try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS); 
				Statement st = conn.createStatement();
		         ResultSet rs = st.executeQuery(sql)) {
			  System.out.println("Kết nối database thành công!");
			//code
			int count = 0;
			while(rs.next()) {
				count++;
				System.out.println("Processing row " + count);
				BuildingEntity building=new BuildingEntity();
				building.setId(rs.getLong("id"));
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setRentPrice(rs.getLong("rentprice"));
				building.setWard(rs.getString("ward"));
				building.setDistrictId(rs.getLong("districtid"));
				buildingEntities.add(building);
				System.out.println("Added building: " + building.getName());
				
				
			}
			
			System.out.println("Total rows processed: " + count);
		}
		catch(SQLException ex) {
			System.out.println("❌ SQL Error: " + ex.getMessage());
			ex.printStackTrace();
		}
		return buildingEntities;
	}
	

}
