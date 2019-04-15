package com.lemuria.noob.service;

import com.lemuria.noob.service.pojo.NOOBAnnotationBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Map;

@Data
@Slf4j
public class NOOBAnnotationExporter implements ApplicationContextAware {
    private NOOBAnnotationResolver noobAnnotationResolver;
    private ConfigurableApplicationContext configurableApplicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    private void export(){
        Map<String, NOOBAnnotationBean> map = noobAnnotationResolver.getNoobAnnotationBeanMap();
        GenericService genericService = (GenericService)configurableApplicationContext.getBeanFactory().getBean("genericService");
        RegistryConfig registryConfig = configurableApplicationContext.getBean(RegistryConfig.class);
        ProtocolConfig protocolConfig = configurableApplicationContext.getBean(ProtocolConfig.class);
        ApplicationConfig applicationConfig = configurableApplicationContext.getBean(ApplicationConfig.class);
        for(Map.Entry<String, NOOBAnnotationBean> entry: map.entrySet()){
            ServiceConfig<GenericService> sc = new ServiceConfig<>();
            sc.setRegistry(registryConfig);
            sc.setProtocol(protocolConfig);
            sc.setApplication(applicationConfig);
            sc.setRef(genericService);
            sc.setGeneric("true");
            sc.setInterface(entry.getKey());
            sc.export();
        }
    }
    @PostConstruct
    public void init(){
        export();
    }
}
