package com.woniu.car.message.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.message.client.UserClient;
import com.woniu.car.message.model.dto.ServiceCommentDto;
import com.woniu.car.message.model.dto.UserInformation;
import com.woniu.car.message.model.feign.CommentPageParam;
import com.woniu.car.message.model.feign.ServiceTagNameLookCommentParam;
import com.woniu.car.message.model.param.*;
import com.woniu.car.message.web.service.ServiceCommentService;
import io.swagger.annotations.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务评论 前端控制器
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/service-comment")
@Api(tags = "服务评论服务接口信息")
public class ServiceCommentController {

    @Resource
    private ServiceCommentService serviceCommentService;
    @Resource
    private UserClient userClient;

    /**
     * 添加服务评论
     * @param param
     * @return
     */
    @PostMapping("upload")
    @ApiOperation(value = "添加服务评论",notes = "<span style='color:red;'>用来添加服务评论的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加服务评论成功"),
            @ApiResponse(code=500,message = "添加服务评论失败")
    })
    public ResultEntity addServiceComment(ServiceCommentParam param){
        Boolean isAddProduct=serviceCommentService.addServiceComment(param);
        if (isAddProduct){
            return new ResultEntity<>().buildSuccessEntity().setMessage("添加服务评价成功");
        }
        return new ResultEntity<>().buildFailEntity().setMessage("添加服务评价失败");
    }
    /**
     * 根据评论编号删除服务评论
     * @param commentSeCode
     * @return
     */
    @DeleteMapping("delete")
    @ApiOperation(value = "删除服务评论",notes = "<span style='color:red;'>用来删除服务评论的接口</span>")
    //@ApiResponses用于描述响应状态信息
    @ApiResponses({
            @ApiResponse(code = 200,message = "删除服务评论成功"),
            @ApiResponse(code=500,message = "添加服务评论成功")
    })
    //@ApiImplicitParams用于描述接口参数
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "commentSeCode",value = "商品服务编码",dataType = "String",paramType = "path",example = "SEca1f4986-cd87-4bbc-80b1-f6ae7aeebe19"),
    })
    public ResultEntity deleteServiceComment(@PathVariable DeleteCommentParam commentSeCode){
        if (!ObjectUtils.isEmpty(commentSeCode)){
            Boolean isDeleteProduct=serviceCommentService.deleteServiceComment(commentSeCode.getCommentPCode());
            if (isDeleteProduct){
                return new ResultEntity<>().buildSuccessEntity().setMessage("删除服务评价成功");
            }
        }
        return new ResultEntity<>().buildFailEntity().setMessage("添加服务评价失败");
    }
    /* *
     * @Author Lints
     * @Date 2021/4/5/005 18:53
     * @Description 查询自己的所有服务评论
     * @Param
     * @Return
     * @Since version-1.0
     */
    @GetMapping("find_yourself_service_comment")
    @ApiOperation(value = "查询自己的所有评论",notes = "<span style='color:red;'>用来查询自己的所有评论的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "查询自己的所有评论成功"),
            @ApiResponse(code=500,message = "查询自己的所有评论失败")
    })
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "userId",value = "用户编号",dataType = "Integer",paramType = "path",example = "1"),
    })
    public ResultEntity<List<ServiceCommentDto>> findYourselfServiceComment(UserIdParam userId){
//        UserInformation data = userClient.selectUerInformation().getData();
//        if(!ObjectUtils.isEmpty(data)){
//            Integer userId = data.getUserId();
//
//        }
        if (!ObjectUtils.isEmpty(userId)){
            List<ServiceCommentDto> serviceComments=serviceCommentService.lookUserServiceComments(userId.getUserId());
            if (!ObjectUtils.isEmpty(serviceComments)){
                return ResultEntity.buildListSuccessEntity(ServiceCommentDto.class).setMessage("查询自己的所有服务评论成功")
                        .setData(serviceComments);
            }
        }
        return ResultEntity.buildListFailEntity(ServiceCommentDto.class).setMessage("查询自己的所有服务评论失败");
    }

    /* *
     * @Author Lints
     * @Date 2021/4/5/005 18:53
     * @Description 根据门店下面的服务分类名字查询某服务分类下面的所有评论
     * @Param
     * @Return
     * @Since version-1.0
     */
    @GetMapping("/list_some_service_comment_by_service_name")
    @ApiOperation(value = "查询某服务的所有评论",notes = "<span style='color:red;'>用来查询某服务的所有评论的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "查询某服务的所有评论成功"),
            @ApiResponse(code=500,message = "查询某服务的所有评论失败")
    })
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "shopId",value = "商品编号",dataType = "Integer",paramType = "path",example = "1"),
            @ApiImplicitParam(name = "serviceName",value = "服务名字",dataType = "String",paramType = "path",example = "精汽车")

    })
    public ResultEntity<List<ServiceCommentDto>> findSomeServiceCommentsByServiceName(SeviceNameCommentParam param){
        if (!ObjectUtils.isEmpty(param)){
            List<ServiceCommentDto> productCommentDtos=serviceCommentService.lookSomeServiceCommentsByServiceName(param);
            if (!ObjectUtils.isEmpty(productCommentDtos)){
                return ResultEntity.buildListSuccessEntity(ServiceCommentDto.class).setMessage("查询某服务分类下面的所有评论成功")
                        .setData(productCommentDtos);
            }
        }
        return ResultEntity.buildListFailEntity(ServiceCommentDto.class).setMessage("查询某服务分类下面的所有评论失败");
    }

    /* *
     * @Author Lints
     * @Date 2021/4/5/005 18:53
     * @Description list查询根据标签查询该商品的评论
     * @Param
     * @Return
     * @Since version-1.0
     */

    @GetMapping("/find_all_service_comment_by_tag_name")
    @ApiOperation(value = "根据标签查询某服务的所有评论",notes = "<span style='color:red;'>用来查询某服务的所有评论的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "根据标签查询该服务的评论成功"),
            @ApiResponse(code=1607,message = "没有该服务标签的评论"),
            @ApiResponse(code=500,message = "根据标签查询该服务的评论失败"),
    })
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "serviceCode",value = "服务编号",dataType = "Integer",paramType = "path",example = "1"),
            @ApiImplicitParam(name = "tagName",value = "标签名字",dataType = "String",paramType = "path",example = "所有标签")
    })
    public ResultEntity<List<ServiceCommentDto>> findAllServiceCommentByTagName(ServiceTagNameLookCommentParam tagName){
        if (!ObjectUtils.isEmpty(tagName)){
            List<ServiceCommentDto> productCommentDtos=serviceCommentService.lookAllServiceCommentsByTagName(tagName);
            if (!ObjectUtils.isEmpty(productCommentDtos)){
                return ResultEntity.buildListSuccessEntity(ServiceCommentDto.class).setMessage("根据标签查询该服务的评论成功")
                        .setData(productCommentDtos);
            }else{
                return ResultEntity.buildListFailEntity(ServiceCommentDto.class).setMessage("没有该服务标签的评论").
                        setCode(ConstCode.No_Service_BY_Tags);
            }
        }
        return ResultEntity.buildListFailEntity(ServiceCommentDto.class).setMessage("根据标签查询该服务的评论失败");
    }

    /* *
     * @Author Lints
     * @Date 2021/4/5/005 18:53
     * @Description 根据门店编号查询某门店下面的所有评论
     * @Param
     * @Return
     * @Since version-1.0
     */
    @GetMapping("/api/find_some_service_comment_by_shop_id")
    public ResultEntity<IPage<ServiceCommentDto>> findSomeServiceComments(CommentPageParam pageParam){
        if (!ObjectUtils.isEmpty(pageParam)){
            IPage<ServiceCommentDto> productCommentDtos=serviceCommentService.lookSomeServiceCommentsByShopId(pageParam);
            if (!ObjectUtils.isEmpty(productCommentDtos)){
                return ResultEntity.buildPageListSuccessEntity(ServiceCommentDto.class).setMessage("根据门店编号查询某门店下面的所有评论成功")
                        .setData(productCommentDtos);
            }
        }
        return ResultEntity.buildPageListFailEntity(ServiceCommentDto.class).setMessage("根据门店编号查询某门店下面的所有评论失败");
    }
















}

