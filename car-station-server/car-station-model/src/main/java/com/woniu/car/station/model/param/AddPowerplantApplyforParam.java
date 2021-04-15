package com.woniu.car.station.model.param;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddPowerplantApplyforParam {
    @NotNull
    @ApiModelProperty(value = "关联电站对应账号id",example = "1")
    private Integer userId;

    @NotNull
    @ApiModelProperty(value = "电站名称",example = "北充晓辉充电站")
    private String powerplanApplyfortName;

    @ApiModelProperty(value = "电站图片")
    private String powerplantApplyforImage;

    @NotNull
    @ApiModelProperty(value = "电站介绍",example = "百年充电老店，始于1923")
    private String powerplantApplyforIntroduce;

    @NotNull
    @ApiModelProperty(value = "电站地址",example = "重庆市江北区红旗河沟地铁站旁")
    private String powerplantApplyforAddress;

    @NotNull
    @ApiModelProperty(value = "电站热线电话",example = "1731658564")
    private String powerplantApplyforPhone;

    @NotNull
    @ApiModelProperty(value = "电站直流电电桩数量",example = "11")
    private Integer powerplantApplyforCocurrentNumber;

    private Integer powerplantApplyforAlternatingNumber;

    @NotNull
    @ApiModelProperty(value = "电站经纬度",example = "17.1")
    private String powerplantCoordinate;

}
