package com.neighborpil.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neighborpil.jpa.hibernate.demo.entity.Employee;
import com.neighborpil.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.neighborpil.jpa.hibernate.demo.entity.PartTimeEmployee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional
public class EmployeeRepository {

	@Autowired
	EntityManager em;
	
	// insert an employee
	public void insert(Employee employee) {
		em.persist(employee);
	}
	
	// retrieve all employees
	public List<Employee> retrieveAllEmployees() {
		// if inheritance strategy is table per class, query will union the inherited tables
		return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
		
		/*
		
		select
        employee0_.id as id1_1_,
        employee0_.fullname as fullname2_1_,
        employee0_.salary as salary1_2_,
        employee0_.hourly_wage as hourly_w1_3_,
        employee0_.clazz_ as clazz_ 
    from
        ( select
            id,
            fullname,
            salary,
            null as hourly_wage,
            1 as clazz_ 
        from
            full_time_employee 
        union
        all select
            id,
            fullname,
            null as salary,
            hourly_wage,
            2 as clazz_ 
        from
            part_time_employee 
    ) employee0_
		
		*/
		
	}

	
	public List<PartTimeEmployee> retrievePartTimeEmployees() {
		// if inheritance strategy is table per class, query will union the inherited tables
		return em.createQuery("SELECT e FROM PartTimeEmployee e", PartTimeEmployee.class).getResultList();
		
	
	}

	public List<FullTimeEmployee> retrieveFullTimeEmployees() {
		// if inheritance strategy is table per class, query will union the inherited tables
		return em.createQuery("SELECT e FROM FullTimeEmployee e", FullTimeEmployee.class).getResultList();
	}
}
