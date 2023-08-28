package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    // setup logger
    private Logger logger=Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void controllerPackage(){}

    // do the same for service and dao package
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void servicePackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void daoPackage(){}

    @Pointcut("controllerPackage() || servicePackage() || daoPackage()")
    private void forAppFlow(){}


    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        // display method we are calling
        String theMethod=joinPoint.getSignature().toString();
        logger.info("=====>>>>> in @Before: calling method: "+theMethod);
        // display the arguments to the method

        // get the arguments
        Object[] args=joinPoint.getArgs();

        // loop through and display
        for (Object o:args){
            logger.info("=====>>>>> arguments: "+o);
        }
    }

    @AfterReturning(pointcut = "forAppFlow()",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        // display method we are returning from
        String theMethod=joinPoint.getSignature().toString();
        logger.info("=====>>>>> in @AfterReturning: calling method: "+theMethod);
        // display data returned
        logger.info("=====>>>>> result: "+result);
    }
}
