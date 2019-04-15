package com.lemuria.noob.client;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Slf4j
public class NOOBClientAnnotationResolver implements BeanPostProcessor, Ordered {
    //TODO 这里map比较多
    private transient volatile Map<String, NOOBClientAnnotationBean> clientAnnotationBeanMap = new ConcurrentHashMap<>();
    private transient volatile Map<String, Field> fieldMap = new ConcurrentHashMap<>();
    private transient volatile Map<String, Object> beanMap = new ConcurrentHashMap<>();
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        if(fields!=null&&fields.length>0){
            for(Field field:fields){
                NOOBClient noobClient = field.getAnnotation(NOOBClient.class);
                if(noobClient!=null){
                    String value = noobClient.value();
                    NOOBClientAnnotationBean noobClientAnnotationBean =
                            new NOOBClientAnnotationBean(noobClient.value(), field, beanName,
                                    //TODO 处理下properties
                                    new NOOBClientAnnotationProperties(value, noobClient.protocol(), new Properties()));
                    clientAnnotationBeanMap.put(value, noobClientAnnotationBean);
                    fieldMap.put(value, field);
                    beanMap.put(value, bean);
                }
            }
        }
        return bean;
    }
}
