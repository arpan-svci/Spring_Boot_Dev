package com.luv2code.AOPDemo.aspect;

import com.luv2code.AOPDemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class MyDemoLoggingAspect {
    // this is where we add all of our related advices for logging

    // let's start with an @Before advice

    @Pointcut("execution(* com.luv2code.AOPDemo.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("execution(* com.luv2code.AOPDemo.service.*.*(..))")
    private void forServicePackage(){}

    @Around("forServicePackage()")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // print out method we are advising on
        String method=proceedingJoinPoint.getSignature().toString();
        System.out.println("\n=====>>> Executing @Around on method: "+method);
        // get begin timestamp
        long begin=System.currentTimeMillis();
        // now, let's execute the method
        Object result=null;
        try{
            result=proceedingJoinPoint.proceed();
        }
        catch (Exception exception){
            // log the exception
            System.out.println(exception.getMessage());
            // give user a custom message
            result= "Major accident! But no worries, your private AOP helicopter is on the way!";
        }
        // get end timestamp
        long end=System.currentTimeMillis();
        // compute duration and display it
        long duration=end-begin;
        System.out.println("\n======>>>> Duration: "+duration/1000.0+" seconds");
        return result;
    }
    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint){
        System.out.println("\n========>>>>> Executing @Before advice on addAccount()");

        //display the method signature
        MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();
        System.out.println("Method signature: "+methodSignature);

        //display the method arguments

        //get args
        Object[] args=joinPoint.getArgs();
        //loop through args
        for (Object tempArg:args){
            System.out.println(tempArg);
        }
    }

    @AfterReturning(pointcut = "forDaoPackage()",returning = "result")
    public void afterReturning(JoinPoint joinPoint, List<Account> result){
        String method=joinPoint.getSignature().toString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: "+method);
        System.out.println(result);

        //lets post-process the data .. let's modify it

        //convert the account names to uppercase
        convertAccountNamesToUpperCase(result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        // loop through accounts
        for(Account account: result){
            // get uppercase version of name
            String theUpperName=account.getName().toUpperCase();
            // update the name on the account
            account.setName(theUpperName);
        }
    }

    @After("forDaoPackage()")
    public void afterFinallyFindAccountAdvices(JoinPoint joinPoint){
        // point out which method we ar advising on
        String method=joinPoint.getSignature().toString();
        System.out.println("\n=====>>> Executing @After(finally) on method: "+method);
    }
    @AfterThrowing(pointcut = "forDaoPackage()",throwing = "exception")
    public void afterThrowingFindAccountAdvice(JoinPoint joinPoint,Throwable exception){
        // point out which method we ar advising on
        String method=joinPoint.getSignature().toString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: "+method);
        // log the exception
        System.out.println("\n=====>>>The exception is: "+exception);
    }
}
