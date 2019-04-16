package com.lemuria.noob.service;

import com.lemuria.noob.service.pojo.NOOBAnnotationBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.reflect.InvocationTargetException;
@Data
@Slf4j
public class NOOBGenericService implements GenericService {
    private NOOBAnnotationResolver noobAnnotationResolver;

    @Override
    public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
        NOOBAnnotationBean noobAnnotationBean = noobAnnotationResolver.getNoobAnnotationBeanMap().get(method);
        Object hostBean = noobAnnotationResolver.getConfigurableApplicationContext().getBean(noobAnnotationBean.getHostBeanName());
        Object result = "";
        try {
            result = noobAnnotationBean.getHostMethod().invoke(hostBean, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
}
