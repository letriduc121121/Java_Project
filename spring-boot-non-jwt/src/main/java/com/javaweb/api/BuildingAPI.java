package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.exception.InvalidBuildingException;
import com.javaweb.model.DTO.BuildingRequestDTO;
import com.javaweb.model.DTO.BuildingResponseDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@RestController
@RequestMapping(value = "/buildings")
public class BuildingAPI {
//	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
//	static final String USER = "root";
//	static final String PASS = "123456";
//	@GetMapping(value = "/search")
//	public String testAPI(@RequestParam(name = "name", required = false) String buildingName,
//			@RequestParam(name = "numberofbasement", required = false) String numberOfBasement,
//			@RequestParam(name = "type", required = false) List<String> types
//
//	) {
//		System.out.print(buildingName + " " + types);
//		return "success";
//	}
////
////	@RequestMapping(value = "/searchs", method = RequestMethod.GET)
////	public String testAPI3() {
////		return "success";
////	}

	@Autowired
	private BuildingService buidlingService;

	@GetMapping()
	public Object getBuiding(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "numberofBasement", required = false) Long numberofBasement) {
		List<BuildingResponseDTO> buildingResponseDTOs = buidlingService.findAll(name, numberofBasement);
		return buildingResponseDTOs;
//		String sql="Select b.* FROM Building b where 1=1";
//		if(name !=null && !name.isEmpty()) {
//			sql+=" and b.name LIKE '%" + name + "%'";
//		} 
//
//	    if (numberofBasement != null) {
//		        sql += " AND b.numberofBasement = " + numberofBasement;
//		   }
//	    List<BuildingResponseDTO> responseDTOS=new ArrayList<>();
//		System.out.println("🔍 SQL Query: " + sql);
//		try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS); 
//				Statement st = conn.createStatement();
//		         ResultSet rs = st.executeQuery(sql)) {
//			  System.out.println("Kết nối database thành công!");
//			//code
//			int count = 0;
//			while(rs.next()) {
//				count++;
//				System.out.println("Processing row " + count);
//				BuildingResponseDTO building=new BuildingResponseDTO();
//				building.setName(rs.getString("name"));
//				building.setNumberOfBasement(rs.getLong("numberofbasement"));
//				building.setStreet(rs.getString("street"));
//				building.setRentPrice(rs.getLong("rentprice"));
//				building.setWard(rs.getString("ward"));
//				responseDTOS.add(building);
//				System.out.println("Added building: " + building.getName());
//				
//				
//			}
//			
//			System.out.println("Total rows processed: " + count);
//		}
//		catch(SQLException ex) {
//			System.out.println("❌ SQL Error: " + ex.getMessage());
//			ex.printStackTrace();
//		}
//		System.out.println("Returning " + responseDTOS.size() + " buildings");
//		return responseDTOS;
		

	}

	@PostMapping
	public Object createBuilding(@RequestBody BuildingRequestDTO building) {
		validateData(building);
		// Removed problematic test code that causes ArrayIndexOutOfBoundsException
		// System.out.println(5 / 1);
		// int a[] = { 1, 2, 3, 4, 5 };
		// System.out.println(a[5]); // This causes ArrayIndexOutOfBoundsException
//		try {
//			validateData(building);
//		}
//		catch(Exception ex) {
//			ErrorResponse errorResponse=new ErrorResponse();
//			errorResponse.setError("Invalid dât building");
//			errorResponse.setDetails(Arrays.asList(ex.getMessage()));
//			return errorResponse;

//		}
		return building;
	}

	private void validateData(BuildingRequestDTO building) {
		if (building.getName() == null || building.getName().equals("") || building.getNumberOfBasement() == null) {
			throw new InvalidBuildingException("Name and numberOfBasement Buidling not be empty");
		}
	}

	@DeleteMapping("{ids}")
	public String deleteBuilding(@PathVariable List<Long> ids,
			@RequestParam(name = "name", required = false) String name) {
		System.out.println(name);
		return null;
	}

}
