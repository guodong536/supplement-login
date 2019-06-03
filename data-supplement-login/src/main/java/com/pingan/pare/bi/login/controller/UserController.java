package com.pingan.pare.bi.login.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.pingan.pare.bi.common.domain.ResponseData;
import com.pingan.pare.bi.login.domain.po.SupplementUser;
import com.pingan.pare.bi.login.service.SupplementUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Api(description = "用户维护")
public class UserController {

    @Autowired
    private SupplementUserService supplementUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/getList")
    public String getList(){
        String a="";
        return a;
    }

    @PostMapping(value = "/loginIn")
    public ResponseData login(@RequestBody SupplementUser supplementUser){

        Map<String,Object> result=new HashMap<>();
        try {
            EntityWrapper<SupplementUser> ew=new EntityWrapper<>();
            ew.eq("um",supplementUser.getUm());
            List<SupplementUser> list= supplementUserService.selectList(ew);
            if (!CollectionUtils.isEmpty(list)){
                SupplementUser user=list.get(0);
                //缓存数据
                redisTemplate.opsForValue().set(supplementUser.getUm(),list.get(0),1, TimeUnit.MINUTES);
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseData.error(-1,e.getMessage());
        }

        return ResponseData.ok(result);
    }


    @PostMapping(value = "/sso")
    public ResponseData sso(@RequestBody SupplementUser supplementUser){

        Map<String,Object> result=new HashMap<>();
        try {
            String um=supplementUser.getUm();
            boolean flag=false;
            if (!StringUtils.isEmpty(um)){
                //缓存数据
                flag = redisTemplate.hasKey(um);
                System.out.println("flag{}"+flag);
               if(flag){
                   supplementUser=(SupplementUser)  redisTemplate.opsForValue().get(um);
                   System.out.println("user{}"+supplementUser);
               }
            }
            result.put("flag",flag);
            result.put("user",supplementUser);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseData.error(-1,e.getMessage());
        }

        return ResponseData.ok(result);
    }
}
