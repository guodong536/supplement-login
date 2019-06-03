package com.pingan.pare.bi.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum ResultInfoEnum {

    request_param_error(10000,"请求参数异常！");

    @Setter
    @Getter
    private int code;

    @Setter
    @Getter
    private String msg;
}
