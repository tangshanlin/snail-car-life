package com.woniu.car.message.model.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;


/**
 * <p>
 *  电站评论参数类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */

@Data
@EqualsAndHashCode(callSuper = false)

public class StationCommentParam implements Serializable {
    /**
     * 电站评论编号
     */
    private String commentStcode;
    /**
     * 评论用户Id
     */
    private Integer commentUserId;
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
    private MultipartFile[] file;
    /**
     * 评论时间
     */
    private Long commentTime;
    /**
     * 电站评论编号
     */
    private Integer commentPowerCode;
    /**
     * 电站评论名字
     */
    private Integer commentPowerName;



}
