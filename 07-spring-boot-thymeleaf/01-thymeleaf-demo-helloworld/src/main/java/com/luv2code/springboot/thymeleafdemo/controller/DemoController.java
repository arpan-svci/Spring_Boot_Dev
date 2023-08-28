package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class DemoController {
    // create a mapping for '/hello'
    /*
    @GetMapping("/hello")
    public ModelAndView sayHello(){
        ModelAndView mav= new ModelAndView("helloworld");
        mav.addObject("theDate",new Date());
        return mav;
    }*/
    @GetMapping("/hello")
    public String sayHello(Model theModel){
        theModel.addAttribute("theDate",new Date());
        return "helloworld";
    }
}
