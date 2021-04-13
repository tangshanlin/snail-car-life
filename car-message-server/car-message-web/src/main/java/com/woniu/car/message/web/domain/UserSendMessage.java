package com.woniu.car.message.web.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户主动发送消息实体类
 * </p>
 *
 * @author ZYY
 * @since 2021-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user_send_message")
@ApiModel(value = "UserSendMessage对象", description = "")
public class UserSendMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户主动发送消息")
    @TableId(value = "user_send_message_id", type = IdType.AUTO)
    private Integer userSendMessageId;

    @ApiModelProperty(value = "发送目标编号")
    private Integer sendTargetId;


    @ApiModelProperty(value = "发送主题")
    private String sendTopic;

    @ApiModelProperty(value = "发送的内容")
    private String sendWords;

    @ApiModelProperty(value = "发送的Key键")
    private String sendKey;

    @ApiModelProperty(value = "发送时间")
    private Long sendTime;

    @ApiModelProperty(value = "发送人编号")
    private Integer sendMessageCode;


}
