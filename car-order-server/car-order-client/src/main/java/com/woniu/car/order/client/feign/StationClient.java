package com.woniu.car.order.client.feign;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.station.model.param.GetPowerplantParam;
import com.woniu.car.station.model.param.station.GetOneStationParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName StationClient
 * @Desc TODO
 * @Author 21174
 * @Date 2021/4/13 8:11
 * @Version 1.0
 */
@FeignClient("car-station-server")
public interface StationClient {

    /**
     * @return com.woniu.car.commons.core.dto.ResultEntity
     * @Author WangPeng
     * @Description TODO 根据充电桩id查询充电桩信息
     * @Date 8:16
     * @Param [getOneStationParam]
     **/
    @PostMapping("station/station/get_station")
    public ResultEntity getOneStation(@RequestBody GetOneStationParam getOneStationParam);

    /**
     * @return com.woniu.car.commons.core.dto.ResultEntity
     * @Author WangPeng
     * @Description TODO 根据电站id查询电站信息
     * @Date 8:27
     * @Param [getPowerplantParam]
     **/
    @PostMapping("station/powerplant/get_one_powerplant")
    public ResultEntity getOnePowerplant(@RequestBody GetPowerplantParam getPowerplantParam);


}