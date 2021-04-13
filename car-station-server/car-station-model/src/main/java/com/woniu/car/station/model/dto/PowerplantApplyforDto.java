package com.woniu.car.station.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PowerplantApplyforDto {
    @TableId(value = "powerplant_applyfor_id")
    private Integer powerplantApplyforId;

    @ApiModelProperty(value = "关联电站对应账号id")
    private Integer userId;

    @ApiModelProperty(value = "电站名称")
    private String powerplanApplyfortName;

    @ApiModelProperty(value = "电站图片")
    private MultipartFile[] powerplantApplyforImage;

    @ApiModelProperty(value = "电站介绍")
    private String powerplantApplyforIntroduce;

    @ApiModelProperty(value = "电站地址")
    private String powerplantApplyforAddress;

    @ApiModelProperty(value = "电站热线电话")
    private String powerplantApplyforPhone;

    @ApiModelProperty(value = "电站直流电电桩数量")
    private Integer powerplantApplyforCocurrentNumber;

    private Integer powerplantApplyforAlternatingNumber;

    @ApiModelProperty(value = "电站经纬度")
    private String powerplantCoordinate;

    @ApiModelProperty(value = "电站申请审核状态0未审核 1审核通过 2审核未通过")
    private Integer powerplantApplyforStatus;
}
