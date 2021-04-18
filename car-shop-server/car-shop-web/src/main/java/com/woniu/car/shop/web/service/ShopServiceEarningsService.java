package com.woniu.car.shop.web.service;

import com.woniu.car.shop.model.dtoVo.FindShopServiceEarningAllByShopIdDtoVo;
import com.woniu.car.shop.model.paramVo.AddShopServiceEarningsParamVo;
import com.woniu.car.shop.model.paramVo.ShopIdParamVo;
import com.woniu.car.shop.web.domain.ShopServiceEarnings;
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
public interface ShopServiceEarningsService extends IService<ShopServiceEarnings> {

    Boolean addShopServiceEarnings(AddShopServiceEarningsParamVo addShopServiceEarningsParamVo);

    List<FindShopServiceEarningAllByShopIdDtoVo> getShopServiceEarnings(ShopIdParamVo shopIdParamVo);
}
