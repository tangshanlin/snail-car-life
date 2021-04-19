package com.woniu.car.message.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.message.client.UserClient;
import com.woniu.car.message.model.dto.ProductCommentDto;
import com.woniu.car.message.model.dto.UserInformation;
//import com.woniu.car.message.model.es.ProductCommentEs;
import com.woniu.car.message.model.feign.CommentPageParam;
import com.woniu.car.message.model.param.*;
import com.woniu.car.message.web.domain.GoodProduct;
import com.woniu.car.message.web.service.GoodProductService;
import com.woniu.car.message.web.service.ProductCommentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  商品评论  前端控制器
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/product_comment")
@Api(tags = "商品评论服务接口信息")
public class ProductCommentController {

    @Resource
    private ProductCommentService productCommentService;
    @Resource
    private GoodProductService goodProductService;
    @Resource
    private UserClient userClient;

//    @Autowired
//    private ElasticsearchOperations operations;
    /**
     * @Author Lints
     * @Date 2021/4/5/005 18:05
     * @Description 添加商品图片返回图片的地址为Json字符串
     * @Param [param]
     * @Return com.woniu.car.commons.core.dto.ResultEntity
     * @Since version-1.0
     */
    @GetMapping("add_picture")
    @ApiOperation(value = "添加商品评论图片",notes = "<span style='color:red;'>用来添加商品评论图片的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加商品评论图片成功"),
            @ApiResponse(code=500,message = "添加商品评论图片失败")
    })
    public ResultEntity<String> addProductPicture(MultipartFile[] file){
        Boolean isAddPicture=productCommentService.addProductPicture(file);
        if(isAddPicture){
            return ResultEntity.buildSuccessEntity(String.class).setMessage("添加商品评论图片成功！！！");
        }
        return ResultEntity.buildFailEntity(String.class).setMessage("添加商品评论图片成功！！！");
    }

    /**
     * @Author Lints
     * @Date 2021/4/5/005 18:05
     * @Description 给商品品添加评论
     * @Param [param]
     * @Return com.woniu.car.commons.core.dto.ResultEntity
     * @Since version-1.0
     */
    @PutMapping("/upload")
    @ApiOperation(value = "添加商品评论",notes = "<span style='color:red;'>用来添加商品评论的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加商品评论成功"),
            @ApiResponse(code=500,message = "添加商品评论失败")
    })
    public ResultEntity addProductComment(@Validated @RequestBody ProductCommentParam param){
        System.out.println(param+"=======================================");
        Boolean isAddProduct=productCommentService.addPComment(param);
        if (isAddProduct){
            return new ResultEntity<>().buildSuccessEntity().setMessage("添加商品评价成功");
        }
        return new ResultEntity<>().buildFailEntity().setMessage("添加评价失败");
    }

   /**
    * @Author Lints
    * @Date 2021/4/5/005 18:50
    * @Description 根据评论编号删除商品成功
    * @Param [commentPid]
    * @Return com.woniu.car.commons.core.dto.ResultEntity
    * @Since version-1.0
    */
    @DeleteMapping("delete")
    @ApiOperation(value = "删除商品评论",notes = "<span style='color:red;'>用来删除商品评论的接口</span>")
    //@ApiResponses用于描述响应状态信息
    @ApiResponses({
            @ApiResponse(code = 200,message = "删除商品评论成功"),
            @ApiResponse(code=500,message = "添加商品评论成功")
    })
    //@ApiImplicitParams用于描述接口参数
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "commentPCode",value = "商品评论编码",dataType = "String",paramType = "query",example = "PRf748e83b-edc0-4215-857d-6046da51a038"),
    })
    public ResultEntity deleteProductComment( @Validated DeleteCommentParam commentPCode){
        if (!ObjectUtils.isEmpty(commentPCode)){
            Boolean isDeleteProduct=productCommentService.deletePComment(commentPCode.getCommentPCode());
            if (isDeleteProduct){
                return new ResultEntity<>().buildSuccessEntity().setMessage("删除商品评价成功");
            }
        }
        return new ResultEntity<>().buildFailEntity().setMessage("添加商品评价失败");
    }

    /* *
     * @Author Lints
     * @Date 2021/4/5/005 18:53
     * @Description 根据用户编号查询自己的所有商品评论
     * @Param
     * @Return
     * @Since version-1.0
     */
    @GetMapping("/find_yourself_product_comment")
    @ApiOperation(value = "查询自己的所有评论",notes = "<span style='color:red;'>用来查询自己的所有评论的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "查询自己的所有评论成功"),
            @ApiResponse(code=500,message = "查询自己的所有评论失败")
    })
    public ResultEntity<List<ProductCommentDto>> findMyselfProductComment(){
        UserInformation data = userClient.selectUerInformation().getData();
        if(!ObjectUtils.isEmpty(data)){
            Integer userId = data.getUserId();
            if (!ObjectUtils.isEmpty(userId)){
                List<ProductCommentDto> productComments=productCommentService.lookUserPComments(userId);
                if (!ObjectUtils.isEmpty(productComments)){
                    return ResultEntity.buildListSuccessEntity(ProductCommentDto.class).setMessage("查询自己的所有评论成功")
                            .setData(productComments);
                }
            }
        }
        return ResultEntity.buildListFailEntity(ProductCommentDto.class).setMessage("查询自己的所有评论失败");
    }

    /* *
     * @Author Lints
     * @Date 2021/4/5/005 18:53
     * @Description 根据商品编号查询某商品的所有商品评论
     * @Param
     * @Return
     * @Since version-1.0
     */
    @GetMapping("/find_all_product_comment_by_product_id")
    @ApiOperation(value = "查询某商品的所有评论",notes = "<span style='color:red;'>用来查询某商品的所有评论的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "查询某商品的所有评论成功"),
            @ApiResponse(code=500,message = "查询某商品的所有评论失败")
    })
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "productId",value = "商品编号",dataType = "Integer",paramType = "path",example = "1"),
    })
    public ResultEntity<List<ProductCommentDto>> findAllProductsProductComment(@Validated ProductIdParam productId){
        if (!ObjectUtils.isEmpty(productId)){
            List<ProductCommentDto> productCommentDtos=productCommentService.lookAllProductComments(productId.getProductId());
            if (!ObjectUtils.isEmpty(productCommentDtos)){
                return ResultEntity.buildListSuccessEntity(ProductCommentDto.class).setMessage("查询某商品的所有评论成功")
                        .setData(productCommentDtos);
            }
        }
        return ResultEntity.buildListFailEntity(ProductCommentDto.class).setMessage("查询某商品的所有评论失败");
    }

    @GetMapping("/get_good_product_conception")
    public ResultEntity<String> getgetGoodProduct(Integer productId){
        QueryWrapper<GoodProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        GoodProduct goodProduct = goodProductService.getOne(queryWrapper);
        if(!ObjectUtils.isEmpty(goodProduct)){
            Integer productNums = goodProduct.getProductNums();
            Integer productGoodNum = goodProduct.getProductGoodNum();
            double goodProbability=productGoodNum*1.0/productNums*100;
            System.out.println("好评率=====>>>>>>>"+goodProbability+"%");
            return ResultEntity.buildSuccessEntity(String.class).setMessage("查询商品好评率成功")
                    .setData(goodProbability+"%");

        }
        return ResultEntity.buildFailEntity(String.class).setMessage("查询商品好评率失败");
    }




    /* *
     * @Author Lints
     * @Date 2021/4/5/005 18:53
     * @Description list查询根据标签查询该商品的评论
     * @Param
     * @Return
     * @Since version-1.0
     */
    @GetMapping("/find_all_product_comment_by_tag_name")
    @ApiOperation(value = "查询某商品的所有评论",notes = "<span style='color:red;'>用来查询某商品的所有评论的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "根据标签查询该商品的评论成功"),
            @ApiResponse(code=1606,message = "没有该商品标签的评论"),
            @ApiResponse(code=500,message = "根据标签查询该商品的评论失败"),
    })
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "productCode",value = "商品编号",dataType = "Integer",paramType = "path",example = "1"),
            @ApiImplicitParam(name = "tagName",value = "标签名字",dataType = "String",paramType = "path",example = "所有标签")
    })
    public ResultEntity<List<ProductCommentDto>> findAllProductsProductCommentByTagName(@Validated ProductTagNameLookCommentParam tagName){
        if (!ObjectUtils.isEmpty(tagName)){
            List<ProductCommentDto> productCommentDtos=productCommentService.lookAllProductCommentsByTagName(tagName);
            if (!ObjectUtils.isEmpty(productCommentDtos)){
                return ResultEntity.buildListSuccessEntity(ProductCommentDto.class).setMessage("根据标签查询该商品的评论成功")
                        .setData(productCommentDtos);
            }else{
                return ResultEntity.buildListFailEntity(ProductCommentDto.class).setMessage("没有该商品标签的评论")
                        .setCode(ConstCode.No_Peoduct_BY_Tags);
            }
        }
        return ResultEntity.buildListFailEntity(ProductCommentDto.class).setMessage("根据标签查询该商品的评论失败");
    }

    /* *
     * @Author Lints
     * @Date 2021/4/5/005 18:53
     * @Description 根据商品编号查询某商品的所有商品评论
     * @Param
     * @Return
     * @Since version-1.0
     */
    @GetMapping("/api/find_all_product_comment_by_product_id")
    @ApiOperation(value = "后台的",notes = "<span style='color:red;'>后台接口</span>")
    public ResultEntity<IPage<ProductCommentDto>> findSomeProductsProductComment(CommentPageParam pageParam){
        if (!ObjectUtils.isEmpty(pageParam)){
            IPage<ProductCommentDto> productCommentDtos=productCommentService.lookSomeProductComments(pageParam);
            if (!ObjectUtils.isEmpty(productCommentDtos)){
                return ResultEntity.buildPageListSuccessEntity(ProductCommentDto.class).setMessage("后台查询某商品的所有评论成功")
                        .setData(productCommentDtos);
            }
        }
        return ResultEntity.buildPageListFailEntity(ProductCommentDto.class).setMessage("后台查询某商品的所有评论失败");
    }


}

