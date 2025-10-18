package com.javaweb.repository.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.NumberFormatUtil;
import com.javaweb.utils.StringUtil;

@Repository // bean String
public class BuildingRepositoryImpl implements BuildingRepository {

	// connect jdbc
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";


	@Override
	public List<BuildingEntity> searchBuildings(Map<String, String> requestParams, List<String> typeCode) {
	    // Dùng StringBuilder để nối chuỗi SQL an toàn và tránh lỗi append
	    StringBuilder sql = new StringBuilder("SELECT DISTINCT b.* FROM building b");

	    
	    buildJoin(requestParams, typeCode, sql);

	   
	    StringBuilder where=new StringBuilder(" where 1=1");

	    buildWhere(requestParams, typeCode, where);
	    sql.append(where);
	    sql.append(" GROUP BY b.id");
	    
	    
	    List<BuildingEntity> buildingEntities = executeQuery(sql.toString());

	    return buildingEntities;
	}

	
	
	private void buildJoin(Map<String, String> requestParams, List<String> typeCode,StringBuilder join) {
		if(typeCode!=null && !typeCode.isEmpty()) {
			join.append(" INNER JOIN buildingrenttype brt ON brt.buildingid = b.id");
			join.append( " INNER JOIN renttype rt ON brt.renttypeid = rt.id ");
		}
		String staffId=requestParams.get("staffId");
		if (staffId!= null) {
			join.append(" INNER JOIN assignmentbuilding as ON as.buildingid = b.id ");
		}
		String rentAreaFrom=requestParams.get("areaFrom");
		String rentAreaTo=requestParams.get("areaTo");
		if (StringUtil.check(rentAreaTo) && StringUtil.check(rentAreaFrom)) {
			join.append(" INNER JOIN rentarea ra ON ra.buildingid = b.id ");
		}	
		
	}
	private void buildWhere(Map<String, String> requestParams, List<String> typeCode,StringBuilder where) {
		for(Map.Entry<String, String> item:requestParams.entrySet()) {
			if(!item.getKey().equals("staffId") && item.getKey().equals("typeCode") && !item.getKey().startsWith("rentArea") &&!item.getKey().startsWith("rentPrice")) {
				String value=item.getValue();
				if(NumberFormatUtil.isLong(value)|| NumberFormatUtil.isFloat(value)) {
					where.append(" AND b. "+item.getKey().toLowerCase() +" = "+value );
				}
				else {
					where.append(" AND b." +item.getKey().toLowerCase()+ " Like '%"+ value+"%'");
				}
			}
		}
		//special
		String staffId=requestParams.get("staffId");
		if(StringUtil.check(staffId)) {
			where.append(" AND as.staffid = "+ staffId);
		}
		
		//price
		String rentPriceFrom=requestParams.get("rentPriceFrom");
		String rentPriceTo=requestParams.get("rentPriceTo");
		if(StringUtil.check(rentPriceFrom)) {
			where.append(" AND b.rentprice >= " +rentPriceFrom);
		}
		if(StringUtil.check(rentPriceTo)) {
			where.append(" AND b.rentprice <= " +rentPriceTo);
		}
		//area
		String areaFrom=requestParams.get("areaFrom");
		String areaTo=requestParams.get("areaTo");
		if(StringUtil.check(areaFrom)) {
			where.append(" AND ra.value >="+ areaFrom);
		}
		if(StringUtil.check(areaTo)) {
			where.append(" AND ra.value >="+ areaTo);
		}
		//type code java7
//		if(typeCode !=null && !typeCode.isEmpty()) {
//			List<String> type=new ArrayList<String>();
//			for(String it: typeCode) {
//				type.add("'"+it+"'");
//			}
//			where.append(" AND rt.code in (" +String.join(",", type)+")");// in('tang-triet','abc');
//			
//		}
		//java 8: stream
		
		
		
	}

	 private List<BuildingEntity> executeQuery(String sql) {
	        List<BuildingEntity> buildingEntities = new ArrayList<>();

	        try (Connection conn = ConnectionJDBCUtil.getConnection();
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
	            }

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	        return buildingEntities;
	    }


}
