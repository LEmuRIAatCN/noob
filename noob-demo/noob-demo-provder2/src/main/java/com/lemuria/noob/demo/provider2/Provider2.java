package com.lemuria.noob.demo.provider2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class Provider2 {
    private static final String RES_KEY = "com.lemuria.noob.demo.api.Demo:test1(java.lang.String)";

    public static void main(String[] args){
        SpringApplication.run(Provider2.class);
    }

}
