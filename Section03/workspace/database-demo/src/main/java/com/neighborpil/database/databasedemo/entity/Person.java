package com.neighborpil.database.databasedemo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="person")
@NamedQuery(name="find_all_persons", query="select p from Person p")
public class Person {

	@Id
	@GeneratedValue
	private int id;
	@Column(name="name")
	private String name;
	private String location;
	private Date birthDate;
	
	public Person(String name, String location, Date birthDate) {
		super();
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}
	
}
