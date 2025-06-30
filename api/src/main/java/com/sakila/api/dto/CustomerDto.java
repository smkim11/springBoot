package com.sakila.api.dto;

import java.sql.Timestamp;

import com.sakila.api.entity.CustomerEntity;

import lombok.Data;

@Data
public class CustomerDto {
	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private int active;
	private Timestamp createDate;
	private Timestamp lastUpdate;
	private int storeId;
	private int addressId;
	
	public CustomerEntity toEntity() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setFirstName(this.firstName);
		customerEntity.setLastName(this.lastName);
		customerEntity.setEmail(this.email);
		customerEntity.setActive(this.active);
		
		return customerEntity;
	}
}
