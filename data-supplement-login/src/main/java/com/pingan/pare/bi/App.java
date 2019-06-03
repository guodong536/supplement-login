package com.pingan.pare.bi;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableApolloConfig
@ComponentScan(value = {"com.pingan.pare.bi.login","com.pingan.pare.bi.common.config",
        "com.pingan.pare.bi.common.domain",
        "com.pingan.pare.bi.common.common"})
public class App 
{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
