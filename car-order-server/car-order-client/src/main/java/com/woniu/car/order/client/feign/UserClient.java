package com.woniu.car.order.client.feign;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.user.param.AddScoreParam;
import com.woniu.car.user.param.AddWalletLogParam;
import com.woniu.car.user.param.SlectAddressByAdressIdParam;
import com.woniu.car.user.web.domain.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

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


    /**
     * @Author WangPeng
     * @Description TODO 添加钱包日志接口
     * @Date  20:38
     * @Param [addWalletLogParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PostMapping("user/walletlog/addWalletLog")
    public ResultEntity addWalletLog(@RequestBody @Valid AddWalletLogParam addWalletLogParam);


    /**
     * @Author WangPeng
     * @Description TODO 新增积分日志接口
     * @Date  20:53
     * @Param [addScoreParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PostMapping("user/score/addScore")
    public ResultEntity addScore(@RequestBody @Valid AddScoreParam addScoreParam);

}