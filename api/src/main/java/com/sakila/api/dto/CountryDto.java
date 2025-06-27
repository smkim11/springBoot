package com.sakila.api.dto;

import java.sql.Timestamp;

import com.sakila.api.entity.CountryEntity;

import lombok.Data;

@Data
public class CountryDto {
	private int countryId;
	private String country;
	private Timestamp lastUpdate;
	
	public CountryEntity toEntity() {
		CountryEntity entity = new CountryEntity();
		entity.setCountryId(this.countryId);
		entity.setCountry(this.country);
		entity.setLastUpdate(this.lastUpdate);
		
		return entity;
	}
}
