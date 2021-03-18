package com.codegym.demospringboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class CategoryController {

    @GetMapping("category")
    public ModelAndView showAll(){
        ModelAndView index = new ModelAndView("category");
        return index;
    }
    @GetMapping("home")
    public ModelAndView home(){
        ModelAndView index = new ModelAndView("trangchu");
        return index;
    }
}
