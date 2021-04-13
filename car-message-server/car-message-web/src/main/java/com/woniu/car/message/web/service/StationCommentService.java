package com.woniu.car.message.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.message.model.dto.StationCommentDto;
import com.woniu.car.message.model.param.CommentPageParam;
import com.woniu.car.message.model.param.StationCommentParam;
import com.woniu.car.message.model.param.StationTagNameLookCommentParam;
import com.woniu.car.message.web.domain.StationComment;

import java.util.List;

/**
 * <p>
 *  电站评论 服务类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
public interface StationCommentService extends IService<StationComment> {

    Boolean addStationComment(StationCommentParam param);

    Boolean deleteStationComment(String commentStCode);

    List<StationCommentDto> lookUserStationComments(Integer userId);


    IPage<StationCommentDto> lookSomeStationCommentsByPowerCode(CommentPageParam pageParam);

    List<StationCommentDto> lookSomeStationCommentssByPowerCode(Integer powerId);

    List<StationCommentDto> lookAllStationCommentsByTagName(StationTagNameLookCommentParam tagName);

}
