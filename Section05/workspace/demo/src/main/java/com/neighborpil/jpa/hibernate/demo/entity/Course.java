package com.neighborpil.jpa.hibernate.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="course") // hibernate function
@NamedQueries(value = {
	@NamedQuery(name="query_get_all_courses", query="SELECT c FROM Course c"),
	@NamedQuery(name="query_get_100_courses", query="SELECT c FROM Course c WHERE name LIKE '%100 steps'")
})
public class Course {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="fullname", nullable = false, unique=true, insertable=true, updatable=true)
	private String name;
	
	@UpdateTimestamp // hibernate function
	private LocalDateTime lastUpdatedDate;
	
	@CreationTimestamp // hibernate function
	private LocalDateTime createdDate;

	public Course(String name) {
		this.name = name;
	}
	
}
