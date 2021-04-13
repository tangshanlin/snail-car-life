package com.woniu.car.product.web.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

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
    @TableName("t_product_cate")
@ApiModel(value="ProductCate对象", description="")
public class ProductCate implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "分类id")
        @TableId(value = "cate_id", type = IdType.AUTO)
      private Integer cateId;

      @ApiModelProperty(value = "分类名称")
      private String cateName;

      @ApiModelProperty(value = "分类状态")
      private Integer cateStatus;

      @ApiModelProperty(value = "创建时间")
        @TableField(fill = FieldFill.INSERT)
      private Long gmtCreate;

      @ApiModelProperty(value = "更新时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private Long gmtModified;

      @ApiModelProperty(value = "逻辑删除")
      @TableLogic
    private Integer deleted;

      @ApiModelProperty(value = "分类图片")
      private String cateImage;

      @ApiModelProperty(value = "父id")
      private Integer parentId;

      @TableField(exist = false)
      private List<ProductCate> childcateList;

      private Integer level;


}
