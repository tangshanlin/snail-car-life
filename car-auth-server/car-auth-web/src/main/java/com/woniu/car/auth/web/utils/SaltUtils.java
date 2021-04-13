package com.woniu.car.auth.web.utils;

import java.util.Random;

public class SaltUtils {
    public static String getSalt(Integer count){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i< count ;i++){
            char achar = chars[new Random().nextInt(chars.length)];
            sb.append(achar);

        }
        return sb.toString();
    }
}
