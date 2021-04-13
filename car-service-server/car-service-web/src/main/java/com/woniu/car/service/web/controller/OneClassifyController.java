package com.woniu.car.service.web.controller;


import com.woniu.car.commons.core.code.ResultEnum;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.items.model.entity.OneClassify;
import com.woniu.car.items.model.param.DeleteOneClassifyByIdParam;
import com.woniu.car.items.model.param.OneClassifyNameParam;
import com.woniu.car.items.model.param.UpdateOneClassifyByIdParam;
import com.woniu.car.service.web.service.OneClassifyService;
import io.swagger.annotations.*;
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
@RequestMapping("/one_classify")
@Api(tags = "一级服务分类接口信息")//用于描述接口类的相关信息，作用于类上
public class OneClassifyController {

    @Resource
    private OneClassifyService oneClassifyService;
   /**
    * @Author HuangZhengXing
    * @Description TODO 新增一级服务分类
    * @Date  2021/4/12
    * @Param [oneClassifyNameParam]
    * @return com.woniu.car.commons.core.dto.ResultEntity
    **/
   @PostMapping("/api/add_one_classify")
   @ApiOperation(value = "新增一级服务分类",notes = "data为true时新增成功,false时生成失败")
   @ApiImplicitParam(name = "oneClassifyNameParam",value = "接收新增一级服务分类对象",required = true,dataType = "OneClassifyNameParam")
   public ResultEntity addService(@RequestBody OneClassifyNameParam oneClassifyNameParam){
       OneClassify oneClassify = new OneClassify();
       oneClassify.setOneClassifyName(oneClassifyNameParam.getOneClassifyName());
       if (oneClassifyService.addOneClassifyService(oneClassify)){
           return ResultEntity.buildSuccessEntity().setMessage("新增成功");
       }else {
           return ResultEntity.buildFailEntity().setMessage("新增失败").setCode(ResultEnum.RES_FAIL.getCode()).setFlag(false);
       }
   }
   /**
    * @Author HuangZhengXing
    * @Description TODO 查询所有一级分类
    * @Date  2021/4/12
    * @Param []
    * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.items.model.entity.OneClassify>>
    **/
   @GetMapping("/find_one_classify_all")
   @ApiOperation(value = "查询所有一级分类")
   @ApiImplicitParam(name = "null",value = "不需要携带参数",required = false)
    public ResultEntity<List<OneClassify>> getOneClassifyAll(){
       List<OneClassify> oneClassifyList = oneClassifyService.listOneClassifyServiceAll();
       return ResultEntity.buildListSuccessEntity(OneClassify.class).setMessage("查询所有一级分类成功").setCode(200).setData(oneClassifyList);
   }
   /**
    * @Author HuangZhengXing
    * @Description TODO 根据一级分类id修改一级分类信息
    * @Date  2021/4/12
    * @Param [updateOneClassifyByIdParam]
    * @return com.woniu.car.commons.core.dto.ResultEntity
    **/
   @PutMapping("/api/update_one_classify")
   @ApiOperation(value = "根据一级分类id修改一级分类信息")
   @ApiImplicitParam(name = "updateOneClassifyByIdParam",value = "接收需要修改的一级服务分类id",required = true,dataType = "UpdateOneClassifyByIdParam")
    public ResultEntity updateOneClassifyById(@RequestBody UpdateOneClassifyByIdParam updateOneClassifyByIdParam){
       OneClassify oneClassify = new OneClassify();
       BeanUtils.copyProperties(updateOneClassifyByIdParam,oneClassify);
       System.out.println("转换成功");
       System.out.println(oneClassify);
       boolean b = oneClassifyService.updateOneClassifyServiceById(oneClassify);
       if (b){
           return ResultEntity.buildSuccessEntity().setMessage("修改成功");
       }else{
           return ResultEntity.buildFailEntity().setMessage("修改失败").setCode(ResultEnum.RES_FAIL.getCode()).setFlag(false);
       }
   }
   
   /**
    * @Author HuangZhengXing
    * @Description TODO 根据一级分类id删除一级分类信息
    * @Date  2021/4/12
    * @Param [deleteOneClassifyByIdParam]
    * @return com.woniu.car.commons.core.dto.ResultEntity
    **/
   @DeleteMapping("/api/delete_one_classify")
   @ApiOperation(value = "根据一级分类id删除一级分类信息")
   @ApiImplicitParam(name = "deleteOneClassifyByIdParam",value = "接收需要删除一级服务分类的id",required = true,dataType = "DeleteOneClassifyByIdParam")
    public ResultEntity deleteOneClassifyById(@RequestBody DeleteOneClassifyByIdParam deleteOneClassifyByIdParam){
       OneClassify oneClassify = new OneClassify();
       BeanUtils.copyProperties(deleteOneClassifyByIdParam,oneClassify);
       System.out.println(oneClassify.getOneClassifyId());
       boolean b = oneClassifyService.deleteOneClassifyServiceById(oneClassify);
       if (b){
           return ResultEntity.buildSuccessEntity().setMessage("删除成功");
       }else {
           return ResultEntity.buildFailEntity().setMessage("删除失败").setCode(ResultEnum.RES_FAIL.getCode()).setFlag(false);
       }
   }
}

