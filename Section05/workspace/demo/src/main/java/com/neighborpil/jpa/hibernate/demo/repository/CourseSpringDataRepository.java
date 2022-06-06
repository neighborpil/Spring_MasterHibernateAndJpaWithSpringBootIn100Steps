package com.neighborpil.jpa.hibernate.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.neighborpil.jpa.hibernate.demo.entity.Course;

@RepositoryRestResource(path="courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long>{

	List<Course> findByName(String name);

	List<Course> findByNameAndId(String name, Long id);
	
	List<Course> findByNameOrderByIdDesc(String name);

	List<Course> deleteByName(String name);
	
	@Query("SELECT c FROM Course c WHERE name LIKE '%100 steps'")
	List<Course> courseWith100StepsInName();

	@Query(value="SELECT * FROM Course c WHERE name LIKE '%100 steps'", nativeQuery=true)
	List<Course> courseWith100StepsInNameUsingNativeQuery();

	@Query(name="query_get_100_courses")
	List<Course> courseWith100StepsInNameUsingNamedQuery();
}
