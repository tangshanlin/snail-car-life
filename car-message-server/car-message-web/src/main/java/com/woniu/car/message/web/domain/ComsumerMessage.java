package com.woniu.car.message.web.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_comsumer_message")
@ApiModel(value = "ComsumerMessage对象", description = "")
public class ComsumerMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "消费者Id")
    @TableId(value = "msg_id", type = IdType.ID_WORKER)
    private String msgId;

    @ApiModelProperty(value = "消费者组名")
    private String groupName;

    @ApiModelProperty(value = "Tag")
    private String msgTag;

    @ApiModelProperty(value = "key")
    private String msgKey;

    @ApiModelProperty(value = "消息体")
    private String msgBody;

    @ApiModelProperty(value = "1.正在处理，2处理成功，3，处理失败")
    private Integer consumerStatus;

    @ApiModelProperty(value = "消费次数")
    private Integer consumerTimes;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date consumerTimestamp;

    @ApiModelProperty(value = "备注")
    private String remark;

    @TableLogic
    private Integer deleted;


}
