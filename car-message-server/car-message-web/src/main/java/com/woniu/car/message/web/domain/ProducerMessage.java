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
@TableName("t_producer_message")
@ApiModel(value = "ProducerMessage对象", description = "")
public class ProducerMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "生产者组名")
    private String groupName;

    @ApiModelProperty(value = "消费主题")
    private String msgTopic;

    @ApiModelProperty(value = "Tag")
    private String msgTag;

    @ApiModelProperty(value = "Key")
    private String msgKey;

    @ApiModelProperty(value = "消息内容")
    private String msgBody;

    @ApiModelProperty(value = "1.未处理，2已经处理")
    private Integer msgStatus;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date msgCreateTime;

    @TableLogic
    private Integer deleted;


}
