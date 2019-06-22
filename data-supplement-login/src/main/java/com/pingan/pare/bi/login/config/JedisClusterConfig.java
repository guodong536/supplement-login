package com.pingan.pare.bi.login.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

//@Configuration
@Slf4j
public class JedisClusterConfig  {

    private String nodes="192.168.171.1:6379";

    //@Bean(value = "jedisCluster")
    public JedisCluster jedisCluster(){
        log.info("---------getJedisCluster-----");
        String[] serverArray = nodes.split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        try{
            for (String ipPort : serverArray) {
                String[] ipPortPair = ipPort.split(":");
                log.info("----ipPortPair[0]----------"+ipPortPair[0]+"----ipPortPair[1]----------"+ipPortPair[1]);
                nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
            }
        }catch (Exception e){
            log.info("-------------exception--------222----------");
        }
        return new JedisCluster(nodes, 3000, 3000, 100, "", new GenericObjectPoolConfig());
    }
}
