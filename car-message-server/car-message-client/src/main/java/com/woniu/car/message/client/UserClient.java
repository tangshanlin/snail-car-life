package com.woniu.car.message.client;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.message.model.dto.UserInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("user-server")
public interface UserClient {

    @GetMapping("/user/user-information/selectUerInformation")
    public ResultEntity<UserInformation> selectUerInformation();




}
