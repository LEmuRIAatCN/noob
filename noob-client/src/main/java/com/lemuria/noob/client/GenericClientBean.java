package com.lemuria.noob.client;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

@Data
@Slf4j
public class GenericClientBean implements GenericClient {
    private NOOBClientAnnotationBean noobClientAnnotationBean;
    private String clientName;
    private ReferenceConfig<GenericService> genericServiceReferenceConfig;
    private GenericService genericService;
    public GenericClientBean(String clientName, NOOBClientAnnotationBean noobClientAnnotationBean,
                             RegistryConfig registryConfig, ProtocolConfig protocolConfig,
                             ApplicationConfig applicationConfig){
        this.clientName = clientName;
        this.noobClientAnnotationBean = noobClientAnnotationBean;
        genericServiceReferenceConfig = new ReferenceConfig<>();
        genericServiceReferenceConfig.setInterface(clientName);
        genericServiceReferenceConfig.setRegistry(registryConfig);
        genericServiceReferenceConfig.setApplication(applicationConfig);
        genericServiceReferenceConfig.setProtocol(protocolConfig.getName());
        genericServiceReferenceConfig.setGeneric(true);
        genericServiceReferenceConfig.setCheck(false);
        genericService = genericServiceReferenceConfig.get();
    }
    @Override
    public Object invoke(String[] parameterTypes, Object[] args) {
        return genericService.$invoke(clientName, parameterTypes, args);
    }
}
