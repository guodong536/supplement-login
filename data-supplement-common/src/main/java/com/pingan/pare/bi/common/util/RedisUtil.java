package com.pingan.pare.bi.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis操作工具类
 */
@Component
public class RedisUtil {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    /**
     * 制定缓存失效时间
     */
    public boolean expire(String key,long time){
        try {
            if(time>0){
                redisTemplate.expire(key,time, TimeUnit.MINUTES);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key获取过期时间
     * 返回0代表永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.MINUTES);
    }

    /**
     * 判断key是否存在
     * true 存在，false-不存在
     */
    public boolean haskey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 放入普通缓存，并且设置时间
     * true-成功，false-失败
     * time>0,如果<=0，意思为设置无限期
     */
    public boolean set(String key,Object value,long time){
        try {
            if(time>0){
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.MINUTES);
            }else{
                redisTemplate.opsForValue().set(key,value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 放入缓存，设置时间
     */
    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * key 可以为一个或者多个
     */
    public void delete(String ... key){
        if(key !=null && key.length>0){
            if (key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 获取key对应的value
     */
    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    /**
     * 递增
     */
    public long incr(String key,long delta){
        if (delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }

    /**
     * 递减
     */
    public long decr(String key,long delta){
        if (delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,-delta);
    }

    /**
     * 获取指定区间的值
     * key
     * start 开始
     * end 结束 0 到 -1代表所有的值
     */
    public List<Map<String,String>> getListRange(String key,long start,long end){
        try {
            return redisTemplate.opsForList().range(key,start,end);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 以集合方式向右添加元素
     */
    public Long setListRightPushAll(String key,List<Map<String,Object>> value,Long time){
        try {
            Long count=redisTemplate.opsForList().rightPushAll(key,value);
            if (time>0){
                expire(key,time);
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return -1L;
        }
    }
}
