package com.liyang.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot 已经启动内嵌的是tomcat 默认的端口是8080
 * 
 * 
 * @author yangcb
 *
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
