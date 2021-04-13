package com.woniu.car.order.client.feign;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.user.param.SlectAddressByAdressIdParam;
import com.woniu.car.user.web.domain.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * @Author WangPeng
 * @Description TODO UserFeign接口
 * @Date  2021/4/10
 * @Param
 * @return
 **/
@FeignClient("user-server")
public interface UserClient {

    /*
     * @Author WangPeng
     * @Description TODO 根据地址id查询地址详情
     * @Date  2021/4/10
     * @Param [selectAddressParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @GetMapping("user/address/selectByAddressId")
    public ResultEntity selectByAddressId(@SpringQueryMap SlectAddressByAdressIdParam selectAddressParam);

}