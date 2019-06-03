package com.pingan.pare.bi.login.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class SysMenuVo {

    private String url;

    private String menuNo;

    private String menuName;

    private List<SysRoleVo> roles;
}
