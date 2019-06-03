package com.pingan.pare.bi.common.common;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 元对象字段填充控制器，实现公共字段填入
 * @author Administrator
 *
 */
@Component
public class StdMetaObjectHandler extends MetaObjectHandler {
	private static final String CREATE_BY="createBy";
	private static final String CREATE_DATE="createDate";
	private static final String UPDATE_BY="updateBy";
	private static final String UPDATE_DATE="updateDate";

	@Override
	public void insertFill(MetaObject metaObject) {
		setFieldValByName(CREATE_BY,"AAA", metaObject);
		setFieldValByName(CREATE_DATE,new Date(), metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		setFieldValByName(UPDATE_BY,"aaa", metaObject);
		setFieldValByName(UPDATE_DATE,new Date(), metaObject);
	}

	public void getCommonMethod(MetaObject metaObject,String by,String time,String newBy) {
		Object updateBy = metaObject.getValue(by);
        Object updateDate = metaObject.getValue(time);
        //获取当前登录用户
        if (null == updateBy) {
            metaObject.setValue(by, newBy);
        }
        if (null == updateDate) {
            metaObject.setValue(time, new Date());
        }
	}
}
