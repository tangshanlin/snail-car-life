package com.woniu.car.message.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 *  服务标签查询条件类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
@ApiModel(value = "服务标签名字参数信息")
public class ServiceTagNameLookCommentParam implements Serializable {

    /**
     * 标签名字
     */
    @ApiModelProperty(value = "标签名字")
    @NotNull(message="标签名字不能为空")
    private String tagName;
    /**
     * 服务编号
     */
    @ApiModelProperty(value = "服务编号",example = "1")
    @NotNull(message="服务编号不能为空")
    private Integer serviceCode;

}
