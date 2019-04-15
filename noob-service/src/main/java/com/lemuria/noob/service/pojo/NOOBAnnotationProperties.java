package com.lemuria.noob.service.pojo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Data
@Slf4j
public class NOOBAnnotationProperties {
    private String noobValue;
    private String noobProtocol;
    private Properties properties;
    public NOOBAnnotationProperties(String noobValue, String noobProtocol, Properties properties){
        this.noobValue = noobValue;
        this.noobProtocol = noobProtocol;
        this.properties = properties;
    }
}
