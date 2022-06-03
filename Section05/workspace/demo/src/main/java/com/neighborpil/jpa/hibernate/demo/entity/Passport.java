package com.neighborpil.jpa.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passport {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String number;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy="passport")
	private Student student;

	public Passport(String number) {
		super();
		this.number = number;
	}

	@Override
	public String toString() {
		return "Passport [id=" + id + ", number=" + number + "]";
	}

}
