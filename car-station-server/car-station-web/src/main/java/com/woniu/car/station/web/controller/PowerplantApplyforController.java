package com.woniu.car.station.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.core.exception.CarException;
import com.woniu.car.station.model.dto.PowerplantApplyforDto;
import com.woniu.car.station.model.dto.PowerplantApplyforVoDto;
import com.woniu.car.station.model.entity.PowerplantApplyfor;
import com.woniu.car.station.model.finalcode.PowerplantApplyforStatus;
import com.woniu.car.station.model.param.*;
import com.woniu.car.station.web.service.PowerplantApplyforService;
import com.woniu.car.station.web.util.StationFileUpload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
 * @author HZX
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/powerplant_applyfor")
@Api(tags = "充电服务电站申请接口信息")//用于描述接口类的相关信息,作用于类上
public class PowerplantApplyforController {

    @Resource
    private PowerplantApplyforService powerplantApplyforService;
    @Resource
    private StationFileUpload stationFileUpload;
    /*
     * @Author WangPeng
     * @Description TODO HZX
     * @Date  2021/4/9
     * @Param [addPowerplantApplyforParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PostMapping("/api/add_powerplant_applyfor")
    @ApiOperation(value = "新增电站申请表",notes = "Flag为true时新增成功，false为失败")
    @ApiImplicitParam(name = "addPowerplantApplyforParam",value = "接收新增电站申请表信息",required = true,dataType = "AddPowerplantApplyforParam")
    public ResultEntity addPowerplantApplyfor(@RequestBody AddPowerplantApplyforParam addPowerplantApplyforParam){
        System.out.println(addPowerplantApplyforParam);
        //复制对象
        //new一个新对象用来接收param前端传的参数
        PowerplantApplyfor powerplantApplyfor = new PowerplantApplyfor();
        BeanUtils.copyProperties(addPowerplantApplyforParam, powerplantApplyfor);
        System.out.println(powerplantApplyfor);
        int result = powerplantApplyforService.addPowerplantApplyfor(powerplantApplyfor);
        if (result > 0){
            return ResultEntity.buildSuccessEntity().setMessage("新增电站申请表信息成功").setCode(ConstCode.ACCESS_SUCCESS);
        }else  if (result == -10){
            return ResultEntity.buildFailEntity().setMessage("电站名称已存在").setCode(ConstCode.NAME_ALREADY_EXISTS).setFlag(false);
        } else {
            throw new CarException("新增电站信息失败",500);
        }
    }
    /*
     * @Author WangPeng
     * @Description TODO HZX
     * @Date  2021/4/9
     * @Param []
     * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.station.model.entity.PowerplantApplyfor>>
     **/
    @GetMapping("/api/list_powerplantapplyfor")
    @ApiOperation(value = "查询所有电站申请表",notes = "不需要填入参数")
    public ResultEntity<List<PowerplantApplyforVoDto>> listPowerplantApplyfor(){
        List<PowerplantApplyforVoDto> powerplantApplyforList = powerplantApplyforService.listPowerplantApplyforAll();
        if (ObjectUtils.isEmpty(powerplantApplyforList)) return ResultEntity.buildListSuccessEntity(PowerplantApplyforVoDto.class).setMessage("查询所有电站申请成功,结果为空").setCode(ConstCode.ACCESS_SUCCESS).setData(powerplantApplyforList);
        return ResultEntity.buildListSuccessEntity(PowerplantApplyforVoDto.class).setMessage("查询所有电站申请成功").setCode(ConstCode.ACCESS_SUCCESS).setData(powerplantApplyforList);
    }
    /*
     * @Author WangPeng
     * @Description TODO HZX
     * @Date  2021/4/9
     * @Param [getOnePowerplantApplyforParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PostMapping("/get_powerplantapplyfor")
    @ApiOperation(value = "查询具体电站申请表",notes = "Flag为true时查询成功，false为失败")
    @ApiImplicitParam(name = "getOnePowerplantApplyforParam",value = "要查询具体电站申请表信息的id",required = true,dataType = "GetOnePowerplantApplyforParam")
    public ResultEntity getPowerplantApplyfor(@RequestBody GetOnePowerplantApplyforParam getOnePowerplantApplyforParam){
        if (ObjectUtils.isEmpty(getOnePowerplantApplyforParam.getPowerplantApplyforId())) return ResultEntity.buildFailEntity().setMessage("输入为空，或有误").setFlag(false).setCode(ConstCode.LAST_STAGE);
        PowerplantApplyfor powerplantApplyfor = new PowerplantApplyfor();
        //复制对象
        BeanUtils.copyProperties(getOnePowerplantApplyforParam,powerplantApplyfor);
        System.out.println(powerplantApplyfor);
        //查询之后的返回值
        PowerplantApplyforVoDto onePowerplantApplyforById = powerplantApplyforService.getOnePowerplantApplyforById(powerplantApplyfor);
        System.out.println(onePowerplantApplyforById);
        if (ObjectUtils.isEmpty(onePowerplantApplyforById))return ResultEntity.buildSuccessEntity(PowerplantApplyforVoDto.class).setMessage("查询具体电站信息成功,结果为空").setCode(ConstCode.ACCESS_SUCCESS).setData(onePowerplantApplyforById);
        return ResultEntity.buildSuccessEntity(PowerplantApplyforVoDto.class).setMessage("查询具体电站信息成功").setCode(ConstCode.ACCESS_SUCCESS).setData(onePowerplantApplyforById);
    }
    /*
     * @Author WangPeng
     * @Description TODO HZX
     * @Date  2021/4/9
     * @Param [updatePowerplantApplyforStatusParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PutMapping("/api/update_powerplant_applyfor")
    @ApiOperation(value = "修改电站申请表的审核状态",notes = "要传入电站申请表的id和要修改的审核状态 0未审核 1审核通过 2审核未通过")
    @ApiImplicitParam(name = "updatePowerplantApplyforStatusParam",value = "要修改具体电站申请表审核状态的id",required = true,dataType = "UpdatePowerplantApplyforStatusParam")
    public ResultEntity updatePowerplantApplyforStatus(@RequestBody UpdatePowerplantApplyforStatusParam updatePowerplantApplyforStatusParam){
        if (ObjectUtils.isEmpty(updatePowerplantApplyforStatusParam.getPowerplantApplyforId())){
            return ResultEntity.buildEntity().setMessage("输入为空，或输入错误").setFlag(false).setCode(ConstCode.LAST_STAGE);
        }else if (ObjectUtils.isEmpty(updatePowerplantApplyforStatusParam.getPowerplantApplyforStatus())){
            return ResultEntity.buildEntity().setMessage("输入为空，或输入错误").setFlag(false).setCode(ConstCode.LAST_STAGE);
        }
        PowerplantApplyfor powerplantApplyfor = new PowerplantApplyfor();
        BeanUtils.copyProperties(updatePowerplantApplyforStatusParam,powerplantApplyfor);
        boolean b = false;
        System.out.println(powerplantApplyfor);
        if (updatePowerplantApplyforStatusParam.getPowerplantApplyforStatus() == 1){
            powerplantApplyfor.setPowerplantApplyforStatus(PowerplantApplyforStatus.APPLYFORSUCCESS);
           if (powerplantApplyforService.updatePowerplantApplyforStatusById(powerplantApplyfor)) {
               return ResultEntity.buildSuccessEntity().setMessage("修改审核状态成功").setCode(ConstCode.ACCESS_SUCCESS);
           }
        }else {
            powerplantApplyfor.setPowerplantApplyforStatus(PowerplantApplyforStatus.APPLYFORFAIL);
        }
        throw new CarException("修改电站审核失败",500);
    }
    /*
     * @Author WangPeng
     * @Description TODO HZX
     * @Date  2021/4/9
     * @Param [deletePowerplantApplyforParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @DeleteMapping("/api/delete_powerplant_applyfor")
    @ApiOperation(value = "删除电站申请表",notes = "Flag为true时删除成功，false为失败")
    @ApiImplicitParam(name = "deletePowerplantApplyforParam",value = "要删除具体电站申请表的id",required = true,dataType = "DeletePowerplantApplyforParam")
    public ResultEntity deletePowerplantApplyfor(@RequestBody DeletePowerplantApplyforParam deletePowerplantApplyforParam){
        if (ObjectUtils.isEmpty(deletePowerplantApplyforParam.getPowerplantApplyforId())) return ResultEntity.buildEntity().setMessage("输入为空，或输入错误").setFlag(false).setCode(ConstCode.LAST_STAGE);
        PowerplantApplyfor powerplantApplyfor = new PowerplantApplyfor();
        BeanUtils.copyProperties(deletePowerplantApplyforParam,powerplantApplyfor);
        System.out.println(powerplantApplyfor);
        boolean b = powerplantApplyforService.deletePowerplantApplyforById(powerplantApplyfor);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("删除成功").setCode(ConstCode.ACCESS_SUCCESS);
        throw new CarException("删除电站申请表失败",500);
    }

    /**
     * @Author HuangZhengXing
     * @Description TODO 电站申请表单张图片上传
     * @Date  2021/4/13
     * @Param [uploadPowerplantApplyforImageParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PostMapping("/uploading_powerplant_applyfor_Image")
    @ApiOperation(value = "电站申请表单张图片上传")
    public ResultEntity uploadingPowerplantApplyforImage(UploadPowerplantApplyforImageParam uploadPowerplantApplyforImageParam){
        if (uploadPowerplantApplyforImageParam.getPowerplantApplyforImage().length>0){
            MultipartFile[] files = uploadPowerplantApplyforImageParam.getPowerplantApplyforImage();
            //将文件上传到minio服务器上
            ArrayList<String> stationImageList = stationFileUpload.upload(files);
            //返回图片地址
            String stationimg = stationImageList.get(0);
            System.out.println(stationimg);
            return ResultEntity.buildSuccessEntity(String.class).setCode(ConstCode.ACCESS_SUCCESS).setData(stationimg).setMessage("图片上传成功");
        }else {
            throw new CarException("图片为空",500);
        }
    }
}

