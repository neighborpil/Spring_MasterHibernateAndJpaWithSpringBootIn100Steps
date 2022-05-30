package com.neighborpil.leanrspringboot.courses.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neighborpil.leanrspringboot.courses.bean.Course;

@RestController
public class CourseController {

	// http://localhost:8080/courses
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return Arrays.asList(new Course(1, "Learn Microservices" ,"in28minutes"),
				new Course(2, "Learn Microservices 2" ,"in28minute2"));
		
	}
	
	// http://localhost:8080/courses/1
	@GetMapping("/courses/1")
	public Course getCourseDetails() {
		return new Course(1, "Learn Microservices11" ,"in28minutes");
	}
}
