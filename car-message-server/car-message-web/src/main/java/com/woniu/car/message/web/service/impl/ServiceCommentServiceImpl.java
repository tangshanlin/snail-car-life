package com.woniu.car.message.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.message.model.dto.ServiceCommentDto;
import com.woniu.car.message.model.feign.CommentPageParam;
import com.woniu.car.message.model.param.ServiceCommentParam;
import com.woniu.car.message.model.feign.ServiceTagNameLookCommentParam;
import com.woniu.car.message.model.param.SeviceNameCommentParam;
import com.woniu.car.message.web.domain.CommentTagConnection;
import com.woniu.car.message.web.domain.ServiceComment;
import com.woniu.car.message.web.domain.Tag;
import com.woniu.car.message.web.mapper.CommentTagConnectionMapper;
import com.woniu.car.message.web.mapper.ServiceCommentMapper;
import com.woniu.car.message.web.mapper.TagMapper;
import com.woniu.car.message.web.service.ServiceCommentService;
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
 *   服务评论 服务实现类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Service
@Transactional
@Slf4j
public class ServiceCommentServiceImpl extends ServiceImpl<ServiceCommentMapper, ServiceComment> implements ServiceCommentService {

    @Resource
    private ServiceCommentMapper serviceCommentMapper;
    @Resource
    private MessageFileUpload messageFileUpload;
    @Resource
    private CommentTagConnectionMapper commentTagConnectionMapper;
    @Resource
    private TagMapper tagMapper;

    /**
     * 添加服务评论
     * @param param
     * @return
     */
    @Override
    public Boolean addServiceComment(ServiceCommentParam param) {
        if (!ObjectUtils.isEmpty(param)){
            param.setCommentTime(new Date().getTime());
            MultipartFile[] file = param.getFile();
            ArrayList<String> uploads = messageFileUpload.upload(file);
            JSONObject jsonObject = new JSONObject();
            if (uploads.size()==0) {
                System.out.println("没有上传服务评价图片！！！");
            }else{
                uploads.forEach(upload->{
                    jsonObject.put("SE"+ UUID.randomUUID().toString(),upload);
                });
            }
            String commentImages = JSONObject.toJSONString(jsonObject);
            ServiceComment serviceComment = BeanCopyUtil.copyOne(param, ServiceComment::new);
            serviceComment.setCommentImage(commentImages);
            String comment="SE"+UUID.randomUUID().toString();
            serviceComment.setCommentSecode(comment);
            System.out.println(serviceComment);
            int insert = serviceCommentMapper.insert(serviceComment);
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
    /**
     * 根据评论编号删除服务评论
     * @param commentSeCode
     * @return
     */
    @Override
    public Boolean deleteServiceComment(String commentSeCode) {
        QueryWrapper<CommentTagConnection> wrapper = new QueryWrapper<>();
        wrapper.eq("comment_code",commentSeCode);

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
        QueryWrapper<ServiceComment> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("comment_secode",commentSeCode);
        int i = serviceCommentMapper.delete(wrapper1);
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
    private List<ServiceCommentDto>  getSameResult(List<ServiceComment> serviceComments){
        List<ServiceCommentDto> serviceCommentDtos = BeanCopyUtil.copyList(serviceComments, ServiceCommentDto::new);
        for (int i=0;i<serviceComments.size();i++){
            JSONObject jsonObject = JSONObject.parseObject(serviceComments.get(i).getCommentImage());
            serviceCommentDtos.get(i).setCommentImages(jsonObject);
            serviceCommentDtos.get(i).setCommentTimes(new Date(serviceCommentDtos.get(i).getCommentTime()));
        }
        return serviceCommentDtos;
    }

    @Override
    public List<ServiceCommentDto> lookUserServiceComments(Integer userId) {
        QueryWrapper<ServiceComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_user_id",userId);
        List<ServiceComment> serviceComments = serviceCommentMapper.selectList(queryWrapper);
        return getSameResult(serviceComments);
    }

    @Override
    public List<ServiceCommentDto> lookSomeServiceCommentsByServiceName(SeviceNameCommentParam param) {
        QueryWrapper<ServiceComment> wrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(param.getShopId())){
            wrapper.eq("comment_shop_code",param.getShopId());
        }
        if(!ObjectUtils.isEmpty(param.getServiceName())){
            wrapper.eq("comment_shop_code",param.getShopId());
        }
        List<ServiceComment> serviceComments = serviceCommentMapper.selectList(wrapper);
        return getSameResult(serviceComments);
    }

    @Override
    public IPage<ServiceCommentDto> lookSomeServiceCommentsByShopId(CommentPageParam pageParam) {
        Page<ServiceComment> page = new Page<>(pageParam.getCurrent(), pageParam.getSize());
        QueryWrapper<ServiceComment> wrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(pageParam.getId())){
            wrapper.eq("comment_shop_code",pageParam.getId());
        }
        Page<ServiceComment> serviceCommentPage = serviceCommentMapper.selectPage(page, wrapper);
        IPage<ServiceCommentDto> serviceCommentDtos=new Page<>(pageParam.getCurrent(), pageParam.getSize());
        serviceCommentDtos.setTotal(serviceCommentPage.getTotal());
        serviceCommentDtos.setPages(serviceCommentPage.getPages());
        List<ServiceCommentDto> serviceCommentDtosList = BeanCopyUtil.copyList(serviceCommentPage.getRecords(), ServiceCommentDto::new);
        for (int i=0;i<serviceCommentPage.getRecords().size();i++){
            JSONObject jsonObject = JSONObject.parseObject(serviceCommentPage.getRecords().get(i).getCommentImage());
            serviceCommentDtosList.get(i).setCommentImages(jsonObject);
            serviceCommentDtosList.get(i).setCommentTimes(new Date(serviceCommentDtosList.get(i).getCommentTime()));
        }
        serviceCommentDtos.setRecords(serviceCommentDtosList);
        return serviceCommentDtos;
    }

    /**
     * list查询根据标签查询该服务的所有评论
     * @param tagName
     * @return
     */
    @Override
    public List<ServiceCommentDto> lookAllServiceCommentsByTagName(ServiceTagNameLookCommentParam tagName) {
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
            QueryWrapper<ServiceComment> queryWrapper2 = new QueryWrapper<>();
            //删选出只有商品的评论编号
            List<String> productCodes=new ArrayList<>();
            commentTagConnections.forEach(commentTagConnection -> {
                if("SE".equals(commentTagConnection.getCommentCode().substring(0,2))){
                    productCodes.add(commentTagConnection.getCommentCode());
                }
            });
            for(int i=0;i<productCodes.size();i++){
                queryWrapper2.or().eq("comment_secode",productCodes.get(i));
            }
            queryWrapper2.eq("comment_service_code",tagName.getServiceCode());
            List<ServiceComment> productComments = serviceCommentMapper.selectList(queryWrapper2);
            return getSameResult(productComments);
        }
        return null;
    }

}
