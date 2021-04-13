package com.woniu.car.message.model.dto;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *  车站评论Dto类，反馈给前端的数据
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StationCommentDto implements Serializable {
    /**
     * 评论用户名字
     */
    private String commentName;
    /**
     * 评论用户头像
     */
    private String commentUrl;
    /**
     * 评分
     */
    private Integer commentScore;
    /**
     * 评论内容
     */
    private String commentWords;
    /**
     * 评论图片
     */
    private JSONObject commentImages;
    /**
     * 评论时间毫秒数
     */
    private Long commentTime;
    /**
     * 服务评论具体时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date commentTimes;

    /**
     * 电站评论名字
     */
    private Integer commentPowerName;


}
