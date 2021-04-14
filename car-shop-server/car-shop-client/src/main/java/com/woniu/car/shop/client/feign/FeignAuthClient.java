package com.woniu.car.shop.client.feign;

import com.woniu.car.auth.model.dto.BackBalanceDto;
import com.woniu.car.auth.model.params.BackBalanceParams;
import com.woniu.car.auth.model.params.InsertAccountByTypeParams;
import com.woniu.car.commons.core.dto.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/13/1:17
 * @Description:
 */
@FeignClient(name = "car-auth-server")
public interface FeignAuthClient {

    @PutMapping("/auth/back-balance/update-back-add-balance")
    ResultEntity<BackBalanceDto> updateBackAddBalance(@RequestBody BackBalanceParams backBalanceParams);//修改余额

    @PostMapping("/auth/back-user/api/insert_account_by_type")
    ResultEntity<String> insertAccountByType(@RequestBody InsertAccountByTypeParams iabt);//注册后台账号
}
