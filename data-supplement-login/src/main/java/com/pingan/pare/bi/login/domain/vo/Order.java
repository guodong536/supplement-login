package com.pingan.pare.bi.login.domain.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order {

    private String name;

    private Integer sumPrice;

    private Integer sumCount;
}
