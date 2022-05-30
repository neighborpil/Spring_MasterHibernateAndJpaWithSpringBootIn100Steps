package com.neighborpil.database.databasedemo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.neighborpil.database.databasedemo.entity.Person;
import com.neighborpil.database.databasedemo.jdbc.PersonJdbcDAO;
import com.neighborpil.database.databasedemo.jpa.PersonJpaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	PersonJpaRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("All users: {}", repository.findAll());
		log.info("User id 10001: {}", repository.findById(10001));
		log.info("Inserting : {}", repository.insert(new Person("Peter", "Berlin", new Date())));
		log.info("Update 10003: {}", repository.update(new Person(10003, "Peter222", "Berlin444", new Date())));
		repository.deleteById(10002);
	}

}
