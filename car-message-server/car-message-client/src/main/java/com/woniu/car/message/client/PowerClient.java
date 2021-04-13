package com.woniu.car.message.client;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.message.model.feign.GetPowerplantParam;
import com.woniu.car.message.model.feign.Powerplant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("car-station-server")
public interface PowerClient {

    @PostMapping("/station/powerplant/get_one_powerplant")
    public ResultEntity<Powerplant> getOnePowerplant(@RequestBody GetPowerplantParam getPowerplantParam);



}
