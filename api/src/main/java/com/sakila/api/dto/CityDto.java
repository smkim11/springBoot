package com.sakila.api.dto;

import java.sql.Timestamp;

import com.sakila.api.entity.CityEntity;

import lombok.Data;
@Data
public class CityDto {
	private int cityId;
	private String city;
	private Timestamp lastUpdate;
	private int countryId;
	
	
	public CityEntity toEntity(){
		CityEntity entity = new CityEntity();
		entity.setCity(this.city);
		
		return entity;
	}
	
}
