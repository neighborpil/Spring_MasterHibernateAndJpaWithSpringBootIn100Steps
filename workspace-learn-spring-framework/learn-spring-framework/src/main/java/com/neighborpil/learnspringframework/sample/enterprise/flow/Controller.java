package com.neighborpil.learnspringframework.sample.enterprise.flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neighborpil.learnspringframework.sample.enterprise.business.BusinessService;

// sending response in the right format
@RestController
public class Controller {
	
	@Autowired
	private BusinessService businessService;

	// "/sum" => 100
	@GetMapping("/sum")
	public long displaySum() {
		return businessService.calculateSum();
	}
	
}



