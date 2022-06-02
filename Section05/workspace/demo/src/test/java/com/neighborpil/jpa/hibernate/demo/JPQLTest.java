package com.neighborpil.jpa.hibernate.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.neighborpil.jpa.hibernate.demo.entity.Course;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes=DemoApplication.class)
class JPQLTest {

	@Autowired
	EntityManager em;
	
	@Test
	void jpql_basic() {
//		Query createQuery = em.createQuery("SELECT c FROM Course c");
		TypedQuery<Course> createNamedQuery = em.createNamedQuery("query_get_all_courses", Course.class);
		List resultList = createNamedQuery.getResultList();
		log.info("SELECT c FROM course c -> {}", resultList);
	}

	@Test
	void jpql_typed() {
//		TypedQuery<Course> createQuery = em.createQuery("SELECT c FROM Course c", Course.class);
		TypedQuery<Course> createNamedQuery = em.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = createNamedQuery.getResultList();
		log.info("SELECT c FROM course c -> {}", resultList);
	}
	
	@Test
	void jpql_where() {
		TypedQuery<Course> createQuery = em.createNamedQuery("query_get_100_courses", Course.class);
		List<Course> resultList = createQuery.getResultList();
		log.info("SELECT c FROM course c WHERE name like '%100 steps' -> {}", resultList);
	}
}
