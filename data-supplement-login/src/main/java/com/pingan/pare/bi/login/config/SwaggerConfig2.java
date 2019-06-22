package com.pingan.pare.bi.login.config;

import com.pingan.pare.bi.common.config.SwaggerConfig;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig2 extends SwaggerConfig {

	//@Value("${login.server.port}")
	private String port="9002";

	//@Value("${login.server.servlet.context-path}")
	private String path="/data-supplement/login";

	private String array="com.pingan.pare.bi.login.controller";

    public SwaggerConfig2() {
        super.setPort(port);
        super.setPath(path);
        super.setArray(array);
    }
}
