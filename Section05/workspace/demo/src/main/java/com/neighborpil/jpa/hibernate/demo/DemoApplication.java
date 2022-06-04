package com.neighborpil.jpa.hibernate.demo;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.neighborpil.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.neighborpil.jpa.hibernate.demo.entity.PartTimeEmployee;
import com.neighborpil.jpa.hibernate.demo.repository.CourseRepository;
import com.neighborpil.jpa.hibernate.demo.repository.EmployeeRepository;
import com.neighborpil.jpa.hibernate.demo.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		 Student student = new Student("Jack");
//		 Course course = new Course("Microservices in 100 steps");
//		 studentRepository.insertStudentAndCourse(student, course);
	
		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));
		
//		log.info("All Employees -> {}", employeeRepository.retrieveAllEmployees());
		log.info("PartTime Employees -> {}", employeeRepository.retrievePartTimeEmployees());
		log.info("FullTime Employees -> {}", employeeRepository.retrieveFullTimeEmployees());
		
	}
}
