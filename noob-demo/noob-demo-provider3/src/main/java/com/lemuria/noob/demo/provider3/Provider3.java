package com.lemuria.noob.demo.provider3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class Provider3 {
    private static final String RES_KEY = "com.lemuria.noob.demo.api.Demo:test1(java.lang.String)";

    public static void main(String[] args){
        SpringApplication.run(Provider3.class);
    }

}
