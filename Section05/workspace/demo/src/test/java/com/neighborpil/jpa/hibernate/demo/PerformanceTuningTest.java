package com.neighborpil.jpa.hibernate.demo;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.neighborpil.jpa.hibernate.demo.entity.Course;
import com.neighborpil.jpa.hibernate.demo.repository.CourseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes=DemoApplication.class)
class PerformanceTuningTest {

	@Autowired
	CourseRepository repository;

	@Autowired
	EntityManager em;
	
	@Test
	@Transactional // N+1 문제 케이스
	void creatingNPlusOneProblem() {
		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
		
		for(Course course : courses) { 
			log.info("Course -> {} Students -> {}", course, course.getStudents());
		}
			
	}
		
	@Test
	@Transactional
	void solvingNPlusOneProblem_EntityGraph() { // hint를 이용한 n+1 문제 해결 방법
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class); // default는 lazy fetch이지만 loadgraph를 설정하여 eager fetch로 바꿀 수 있다
		Subgraph<Object> subgraph = entityGraph.addSubgraph("students");
		
		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
				.setHint("javax.persistence.loadgraph", entityGraph)
				.getResultList();
		
		for(Course course : courses) { 
			log.info("Course -> {} Students -> {}", course, course.getStudents());
		}
			
	}

	@Test
	@Transactional
	void solvingNPlusOneProblem_JOinFetch() { // named query의 join을 이용한 n+1 문제 해결 방법
		List<Course> courses = em.createNamedQuery("query_get_all_courses_join_fetch", Course.class).getResultList();
		
		for(Course course : courses) { 
			log.info("Course -> {} Students -> {}", course, course.getStudents());
		}
			
	}
}
