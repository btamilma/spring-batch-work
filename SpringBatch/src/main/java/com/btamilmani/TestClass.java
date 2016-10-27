package com.btamilmani;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class TestClass {

	@PostConstruct
	public void testMethod(){
		System.out.println("from post construct of TestClass");
	}
}
