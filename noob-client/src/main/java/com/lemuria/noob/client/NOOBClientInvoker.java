package com.lemuria.noob.client;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.Map;

@Data
@Slf4j
public class NOOBClientInvoker implements ApplicationContextAware {
    private ConfigurableApplicationContext configurableApplicationContext;
    private NOOBClientAnnotationResolver noobClientAnnotationResolver;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.configurableApplicationContext = (ConfigurableApplicationContext)applicationContext;
    }
    @PostConstruct
    public void init(){
        invoke();
    }

    private void invoke(){
        Map<String, NOOBClientAnnotationBean> clientAnnotationBeanMap = noobClientAnnotationResolver.getClientAnnotationBeanMap();
        Map<String, Field> fieldMap = noobClientAnnotationResolver.getFieldMap();
        Map<String, Object> beanMap = noobClientAnnotationResolver.getBeanMap();
        //TODO 多个注册中心的问题
        RegistryConfig registryConfig = configurableApplicationContext.getBean(RegistryConfig.class);
        ProtocolConfig protocolConfig = configurableApplicationContext.getBean(ProtocolConfig.class);
        ApplicationConfig applicationConfig = configurableApplicationContext.getBean(ApplicationConfig.class);
        if(null!=clientAnnotationBeanMap&&clientAnnotationBeanMap.size()>0){
            for(Map.Entry<String, NOOBClientAnnotationBean> entry: clientAnnotationBeanMap.entrySet()){
                GenericClientBean genericClientBean = new GenericClientBean(entry.getKey(),
                        entry.getValue(), registryConfig, protocolConfig, applicationConfig);
                Field field = fieldMap.get(entry.getKey());
                ReflectionUtils.makeAccessible(field);
                try {
                    field.set(beanMap.get(entry.getKey()), genericClientBean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
