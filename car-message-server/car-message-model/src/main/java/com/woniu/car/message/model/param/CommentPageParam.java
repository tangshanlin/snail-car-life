package com.woniu.car.message.model.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Lints
 * @Date 2021/4/7/007 13:43
 * @Description 商品评论分页参数类
 * @Since version-1.0
 */
@Data
public class CommentPageParam implements Serializable {
    /**
     * 分页长度
     */
    private Integer size;
    /**
     * 当前页
     */
    private Integer current;
    /**
     * 传递的条件参数编号
     */
    private Integer id;
    /**
     * 传递的条件XXX名字(列如服务名字)
     */
    private String name;


}
