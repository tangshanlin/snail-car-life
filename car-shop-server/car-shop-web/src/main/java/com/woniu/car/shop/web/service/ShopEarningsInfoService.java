package com.woniu.car.shop.web.service;

import com.woniu.car.shop.model.paramVo.AddShopEarningsInfoParamVo;
import com.woniu.car.shop.web.domain.ShopEarningsInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tsl
 * @since 2021-04-05
 */
public interface ShopEarningsInfoService extends IService<ShopEarningsInfo> {

    Boolean addShopEarningsInfo(AddShopEarningsInfoParamVo addShopEarningsInfoParamVo);
}
