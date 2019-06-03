package com.pingan.pare.bi.datasupplementanalysis.temp.controller;



import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.pingan.pare.bi.common.domain.PageInfo;
import com.pingan.pare.bi.datasupplementanalysis.temp.domain.po.SupplementUser;
import com.pingan.pare.bi.datasupplementanalysis.temp.service.SupplementUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author GUODONG536
 * @since 2019-05-17
 */
@RestController
@RequestMapping("/supplementUser")
@Slf4j
@Api(description = "数据解析管理")
public class SupplementUserController {

    @Autowired
    private SupplementUserService supplementUserService;

    @PostMapping(value = {"/config/getValue"})
    @ApiOperation(value = "查询数据列表", notes = "根据参数查询数据")
    public Map<String, Object> getValue(@RequestBody SupplementUser supplementUser,@ApiIgnore PageInfo pageInfo) {

        Map<String, Object> result = new HashMap<>();

        try {
            EntityWrapper<SupplementUser> ew=new EntityWrapper<>();

            ew.eq("um","guodong536");
            result.put("resList", supplementUserService.selectList(ew));
            result.put("ret", 0);
        } catch (Exception e) {
            result.put("ret", -1);
            result.put("msg", e.getMessage());
        }

        return result;
    }

    @PostMapping(value = {"/config/addValue"})
    @ApiOperation(value = "新增数据", notes = "根据参数添加数据记录")
    public Map<String, Object> addValue(@RequestBody SupplementUser supplementUser) {

        Map<String, Object> result = new HashMap<>();

        try {
            if(supplementUser !=null){
                supplementUser.setUm("002");
                supplementUser.setStatus("1");
                supplementUserService.insert(supplementUser);
            }
            result.put("ret", 0);
        } catch (Exception e) {
            result.put("ret", -1);
            result.put("msg", e.getMessage());
        }

        return result;
    }

    @PostMapping(value = {"/deleteOrUpdate"})
    @ApiOperation(value = "更新或者删除数据", notes = "根据参数更新或删除数据记录")
    public Map<String, Object> deleteOrUpdate(@RequestBody SupplementUser supplementUser) {

        Map<String, Object> result = new HashMap<>();

        try {
            if(supplementUser !=null){
                String um= supplementUser.getUm();
                if(!StringUtils.isEmpty(um)){
                    String[] umArray =um.split("-");
                    List<SupplementUser> sysList=new ArrayList<>();
                    List<String> list = Arrays.asList(umArray);
                    SupplementUser user=new SupplementUser();
                    user.setStatus("0");
                    EntityWrapper<SupplementUser> ew=new EntityWrapper<>();
                    ew.in("um",list);
                    supplementUserService.update(user,ew);
                }
            }
            result.put("ret", 0);
        } catch (Exception e) {
            result.put("ret", -1);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    //@Autowired
    //private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/send")
    public String send(@RequestParam String msg ){
        //kafkaTemplate.send("test1", msg);
        return "success";
    }
}

