package com.woniu.car.message.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * <p>
 *  电站标签查询条件类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
@ApiModel(value = "电站标签名字参数信息")
public class StationTagNameLookCommentParam implements Serializable {

    /**
     * 标签名字
     */
    @ApiModelProperty(value = "标签名字")
    @NotEmpty(message="标签名字不能为空")
    private String tagName;
    /**
     * 电站编号
     */
    @ApiModelProperty(value = "电站编号",example = "1")
    @NotNull(message="电站编号不能为空")
    private Integer commentPowerCode;

}
