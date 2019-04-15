package com.lemuria.noob.service.pojo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
@Data
public class NOOBAnnotationBean {
    private String noobValue;
    private String hostBeanName;
    private Method hostMethod;
    private NOOBAnnotationProperties noobAnnotationProperties;
    public NOOBAnnotationBean(String noobValue, String hostBeanName, Method hostMethod, NOOBAnnotationProperties noobAnnotationProperties){
        this.noobValue = noobValue;
        this.hostBeanName = hostBeanName;
        this.hostMethod = hostMethod;
        this.noobAnnotationProperties = noobAnnotationProperties;
    }
}
