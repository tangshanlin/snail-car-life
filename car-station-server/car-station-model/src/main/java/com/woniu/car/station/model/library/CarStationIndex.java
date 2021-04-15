package com.woniu.car.station.model.library;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * Copyright (C), 2021, 温天宇
 *
 * @author WTY
 * Date: 2021/4/14 16:39
 * FileName: CarStationIndex
 */
@Data
@Document(indexName = "car_station_index")
public class CarStationIndex implements Serializable {
    /**
     * @Id表示id属性
     * @Field:定义属性字段
     * analyzer:是否加入中文分词 ik_smart粗分 ik_max_word细分
     * type指定属性类型 text 中文分词  integer整形  keyword 直接作为关键词
     *
     */

    @Id
    private Integer powerplantId;

    @Field(name = "userId",type = FieldType.Integer)
    private Integer userId;


    @Field(name = "productName", analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String powerplanName;

    @ApiModelProperty(value = "电站图片")
    private String powerplantImage;

    @ApiModelProperty(value = "电站介绍")
    private String powerplantIntroduce;

    @ApiModelProperty(value = "电站地址")
    private String powerplantAddress;

    @ApiModelProperty(value = "电站热线电话")
    private String powerplantPhone;

    @ApiModelProperty(value = "电站直流电电桩数量")
    private Integer powerplantCocurrentNumber;

    @ApiModelProperty(value = "电站交流电电桩数量")
    private Integer powerplantAlternatingNumber;

    @ApiModelProperty(value = "电站经纬度")
    private String powerplantCoordinate;

    @ApiModelProperty(value = "电站申请审核状态0未审核 1审核通过 2审核未通过")
    private Integer powerplantApplyforStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "删除状态 0未删除 1已删除")
    @TableLogic
    private Integer deleted;
}
