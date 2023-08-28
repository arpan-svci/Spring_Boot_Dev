package com.luv2code.AOPDemo.service;

public interface TrafficFortuneService {
    String getFortune() throws InterruptedException;

    String getFortune(boolean tripWire);
}
