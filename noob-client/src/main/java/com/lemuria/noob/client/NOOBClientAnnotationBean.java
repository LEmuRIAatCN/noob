package com.lemuria.noob.client;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Data
@Slf4j
public class NOOBClientAnnotationBean {
    private String clientName;
    private Field clientHostField;
    private String clientHostBeanName;
    private NOOBClientAnnotationProperties noobClientAnnotationProperties;
    public NOOBClientAnnotationBean(String clientName, Field clientHostField, String clientHostBeanName, NOOBClientAnnotationProperties noobClientAnnotationProperties){
        this.clientName = clientName;
        this.clientHostField = clientHostField;
        this.clientHostBeanName = clientHostBeanName;
        this.noobClientAnnotationProperties = noobClientAnnotationProperties;
    }
}
