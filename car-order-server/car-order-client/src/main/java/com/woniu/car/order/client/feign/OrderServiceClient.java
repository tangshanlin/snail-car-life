package com.woniu.car.order.client.feign;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.items.model.entity.CarService;
import com.woniu.car.items.model.param.carservice.GetOneCarServiceParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName OrderServiceClient
 * @Desc TODO
 * @Author 21174
 * @Date 2021/4/14 14:48
 * @Version 1.0
 */

@FeignClient("car-service-server")
public interface OrderServiceClient {

    /**
     * @Author WangPeng
     * @Description TODO 根据id查询汽车具体服务信息
     * @Date  14:55
     * @Param [getOneCarServiceParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity<CarService>
     **/
    @PostMapping("service/car_service/get_one_car_service")
    public ResultEntity<CarService> getOneCarService(@RequestBody GetOneCarServiceParam getOneCarServiceParam);

}
