package com.liyang.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot 已经启动内嵌的是tomcat 默认的端口是8080
 * 
 * 
 * @author yangcb
 *http://127.0.0.1:8080/index/张三
 */

//@SpringBootApplication相当于@Configuration、@EnableAutoConfiguration和  @ComponentScan
@SpringBootApplication
public class Application  
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class, args);
    }
}


//http://127.0.0.1:8080/
//@SpringBootApplication  
//@RestController  
//public class Application {  
//  @RequestMapping("/")  
//  public String greeting() {  
//      return "Hello World!";  
//  }  
//
//  public static void main(String[] args) {  
//  	SpringApplication.run(Application.class, args);
//  }  
//}  