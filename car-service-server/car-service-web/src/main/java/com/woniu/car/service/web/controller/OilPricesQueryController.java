package com.woniu.car.service.web.controller;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.service.web.util.OilPricesQueryUtil;
import io.swagger.annotations.Api;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OilPricesQueryController
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/16 9:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/query")
@Api(tags = "今日油价查询接口")
public class OilPricesQueryController {

    @PostMapping("/query_oil_by_place")
    public String queryOilByPlace(){
        String s = null;
        try {
            s = OilPricesQueryUtil.OilPricesQuseryByPlace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

}
