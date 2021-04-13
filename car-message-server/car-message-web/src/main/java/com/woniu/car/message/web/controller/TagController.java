package com.woniu.car.message.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.code.ResultEnum;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.message.model.param.AddTagsForCommentParam;
import com.woniu.car.message.web.domain.Tag;
import com.woniu.car.message.web.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/tag")
@Api(tags = "标签服务接口信息")
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 后台查询所有的编号列表
     */
    @GetMapping("all_tags")
    @ApiOperation(value = "查询所有标签",notes = "<span style='color:red;'>用来查询所有标签的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "查询所有标签列表成功"),
            @ApiResponse(code=500,message = "查询所有标签列表失败")
    })
    public ResultEntity<List<Tag>> findListTags(){
        List<Tag> tagslist = tagService.list();
        if(!ObjectUtils.isEmpty(tagslist)){
            return ResultEntity.buildListEntity(Tag.class)
                    .setData(tagslist)
                    .setMessage("查询所有标签列表成功")
                    .setCode(ConstCode.Get_Tags_SUCCESS)
                    .setFlag(ResultEnum.RES_SUCCESS.getFlag());
        }
        return ResultEntity.buildListEntity(Tag.class)
                .setMessage("查询所有标签列表失败")
                .setCode(ConstCode.Get_Tags_Fail)
                .setFlag(ResultEnum.RES_FAIL.getFlag());
    }

    @PostMapping("add_tags")
    @ApiOperation(value = "添加标签",notes = "<span style='color:red;'>用来添加标签的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1604,message = "给某一条评论添加标签成功"),
            @ApiResponse(code=1605,message = "该评论已经添加过不是全部评论标签以外的标签"),
            @ApiResponse(code=1603,message = "给某一条评论添加标签失败")

    })
    public ResultEntity<?> addListTags(@RequestBody AddTagsForCommentParam addTagsParam){
        System.out.println("add-------------------"+addTagsParam);
        Integer isAdd=tagService.addSomeTagsForComment(addTagsParam);
        if(isAdd==2){
            return ResultEntity.buildSuccessEntity().setMessage("给某一条评论添加标签成功").setCode(ConstCode.ADD_Tags_SUCCESS);
        }else if(isAdd==1){
            return ResultEntity.buildFailEntity().setMessage("该评论已经添加过不是全部评论标签以外的标签").setCode(ConstCode.Last_ADD_Tags);
        }
       return ResultEntity.buildFailEntity().setMessage("给某一条评论添加标签失败").setCode(ConstCode.ADD_Tags_Fail);
    }




















}

