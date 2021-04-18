package com.woniu.car.message.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
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
     * 商品评论评分
     */

    @NotNull(message = "商品评论评分不能为空")
    @Range(min = 1, max = 5, message = "该值的取值范围为1-5之间")
    @ApiModelProperty(value = "商品评论评分",example = "1")
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
    @ApiModelProperty(value = "商品评论商品编号",example = "1")
    @NotNull(message="商品评论商品编号不能为空")
    private Integer productCode;

}
