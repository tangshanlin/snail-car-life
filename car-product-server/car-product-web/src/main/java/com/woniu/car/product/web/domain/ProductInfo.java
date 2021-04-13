package com.woniu.car.product.web.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @author cx
 * @since 2021-04-06
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("t_product_info")
@ApiModel(value="ProductInfo对象", description="")
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "详情id")
        @TableId(value = "info_id", type = IdType.AUTO)
      private Integer infoId;

      @ApiModelProperty(value = "商品id")
      private Integer productId;

      @ApiModelProperty(value = "属性名称")
      private String attributeName;

      @ApiModelProperty(value = "属性值")
      private String attributeValue;

      @ApiModelProperty(value = "创建时间")
        @TableField(fill = FieldFill.INSERT)
      private Long gmtCreate;

      @ApiModelProperty(value = "更新时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private Long gmtModified;

      @ApiModelProperty(value = "逻辑删除")
      @TableLogic
    private Integer deleted;


}
