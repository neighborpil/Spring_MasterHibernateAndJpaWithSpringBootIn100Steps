package com.neighborpil.jpa.hibernate.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="course") // hibernate function
@NamedQueries(value = {
	@NamedQuery(name="query_get_all_courses", query="SELECT c FROM Course c"),
	@NamedQuery(name="query_get_all_courses_join_fetch", query="SELECT c FROM Course c JOIN FETCH c.students s"),
	@NamedQuery(name="query_get_100_courses", query="SELECT c FROM Course c WHERE name LIKE '%100 steps'")
})
@Getter
@Setter
@Cacheable
@SQLDelete(sql="update course set is_deleted = true where id=?") // hibernate fuction
@Where(clause="is_deleted = false")
public class Course {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="fullname", nullable = false, unique=true, insertable=true, updatable=true)
	private String name;
	
	@OneToMany(mappedBy = "course") // default fetchtype = lazy
//	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
	private List<Review> reviews = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<>();
	
	@UpdateTimestamp // hibernate function
	private LocalDateTime lastUpdatedDate;
	
	@CreationTimestamp // hibernate function
	private LocalDateTime createdDate;

	private boolean isDeleted;
	
	@PreRemove
	private void preRemove() {
		log.info("Setting isDeleted to True");
		this.isDeleted = true;
	}
	
	public Course(String name) {
		this.name = name;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.students.remove(student);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", lastUpdatedDate=" + lastUpdatedDate + ", createdDate="
				+ createdDate + "]";
	}
	
	
}
