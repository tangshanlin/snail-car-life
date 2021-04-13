package com.woniu.car.items.model.dto;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class CarServiceDto implements Serializable {

    @ApiModelProperty(value = "要修改的具体服务id")
    private Integer carServiceId;

    @ApiModelProperty(value = "服务名称")
    private String carServiceName;

    @ApiModelProperty(value = "服务价格")
    private BigDecimal carServicePrice;

    @ApiModelProperty(value = "服务图片")
    private MultipartFile[] carServiceImage;

    @ApiModelProperty(value = "服务介绍")
    private String carServiceIntroduce;

    @ApiModelProperty(value = "服务详情")
    private MultipartFile[] carServiceInfo;

    @ApiModelProperty(value = "适用车型")
    private String carServiceType;

}
