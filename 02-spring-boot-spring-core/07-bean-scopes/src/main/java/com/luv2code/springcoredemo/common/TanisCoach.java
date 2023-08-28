package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class TanisCoach implements Coach{
    public TanisCoach() {
        System.out.println("in constructor : "+getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}
