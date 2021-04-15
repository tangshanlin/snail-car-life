package com.woniu.car.station.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.station.model.dto.PowerplantApplyforDto;
import com.woniu.car.station.model.entity.PowerplantApplyfor;
import com.woniu.car.station.model.finalcode.PowerplantApplyforStatus;
import com.woniu.car.station.model.param.AddPowerplantApplyforParam;
import com.woniu.car.station.model.param.DeletePowerplantApplyforParam;
import com.woniu.car.station.model.param.GetOnePowerplantApplyforParam;
import com.woniu.car.station.model.param.UpdatePowerplantApplyforStatusParam;
import com.woniu.car.station.web.service.PowerplantApplyforService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
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

    /**
     * @return com.woniu.car.commons.core.dto.ResultEntity
     * @Author WangPeng
     * @Description TODO HZX
     * @Date 2021/4/9
     * @Param [addPowerplantApplyforParam]
     **/
    @PostMapping("/api/add_powerplant_applyfor")
    @ApiOperation(value = "新增电站申请表", notes = "Flag为true时新增成功，false为失败")
    @ApiImplicitParam(name = "addPowerplantApplyforParam", value = "接收新增电站申请表信息", required = true, dataType = "AddPowerplantApplyforParam")
    public ResultEntity addPowerplantApplyfor(AddPowerplantApplyforParam addPowerplantApplyforParam) {
        System.out.println(addPowerplantApplyforParam);
        //复制对象
        //new一个新对象用来接收param前端传的参数
        PowerplantApplyforDto powerplantApplyforDto = new PowerplantApplyforDto();
        BeanUtils.copyProperties(addPowerplantApplyforParam, powerplantApplyforDto);
        System.out.println(powerplantApplyforDto);
        int result = powerplantApplyforService.addPowerplantApplyfor(powerplantApplyforDto);
        if (result > 0) {
            return ResultEntity.buildSuccessEntity().setMessage("新增电站申请表信息成功").setCode(ConstCode.ACCESS_SUCCESS);
        } else if (result == -10) {
            return ResultEntity.buildFailEntity().setMessage("电站名称已存在").setCode(ConstCode.NAME_ALREADY_EXISTS).setFlag(false);
        } else {
            return ResultEntity.buildFailEntity().setMessage("新增电站申请表信息失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
        }
    }

    /**
     * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List < com.woniu.car.station.model.entity.PowerplantApplyfor>>
     * @Author WangPeng
     * @Description TODO HZX
     * @Date 2021/4/9
     * @Param []
     **/
    @GetMapping("/api/list_powerplantapplyfor")
    @ApiOperation(value = "查询所有电站申请表", notes = "不需要填入参数")
    public ResultEntity<List<PowerplantApplyfor>> listPowerplantApplyfor() {
        List<PowerplantApplyfor> powerplantApplyforList = powerplantApplyforService.listPowerplantApplyforAll();
        return ResultEntity.buildListSuccessEntity(PowerplantApplyfor.class).setMessage("查询所有电站申请成功").setCode(ConstCode.ACCESS_SUCCESS).setData(powerplantApplyforList);
    }

    /**
     * @return com.woniu.car.commons.core.dto.ResultEntity
     * @Author WangPeng
     * @Description TODO HZX
     * @Date 2021/4/9
     * @Param [getOnePowerplantApplyforParam]
     **/
    @PostMapping("/get_powerplantapplyfor")
    @ApiOperation(value = "查询具体电站申请表", notes = "Flag为true时查询成功，false为失败")
    @ApiImplicitParam(name = "getOnePowerplantApplyforParam", value = "要查询具体电站申请表信息的id", required = true, dataType = "GetOnePowerplantApplyforParam")
    public ResultEntity getPowerplantApplyfor(@RequestBody GetOnePowerplantApplyforParam getOnePowerplantApplyforParam) {
        PowerplantApplyfor powerplantApplyfor = new PowerplantApplyfor();
        //复制对象
        BeanUtils.copyProperties(getOnePowerplantApplyforParam, powerplantApplyfor);
        System.out.println(powerplantApplyfor);
        //查询之后的返回值
        PowerplantApplyfor onePowerplantApplyforById = powerplantApplyforService.getOnePowerplantApplyforById(powerplantApplyfor);
        System.out.println(onePowerplantApplyforById);
        return ResultEntity.buildSuccessEntity(PowerplantApplyfor.class).setMessage("查询具体电站信息成功").setCode(ConstCode.ACCESS_SUCCESS).setData(onePowerplantApplyforById);
    }

    /**
     * @return com.woniu.car.commons.core.dto.ResultEntity
     * @Author WangPeng
     * @Description TODO HZX
     * @Date 2021/4/9
     * @Param [updatePowerplantApplyforStatusParam]
     **/
    @PutMapping("/api/update_powerplant_applyfor")
    @ApiOperation(value = "修改电站申请表的审核状态", notes = "要传入电站申请表的id和要修改的审核状态 0未审核 1审核通过 2审核未通过")
    @ApiImplicitParam(name = "updatePowerplantApplyforStatusParam", value = "要修改具体电站申请表审核状态的id", required = true, dataType = "UpdatePowerplantApplyforStatusParam")
    public ResultEntity updatePowerplantApplyforStatus(@RequestBody UpdatePowerplantApplyforStatusParam updatePowerplantApplyforStatusParam) {
        PowerplantApplyfor powerplantApplyfor = new PowerplantApplyfor();
        BeanUtils.copyProperties(updatePowerplantApplyforStatusParam, powerplantApplyfor);
        boolean b = false;
        System.out.println(powerplantApplyfor);
        if (updatePowerplantApplyforStatusParam.getPowerplantApplyforStatus() == 1) {
            powerplantApplyfor.setPowerplantApplyforStatus(PowerplantApplyforStatus.APPLYFORSUCCESS);
            if (powerplantApplyforService.updatePowerplantApplyforStatusById(powerplantApplyfor)) {
                return ResultEntity.buildSuccessEntity().setMessage("修改审核状态成功").setCode(ConstCode.ACCESS_SUCCESS);
            }
        } else {
            powerplantApplyfor.setPowerplantApplyforStatus(PowerplantApplyforStatus.APPLYFORFAIL);
        }
        return ResultEntity.buildSuccessEntity().setMessage("修改审核状态失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }

    /**
     * @return com.woniu.car.commons.core.dto.ResultEntity
     * @Author WangPeng
     * @Description TODO HZX
     * @Date 2021/4/9
     * @Param [deletePowerplantApplyforParam]
     **/
    @DeleteMapping("/api/delete_powerplant_applyfor")
    @ApiOperation(value = "删除电站申请表", notes = "Flag为true时删除成功，false为失败")
    @ApiImplicitParam(name = "deletePowerplantApplyforParam", value = "要删除具体电站申请表的id", required = true, dataType = "DeletePowerplantApplyforParam")
    public ResultEntity deletePowerplantApplyfor(@RequestBody DeletePowerplantApplyforParam deletePowerplantApplyforParam) {
        PowerplantApplyfor powerplantApplyfor = new PowerplantApplyfor();
        BeanUtils.copyProperties(deletePowerplantApplyforParam, powerplantApplyfor);
        System.out.println(powerplantApplyfor);
        boolean b = powerplantApplyforService.deletePowerplantApplyforById(powerplantApplyfor);
        if (b) {
            return ResultEntity.buildSuccessEntity().setMessage("删除成功").setCode(ConstCode.ACCESS_SUCCESS);
        }
        return ResultEntity.buildFailEntity().setMessage("删除失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }

}

