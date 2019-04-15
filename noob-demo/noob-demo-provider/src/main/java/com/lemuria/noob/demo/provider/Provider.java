package com.lemuria.noob.demo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class Provider {
    public static void main(String[] args){
        SpringApplication.run(Provider.class);
    }
}
