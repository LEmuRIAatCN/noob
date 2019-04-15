package com.lemuria.noob.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NOOB {
    String protocol() default "dubbo";

    String value();

    //TODO more properties on the way

}
