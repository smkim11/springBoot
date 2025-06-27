package com.sakila.api.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "country")
@Getter
@Setter
public class CountryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private int countryId;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "last_update", nullable=true) // null값 허용
	@CurrentTimestamp // 현재시간 자동입력
	private Timestamp lastUpdate;
	
	/* 자식테이블(City)에 있는 외래키를 부모Entity(CountryEntity)에 적을때
	@OneToMany
	private CityEntity cityEntity;
	*/
}