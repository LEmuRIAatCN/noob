package com.lemuria.noob.demo.provider;

import com.lemuria.noob.demo.api.Demo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;

import java.util.Map;
@Slf4j
public class DemoImpl implements Demo {
    @Override
    public ModelMap test1(String s) {
        ModelMap mm = new ModelMap();
        log.info("hahaha");
        mm.put("msg", "success");
        mm.put("data", s);
        return mm;
    }

    @Override
    public ModelMap test2(String s, Map<String, Object> map) {
        ModelMap mm = new ModelMap();
        mm.put("msg", "success");
        mm.put("data", s);
        mm.put("mapdata", map);
        return mm;
    }
}
