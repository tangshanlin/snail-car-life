package com.woniu.car.shop.web.service;

import com.woniu.car.shop.model.dtoVo.FindShopServiceEarningInfoByEarningIdDtoVo;
import com.woniu.car.shop.model.paramVo.AddShopEarningsInfoParamVo;
import com.woniu.car.shop.model.paramVo.FindShopEarningIdParamVo;
import com.woniu.car.shop.web.domain.ShopEarningsInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tsl
 * @since 2021-04-05
 */
public interface ShopEarningsInfoService extends IService<ShopEarningsInfo> {

    Integer addShopEarningsInfo(AddShopEarningsInfoParamVo addShopEarningsInfoParamVo);

    List<FindShopServiceEarningInfoByEarningIdDtoVo> getShopEarningsInfo(FindShopEarningIdParamVo findShopEarningIdParamVo);
}
