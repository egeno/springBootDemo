package com.liyang.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

  //http://127.0.0.1:8080/
@SpringBootApplication  
@RestController  
public class Application {  
    @RequestMapping("/")  
    public String greeting() {  
        return "Hello World!";  
    }  
  
    public static void main(String[] args) {  
    	SpringApplication.run(Application.class, args);
    }  
}  