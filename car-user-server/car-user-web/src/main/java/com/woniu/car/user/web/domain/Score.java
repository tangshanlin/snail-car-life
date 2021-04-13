package com.woniu.car.user.web.domain;

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
 * 积分表
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_score")
@ApiModel(value="Score对象", description="积分表")
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "积分表id")
      @TableId(value = "score_id", type = IdType.AUTO)
    private Integer scoreId;

    private Integer userId;

    @ApiModelProperty(value = "积分变化数")
    private Integer scoreNumber;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    @ApiModelProperty(value = "修改时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "积分变化事件")
    private String scoreChange;

    @ApiModelProperty(value = "积分变化前")
    private Integer scoreOld;

    @ApiModelProperty(value = "积分改变后")
    private Integer scoreBalance;

    @ApiModelProperty(value = "积分变化类型(1消费2兑换优惠券)")
    private Integer scoreChangeType;


}
