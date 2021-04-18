package com.woniu.car.user.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName SelectCarinforamtionParam
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/14 15:17
 * @Version 1.0
 */
@Data
@ApiModel(value = "查询车系的参数")
public class SelectCarinforamtionParam {
    @ApiModelProperty(value = "车系id", example = "82")
    @NotNull(message = "参数不能为空")
    private Integer carseriesId;
}