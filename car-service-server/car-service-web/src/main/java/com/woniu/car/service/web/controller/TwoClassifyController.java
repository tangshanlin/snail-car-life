package com.woniu.car.service.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.items.model.entity.TwoClassify;
import com.woniu.car.items.model.param.twoclassify.AddTwoClassifyParam;
import com.woniu.car.items.model.param.twoclassify.DeleteClassifyParam;
import com.woniu.car.items.model.param.twoclassify.ListTwoClassifyParam;
import com.woniu.car.items.model.param.twoclassify.UpdateTwoClassifyParam;
import com.woniu.car.service.web.service.TwoClassifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sokyo
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/two_classify")
@Api(tags = "二级服务分类接口信息")//用于描述接口类的相关信息,作用于类上
public class TwoClassifyController {
    @Resource
    private TwoClassifyService twoClassifyService;

    @PostMapping("/list_two_classify")
    @ApiOperation(value = "根据一级分类id查询所有二级分类",notes = "传入要查询的一级分类id")
    @ApiImplicitParam(name = "listTwoClassifyParam",value = "要查询的一级分类id",required = true,dataType = "ListTwoClassifyParam")
    public ResultEntity<List<TwoClassify>> listTwoClassifyByOneClassifyId(@RequestBody ListTwoClassifyParam listTwoClassifyParam){
        TwoClassify twoClassify = new TwoClassify();
        BeanUtils.copyProperties(listTwoClassifyParam,twoClassify);
        System.out.println("twoClassify"+":"+twoClassify);
        List<TwoClassify> twoClassifies = twoClassifyService.selectTwoClassifyServiceAll(twoClassify);
        return ResultEntity.buildListSuccessEntity(TwoClassify.class).setMessage("查询成功").setCode(ConstCode.ACCESS_SUCCESS);
    }

    @PostMapping("/api/add_two_classify")
    @ApiOperation(value = "接收新增二级分类信息",notes = "传入要新增二级分类信息")
    @ApiImplicitParam(name = "addTwoClassifyParam",value = "新增二级分类信息",required = true,dataType = "AddTwoClassifyParam")
    public ResultEntity addTwoClassify(@RequestBody AddTwoClassifyParam addTwoClassifyParam){
        TwoClassify twoClassify = new TwoClassify();
        BeanUtils.copyProperties(addTwoClassifyParam,twoClassify);
        System.out.println("twoClassify"+":"+twoClassify);
        int i = twoClassifyService.insertTwoClassifyService(twoClassify);
        if (i>0){
            return ResultEntity.buildSuccessEntity().setMessage("新增二级服务分类成功").setCode(ConstCode.ACCESS_SUCCESS);
        }else if (i==-10){
            return ResultEntity.buildFailEntity().setMessage("名称已存在").setCode(ConstCode.LAST_STAGE).setFlag(false);
        }
        return ResultEntity.buildFailEntity().setMessage("新增失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }

    @PutMapping("/api/update_two_classify")
    @ApiOperation(value = "根据二级分类id修改二级分类名称",notes = "传入要修改二级分类id和名称")
    @ApiImplicitParam(name = "updateTwoClassifyParam",value = "要修改二级分类id和名称",required = true,dataType = "UpdateTwoClassifyParam")
    public ResultEntity updateTwoClassifyName(@RequestBody UpdateTwoClassifyParam updateTwoClassifyParam){
        TwoClassify twoClassify = new TwoClassify();
        BeanUtils.copyProperties(updateTwoClassifyParam,twoClassify);
        System.out.println("twoClassify"+":"+twoClassify);
        boolean b = twoClassifyService.updateTwoClassifyServiceById(twoClassify);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("修改成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("修改失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }

    @DeleteMapping("/api/delete_two_classify")
    @ApiOperation(value = "根据二级分类id删除二级分类",notes = "传入要删除二级分类id和名称")
    @ApiImplicitParam(name = "deleteClassifyParam",value = "要删除二级分类id",required = true,dataType = "DeleteClassifyParam")
    public ResultEntity deleteTwoClassifyById(@RequestBody DeleteClassifyParam deleteClassifyParam){
        TwoClassify twoClassify = new TwoClassify();
        BeanUtils.copyProperties(deleteClassifyParam,twoClassify);
        System.out.println("twoClassify"+":"+twoClassify);
        boolean b = twoClassifyService.deleteTwoClassifyServiceById(twoClassify);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("删除成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("删除失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }


}
