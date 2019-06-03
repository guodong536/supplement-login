package com.pingan.pare.bi.datasupplementanalysis;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableApolloConfig
@ComponentScan(value = {"com.pingan.pare.bi.datasupplementanalysis.temp",
        "com.pingan.pare.bi.common.config",
        "com.pingan.pare.bi.common.domain",
        "com.pingan.pare.bi.common.common"})
public class DataSupplementAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSupplementAnalysisApplication.class, args);
    }

}
