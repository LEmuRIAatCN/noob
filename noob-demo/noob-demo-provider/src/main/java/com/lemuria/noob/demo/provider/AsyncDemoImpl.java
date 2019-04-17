package com.lemuria.noob.demo.provider;

import com.lemuria.noob.demo.api.AsyncDemo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.ui.ModelMap;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
@Data
@Slf4j
public class AsyncDemoImpl implements AsyncDemo {
    @Override
    public CompletableFuture<ModelMap> test1(String s) {
        RpcContext context = RpcContext.getContext();
        return CompletableFuture.supplyAsync(()->{

            for(Map.Entry<String, String> entry: context.getAttachments().entrySet()){
                log.info("attachment is :{}={}",entry.getKey(),entry.getValue());
            }
            ModelMap mm = new ModelMap();
            mm.put("data", "!!!");
            mm.put("msg", "@@@");
            return mm;
        });
    }
}
