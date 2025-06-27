package com.sakila.api.dto;

import java.sql.Timestamp;

import com.sakila.api.entity.AddressEntity;

import lombok.Data;

@Data
public class AddressDto {
	private int addressId;
	private String address;
	private String address2;
	private String district;
	private String postalCode;
	private String phone;
	private Timestamp lastUpdate;
	private int cityId;
	
	public AddressEntity toEntity() {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setAddress(this.address);
		addressEntity.setAddress2(this.address2);
		addressEntity.setDistrict(this.address);
		addressEntity.setPostalCode(this.postalCode);
		addressEntity.setPhone(this.phone);
		
		return addressEntity;
	}
	
	
}
