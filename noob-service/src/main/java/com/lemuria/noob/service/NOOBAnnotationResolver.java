package com.lemuria.noob.service;

import com.lemuria.noob.service.pojo.NOOBAnnotationBean;
import com.lemuria.noob.service.pojo.NOOBAnnotationProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Data
public class NOOBAnnotationResolver implements ApplicationContextAware, BeanPostProcessor, Ordered {
    private ConfigurableApplicationContext configurableApplicationContext;
    private transient volatile Map<String, NOOBAnnotationBean> noobAnnotationBeanMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 1;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getDeclaredMethods();
        if (methods != null) {
            if (methods.length > 0) {
                for (Method method : methods) {
                    NOOB noob = method.getAnnotation(NOOB.class);
                    if (null != noob) {
                        noobAnnotationBeanMap.put(noob.value(),
                                new NOOBAnnotationBean(noob.value(), beanName, method,
                                        new NOOBAnnotationProperties(noob.value(), noob.protocol(),
                                                //TODO properties还需要处理
                                                new Properties())));
                    }
                }
            }
        }
        return bean;
    }
}
