package com.fairy.base.common.utils;

import java.lang.reflect.Field;

/**
 * @author huanglulu
 * @version 1.0
 * @date 2022/4/2 20:59
 */
public class RefleshUtil {

    // 给定一个类，构造出一个对象。
    public Object create(Class clazz) throws Exception {
        // 构造出对象
        Object obj = clazz.newInstance();
        return obj;
    }

    /**
     * @param obj 对象
     * @param propertyName 字段名称
     * @param value 值
     */
    public void setProperty(Object obj, String propertyName, Object value) {
        //获取字节码对象
        Class clazz = obj.getClass();
        //暴力反射获取字段  propertyName 字段名
        Field field = null;
        try {
            field = clazz.getDeclaredField(propertyName);
            //设置访问权限
            field.setAccessible(true);
            //设置值
            field.set(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String firstUpper(String property) {
        return ("" + property.charAt(0)).toUpperCase() + property.substring(1);
    }

    public Object getFiled(Object obj, String propertyName) {
        Class clazz = obj.getClass();
        try {
            Object value = clazz.getMethod("get" + firstUpper(propertyName)).invoke(obj);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    public Object getProperty(Object obj, String propertyName) {
        Class clazz = obj.getClass();
        Field field = null;
        Object o = null;
        try {
            field = clazz.getDeclaredField(propertyName);
            field.setAccessible(true);
            o = field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }


}
