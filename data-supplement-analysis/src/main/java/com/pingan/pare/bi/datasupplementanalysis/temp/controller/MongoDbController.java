package com.pingan.pare.bi.datasupplementanalysis.temp.controller;

import com.pingan.pare.bi.datasupplementanalysis.temp.domain.dto.UserDemoDto;
import com.pingan.pare.bi.datasupplementanalysis.temp.domain.po.UserDemo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mongodb")
@Api(description = "mongdb管理")
@Slf4j
public class MongoDbController {

    @Autowired
    private UserDemoDto userDemoDto;

    @PostMapping(value = {"/addValue"})
    @ApiOperation(value = "新增数据", notes = "根据参数添加数据记录")
    public Map<String, Object> addValue(@RequestBody UserDemo userDemo) {

        Map<String, Object> result = new HashMap<>();

        try {
            if(userDemoDto !=null){
                userDemoDto.addUser(userDemo);
            }
            result.put("ret", 0);
        } catch (Exception e) {
            result.put("ret", -1);
            result.put("msg", e.getMessage());
        }

        return result;
    }

    @PostMapping(value = {"/getValue"})
    @ApiOperation(value = "获取数据", notes = "根据参数获取记录")
    public Map<String, Object> getValue(@RequestBody UserDemo userDemo) {

        Map<String, Object> result = new HashMap<>();

        try {
            if(userDemoDto !=null){
                UserDemo user=userDemoDto.findTestByName(userDemo.getName());
                result.put("user",user);
            }
            result.put("ret", 0);
        } catch (Exception e) {
            result.put("ret", -1);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @PostMapping(value = {"/updateValue"})
    @ApiOperation(value = "修改数据", notes = "根据参数修改记录")
    public Map<String, Object> updateValue(@RequestBody UserDemo userDemo) {

        Map<String, Object> result = new HashMap<>();

        try {
            if(userDemoDto !=null){
                userDemoDto.updateTest(userDemo);
            }
            result.put("ret", 0);
        } catch (Exception e) {
            result.put("ret", -1);
            result.put("msg", e.getMessage());
        }

        return result;
    }

    @PostMapping(value = {"/deleteValue"})
    @ApiOperation(value = "删除数据", notes = "根据参数删除记录")
    public Map<String, Object> deleteValue(@RequestBody UserDemo userDemo) {

        Map<String, Object> result = new HashMap<>();

        try {
            if(userDemoDto !=null){
                userDemoDto.deleteTestById(1);
            }
            result.put("ret", 0);
        } catch (Exception e) {
            result.put("ret", -1);
            result.put("msg", e.getMessage());
        }

        return result;
    }
}
