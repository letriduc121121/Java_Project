package com.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.javaweb.repository.entity.BuildingEntity;

public class ConnectionJDBCUtil {
	// connect jdbc
		static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic_1";
		static final String USER = "root";
		static final String PASS = "123456";
		public static final Connection getConnection() {
			try {
				Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
				return conn;
			}
			catch(SQLException E) {
				E.printStackTrace();
			}
			return null;

		}
}
