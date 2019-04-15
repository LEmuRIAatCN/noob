package com.lemuria.noob.client;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Data
@Slf4j
public class NOOBClientAnnotationProperties {
    private String clientName;
    private String protocol;
    private Properties properties;
    public NOOBClientAnnotationProperties(String clientName, String protocol, Properties properties){
        this.clientName = clientName;
        this.protocol = protocol;
        this.properties = properties;
    }
}
