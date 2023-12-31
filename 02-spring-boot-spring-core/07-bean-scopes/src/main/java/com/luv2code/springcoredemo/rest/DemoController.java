package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    // define a private field for dependency
    private Coach myCoach;
    private Coach anotherCoach;

    // define constructor for dependency injection
     @Autowired
     public DemoController(@Qualifier("cricketCoach") Coach theCoach,@Qualifier("cricketCoach") Coach theAnotherCoach){
         System.out.println("in constructor : "+getClass().getSimpleName());
         myCoach=theCoach;
         anotherCoach=theAnotherCoach;
     }
//    @Autowired
//    public void setCoach(Coach theCoach){
//        myCoach=theCoach;
//    }
    @GetMapping(value = "/daily")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check(){
         return "compare beans: myCoach == anotherCoach , "+ (myCoach==anotherCoach);
    }
}
