package com.luv2code.AOPDemo;

import com.luv2code.AOPDemo.dao.AccountDAO;
import com.luv2code.AOPDemo.dao.MembershipDAO;
import com.luv2code.AOPDemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO,
											   MembershipDAO membershipDAO,
											   TrafficFortuneService trafficFortuneService){
		return runner->{
			//demoTheBeforeAdvice(accountDAO,membershipDAO);
			//demoAfterReturningAdvice(accountDAO);
			//demoTheAfterThrowingAdvice(accountDAO);
			//demoTheAfterAdvice(accountDAO);
			//demoTheAroundAdvice(trafficFortuneService);
			demoTheAroundAdviceHandleException(trafficFortuneService);
		};
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) throws InterruptedException {
		System.out.println("\nMain Program: demoTheAroundAdvice");
		System.out.println("calling getFortune()");
		boolean tripWire=true;
		String data=trafficFortuneService.getFortune(tripWire);
		System.out.println("\nMy fortune is:"+data);
		System.out.println("Finished");
	}

	private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) throws InterruptedException {
		System.out.println("\nMain Program: demoTheAroundAdvice");
		System.out.println("calling getFortune()");
		String data=trafficFortuneService.getFortune();
		System.out.println("\nMy fortune is:"+data);
		System.out.println("Finished");
	}

	private void demoTheAfterAdvice(AccountDAO accountDAO) {
		// call the method to find the accounts
		List<Account> theAccounts=null;
		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire= false;
			theAccounts=accountDAO.findAccounts(tripWire);
		}
		catch (Exception exception){
			System.out.println("\n\nMain Program: caught exception"+exception);
		}
		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("========");
		System.out.println(theAccounts);
		System.out.println("========");
	}

	private void demoTheAfterThrowingAdvice(AccountDAO accountDAO) {
		// call the method to find the accounts
		List<Account> theAccounts=null;
		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire= true;
			theAccounts=accountDAO.findAccounts(tripWire);
		}
		catch (Exception exception){
			System.out.println("\n\nMain Program: caught exception"+exception);
		}
		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("========");
		System.out.println(theAccounts);
		System.out.println("========");
	}

	private void demoAfterReturningAdvice(AccountDAO accountDAO) {
		// call the method to find the accounts
		List<Account> theAccounts=accountDAO.findAccounts();

		// display the accounts
		System.out.println("\n\nMain Program: demoAfterReturningAdvice");
		System.out.println("========");
		System.out.println(theAccounts);
		System.out.println("========");
	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO,MembershipDAO membershipDAO) {
		// call the business method
		accountDAO.addAccount(new Account("Custom","savings"));
		membershipDAO.addSillyMember();
	}

}
