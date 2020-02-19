package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public int addAccount() {
		System.out.println(getClass() + " : DOING STUFF: ADDING A MEMEBERSHIP ACCOUNT");
		return 1;
	}
	
	public void goToSleep() {
		System.out.println(getClass() + ": I'm going to sleep now!");
	}
}
