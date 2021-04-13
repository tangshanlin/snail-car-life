package com.woniu.car.message.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.message.model.param.AddTagsForCommentParam;
import com.woniu.car.message.web.domain.Tag;

/**
 * <p>
 *  标签  服务类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
public interface TagService extends IService<Tag> {

    Integer addSomeTagsForComment(AddTagsForCommentParam addTagsParam);

}
