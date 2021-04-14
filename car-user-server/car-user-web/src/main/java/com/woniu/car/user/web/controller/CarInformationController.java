package com.woniu.car.user.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.user.param.SelectCarinforamtionParam;
import com.woniu.car.user.web.domain.CarInformation;
import com.woniu.car.user.web.domain.Carseries;
import com.woniu.car.user.web.service.CarInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 车型信息表 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/car-information")
@Api(tags = "车型号信息的接口")
public class CarInformationController {
    @Resource
    private CarInformationService carInformationService;

    /*通过车系查询车型号的接口*
     * @Author SuShanHu
     * @Description TODO Administrator
     * @Date  2021/4/14
     * @Param
     * @return
     **/
    @GetMapping("/select_by_carseriesId")
    @ApiOperation(value = "查询车型的接口",notes = "<span style='color:red;'>根据车系ID查询车型的接口</span>" )
    public ResultEntity<List<CarInformation>> selectByCarseriesId(SelectCarinforamtionParam selectCarinforamtionParam){
        if (!ObjectUtils.isEmpty(selectCarinforamtionParam)){
            //校验通过开始查询
            List<CarInformation> list = carInformationService.list(new QueryWrapper<CarInformation>().eq("carseries_id", selectCarinforamtionParam.getCarseriesId()));
            return ResultEntity.buildListEntity(CarInformation.class).setCode(ConstCode.SELECTCARINFORMATION_SUCESS)
                    .setFlag(true).setMessage("查询车型成功").setData(list);

        }
        return  ResultEntity.buildListEntity(CarInformation.class).setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }



}

