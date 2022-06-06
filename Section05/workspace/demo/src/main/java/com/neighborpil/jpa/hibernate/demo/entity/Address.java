package com.neighborpil.jpa.hibernate.demo.entity;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	private String line1;
	private String line2;
	private String city;
}
