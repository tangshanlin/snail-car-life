package com.woniu.car.message.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.message.model.dto.ServiceCommentDto;
import com.woniu.car.message.model.feign.CommentPageParam;
import com.woniu.car.message.model.param.ServiceCommentParam;
import com.woniu.car.message.model.feign.ServiceTagNameLookCommentParam;
import com.woniu.car.message.model.param.SeviceNameCommentParam;
import com.woniu.car.message.web.domain.ServiceComment;

import java.util.List;

/**
 * <p>
 *  服务评论 服务类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
public interface ServiceCommentService extends IService<ServiceComment> {

    /**
     * 添加服务评论
     * @param param
     * @return
     */
    Boolean addServiceComment(ServiceCommentParam param);

    /**
     * 根据评论编号删除服务评论
     * @param commentSeCode
     * @return
     */
    Boolean deleteServiceComment(String commentSeCode);



    List<ServiceCommentDto> lookUserServiceComments(Integer userId);

    List<ServiceCommentDto> lookSomeServiceCommentsByServiceName(SeviceNameCommentParam param);

    IPage<ServiceCommentDto> lookSomeServiceCommentsByShopId(CommentPageParam pageParam);


    List<ServiceCommentDto> lookAllServiceCommentsByTagName(ServiceTagNameLookCommentParam tagName);

}
