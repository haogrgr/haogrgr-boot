package com.haogrgr.boot.web.controller;

import com.haogrgr.boot.dal.mapper.TestMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
public class TestController {

    @Resource
    private TestMapper testMapper;

    @GetMapping("/hello")
    public Object hello() {
        HashMap<String, String> map = new HashMap<>();
        map.put("hello", "world" + testMapper.selectAll());
        return map;
    }

}
