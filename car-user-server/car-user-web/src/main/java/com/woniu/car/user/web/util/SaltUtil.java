package com.woniu.car.user.web.util;

import java.util.Random;

public class SaltUtil {
    public static String getSalt(Integer count){
        char[] bytes="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm;'[]!@#$%&*()".toCharArray();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<count;i++){
            char a=bytes[new Random().nextInt(bytes.length)];
            sb.append(a);
        }
        return sb.toString();
    }
}
