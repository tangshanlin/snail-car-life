package com.woniu.car.message.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.message.model.dto.ProductCommentDto;
import com.woniu.car.message.model.param.CommentPageParam;
import com.woniu.car.message.model.param.ProductCommentParam;
import com.woniu.car.message.model.param.ProductTagNameLookCommentParam;
import com.woniu.car.message.web.domain.CommentTagConnection;
import com.woniu.car.message.web.domain.GoodProduct;
import com.woniu.car.message.web.domain.ProductComment;
import com.woniu.car.message.web.domain.Tag;
import com.woniu.car.message.web.mapper.CommentTagConnectionMapper;
import com.woniu.car.message.web.mapper.GoodProductMapper;
import com.woniu.car.message.web.mapper.ProductCommentMapper;
import com.woniu.car.message.web.mapper.TagMapper;
import com.woniu.car.message.web.service.ProductCommentService;
import com.woniu.car.message.web.util.MessageFileUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
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
 *  商品评论 服务实现类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@Service
@Transactional
@Slf4j
public class ProductCommentServiceImpl extends ServiceImpl<ProductCommentMapper, ProductComment> implements ProductCommentService {


    private String commentCode;
    @Resource
    private ProductCommentMapper productCommentMapper;
    @Resource
    private MessageFileUpload messageFileUpload;
    @Resource
    private CommentTagConnectionMapper commentTagConnectionMapper;
    @Resource
    private TagMapper tagMapper;

    @Resource
    private RedisTemplate<String, ProductCommentDto> redisTemplate;

    @Resource
    private GoodProductMapper goodProductMapper;

    @Override
    public Boolean addProductPicture(MultipartFile[] file) {
        ArrayList<String> uploads = messageFileUpload.upload(file);
        JSONObject jsonObject = new JSONObject();
        if (uploads.size()==0) {
            System.out.println("没有上传商品评价图片！！！");
        }else{
            uploads.forEach(upload->{
                jsonObject.put("PR"+ UUID.randomUUID().toString(),upload);
            });
        }
        String commentImages = JSONObject.toJSONString(jsonObject);
        ProductComment productComment= new ProductComment();
        commentCode="PR"+UUID.randomUUID().toString();
        productComment.setCommentPcode(commentCode);
        productComment.setCommentImage(commentImages);
        int insert = productCommentMapper.insert(productComment);
        if(insert>0){
            return true;
        }
        return false;
    }
    /**
     * 添加商品评论
     * @param param
     * @return
     */
    @Override
    public Boolean addPComment(ProductCommentParam param) {
        if (!ObjectUtils.isEmpty(param)){
            QueryWrapper<ProductComment> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("comment_pcode",commentCode);
            ProductComment productComment1 = productCommentMapper.selectOne(queryWrapper);
            if(!ObjectUtils.isEmpty(productComment1)){
                productComment1.setCommentTime(new Date().getTime());
                productComment1.setCommentName(param.getCommentName());
                productComment1.setCommentWords(param.getCommentWords());
                productComment1.setCommentScore(param.getCommentScore());
                productComment1.setProductCode(param.getProductCode());
                productComment1.setCommentUrl(param.getCommentUrl());
                productComment1.setCommentOrderCode(param.getCommentOrderCode());
                productComment1.setCommentUserId(param.getCommentUserId());
                ProductComment productComment = BeanCopyUtil.copyOne(productComment1, ProductComment::new);
                System.out.println(productComment+"========================================");
                int insert = productCommentMapper.updateById(productComment);
                if (insert>0){
                    //修改Tag表的全部评论的数量
                    Tag tag = tagMapper.selectById(1);
                    tag.setTagNum(tag.getTagNum()+1);
                    tagMapper.updateById(tag);
                    //自动增加在标签联系表内容
                    CommentTagConnection tagConnection = new CommentTagConnection();
                    tagConnection.setTagId(1);
                    tagConnection.setCommentCode(commentCode);
                    commentTagConnectionMapper.insert(tagConnection);
                    QueryWrapper<GoodProduct> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.eq("product_id",param.getProductCode());
                    GoodProduct goodProduct = goodProductMapper.selectOne(queryWrapper1);

                    if(!ObjectUtils.isEmpty(goodProduct)){
                        goodProduct.setProductId(param.getProductCode());
                        goodProduct.setProductNums(goodProduct.getProductNums()+1);
                        //如果这次的评分>=3分，好评加1
                        if(param.getCommentScore()>=3){
                            goodProduct.setProductGoodNum(goodProduct.getProductGoodNum()+1);
                        }
                        goodProductMapper.updateById(goodProduct);
                    }else{
                        GoodProduct product = new GoodProduct();
                        product.setProductId(param.getProductCode());
                        product.setProductNums(1);
                        if(param.getCommentScore()>=3){
                            product.setProductGoodNum(1);
                        }
                        System.out.println(product);
                        goodProductMapper.insert(product);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据评论编号删除商品评论
     * @param commentPCode
     * @return
     */
    @Override
    public Boolean deletePComment(String commentPCode) {
        log.info("删除商品评论的方法开始了，传入参数:{}",commentPCode);
        QueryWrapper<CommentTagConnection> wrapper = new QueryWrapper<>();
        wrapper.eq("comment_code",commentPCode);
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
        QueryWrapper<ProductComment> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("comment_pcode",commentPCode);
        int i = productCommentMapper.delete(wrapper1);
        if (i>0){
            log.info("删除商品评论的方法结束了，返回值为:{}",true);
            return true;
        }
        log.info("删除商品评论的方法结束了，返回值为:{}",false);
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
    private List<ProductCommentDto>  getSameResult(List<ProductComment> productComments){
        List<ProductCommentDto> productCommentDtos = BeanCopyUtil.copyList(productComments, ProductCommentDto::new);
        for (int i=0;i<productComments.size();i++){
            JSONObject jsonObject = JSONObject.parseObject(productComments.get(i).getCommentImage());
            productCommentDtos.get(i).setCommentImages(jsonObject);
            productCommentDtos.get(i).setCommentTimes(new Date(productCommentDtos.get(i).getCommentTime()));
        }
        return productCommentDtos;
    }

    /**
     * 查询该用户下面的所有评论
     * @param userId
     * @return
     */
    @Override
    public List<ProductCommentDto> lookUserPComments(Integer userId) {
        QueryWrapper<ProductComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_user_id",userId);
        List<ProductComment> productComments = productCommentMapper.selectList(queryWrapper);
        return getSameResult( productComments);
    }

    /**
     * 查询该商品编号下面的所有评论
     * @param productId
     * @return
     */
    @Override
    public List<ProductCommentDto> lookAllProductComments(Integer productId) {
        QueryWrapper<ProductComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_code",productId);
        List<ProductComment> productComments = productCommentMapper.selectList(queryWrapper);
        return getSameResult( productComments);
    }

    /**
     * list查询根据标签查询该商品的所有评论
     * @param tagName
     * @return
     */
    @Override
    public List<ProductCommentDto> lookAllProductCommentsByTagName(ProductTagNameLookCommentParam tagName) {
        log.info("根据参数{}查询整个标签内容",tagName);
        ListOperations<String, ProductCommentDto> productCommentDtoListOperations = redisTemplate.opsForList();
        List<ProductCommentDto> tagNamesProductComents = productCommentDtoListOperations.range("TagNamesProductComents", 0, -1);

        if(ObjectUtils.isEmpty(tagNamesProductComents)) {
            synchronized (this){
                tagNamesProductComents = productCommentDtoListOperations.range("TagNamesProductComents", 0, -1);
                if(ObjectUtils.isEmpty(tagNamesProductComents)){
                    //从数据库中获取数据
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
                        QueryWrapper<ProductComment> queryWrapper2 = new QueryWrapper<>();
                        //删选出只有商品的评论编号
                        List<String> productCodes=new ArrayList<>();
                        commentTagConnections.forEach(commentTagConnection -> {
                            if("PR".equals(commentTagConnection.getCommentCode().substring(0,2))){
                                productCodes.add(commentTagConnection.getCommentCode());
                            }
                        });
                        for(int i=0;i<productCodes.size();i++){
                            queryWrapper2.or().eq("comment_pcode",productCodes.get(i));
                        }
                        queryWrapper2.eq("product_code",tagName.getProductCode());
                        List<ProductComment> productComments = productCommentMapper.selectList(queryWrapper2);
                        List<ProductCommentDto> sameResult = getSameResult(productComments);
                        for (int i = 0; i < sameResult.size(); i++) {
                            productCommentDtoListOperations.rightPush("TagNamesProductComents",sameResult.get(i));
                        }
                        System.out.println("mysql查出-----------");
                        System.out.println(sameResult);
                        return sameResult;
                    }
                }
            }
        }
        ArrayList<ProductCommentDto> neesProductComments=new ArrayList<>();
        for(Object productCommentDto:tagNamesProductComents){
            String s = JSON.toJSONString(productCommentDto);
            ProductCommentDto reverseClassify= JSONObject.parseObject(s, ProductCommentDto.class);
            neesProductComments.add(reverseClassify);
        }
        System.out.println("redis查出------------"+neesProductComments+"\n");
        return neesProductComments;
    }

    /**
     * 查询某种商品下面的所有评论
     * @param pageParam
     * @return
     */
    @Override
    public IPage<ProductCommentDto> lookSomeProductComments(CommentPageParam pageParam){
        Page<ProductComment> page = new Page<>(pageParam.getCurrent(), pageParam.getSize());
        QueryWrapper<ProductComment> wrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(pageParam.getId())){
             wrapper.eq("product_code",pageParam.getId());
        }
        Page<ProductComment> productCommentPage = productCommentMapper.selectPage(page, wrapper);
        IPage<ProductCommentDto> productCommentDtos=new Page<>(pageParam.getCurrent(), pageParam.getSize());
        productCommentDtos.setTotal(productCommentPage.getTotal());
        productCommentDtos.setPages(productCommentPage.getPages());
        List<ProductCommentDto> productCommentDtos1 = BeanCopyUtil.copyList(productCommentPage.getRecords(), ProductCommentDto::new);
        for (int i=0;i<productCommentPage.getRecords().size();i++){
            JSONObject jsonObject = JSONObject.parseObject(productCommentPage.getRecords().get(i).getCommentImage());
            productCommentDtos1.get(i).setCommentImages(jsonObject);
            productCommentDtos1.get(i).setCommentTimes(new Date(productCommentDtos1.get(i).getCommentTime()));
        }
        productCommentDtos.setRecords(productCommentDtos1);
        return productCommentDtos;
    }




}
