package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	// add a new advice for @AfterReturning on the findAccounts method
	@AfterReturning(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", returning="result")
	public void afterReturningFindAccounts(JoinPoint theJoinPoint, List<Account> result) {

		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n===> Excuting @AfterReturning on method: " + method);
		System.out.println("\n===> Result is : " + result);

		// modify "result" list, before return the data
		
	}
	
	
	
	// before order is undefined, has no order
	// let's start with and @Before advice
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("\n====> Executing @Before advice on addAccount()");
		
		// display the method signature
		MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
		// display method arguments
		System.out.println("Method: " + methodSignature);
		
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		for (Object tempArg: args) {
			System.out.println(tempArg);
			
			if (tempArg instanceof Account) {
				Account theAccount = (Account) tempArg; // downCast
				
				System.out.println("account name: " + theAccount.getName());
				System.out.println("account level: " + theAccount.getLevel());	
			}
		}
		
		
	}

}
