package com.obitosnn.util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.Cookie;
import java.util.Map;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/14 16:54
 */
public class WebUtils {

    public static <T> T copyParamToBean(T bean, Map map) {
        try {
            System.out.println("注入属性之前");
            System.out.println(bean);
            BeanUtils.populate(bean, map);
            System.out.println("注入属性之后");
            System.out.println(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串类型的数据转换成int型数据
     * @param name
     * @param defaultValue
     * @return 转换不成功返回defaultValue
     */
    public static int parseInt(String name, int defaultValue) {
        try {
            return Integer.parseInt(name);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
    public static Cookie findCookie(String cookieName, Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }
        return null;
    }

}
