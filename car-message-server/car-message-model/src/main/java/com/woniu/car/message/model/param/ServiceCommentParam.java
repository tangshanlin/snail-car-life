package com.woniu.car.message.model.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * <p>
 *  服务评论参数类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceCommentParam implements Serializable {
    /**
     * 服务评论编号
     */
    private String commentSecode;

    /**
     * 服务评论用户id
     */
    private Integer commentUserId;
    /**
     * 服务评论用户名字
     */
    private String commentName;

    /**
     * 服务评论用户头像
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
    private MultipartFile[] file;

    /**
     * 服务评论时间
     */
    private Long commentTime;

    /**
     * 服务评论订单号
     */
    private String commentOrderCode;

    /**
     * 服务评论门店编号
     */
    private Integer commentShopCode;

    /**
     * 服务评论门店名字
     */
    private String commentShopname;

    /**
     * 服务评论服务编号
     */
    private Integer commentServiceCode;

    /**
     * 服务评论服务名字
     */
    private String serviceName;

}
