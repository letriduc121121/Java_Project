package com.javaweb.repository.Impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
@Primary  // Đánh dấu đây là implementation chính (ưu tiên sử dụng)
public class BuildingRepositoryImpl implements BuildingRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BuildingEntity> searchBuildings(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder("SELECT  b.* FROM building b");

		buildJoin(buildingSearchBuilder, sql);

		StringBuilder where = new StringBuilder(" where 1=1");

		buildWhere(buildingSearchBuilder, where);
		sql.append(where);
		sql.append(" GROUP BY b.id");

		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		
		@SuppressWarnings("unchecked")
		List<BuildingEntity> result = (List<BuildingEntity>) query.getResultList();
		return result;
	}

	private void buildJoin(BuildingSearchBuilder buildingSearchBuilder, StringBuilder join) {
		if (buildingSearchBuilder.getTypeCode() != null && !buildingSearchBuilder.getTypeCode().isEmpty()) {
			join.append(" INNER JOIN buildingrenttype  ON buildingrenttype.buildingid = b.id");
			join.append(" INNER JOIN renttype rt ON buildingrenttype.renttypeid = rt.id");
		}

		Long staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
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
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			// duye qua tung bien
			for (Field field : fields) {
				field.setAccessible(true); // Bug fixed: Đặt ở đầu để có thể truy cập private fields
				String fieldName = field.getName();

				if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("rentArea")
						&& !fieldName.startsWith("rentPrice")) {
					Object value = field.get(buildingSearchBuilder);// lay value cua 1 field trong doi tuong
																	// BuildingSearchBuilder
					if (value != null) {
						if (field.getType().getName().equals("java.lang.Long")
								|| field.getType().getName().equals("java.lang.Integer")) {
							where.append(" AND b.").append(fieldName.toLowerCase()).append(" = ").append(value);
						} else {
							where.append(" AND b.").append(fieldName.toLowerCase()).append(" LIKE '%").append(value)
									.append("%'");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// special
		Long staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			where.append(" AND ab.staffid = ").append(staffId);
		}
		// rentArea
		Long rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
		Long rentAreaTo = buildingSearchBuilder.getRentAreaTo();
		if (rentAreaFrom != null) {
			where.append(" AND ra.value >= ").append(rentAreaFrom);
		}
		if (rentAreaTo != null) {
			where.append(" AND ra.value <= ").append(rentAreaTo);
		}
		// rentPrice
		Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
		Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
		if (rentPriceFrom != null) {
			where.append(" AND b.rentprice >= ").append(rentPriceFrom);
		}
		if (rentPriceTo != null) {
			where.append(" AND b.rentprice <= ").append(rentPriceTo);
		}

		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		// typeCode
		if (typeCode != null && !typeCode.isEmpty()) {
			List<String> codes = new ArrayList<>();
			for (String code : typeCode) {
				codes.add("'" + code + "'");
			}
			where.append(" AND rt.code IN (").append(String.join(",", codes)).append(")");
		}
	}

}
