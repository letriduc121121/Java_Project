package com.javaweb.repository.Impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;

@Repository // bean String
public class BuildingRepositoryImpl implements BuildingRepository {


	@Override
	public List<BuildingEntity> searchBuildings(BuildingSearchBuilder buildingSearchBuilder) {
	    StringBuilder sql = new StringBuilder("SELECT  b.* FROM building b");

	    buildJoin(buildingSearchBuilder, sql);

	   
	    StringBuilder where=new StringBuilder(" where 1=1");

	    buildWhere(buildingSearchBuilder, where);
	    sql.append(where);
	    sql.append(" GROUP BY b.id");
	    
	    
	    List<BuildingEntity> buildingEntities = executeQuery(sql.toString());

	    return buildingEntities;
	}

	
	
	private void buildJoin(BuildingSearchBuilder buildingSearchBuilder, StringBuilder join) {
	    if(buildingSearchBuilder.getTypeCode() != null && !buildingSearchBuilder.getTypeCode().isEmpty()) {
	        join.append(" INNER JOIN buildingrenttype  ON buildingrenttype.buildingid = b.id");
	        join.append(" INNER JOIN renttype rt ON buildingrenttype.renttypeid = rt.id");
	    }
	    
	    Long staffId =buildingSearchBuilder.getStaffId();
	    if (staffId!=null) {
	        join.append(" INNER JOIN assignmentbuilding ab ON ab.buildingid = b.id");
	    }
	    
	    // Bug fixed: Uncomment và sửa logic để JOIN rentarea khi cần
	    Long rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
	    Long rentAreaTo = buildingSearchBuilder.getRentAreaTo();
	    if (rentAreaFrom != null || rentAreaTo != null) {
	        join.append(" INNER JOIN rentarea ra ON ra.buildingid = b.id");
	    }
	}

	private void buildWhere(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		try {
			Field[] fields= BuildingSearchBuilder.class.getDeclaredFields();
			//duye qua tung bien
		for(Field field: fields) {
			field.setAccessible(true); // Bug fixed: Đặt ở đầu để có thể truy cập private fields
			String fieldName=field.getName();
			
			if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("rentArea") && !fieldName.startsWith("rentPrice")) {
				Object value=field.get(buildingSearchBuilder);// lay value cua 1 field trong doi tuong BuildingSearchBuilder
					if(value !=null) {
						if(field.getType().getName().equals("java.lang.Long") || field.getType().getName().equals("java.lang.Integer")) {
							where.append(" AND b.").append(fieldName.toLowerCase()).append(" = ").append(value);
						}
						else {
							where.append(" AND b.").append(fieldName.toLowerCase()).append(" LIKE '%").append(value).append("%'");
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	    //special
	    Long staffId = buildingSearchBuilder.getStaffId();
	    if(staffId !=null) {
	        where.append(" AND ab.staffid = ").append(staffId);
	    }
	    // rentArea 
	    Long rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
	    Long rentAreaTo = buildingSearchBuilder.getRentAreaTo();
	    if(rentAreaFrom !=null) {
	        where.append(" AND ra.value >= ").append(rentAreaFrom);
	    }
	    if(rentAreaTo!=null) {
	        where.append(" AND ra.value <= ").append(rentAreaTo);
	    }
	    // rentPrice 
	    Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
	    Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
	    if(rentPriceFrom!=null) {
	        where.append(" AND b.rentprice >= ").append(rentPriceFrom);
	    }
	    if(rentPriceTo !=null) {
	        where.append(" AND b.rentprice <= ").append(rentPriceTo);
	    }
	    
	
	    List<String> typeCode=buildingSearchBuilder.getTypeCode();
	    // typeCode
	    if(typeCode != null && !typeCode.isEmpty()) {
	        List<String> codes = new ArrayList<>();
	        for(String code : typeCode) {
	            codes.add("'" + code + "'");
	        }
	        where.append(" AND rt.code IN (").append(String.join(",", codes)).append(")");
	    }
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