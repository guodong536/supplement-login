package com.pingan.pare.bi.login.domain.po;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.pingan.pare.bi.login.domain.vo.SysMenuVo;
import com.pingan.pare.bi.login.domain.vo.SysRoleVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author GUODONG536
 * @since 2019-05-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bi_supplement_user")
public class SupplementUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * um账户
     */
    private String um;
    /**
     * 状态
     */
    private String status;

    @Transient
    private List<SysRoleVo> roleList;

    @Transient
    private List<SysMenuVo> menuList;
}
