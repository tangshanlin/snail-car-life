package com.woniu.car.message.web.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 评论标签联系表
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_comment_tag_connection")
@ApiModel(value="CommentTagConnection对象", description="")
public class CommentTagConnection implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "评论标签联系编号")
      @TableId(value = "comment_tag_id", type = IdType.AUTO)
      private Integer commentTagId;

      @ApiModelProperty(value = "评论编号")
      private String commentCode;

      @ApiModelProperty(value = "标签编号")
      private Integer tagId;

      @TableField(fill = FieldFill.INSERT)
      private Long gmtCreate;

      @TableField(fill = FieldFill.INSERT_UPDATE)
      private Long gmtModified;

      @TableLogic
      private Integer deleted;


}
