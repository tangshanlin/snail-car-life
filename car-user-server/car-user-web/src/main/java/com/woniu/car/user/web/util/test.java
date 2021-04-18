package com.woniu.car.user.web.util;

import org.elasticsearch.search.sort.MinAndMax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

/**
 * @ClassName test
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/17 16:32
 * @Version 1.0
 */
public class test {
    @Autowired
    private static ElasticsearchOperations operations;
    public static void main(String[] args) {
        operations.delete("car_information");
        operations.delete("carbrand");
        operations.delete("carseries");

    }
}
