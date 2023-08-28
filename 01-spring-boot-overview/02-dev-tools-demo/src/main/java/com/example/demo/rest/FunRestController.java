package com.example.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    //expose a / end point
    @GetMapping("/")
    public String sayHello(){
        return "hello world";
    }
    //expose a new endpoint for workout
    @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Run a hard 5k!";
    }
    @GetMapping("/working")
    public String setDailyWork(){
        return "run";
    }
}
