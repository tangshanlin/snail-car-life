package com.woniu.car.message.model.dto;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *  服务评论Dto类，反馈给前端的数据
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceCommentDto implements Serializable {
    /**
     *  用户名字
     */
    private String commentName;
    /**
     * 用户头像
     */
    private String commentUrl;
    /**
     * 服务评分
     */
    private Integer commentScore;
    /**
     * 服务评论内容
     */
    private String commentWords;
    /**
     * 服务评论图片
     */
    private JSONObject commentImages;
    /**
     * 服务评论时间毫秒数
     */
    private Long commentTime;
    /**
     * 服务评论具体时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date commentTimes;

    /**
     * 服务评论门店名字
     */
    private String commentShopname;

    /**
     * 服务评论服务名字
     */
    private String serviceName;


}
