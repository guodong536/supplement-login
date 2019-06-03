package com.pingan.pare.bi.common.page;

import com.alibaba.fastjson.JSON;
import com.pingan.pare.bi.common.domain.PageInfo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PageInfoArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return PageInfo.class.equals(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String body= (String) RequestContextHolder.getRequestAttributes().getAttribute(PageInfo.REQUEST_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        PageInfo result= JSON.parseObject(body,PageInfo.class);
        if(result == null){
            result = new PageInfo();
        }
        return result;
    }
}
