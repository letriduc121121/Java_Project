package com.javaweb.convert;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;
@Component
public class BuildingSearchBuilderConvertor {

	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, String> params, List<String> typeCode) {

        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(MapUtil.getObject(params, "name", String.class))
                .setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
                .setDistrictId(MapUtil.getObject(params, "districtId", Long.class))
                .setWard(MapUtil.getObject(params, "ward", String.class))
                .setStreet(MapUtil.getObject(params, "street", String.class))
                .setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Long.class))
                .setDirection(MapUtil.getObject(params, "direction", String.class))
                .setLevel(MapUtil.getObject(params, "level", String.class))
                .setRentAreaFrom(MapUtil.getObject(params, "rentAreaFrom", Long.class))
                .setRentAreaTo(MapUtil.getObject(params, "rentAreaTo", Long.class))
                .setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Long.class))
                .setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Long.class))
                .setManagerName(MapUtil.getObject(params, "managerName", String.class))
                .setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
                .setStaffId(MapUtil.getObject(params, "staffId", Long.class))
                .setTypeCode(typeCode)
                .build();

        return buildingSearchBuilder;
    }
}
