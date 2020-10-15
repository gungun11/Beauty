package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {
    /**
     * 把map的数据直接注入到Bean属性中
     * @param bean
     * @param params
     */
    public static <T> T copyParamToBean(T bean, Map params){
        try {
            BeanUtils.populate(bean,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  bean;
    }

    /**
     * 转换字符串为integer类型
     * @return
     */
    public static Integer parseInt(String intStr , Integer defaultValue){
        try {
            return Integer.parseInt(intStr);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }


}
