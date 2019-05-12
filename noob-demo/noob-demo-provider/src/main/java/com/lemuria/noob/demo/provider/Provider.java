package com.lemuria.noob.demo.provider;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.Collections;

@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class Provider {
    private static final String RES_KEY = "com.lemuria.noob.demo.api.Demo:test1(java.lang.String)";

    public static void main(String[] args){
        initFlowRule();
        SpringApplication.run(Provider.class);
    }
    private static void initFlowRule() {
        FlowRule flowRule = new FlowRule();
        flowRule.setResource(RES_KEY);
        flowRule.setCount(10);
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setLimitApp("default");
        FlowRuleManager.loadRules(Collections.singletonList(flowRule));
    }
}
