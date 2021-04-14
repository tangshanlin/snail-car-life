package com.woniu.car.shop.web.service;

import com.woniu.car.shop.model.dtoVo.*;
import com.woniu.car.shop.model.paramVo.AddShopParamVo;
import com.woniu.car.shop.model.paramVo.FindShopByClassParamVo;
import com.woniu.car.shop.model.paramVo.FindShopInfoByMeLngLat;
import com.woniu.car.shop.model.paramVo.ShopIdParamVo;
import com.woniu.car.shop.web.domain.Shop;
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
public interface ShopService extends IService<Shop> {

    int addShopParamVo(AddShopParamVo addShopParamVo);

    FindShopInfoVo findShopInfo(ShopIdParamVo shopId);

    List<FindShopByClassDtoVo> findShopByClass(FindShopByClassParamVo findShopByClass);

    List<FindShopByIntegralDtoVo> findShopByIntegral();

    List<FindShopInfoAll> findShopInfoAll(FindShopInfoByMeLngLat meLngLat);

    ShopNameDtoVo getShopNameByShopId(ShopIdParamVo shopIdParamVo);

    ShopIntegralDtoVo getShopIntegralByShopId(ShopIdParamVo shopIdParamVo);

    Boolean updateShopIntegralByShopId(ShopIdParamVo shopIdParamVo);

    List<FindShopInfoByStateDtoVo> listShopInfoByState();

    Integer updateShopAccountStart(ShopIdParamVo shopId);
}
