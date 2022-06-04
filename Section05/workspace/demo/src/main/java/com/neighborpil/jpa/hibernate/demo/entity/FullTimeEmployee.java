package com.neighborpil.jpa.hibernate.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FullTimeEmployee extends Employee{

	protected FullTimeEmployee() {}
	
	private BigDecimal salary;

	public FullTimeEmployee(String name, BigDecimal salary) {
		super(name);
		this.salary = salary;
	}
}
