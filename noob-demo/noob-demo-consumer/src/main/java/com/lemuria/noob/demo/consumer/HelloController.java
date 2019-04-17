package com.lemuria.noob.demo.consumer;

import com.lemuria.noob.client.GenericClient;
import com.lemuria.noob.client.NOOBClient;
import com.lemuria.noob.demo.api.Demo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@Slf4j
@Controller
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private Demo demo;
    @NOOBClient("ns1")
    private GenericClient noob1;
    @NOOBClient("ns2")
    private GenericClient noob2;
    @RequestMapping("/hello1")
    public ModelMap hello1(){
        log.info("asdfasdf");
        ModelMap mm = new ModelMap();
        mm.put("data", "这里是数据");
        mm.put("msg", "沃尔沃二");
        return mm;
    }
}
