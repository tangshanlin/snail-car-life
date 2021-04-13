package com.woniu.car.items.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * @author Sokyo
 * @since 2021-04-05
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("t_two_classify")
@ApiModel(value="TwoClassify对象", description="")
public class TwoClassify implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "two_classify_id", type = IdType.AUTO)
      private Integer twoClassifyId;

      @ApiModelProperty(value = "关联一级分类id")
      private Integer oneClassifyId;

      @ApiModelProperty(value = "二级分类名称")
      private String twoClassifyName;

      @ApiModelProperty(value = "创建时间")
        @TableField(fill = FieldFill.INSERT)
      private Long gmtCreate;

      @ApiModelProperty(value = "修改时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private Long gmtModified;

      @ApiModelProperty(value = "删除状态 0未删除 1已删除")
      @TableLogic
    private Integer deleted;


}
