package com.neighborpil.jpa.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // default strategy: single_table
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // table per concrete entity class, it repeats common columns like id, name
//@Inheritance(strategy = InheritanceType.JOINED) // 
@MappedSuperclass // cannnot define as a @Entity, and it's not a entity, I should define retrieving subclasses separately
//@Entity
@DiscriminatorColumn(name="EmployeeType")
public abstract class Employee {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="fullname", nullable = false, unique=true, insertable=true, updatable=true)
	private String name;

	protected Employee() {
		super();
	}
	
	public Employee(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
	
}
