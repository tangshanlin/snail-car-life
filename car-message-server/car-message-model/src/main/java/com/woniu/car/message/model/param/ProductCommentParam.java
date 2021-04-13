package com.woniu.car.message.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * <p>
 *  商品评论参数类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "添加商品评论参数信息")
public class ProductCommentParam implements Serializable {
    /**
     * 评论用户id
     */
    @ApiModelProperty(value = "用户编号")
    @NotNull(message="用户编号不能为空")
    private Integer commentUserId;
    /**
     * 评论用户名字
     */
    @ApiModelProperty(value = "用户名字")
    @NotEmpty(message="用户名字不能为空")
    private String commentName;

    /**
     * 商品评论人头像
     */

    private String commentUrl;

    /**
     * 商品评论评分
     */
    @ApiModelProperty(value = "商品评论评分")
    @NotNull(message="商品评论评分不能为空")
    private Integer commentScore;
    /**
     * 商品评论内容
     */
    @ApiModelProperty(value = "商品评论内容")
    @NotEmpty(message="商品评论内容不能为空")
    private String commentWords;
    /**
     * 商品订单编号
     */
    @ApiModelProperty(value = "商品订单编号")
    @NotEmpty(message="商品订单编号不能为空")
    private String commentOrderCode;
    /**
     * 商品评论商品编号
     */
    @ApiModelProperty(value = "商品评论商品编号")
    @NotNull(message="商品评论商品编号不能为空")
    private Integer productCode;

}
