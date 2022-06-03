package com.neighborpil.jpa.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.neighborpil.jpa.hibernate.demo.entity.Review;
import com.neighborpil.jpa.hibernate.demo.repository.CourseRepository;
import com.neighborpil.jpa.hibernate.demo.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// studentRepository.saveStudentWithPassport();
		// courseRepository.addHardcodedReviewsForCourse();
		
		List<Review> reviews = new ArrayList<>();
		reviews.add(new Review("5", "Greate Hands-on Stuff."));
		reviews.add(new Review("2", "Hands-on"));
		courseRepository.addReviewsForCourse(10003L, reviews);
	
	}
}
