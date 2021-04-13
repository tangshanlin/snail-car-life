package com.woniu.car.message.web.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZYY
 * @since 2021-04-10
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("t_good_product")
@ApiModel(value="GoodProduct对象", description="")
public class GoodProduct implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "商品好评统计id")
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "商品编号")
      private Integer productId;

      @ApiModelProperty(value = "商品好评数量")
      private Integer productGoodNum;

      @ApiModelProperty(value = "商品总数量")
      private Integer productNums;

  @TableLogic
  private Integer deleted;


}
