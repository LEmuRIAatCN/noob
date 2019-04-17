package com.lemuria.noob.demo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
@PropertySource("classpath:dubbo.properties")
public class Consumer {
    public static void main(String[] rag){
        SpringApplication.run(Consumer.class);
    }
}
