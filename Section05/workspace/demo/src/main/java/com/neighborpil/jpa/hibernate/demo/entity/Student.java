package com.neighborpil.jpa.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@ToString
public class Student {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;

	@Embedded
	private Address address;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Passport passport;
	
	@ManyToMany // default: lazy fetch, I can change it to eager fetch
	@JoinTable(name="STUDENT_COURSE", // owning side can add join table	 
		joinColumns = @JoinColumn(name = "STUDENT_ID"),
		inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
	private List<Course> courses = new ArrayList<>();

	public void addCourse(Course course) {
		this.courses.add(course);
	}
	
	public Student(String name, Passport passport) {
		super();
		this.name = name;
		this.passport = passport;
	}

	public Student(String name) {
		super();
		this.name = name;
	}



}
