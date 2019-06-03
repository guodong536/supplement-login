package com.pingan.pare.bi.datasupplementanalysis.temp.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.pingan.pare.bi.common.domain.PageInfo;
import com.pingan.pare.bi.common.domain.ResponseData;
import com.pingan.pare.bi.datasupplementanalysis.temp.dao.monodb.BugetTemplateDao;
import com.pingan.pare.bi.datasupplementanalysis.temp.domain.po.BugetTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(description = "测试")
@RequestMapping("/democ")
@RestController
@Slf4j
public class DemoController {

    @Autowired
    private BugetTemplateDao bugetTemplateDao;

    @PostMapping(value = {"/save"})
    @ApiOperation(value = "保存数据", notes = "根据参数保存记录")
    public Map<String, Object> save(@RequestBody BugetTemplate bugetTemplate) {

        Map<String, Object> result = new HashMap<>();

        try {
            if(bugetTemplate !=null){
                bugetTemplateDao.save(bugetTemplate);
                result.put("user",bugetTemplate);
            }
            result.put("ret", 0);
        } catch (Exception e) {
            result.put("ret", -1);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @PostMapping(value = {"/getList"})
    @ApiOperation(value = "查询数据列表", notes = "根据参数获取记录")
    public ResponseData save(@RequestBody BugetTemplate bugetTemplate, @ApiIgnore PageInfo pageInfo) {

        Map<String, Object> result = new HashMap<>();
        int start =pageInfo.getCurrent();
        int end =pageInfo.getPageSize();
        Page<BugetTemplate> page =new Page<>(start,end);
        try {
            if(bugetTemplate !=null){

                List<BugetTemplate> list= bugetTemplateDao.getPage(bugetTemplate,(start-1)*end,end);
                page.setRecords(list);
                page.setTotal(bugetTemplateDao.getCount(bugetTemplate));
            }
            result.put("ret", 0);
        } catch (Exception e) {
            return ResponseData.error(-1,e.getMessage());
        }
        return ResponseData.ok(page);
    }
}
