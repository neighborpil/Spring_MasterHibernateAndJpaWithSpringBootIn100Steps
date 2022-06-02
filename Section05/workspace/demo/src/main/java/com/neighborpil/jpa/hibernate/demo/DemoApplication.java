package com.neighborpil.jpa.hibernate.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.neighborpil.jpa.hibernate.demo.entity.Course;
import com.neighborpil.jpa.hibernate.demo.repository.CourseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Course course = repository.findById(10001L);
		log.info("Course details: {}", course);
		
		repository.deleteById(10001L);
		repository.save(new Course("Microservices in 100 steps"));
		repository.playWithEntityManager();
	}
}
