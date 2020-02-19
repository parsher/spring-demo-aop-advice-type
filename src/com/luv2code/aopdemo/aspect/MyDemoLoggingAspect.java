package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		System.out.println("\n===> Excuting @After (finally) on method: " + method);
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// let's execute the method
		Object result = theProceedingJoinPoint.proceed();
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		long duration = end - begin;
		System.out.println("\n===> Duration:" + duration / 1000.0 + "sec");
		
		
		return result;
	}
	
	// success or fail, always trigger this., before @AfterThrowing or @AfterReturning
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n===> Excuting @After (finally) on method: " + method);
	}
	
	@AfterThrowing(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", throwing="theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		
		// print out which method we get.
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n===> Excuting @AfterThrowing on method: " + method);
		// log the exception
		
		System.out.println("\n===> The exception is: " + theExc);
	}
	
	
	// add a new advice for @AfterReturning on the findAccounts method
	@AfterReturning(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", returning="result")
	public void afterReturningFindAccounts(JoinPoint theJoinPoint, List<Account> result) {

		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n===> Excuting @AfterReturning on method: " + method);
		System.out.println("\n===> Result is : " + result);

		// modify "result" list, before return the data
		// let's post-process the data ... let's modify it 
		
		// convert the account names to uppercase
		convertAccountNamesToUpperCase(result);
	}
	
	
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		// loop through accounts
		for (Account tempAccount: result) {
			// get uppercase version of name
			String theUpperName = tempAccount.getName().toUpperCase();
			tempAccount.setName(theUpperName);
		}
		
		// update the name on the account
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
