package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class TanisCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volly";
    }
}
