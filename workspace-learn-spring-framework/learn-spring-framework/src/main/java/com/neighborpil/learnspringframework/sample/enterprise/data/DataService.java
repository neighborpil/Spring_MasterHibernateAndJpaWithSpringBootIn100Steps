package com.neighborpil.learnspringframework.sample.enterprise.data;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

// Getting data
@Component
public class DataService {
	public List<Integer> retrieveDate() {
		return Arrays.asList(12, 34, 56, 78, 90);
	}
}