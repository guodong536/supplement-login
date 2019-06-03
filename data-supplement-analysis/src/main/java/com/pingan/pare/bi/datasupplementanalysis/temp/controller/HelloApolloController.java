package com.pingan.pare.bi.datasupplementanalysis.temp.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
@Slf4j
@Api(description = "测试案例")
public class HelloApolloController {

    @Value("${apollo.hello}")
    private String str;

    @Value("${umcase_url_key}")
    private String umcase_url_key;

    @GetMapping(value = {"/apollo/hello"})
    public String getHello(){

        log.debug("str="+str);
        System.out.println("str="+str);
        return "str="+str;
    }

    @GetMapping(value = {"/config/getValue"})
    public String getValue(){
        log.debug("str="+umcase_url_key);
        System.out.println("str="+umcase_url_key);
        return "str="+umcase_url_key;
    }
}
