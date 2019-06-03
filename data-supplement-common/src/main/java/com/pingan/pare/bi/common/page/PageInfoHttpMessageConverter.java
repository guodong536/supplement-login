package com.pingan.pare.bi.common.page;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.pingan.pare.bi.common.domain.PageInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

public class PageInfoHttpMessageConverter extends FastJsonHttpMessageConverter {

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return readType(getType(type, contextClass), inputMessage);
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return readType(getType(clazz,null),inputMessage);
    }

    private Object readType(Type type,HttpInputMessage message){

        try {
            InputStream in= message.getBody();
            String body= IOUtils.toString(in, Charset.forName("UTF-8"));
            if (type.getTypeName().contains("List")){
                return JSON.parseObject(body,type,getFastJsonConfig().getFeatures());
            }

            PageInfo pageInfo = JSON.parseObject(body,PageInfo.class,getFastJsonConfig().getFeatures());
            if(pageInfo !=null){
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                requestAttributes.setAttribute(PageInfo.REQUEST_ATTRIBUTE,JSON.toJSONString(pageInfo),RequestAttributes.SCOPE_REQUEST);
            }
            return JSON.parseObject(body,type,getFastJsonConfig().getFeatures());
        } catch (JSONException e){
            throw new HttpMessageNotReadableException("JSON parse error:"+e.getMessage(),e);
        } catch (Exception e){
            throw new HttpMessageNotReadableException("I/O parse error:"+e.getMessage(),e);
        }
    }
}
