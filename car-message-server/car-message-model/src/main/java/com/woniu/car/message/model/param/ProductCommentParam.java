package com.woniu.car.message.model.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * <p>
 *  商品评论参数类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductCommentParam implements Serializable {
    /**
     * 评论用户id
     */
    private Integer commentUserId;
    /**
     * 评论用户名字
     */
    private String commentName;
    /**
     * 商品评论人头像
     */
    private String commentUrl;
    /**
     * 评论的商品图片
     */
    private MultipartFile[] file;
    /**
     * 商品评论评分
     */
    private Integer commentScore;
    /**
     * 商品评论内容
     */
    private String commentWords;
    /**
     * 商品评论时间
     */
    private Long commentTime;
    /**
     * 商品订单编号
     */
    private String commentOrderCode;
    /**
     * 商品评论商品编号
     */
    private Integer productCode;

}
