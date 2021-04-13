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
    @TableName("t_user_car_service")
@ApiModel(value="UserCarService对象", description="")
public class UserCarService implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "user_service_id", type = IdType.AUTO)
      private Integer userServiceId;

      @ApiModelProperty(value = "关联用户id")
      private Integer userId;

      @ApiModelProperty(value = "关联服务id")
      private Integer carServiceId;

      @ApiModelProperty(value = "服务名称")
      private String carServiceName;

      @ApiModelProperty(value = "是否预约")
      private String userServiceSubscribe;

      @ApiModelProperty(value = "预约服务时间")
      private String userServiceSubscribeTime;

      @ApiModelProperty(value = "服务状态")
      private Integer userServiceStatus;

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
