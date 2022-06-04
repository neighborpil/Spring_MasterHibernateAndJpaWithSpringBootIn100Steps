package com.neighborpil.jpa.hibernate.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PartTimeEmployee extends Employee{

	protected PartTimeEmployee() {}
	
	private BigDecimal hourlyWage;

	public PartTimeEmployee(String name, BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage = hourlyWage;
	}
}
