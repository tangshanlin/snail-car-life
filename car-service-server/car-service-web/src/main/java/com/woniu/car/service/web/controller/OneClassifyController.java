package com.woniu.car.service.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.code.ResultEnum;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.items.model.entity.OneClassify;
import com.woniu.car.items.model.param.AddOneClassifyImageParam;
import com.woniu.car.items.model.param.DeleteOneClassifyByIdParam;
import com.woniu.car.items.model.param.OneClassifyNameParam;
import com.woniu.car.items.model.param.UpdateOneClassifyByIdParam;
import com.woniu.car.service.web.service.OneClassifyService;
import com.woniu.car.service.web.util.ServiceFileUpload;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private ServiceFileUpload serviceFileUpload;
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
       System.out.println(oneClassifyNameParam);
       if (ObjectUtils.isEmpty(oneClassifyNameParam))return ResultEntity.buildFailEntity().setMessage("输入为空，或输入有误").setFlag(false).setCode(ConstCode.LAST_STAGE);
       OneClassify oneClassify = new OneClassify();
       BeanUtils.copyProperties(oneClassifyNameParam,oneClassify);
       System.out.println(oneClassify);
       int i = oneClassifyService.addOneClassifyService(oneClassify);
       if (i>0){
           return ResultEntity.buildSuccessEntity().setMessage("新增成功");
       }else if (i == -10){
           return ResultEntity.buildFailEntity().setMessage("名称已存在").setFlag(false).setCode(ConstCode.NAME_ALREADY_EXISTS);
       } else {
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
       if (ObjectUtils.isEmpty(oneClassifyList)){
           return ResultEntity.buildListSuccessEntity(OneClassify.class).setMessage("查询成功，结果为空").setCode(ConstCode.ACCESS_SUCCESS).setData(oneClassifyList);
       }
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
       if (ObjectUtils.isEmpty(updateOneClassifyByIdParam.getOneClassifyId())){
           return ResultEntity.buildFailEntity().setMessage("输入为空，或输入有误").setFlag(false).setCode(ConstCode.LAST_STAGE);
       }else if (ObjectUtils.isEmpty(updateOneClassifyByIdParam.getOneClassifyName())){
           return ResultEntity.buildFailEntity().setMessage("输入为空，或输入有误").setFlag(false).setCode(ConstCode.LAST_STAGE);
       }
       OneClassify oneClassify = new OneClassify();
       BeanUtils.copyProperties(updateOneClassifyByIdParam,oneClassify);
       System.out.println("OneClassify"+":"+oneClassify);
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
       if (ObjectUtils.isEmpty(deleteOneClassifyByIdParam.getOneClassifyId())) return ResultEntity.buildFailEntity().setMessage("输入为空，或输入有误").setFlag(false).setCode(ConstCode.LAST_STAGE);
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

    /**
     * @Author HuangZhengXing
     * @Description TODO 一级分类单张图片上传
     * @Date  2021/4/15
     * @Param [addOneClassifyImageParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PostMapping("/uploading_one_classify_image")
    @ApiOperation(value = "一级分类单张图片上传")
    public ResultEntity uploadingStationImage(AddOneClassifyImageParam addOneClassifyImageParam){
        if (ObjectUtils.isEmpty(addOneClassifyImageParam.getOneClassifyImage())){
            return ResultEntity.buildFailEntity().setMessage("图片为空").setCode(ConstCode.LAST_STAGE).setFlag(false);
        }else {
            if (addOneClassifyImageParam.getOneClassifyImage().length>0){
                MultipartFile[] files = addOneClassifyImageParam.getOneClassifyImage();
                //将文件上传到minio服务器上
                ArrayList<String> stationImageList = serviceFileUpload.upload(files);
                //返回图片地址
                String oneClassifyImg = stationImageList.get(0);
                System.out.println(oneClassifyImg);
                return ResultEntity.buildSuccessEntity(String.class).setCode(ConstCode.ACCESS_SUCCESS).setData(oneClassifyImg).setMessage("图片上传成功");
            }else {
                return ResultEntity.buildFailEntity().setMessage("图片为空").setCode(ConstCode.LAST_STAGE).setFlag(false);
            }
        }

    }
}

