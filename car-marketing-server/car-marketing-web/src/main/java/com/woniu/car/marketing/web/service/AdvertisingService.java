package com.woniu.car.marketing.web.service;

import com.woniu.car.marketing.model.dtoVo.AdvertisingInfoBySourceAllDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetAdvertisingByAuditAllDtoVo;
import com.woniu.car.marketing.model.paramVo.AddAdvertising;
import com.woniu.car.marketing.model.paramVo.AdvertisingByAuditParamVo;
import com.woniu.car.marketing.model.paramVo.AdvertisingBySourceParamVo;
import com.woniu.car.marketing.model.paramVo.UpdateAdvertisingIdParamVo;
import com.woniu.car.marketing.web.domain.Advertising;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tsl
 * @since 2021-04-07
 */
public interface AdvertisingService extends IService<Advertising> {

    Boolean addAdvertising(AddAdvertising addAdvertising);

    Boolean updateAdvertisingPass(UpdateAdvertisingIdParamVo updateAdvertisingIdParamVo);

    Boolean updateAdvertisingRefuse(UpdateAdvertisingIdParamVo updateAdvertisingIdParamVo);

    List<GetAdvertisingByAuditAllDtoVo> listAdvertisingByAuditAll(AdvertisingByAuditParamVo getListAdvertisingByAuditAll);

    List<AdvertisingInfoBySourceAllDtoVo> listAdvertisingBySource(AdvertisingBySourceParamVo advertisingBySourceParamVo);
}
