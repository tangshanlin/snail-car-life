package com.woniu.car.message.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.message.model.dto.StationCommentDto;
import com.woniu.car.message.model.param.CommentPageParam;
import com.woniu.car.message.model.param.StationCommentParam;
import com.woniu.car.message.model.param.StationTagNameLookCommentParam;
import com.woniu.car.message.web.domain.CommentTagConnection;
import com.woniu.car.message.web.domain.StationComment;
import com.woniu.car.message.web.domain.Tag;
import com.woniu.car.message.web.mapper.CommentTagConnectionMapper;
import com.woniu.car.message.web.mapper.StationCommentMapper;
import com.woniu.car.message.web.mapper.TagMapper;
import com.woniu.car.message.web.service.StationCommentService;
import com.woniu.car.message.web.util.MessageFileUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  电站评论 服务实现类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Service
@Transactional
@Slf4j
public class StationCommentServiceImpl extends ServiceImpl<StationCommentMapper, StationComment> implements StationCommentService {

    @Resource
    private MessageFileUpload messageFileUpload;
    @Resource
    private CommentTagConnectionMapper commentTagConnectionMapper;
    @Resource
    private StationCommentMapper stationCommentMapper;
    @Resource
    private TagMapper tagMapper;

    @Override
    public Boolean addStationComment(StationCommentParam param) {
        if (!ObjectUtils.isEmpty(param)){
            param.setCommentTime(new Date().getTime());
            MultipartFile[] file = param.getFile();
            ArrayList<String> uploads = messageFileUpload.upload(file);
            JSONObject jsonObject = new JSONObject();
            if (uploads.size()==0) {
                System.out.println("没有上传车站评价图片！！！");
            }else{
                uploads.forEach(upload->{
                    jsonObject.put("ST"+ UUID.randomUUID().toString(),upload);
                });
            }
            String commentImages = JSONObject.toJSONString(jsonObject);
            StationComment stationComment = BeanCopyUtil.copyOne(param, StationComment::new);
            stationComment.setCommentImage(commentImages);
            String comment="ST"+UUID.randomUUID().toString();
            stationComment.setCommentStcode(comment);
            int insert = stationCommentMapper.insert(stationComment);
            if (insert>0){
                //修改Tag表的全部评论的数量
                Tag tag = tagMapper.selectById(1);
                tag.setTagNum(tag.getTagNum()+1);
                tagMapper.updateById(tag);
                //自动增加在标签联系表内容
                CommentTagConnection tagConnection = new CommentTagConnection();
                tagConnection.setTagId(1);
                tagConnection.setCommentCode(comment);
                commentTagConnectionMapper.insert(tagConnection);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteStationComment(String commentStCode) {
        QueryWrapper<CommentTagConnection> wrapper = new QueryWrapper<>();
        wrapper.eq("comment_code",commentStCode);
        List<CommentTagConnection> commentTagConnections = commentTagConnectionMapper.selectList(wrapper);
        if(!ObjectUtils.isEmpty(commentTagConnections)){
            commentTagConnections.forEach(commentTagConnection -> {
                QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("tag_id",commentTagConnection.getTagId());
                Tag tag = tagMapper.selectOne(queryWrapper);
                if(!ObjectUtils.isEmpty(tag)){
                    tag.setTagNum(tag.getTagNum()-1);
                    tagMapper.updateById(tag);
                }
            });
        }
        commentTagConnectionMapper.delete(wrapper);
        QueryWrapper<StationComment> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("comment_stcode",commentStCode);
        int i = stationCommentMapper.delete(wrapper1);
        if (i>0){

            return true;
        }
        return false;
    }


    /**
     * @Author Lints
     * @Date 2021/4/7/007 13:26
     * @Description 此方法是为了返回相同的商品评论相关分页信息
     * @Param [pageParam, page, wrapper]
     * @Return com.baomidou.mybatisplus.core.metadata.IPage<com.woniu.car.message.model.dto.ProductCommentDto>
     * @Since version-1.0
     */
    private List<StationCommentDto>  getSameResult(List<StationComment> productComments){
        List<StationCommentDto> stationCommentDtos = BeanCopyUtil.copyList(productComments, StationCommentDto::new);
        for (int i=0;i<productComments.size();i++){
            JSONObject jsonObject = JSONObject.parseObject(productComments.get(i).getCommentImage());
            stationCommentDtos.get(i).setCommentImages(jsonObject);
            stationCommentDtos.get(i).setCommentTimes(new Date(stationCommentDtos.get(i).getCommentTime()));
        }
        return stationCommentDtos;
    }

    @Override
    public List<StationCommentDto> lookUserStationComments(Integer userId) {
        QueryWrapper<StationComment> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(userId)){
            queryWrapper.eq("comment_user_id",userId);
        }
        List<StationComment> stationComments = stationCommentMapper.selectList(queryWrapper);
        return getSameResult(stationComments);
    }

    @Override
    public List<StationCommentDto> lookSomeStationCommentssByPowerCode(Integer powerId) {
        QueryWrapper<StationComment> wrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(powerId)){
            wrapper.eq("comment_power_code",powerId);
        }
        List<StationComment> serviceComments = stationCommentMapper.selectList(wrapper);
        return getSameResult(serviceComments);
    }

    /**
     * list查询根据标签查询该电站的所有评论
     * @param tagName
     * @return
     */
    @Override
    public List<StationCommentDto> lookAllStationCommentsByTagName(StationTagNameLookCommentParam tagName) {
        log.info("根据参数{}查询整个标签内容",tagName);
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_name",tagName.getTagName());
        Tag tag = tagMapper.selectOne(queryWrapper);
        if(ObjectUtils.isEmpty(tag)){
            return null;
        }
        log.info("根据标签内容的id:{}去查询评论编码",tag.getTagId());
        QueryWrapper<CommentTagConnection> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("tag_id",tag.getTagId());
        List<CommentTagConnection> commentTagConnections = commentTagConnectionMapper.selectList(queryWrapper1);
        if(!ObjectUtils.isEmpty(commentTagConnections)){
            QueryWrapper<StationComment> queryWrapper2 = new QueryWrapper<>();
            //删选出只有商品的评论编号
            List<String> productCodes=new ArrayList<>();
            commentTagConnections.forEach(commentTagConnection -> {
                if("ST".equals(commentTagConnection.getCommentCode().substring(0,2))){
                    productCodes.add(commentTagConnection.getCommentCode());
                }
            });
            for(int i=0;i<productCodes.size();i++){
                queryWrapper2.or().eq("comment_stcode",productCodes.get(i));
            }
            queryWrapper2.eq("comment_power_code",tagName.getCommentPowerCode());
            List<StationComment> productComments = stationCommentMapper.selectList(queryWrapper2);
            return getSameResult(productComments);
        }
        return null;
    }


    @Override
    public IPage<StationCommentDto> lookSomeStationCommentsByPowerCode(CommentPageParam pageParam) {
        Page<StationComment> page = new Page<>(pageParam.getCurrent(), pageParam.getSize());
        QueryWrapper<StationComment> wrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(pageParam.getId())){
            wrapper.eq("comment_power_code",pageParam.getId());
        }
        Page<StationComment> serviceCommentPage = stationCommentMapper.selectPage(page, wrapper);
        IPage<StationCommentDto> serviceCommentDtos=new Page<>(pageParam.getCurrent(), pageParam.getSize());
        serviceCommentDtos.setTotal(serviceCommentPage.getTotal());
        serviceCommentDtos.setPages(serviceCommentPage.getPages());
        List<StationCommentDto> serviceCommentDtosList = BeanCopyUtil.copyList(serviceCommentPage.getRecords(), StationCommentDto::new);
        for (int i=0;i<serviceCommentPage.getRecords().size();i++){
            JSONObject jsonObject = JSONObject.parseObject(serviceCommentPage.getRecords().get(i).getCommentImage());
            serviceCommentDtosList.get(i).setCommentImages(jsonObject);
            serviceCommentDtosList.get(i).setCommentTimes(new Date(serviceCommentDtosList.get(i).getCommentTime()));
        }
        serviceCommentDtos.setRecords(serviceCommentDtosList);
        return serviceCommentDtos;
    }




}
