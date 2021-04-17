package com.woniu.car.station.model.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @ClassName UpdatePowerplantParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/9 11:44
 * @Version 1.0
 */
@Data
public class UpdatePowerplantParam {

    @ApiModelProperty(value = "电站id",example = "1")
    private Integer powerplantId;

    @ApiModelProperty(value = "电站名称",example = "湖南山川充电站")
    private String powerplanName;

    @ApiModelProperty(value = "电站图片,可以为空")
    private MultipartFile[] powerplantImage;

    @ApiModelProperty(value = "电站介绍",example = "随时可充")
    private String powerplantIntroduce;

    @ApiModelProperty(value = "电站地址",example = "重庆市南岸区")
    private String powerplantAddress;

    @Size(max = 11,min = 8)
    @ApiModelProperty(value = "电站热线电话",example = "15654865355")
    private String powerplantPhone;

    @ApiModelProperty(value = "电站直流电电桩数量",example = "20")
    private Integer powerplantCocurrentNumber;

    @ApiModelProperty(value = "电站交流电电桩数量",example = "21")
    private Integer powerplantAlternatingNumber;

    @ApiModelProperty(value = "电站经纬度",example = "11.1")
    private String powerplantCoordinate;
}
