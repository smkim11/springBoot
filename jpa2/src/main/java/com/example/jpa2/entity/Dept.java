package com.example.jpa2.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="dept")
public class Dept {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dno;
	
	@Column(name="dname")
	private String dname;
	
	@Column(name="loc")
	private String loc;
	
	@OneToMany(mappedBy="dept")
	private List<Emp> empList = new ArrayList<>();
}
