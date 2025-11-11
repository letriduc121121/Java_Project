package com.javaweb.model.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingRequestDTO {
	Long id;
	String name;
	Long districtId;
	String ward;
	String street;
	@JsonProperty(value = "number_of_basement")
	Long numberOfBasement;
	String managerName;
	String managerPhoneNumber;
	Long rentPrice;
	List<Long> rentAreas;

}
