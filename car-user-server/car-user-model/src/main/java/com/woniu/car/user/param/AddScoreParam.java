package com.woniu.car.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AddScoreParam
 * @Desc 新增积分的参数
 * @Author Administrator
 * @Date 2021/4/9 15:28
 * @Version 1.0
 */
@Data
public class AddScoreParam {

    //积分变化数
    @ApiModelProperty(value = "积分变化数")
    private Integer scoreNumber;
    //积分变化时间
    @ApiModelProperty(value = "积分变化事件")
    private String scoreChange;
    //积分变化类型（1消费获得2兑换积分消耗）
    @ApiModelProperty(value = "积分变化类型1消费获得2兑换消费券消耗")
    private Integer ScoreChangeType;


}
