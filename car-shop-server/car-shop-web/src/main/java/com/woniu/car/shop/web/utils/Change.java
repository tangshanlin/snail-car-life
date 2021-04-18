package com.woniu.car.shop.web.utils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/17/0:14
 * @Description:
 */
public class Change {
    public static String[] stringChangeArray(String s,String value){
        return s.split(value);
    }

    public static String arrayChangeString(String[] s,String value){
        return String.join(value,s);
    }
}
