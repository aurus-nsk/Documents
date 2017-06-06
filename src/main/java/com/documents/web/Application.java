package com.documents.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;

@SpringBootApplication
@ComponentScan("com.documents.web")
public class Application extends AsyncConfigurerSupport {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}