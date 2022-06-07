package com.neighborpil.learnspringframework.sample.enterprise.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neighborpil.learnspringframework.sample.enterprise.data.DataService;

// Business logic
@Component
public class BusinessService {
	
	@Autowired
	private DataService dataService;

	public long calculateSum() {
		List<Integer> data = dataService.retrieveDate();
		return data.stream().reduce(Integer::sum).get();
	}
	
}