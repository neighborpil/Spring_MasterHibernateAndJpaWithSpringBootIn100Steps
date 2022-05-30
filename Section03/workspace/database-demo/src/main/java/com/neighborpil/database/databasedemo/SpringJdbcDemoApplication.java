package com.neighborpil.database.databasedemo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.neighborpil.database.databasedemo.entity.Person;
import com.neighborpil.database.databasedemo.jdbc.PersonJdbcDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @SpringBootApplication
public class SpringJdbcDemoApplication implements CommandLineRunner {

	@Autowired
	PersonJdbcDAO dao;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("All users: {}", dao.findAll());
		log.info("User id 10001: {}", dao.findById(10001));
		log.info("Deleting 10002: {}", dao.deleteById(10002));
		log.info("Inserting 10004: {}", dao.insert(new Person(10004, "Peter", "Berlin", new Date())));
		log.info("Update 10003: {}", dao.update(new Person(10003, "Peter222", "Berlin444", new Date())));
	}

}
