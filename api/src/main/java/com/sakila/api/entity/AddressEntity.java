package com.sakila.api.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
public class AddressEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int addressId;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "address2")
	private String address2;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "last_update")
	@CurrentTimestamp // 현재시간 자동입력
	private Timestamp lastUpdate;
	
	@ManyToOne 
	@JoinColumn(name = "city_id") // 단방향(address에서 city) addressEntity -> cityEntity -> countryEntity
	private CityEntity cityEntity;
}