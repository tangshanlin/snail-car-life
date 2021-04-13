package com.woniu.car.commons.web.util;


import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


/**
 * @Author Lints
 * @Date 2021/4/6/006 13:11
 * @Description 类与类之间的属性赋值
 * @Since version-1.0
 */
public class BeanCopyUtil extends BeanUtils {

    /**
     * @Author Lints
     * @Date 2021/4/6/006 13:11
     * @Description 针对list集合
     * @Since version-1.0
     */

    public static <S,T> List<T> copyList(List<S> source, Supplier<T> target){
        List<T> list=new ArrayList<>(source.size());
        for(S s:source){
            T t=target.get();
            copyProperties(s,t);
            list.add(t);
        }
        return list;
    }

    /**
     * @Author Lints
     * @Date 2021/4/6/006 13:11
     * @Description 针对单个对象
     * @Since version-1.0
     */
    public static <S,T> T copyOne(S source, Supplier<T> target){
        T t=target.get();
        copyProperties(source,t);
        return t;
    }

}
