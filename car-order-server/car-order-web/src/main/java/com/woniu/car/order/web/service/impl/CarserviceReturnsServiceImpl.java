package com.woniu.car.order.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.order.web.entity.CarserviceReturns;
import com.woniu.car.order.web.mapper.CarserviceReturnsMapper;
import com.woniu.car.order.web.service.CarserviceReturnsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务退款表 服务实现类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
@Service("carserviceReturnsService")
@Slf4j
public class CarserviceReturnsServiceImpl extends ServiceImpl<CarserviceReturnsMapper, CarserviceReturns> implements CarserviceReturnsService {

    @Resource
    private CarserviceReturnsMapper carserviceReturnsMapper;

    /**
     * @Author WangPeng
     * @Description TODO 生成服务退款表
     * @Date  1:32
     * @Param [carserviceReturns]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean insertCarserviceReturn(CarserviceReturns carserviceReturns) {
        log.info("服务订单编号为："+carserviceReturns.getCarserviceOrderNo()+",开始生成退货订单表");
        int row = carserviceReturnsMapper.insert(carserviceReturns);
        if(row>0){
            log.info("生成退货订单表成功");
            return true;
        }else{
            log.info("生成退货订单表失败");
            return false;
        }

    }
}
