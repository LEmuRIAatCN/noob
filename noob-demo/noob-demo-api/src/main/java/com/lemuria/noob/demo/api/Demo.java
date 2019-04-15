package com.lemuria.noob.demo.api;

import org.springframework.ui.ModelMap;

import java.util.Map;

public interface Demo {
    ModelMap test1(String s);
    ModelMap test2(String s, Map<String, Object> map);
}
