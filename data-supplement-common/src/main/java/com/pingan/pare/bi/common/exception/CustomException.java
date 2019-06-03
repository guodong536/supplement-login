package com.pingan.pare.bi.common.exception;

import com.pingan.pare.bi.common.util.ResultInfoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomException extends RuntimeException{

    private int errCode;

    private String errMsg;

    public CustomException(ResultInfoEnum resultInfoEnum){
        this(resultInfoEnum.getCode(),resultInfoEnum.getMsg());
    }
}
