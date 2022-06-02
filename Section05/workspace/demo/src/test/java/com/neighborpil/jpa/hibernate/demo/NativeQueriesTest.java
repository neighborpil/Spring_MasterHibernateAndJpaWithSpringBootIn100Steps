package com.neighborpil.jpa.hibernate.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.neighborpil.jpa.hibernate.demo.entity.Course;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes=DemoApplication.class)
class NativeQueriesTest {

	@Autowired
	EntityManager em;
	
	@Test
	void native_queries_basic() {
		Query query = em.createNativeQuery("SELECT * FROM course", Course.class);
		List resultList = query.getResultList();
		log.info("SELECT * FROM course -> {}", resultList);
	}
	
	@Test
	void native_queries_with_parameters() {
		Query query = em.createNativeQuery("SELECT * FROM course WHERE id = ?", Course.class);
		query.setParameter(1, 10002L);
		List resultList = query.getResultList();
		log.info("SELECT * FROM course -> {}", resultList);
	}

	@Test
	void native_queries_with_positional_parameters() {
		Query query = em.createNativeQuery("SELECT * FROM course WHERE id = :id", Course.class);
		query.setParameter("id", 10002L);
		List resultList = query.getResultList();
		log.info("SELECT * FROM course -> {}", resultList);
	}
	
	@Test
	@Transactional
	void native_queries_to_update() {
		Query query = em.createNativeQuery("UPDATE course SET last_updated)date = current_timestampe()", Course.class);
		int noOfRowsUpdated = query.executeUpdate();
		log.info("noOfRowsUpdated -> {}", noOfRowsUpdated);
	}

}
