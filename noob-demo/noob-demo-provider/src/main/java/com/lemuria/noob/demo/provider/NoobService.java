package com.lemuria.noob.demo.provider;

import com.lemuria.noob.service.NOOB;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Map;

@Service
public class NoobService {
    private String test1 = "111";
    @NOOB("ns1")
    public ModelMap ns1(String s){
        ModelMap mm = new ModelMap();
        mm.put("data", s);
        mm.put("msg", "success");
        return mm;
    }
    @NOOB("ns2")
    public ModelMap ns2(String s, Map<String, Object> map){
        ModelMap mm = new ModelMap();
        mm.put("data", s);
        mm.put("datamap", map);
        mm.put("msg", "success");
        return mm;
    }
}
