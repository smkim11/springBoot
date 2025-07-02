package com.sakila.api.entity;

// CountryEntity 맵핑 -> CountryEntity Getter 생성(필드 일부 읽기전용 타입)
public interface CountryMapping {

	int getCountryId(); // CountryEntity Getter만 사용가능
	String getCountry();
}
