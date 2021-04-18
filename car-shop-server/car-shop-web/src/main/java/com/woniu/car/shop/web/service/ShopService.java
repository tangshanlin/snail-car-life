package com.woniu.car.shop.web.service;

import com.woniu.car.shop.model.dtoVo.*;
import com.woniu.car.shop.model.paramVo.*;
import com.woniu.car.shop.web.domain.EsShopWoniuCar;
import com.woniu.car.shop.web.domain.Shop;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.elasticsearch.core.SearchHits;

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

    void addShopParamVo(AddShopParamVo addShopParamVo);

    FindShopInfoVo findShopInfo(ShopIdParamVo shopId);

    List<FindShopByClassDtoVo> findShopByClass(FindShopByClassParamVo findShopByClass);

    List<FindShopByIntegralDtoVo> findShopByIntegral();

    SearchHits<EsShopWoniuCar> findShopInfoAll(FindShopInfoByMeLngLat meLngLat);

    ShopNameDtoVo getShopNameByShopId(ShopIdParamVo shopIdParamVo);

    ShopIntegralDtoVo getShopIntegralByShopId(ShopIdParamVo shopIdParamVo);

    Boolean updateShopIntegralByShopId(ShopIdParamVo shopIdParamVo);

    List<FindShopInfoByStateDtoVo> listShopInfoByState();

    Integer updateShopAccountStart(ShopIdParamVo shopId);

    Boolean updateShopGrade(AmendShopGradeByShopIdParamVo amendShopGradeByShopIdParamVo);

    Boolean updateShopOrderNumber(ShopIdParamVo shopIdParamVo);
}
