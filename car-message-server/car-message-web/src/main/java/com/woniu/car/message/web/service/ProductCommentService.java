package com.woniu.car.message.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.message.model.dto.ProductCommentDto;
import com.woniu.car.message.model.param.CommentPageParam;
import com.woniu.car.message.model.param.ProductCommentParam;
import com.woniu.car.message.model.param.ProductTagNameLookCommentParam;
import com.woniu.car.message.web.domain.ProductComment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  商品评论 服务类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
public interface ProductCommentService extends IService<ProductComment> {


    /**
     * 添加商品评论
     * @param param
     * @return
     */
    Boolean addPComment(ProductCommentParam param);
    /**
     * 根据评论编号删除商品评论
     * @param commentPCode
     * @return
     */
    public Boolean deletePComment(String commentPCode) ;

    /**
     * 查询该用户下面的所有评论
     * @param userId
     * @return
     */

    List<ProductCommentDto> lookUserPComments(Integer userId);

    /**
     * list查询某种下面的所有评论
     * @param productId
     * @return
     */
    List<ProductCommentDto> lookAllProductComments(Integer productId);

    /**
     * list查询根据标签查询该商品的所有评论
     * @param tagName
     * @return
     */

    List<ProductCommentDto> lookAllProductCommentsByTagName(ProductTagNameLookCommentParam tagName);



    /**
     * 分页查询某种下面的所有评论
     * @param pageParam
     * @return
     */
    IPage<ProductCommentDto> lookSomeProductComments(CommentPageParam pageParam);


    Boolean addProductPicture(MultipartFile[] file);

}
