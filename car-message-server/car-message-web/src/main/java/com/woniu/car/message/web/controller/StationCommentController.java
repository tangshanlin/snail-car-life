package com.woniu.car.message.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.message.model.dto.StationCommentDto;
import com.woniu.car.message.model.param.*;
import com.woniu.car.message.web.service.StationCommentService;
import io.swagger.annotations.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/station-comment")
@Api(tags = "电站评论服务接口信息")
public class StationCommentController {
    @Resource
    private StationCommentService stationCommentService;

    @PostMapping("upload")
    @ApiOperation(value = "添加电站评论",notes = "<span style='color:red;'>用来添加电站评论的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加电站评论成功"),
            @ApiResponse(code=500,message = "添加电站评论失败")
    })
    public ResultEntity addProductComment(StationCommentParam param){
        Boolean isAddProduct=stationCommentService.addStationComment(param);
        if (isAddProduct){
            return new ResultEntity<>().buildSuccessEntity().setMessage("添加电站评价成功");
        }
        return new ResultEntity<>().buildFailEntity().setMessage("添加电站评价失败");
    }

    @DeleteMapping("/delete/{commentStCode}")
    @ApiOperation(value = "删除电站评价",notes = "<span style='color:red;'>用来删除电站评价的接口</span>")
    //@ApiResponses用于描述响应状态信息
    @ApiResponses({
            @ApiResponse(code = 200,message = "删除电站评价成功"),
            @ApiResponse(code=500,message = "删除电站评价失败")
    })
    //@ApiImplicitParams用于描述接口参数
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "commentStCode",value = "电站评论编号",dataType = "String",paramType = "path",example = "SEkejveoeoifnvndimdddd"),
    })
    public ResultEntity deleteServiceComment(@PathVariable String commentStCode){
        if (!ObjectUtils.isEmpty(commentStCode)){
            Boolean isDeleteProduct=stationCommentService.deleteStationComment(commentStCode);
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
    @GetMapping("/find_yourself_station_comment")
    @ApiOperation(value = "查询自己的所有评论",notes = "<span style='color:red;'>用来查询自己的所有评论的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "查询自己的所有电站评论成功"),
            @ApiResponse(code=500,message = "查询自己的所有电站评论失败")
    })
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "userId",value = "用户编号",dataType = "Integer",paramType = "path",example = "1"),
    })
    public ResultEntity<List<StationCommentDto>> findMyselfServiceComment(UserIdParam userId){
        if (!ObjectUtils.isEmpty(userId)){
            List<StationCommentDto> serviceComments=stationCommentService.lookUserStationComments(userId.getUserId());
            if (!ObjectUtils.isEmpty(serviceComments)){
                return ResultEntity.buildListSuccessEntity(StationCommentDto.class).setMessage("查询自己的所有电站评论成功")
                        .setData(serviceComments);
            }
        }
        return ResultEntity.buildListFailEntity(StationCommentDto.class).setMessage("查询自己的所有电站评论失败");
    }


    /* *
     * @Author Lints
     * @Date 2021/4/5/005 18:53
     * @Description 根据电站编号查询某门店下面的所有评论
     * @Param
     * @Return
     * @Since version-1.0
     */
    @GetMapping("/find_some_station_comment_by_power_code")
    @ApiOperation(value = "查询某电站的所有评论",notes = "<span style='color:red;'>用来查询某电站的所有评论的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "查询某电站的所有评论成功"),
            @ApiResponse(code=500,message = "查询某电站的所有评论失败")
    })
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "powerId",value = "电站编号",dataType = "Integer",paramType = "path",example = "1"),

    })
    public ResultEntity<List<StationCommentDto>> findSomeServiceCommentss(PowerIdParam powerId){
        if (!ObjectUtils.isEmpty(powerId)){
            List<StationCommentDto> productCommentDtos=stationCommentService.lookSomeStationCommentssByPowerCode(powerId.getPowerId());
            if (!ObjectUtils.isEmpty(productCommentDtos)){
                return ResultEntity.buildListSuccessEntity(StationCommentDto.class).setMessage("根据电站编号查询某门店下面的所有评论成功")
                        .setData(productCommentDtos);
            }
        }
        return ResultEntity.buildListFailEntity(StationCommentDto.class).setMessage("根据电站编号查询某门店下面的所有评论失败");
    }

    /* *
     * @Author Lints
     * @Date 2021/4/5/005 18:53
     * @Description list查询根据标签查询该商品的评论
     * @Param
     * @Return
     * @Since version-1.0
     */

    @GetMapping("/find_all_station_comment_by_tag_name")
    @ApiOperation(value = "根据标签查询某电站的所有评论",notes = "<span style='color:red;'>用来查询某电站的所有评论的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "根据标签查询该电站的评论成功"),
            @ApiResponse(code=1608,message = "没有该电站标签的评论"),
            @ApiResponse(code=500,message = "根据标签查询该电站的评论失败"),
    })
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "commentPowerCode",value = "电站编号",dataType = "Integer",paramType = "path",example = "1"),
            @ApiImplicitParam(name = "tagName",value = "标签名字",dataType = "String",paramType = "path",example = "所有标签")
    })
    public ResultEntity<List<StationCommentDto>> findAllServiceCommentByTagName(StationTagNameLookCommentParam tagName){
        if (!ObjectUtils.isEmpty(tagName)){
            List<StationCommentDto> productCommentDtos=stationCommentService.lookAllStationCommentsByTagName(tagName);
            if (!ObjectUtils.isEmpty(productCommentDtos)){
                return ResultEntity.buildListSuccessEntity(StationCommentDto.class).setMessage("根据标签查询该服务的评论成功")
                        .setData(productCommentDtos);
            }else{
                return ResultEntity.buildListFailEntity(StationCommentDto.class).setMessage("没有该服务标签的评论");
            }
        }
        return ResultEntity.buildListFailEntity(StationCommentDto.class).setMessage("根据标签查询该服务的评论失败");
    }





    /* *
     * @Author Lints
     * @Date 2021/4/5/005 18:53
     * @Description 根据电站编号查询某门店下面的所有评论
     * @Param
     * @Return
     * @Since version-1.0
     */
    @GetMapping("/api/find_some_station_comment_by_power_code")
    public ResultEntity<IPage<StationCommentDto>> findSomeServiceComments(CommentPageParam pageParam){
        if (!ObjectUtils.isEmpty(pageParam)){
            IPage<StationCommentDto> productCommentDtos=stationCommentService.lookSomeStationCommentsByPowerCode(pageParam);
            if (!ObjectUtils.isEmpty(productCommentDtos)){
                return ResultEntity.buildPageListSuccessEntity(StationCommentDto.class).setMessage("根据电站编号查询某门店下面的所有评论成功")
                        .setData(productCommentDtos);
            }
        }
        return ResultEntity.buildPageListFailEntity(StationCommentDto.class).setMessage("根据电站编号查询某门店下面的所有评论失败");
    }

}

