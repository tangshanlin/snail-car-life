//package com.woniu.car.message.model.es;
//
//import com.alibaba.fastjson.JSONObject;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * <p>
// *  商品评论Dto类，反馈给前 端的数据
// * </p>
// *
// * @author zyy
// * @since 2021-04-05
// */
//@Data
//@EqualsAndHashCode(callSuper = false)
//@Document(indexName = "productComment")
//public class ProductCommentEs implements Serializable {
//
//    /**
//     * 商品评论用户名字
//     */
//    @Field(type = FieldType.Keyword,name = "commentName")
//    private String commentName;
//    /**
//     * 评论用户头像
//     */
//    @Field(type = FieldType.Keyword,name = "commentUrl")
//    private String commentUrl;
//    /**
//     * 评分
//     */
//    @Field(type = FieldType.Integer,name = "commentScore")
//    private Integer commentScore;
//    /**
//     * 评论内容
//     */
//    @Field(type = FieldType.Text,analyzer = "ik_max_word",name = "commentWords")
//    private String commentWords;
//    /**
//     * 评论图片
//     */
//    private JSONObject commentImages;
//    /**
//     * 服务评论时间毫秒数
//     */
//
//    private Long commentTime;
//    /**
//     * 服务评论具体时间
//     */
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
//    private Date commentTimes;
//
//    /**
//     * 商品由后台提供，直接写死
//     */
//
//
//
//}
