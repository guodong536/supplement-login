package com.pingan.pare.bi.datasupplementanalysis.temp.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 */
public class ReflectionUtil {

    /**
     * 获取对象属性返回的字符串数组
     */
    public static String[] getFiledName(Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames= new String[fields.length];
        for (int i = 0; i < fieldNames.length; i++) {
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }

    //通过属性名获取属性值
    public static Object getObjByName(String key,Object o){
        Object obj =null;
        try {
            //根据某一属性名称获取域
            Field field=o.getClass().getDeclaredField(key);
            //设置可访问
            field.setAccessible(true);
            PropertyDescriptor propertyDescriptor =new PropertyDescriptor(field.getName(),o.getClass());
            //获取属性名对应的方法名称
            Method method =propertyDescriptor.getReadMethod();
            //获取属性值
            obj =method.invoke(o);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return obj;
    }
}
