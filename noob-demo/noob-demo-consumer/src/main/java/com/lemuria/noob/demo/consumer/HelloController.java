package com.lemuria.noob.demo.consumer;

import com.lemuria.noob.client.GenericClient;
import com.lemuria.noob.client.NOOBClient;
import com.lemuria.noob.demo.api.AsyncDemo;
import com.lemuria.noob.demo.api.Demo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Data
@Slf4j
@Controller
@RequestMapping("/hello")
@ResponseBody
public class HelloController {
    @Autowired
    private Demo demo;
    @NOOBClient("ns1")
    private GenericClient noob1;
    @NOOBClient("ns2")
    private GenericClient noob2;
    @Autowired
    private AsyncDemo asyncDemo;
    @Resource(name= "asyncConsume")
    private AsyncDemo asyncConsume;
    @RequestMapping("/hello1")
    public ModelMap hello1(){
        log.info("asdfasdf");
        demo.test1("asdfddf");
        ModelMap mm = new ModelMap();
        mm.put("data", "这里是数据");
        mm.put("msg", "沃尔沃二");
        return mm;
    }

    @RequestMapping("/hello2")
    public ModelMap hello2(){
        log.info("async");
        ModelMap mm = new ModelMap();
        mm.put("data", "这里是数据");
        mm.put("msg", "沃尔沃二");
        asyncDemo.test1("async");
        return mm;
    }

    @RequestMapping("/hello3")
    public ModelMap hello3(){
        log.info("async consume");
        final ModelMap mm = new ModelMap();
        mm.put("data", "这里是数据");
        CompletableFuture<ModelMap> future = asyncConsume.test1("async");
        future.whenComplete((M,T)->{
            log.info("异步消费结果："+M);
        });
        log.info("异步消费结果延迟获得，因为是异步消费");
        return mm;
    }
}
