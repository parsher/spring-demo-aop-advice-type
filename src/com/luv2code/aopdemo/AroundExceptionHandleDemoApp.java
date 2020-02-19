package com.luv2code.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.service.TrafficFortuneService;

public class AroundExceptionHandleDemoApp {

	// For sync print log, add my Logger : not important
	private static Logger myLogger = Logger.getLogger(AroundExceptionHandleDemoApp.class.getName());

	public static void main(String[] args) {
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean from spring container
		TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService",
				TrafficFortuneService.class);

		myLogger.info("\nMain Program: Around Demo App");
		boolean tripWire = true;
		String data = trafficFortuneService.getFortune(tripWire);

		myLogger.info("\nFortune:" + data);
		myLogger.info("\nFinished");

		// close the context
		context.close();
	}

}
