package com.luv2code.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {

	public String getFortune() {

		// simulate a delay
		try {
			TimeUnit.SECONDS.sleep(5);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// return a fortune
		return "Expect Heavy Traffic";
	}

	public String getFortune(boolean tripWire) {
		if (tripWire) {
			throw new RuntimeException("Major accident! Highway is closed!");
		}
		
		// return a fortune
		return getFortune();
	}
}
