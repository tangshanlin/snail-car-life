package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName AddScoreParam
 * @Desc 新增积分的参数
 * @Author Administrator
 * @Date 2021/4/9 15:28
 * @Version 1.0
 */
@Data
@ApiModel(value = "添加积分的参数")
public class AddScoreParam {

    //积分变化数
    @ApiModelProperty(value = "积分变化数")
    @NotNull
    private Integer scoreNumber;
    //积分变化时间
    @ApiModelProperty(value = "积分变化事件")
    @NotNull
    private String scoreChange;
    //积分变化类型（1消费获得2兑换积分消耗）
    @ApiModelProperty(value = "积分变化类型1消费获得2兑换消费券消耗")
    @NotNull
    private Integer ScoreChangeType;


}
