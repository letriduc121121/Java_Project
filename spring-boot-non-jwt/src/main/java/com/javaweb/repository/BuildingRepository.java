package com.javaweb.repository;

import java.util.List;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository {
	//cac phuong truu tuong
	//ham lam viec vs database
	List<BuildingEntity> findAll(String name, Long rentPrice);
}
