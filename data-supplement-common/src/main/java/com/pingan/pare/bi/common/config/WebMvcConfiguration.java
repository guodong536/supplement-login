package com.pingan.pare.bi.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.pingan.pare.bi.common.page.PageInfoArgumentResolver;
import com.pingan.pare.bi.common.page.PageInfoHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 注册PageInfoArgumentResolver的参数解析器
        argumentResolvers.add(new PageInfoArgumentResolver());
    }

    /**
     * 消息内容转换配置
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        //调用父类的配置
        super.configureMessageConverters(converters);
        PageInfoHttpMessageConverter c= new PageInfoHttpMessageConverter();
        List<MediaType> supportedMediaType= new ArrayList<>();
        supportedMediaType.add(MediaType.APPLICATION_JSON);
        supportedMediaType.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaType.add(MediaType.TEXT_PLAIN);
        supportedMediaType.add(MediaType.APPLICATION_OCTET_STREAM);
        c.setSupportedMediaTypes(supportedMediaType);

        //创建配置类
        FastJsonHttpMessageConverter converter =new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig =new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                //保留map空的字段
                SerializerFeature.WriteMapNullValue,

                SerializerFeature.PrettyFormat
        );

        fastJsonConfig.setDateFormat("yyyy-MM-dd");
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        List<MediaType> mediaTypeList =new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypeList);
        c.setFastJsonConfig(fastJsonConfig);
        converters.add(c);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    //@Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true);
        resolver.setMaxInMemorySize(20*1024*1024);
        resolver.setMaxUploadSize(20*1024*1024);
        return resolver;
    }

}
