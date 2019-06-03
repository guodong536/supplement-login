package com.pingan.pare.bi.datasupplementanalysis.temp.config;

import com.pingan.pare.bi.common.config.SwaggerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerLogin extends SwaggerConfig {


	@Value("${server.port}")
    private String port;

	@Value("${server.servlet.context-path}")
    private String path;

	private String array="com.pingan.pare.bi.datasupplementanalysis.temp.controller";

    public SwaggerLogin() {
        super.setArray(array);
        super.setPort(port);
        super.setPath(path);
    }
}
