package com.neighborpil.leanrspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @SpringBootConfiguration
// @EnableAutoConfiguration
// @ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
// 		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public class LeanrSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeanrSpringBootApplication.class, args);
	}

}
