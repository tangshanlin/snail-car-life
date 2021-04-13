package com.woniu.car.order.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.order.web.entity.CarserviceReturns;

/**
 * <p>
 * 服务退款表 服务类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
public interface CarserviceReturnsService extends IService<CarserviceReturns> {

    /**
     * @Author WangPeng
     * @Description //根据申请的信息新增服务退款表
     * @Date  2021/4/8
     * @Param [carserviceReturns]
     * @return java.lang.Boolean
     **/
    public Boolean insertCarserviceReturn(CarserviceReturns carserviceReturns);

}
