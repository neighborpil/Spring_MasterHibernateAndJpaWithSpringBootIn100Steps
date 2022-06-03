package com.neighborpil.jpa.hibernate.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String rating;
	
	private String description;

	@ManyToOne // default: eager fetching
	private Course course;
	
	public Review(String rating, String description) {
		super();
		this.rating = rating;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", rating=" + rating + ", description=" + description + "]";
	}
	
}
