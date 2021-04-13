package com.woniu.car.message.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.message.model.param.AddTagsForCommentParam;
import com.woniu.car.message.web.domain.CommentTagConnection;
import com.woniu.car.message.web.domain.Tag;
import com.woniu.car.message.web.mapper.CommentTagConnectionMapper;
import com.woniu.car.message.web.mapper.TagMapper;
import com.woniu.car.message.web.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  评论标签  服务实现类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Resource
    private TagMapper tagMapper;
    @Resource
    private CommentTagConnectionMapper commentTagConnectionMapper;

    /**
     * 为评论添加标签
     * @param addTagsParam
     * @return
     */
    @Override
    public Integer addSomeTagsForComment(AddTagsForCommentParam addTagsParam) {
        if(!ObjectUtils.isEmpty(addTagsParam)){
            if(!ObjectUtils.isEmpty(addTagsParam.getCommentCode())){
              if(!ObjectUtils.isEmpty(addTagsParam.getTagId())){
                  QueryWrapper<CommentTagConnection> queryWrapper = new QueryWrapper<>();
                  queryWrapper.eq("comment_code",addTagsParam.getCommentCode());
                  List<CommentTagConnection> commentTagConnections = commentTagConnectionMapper.selectList(queryWrapper);
                  if(!ObjectUtils.isEmpty(commentTagConnections)&&commentTagConnections.size()>1){
                      return 1;
                  }
                  for(int i=0;i<addTagsParam.getTagId().length;i++){
                     CommentTagConnection entity = new CommentTagConnection();
                     entity.setCommentCode(addTagsParam.getCommentCode());
                     entity.setTagId(addTagsParam.getTagId()[i]);
                     commentTagConnectionMapper.insert(entity);
                     Tag tag = tagMapper.selectById(addTagsParam.getTagId()[i]);
                     if(!ObjectUtils.isEmpty(tag)){
                         tag.setTagNum(tag.getTagNum()+1);
                         tagMapper.updateById(tag);
                     }
                 }
                  return 2;
              }
            }
        }
        return 3;
    }





}
