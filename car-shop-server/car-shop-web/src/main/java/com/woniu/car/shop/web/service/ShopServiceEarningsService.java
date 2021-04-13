package com.woniu.car.shop.web.service;

import com.woniu.car.shop.model.paramVo.AddShopServiceEarningsParamVo;
import com.woniu.car.shop.web.domain.ShopServiceEarnings;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tsl
 * @since 2021-04-05
 */
public interface ShopServiceEarningsService extends IService<ShopServiceEarnings> {

    Boolean addShopServiceEarnings(AddShopServiceEarningsParamVo addShopServiceEarningsParamVo);
}
