package com.woniu.car.shop.web.domain;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/17/16:16
 * @Description:
 */
@Data
@Document(indexName = "woniucarshop")
public class EsShopWoniuCar {
    @Field(name = "shopId",type = FieldType.Text)
    private Integer shopId;//门店id

    @Field(name = "shopName",type = FieldType.Text)
    private String shopName;//门店名称

    @Field(name = "shopImage",type = FieldType.Text)
    private String shopImage;//门店图片

    @GeoPointField
    private GeoPoint location;//经纬度坐标

    @Field(name = "shopAddress",type = FieldType.Text,analyzer = "ik_max_word")
    private String shopAddress;//门店地址

    @Field(name = "shopOrderNumber",type = FieldType.Integer)
    private Integer shopOrderNumber;//总订单数（根据总成交单数计算）

    @Field(name = "shopGrade",type = FieldType.Double)
    private Double shopGrade;//总评分（从成交总评分/总成交数）

    @Field(name = "shopTag",type = FieldType.Text)
    private String shopTag;//门店标签（json）
}
