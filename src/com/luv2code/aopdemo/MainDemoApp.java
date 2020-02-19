package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// get the mebmership bean from spring container
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		// call the business method
		Account myAccount = new Account();
		myAccount.setLevel("Pat");
		myAccount.setName("Madu");
		
		theAccountDAO.addAccount(myAccount, true);
		
		theAccountDAO.work();
		
		// call the accountDAO getter/setter methods
		theAccountDAO.getName();
		theAccountDAO.getServiceCode();
		
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");
		
		
		// call the business method 
		theMembershipDAO.addAccount();
		
		theMembershipDAO.goToSleep();

		// close the context
		context.close();
	}

}
