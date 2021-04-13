package com.woniu.car.shop.client.feign;

import com.woniu.car.auth.model.dto.BackBalanceDto;
import com.woniu.car.auth.model.params.BackBalanceParams;
import com.woniu.car.commons.core.dto.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/13/1:17
 * @Description:
 */
@FeignClient(name = "car-auth-server")
public interface FeignAuthClient {

    @PutMapping("/auth/back-user/update_back_add_balance")
    ResultEntity<BackBalanceDto> updateBackAddBalance(@SpringQueryMap BackBalanceParams backBalanceParams);
}
