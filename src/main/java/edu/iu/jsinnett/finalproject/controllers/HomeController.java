package edu.iu.jsinnett.finalproject.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController implements ErrorController {
    @GetMapping("/")
    public String greetings()
    {
        return "Welcome to the flower service!!";
    }



}
