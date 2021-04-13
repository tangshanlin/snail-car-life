package com.woniu.car.marketing.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.util.BeanCopyUtil;

import com.woniu.car.marketing.client.feign.FeignMarketingClient;
import com.woniu.car.marketing.client.feign.FeignShopClient;
import com.woniu.car.marketing.model.dtoVo.AdvertisingInfoBySourceAllDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetAdvertisingByAuditAllDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponBySourceDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponNameDtoVo;
import com.woniu.car.marketing.model.paramVo.*;
import com.woniu.car.marketing.web.domain.Advertising;


import com.woniu.car.marketing.web.mapper.AdvertisingMapper;
import com.woniu.car.marketing.web.service.AdvertisingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.marketing.web.util.MarketingFileUpload;
import com.woniu.car.shop.model.dtoVo.ShopNameDtoVo;
import com.woniu.car.shop.model.paramVo.ShopIdParamVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tsl
 * @since 2021-04-07
 */
@Service
public class AdvertisingServiceImpl extends ServiceImpl<AdvertisingMapper, Advertising> implements AdvertisingService {

    @Resource
    private AdvertisingMapper advertisingMapper;

    @Resource
    private MarketingFileUpload myFileUpload;//minio文件上传

    //feign相关
    @Resource
    private FeignShopClient feignShopClient;
    @Resource
    private FeignMarketingClient feignMarketingClient;


    /*
    * @Author TangShanLin
    * @Description TODO 发送活动申请逻辑
    * @Date  0:03
    * @Param [addAdvertising]
    * @return java.lang.Boolean
    **/
    @Override
    public Boolean addAdvertising(AddAdvertising addAdvertising) {
        MultipartFile[] file = addAdvertising.getFile();
        /**
         * 将文件上传到minlo服务器上面
         */
        ArrayList<String> shopImage = myFileUpload.upload(file);
        String shopImages = shopImage.get(0);
        Advertising advertising = BeanCopyUtil.copyOne(addAdvertising, Advertising::new);

        advertising.setAdvertisingImage(shopImages);
        int insert = advertisingMapper.insert(advertising);
        if(insert!=0) return true;
        return false;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 审核通过逻辑
    * @Date  0:03
    * @Param [updateAdvertisingIdParamVo]
    * @return java.lang.Boolean
    **/
    @Override
    public Boolean updateAdvertisingPass(UpdateAdvertisingIdParamVo updateAdvertisingIdParamVo) {
        Advertising advertising = new Advertising();
        advertising.setAdvertisingAudit(1);
        advertising.setAdvertisingId(updateAdvertisingIdParamVo.getAdvertisingId());
        int insert = advertisingMapper.updateById(advertising);
        if(insert!=0) return true;
        return false;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 审核拒绝逻辑
    * @Date  0:07
    * @Param [updateAdvertisingIdParamVo]
    * @return java.lang.Boolean
    **/
    @Override
    public Boolean updateAdvertisingRefuse(UpdateAdvertisingIdParamVo updateAdvertisingIdParamVo) {
        Advertising advertising = new Advertising();
        advertising.setAdvertisingAudit(2);
        advertising.setAdvertisingId(updateAdvertisingIdParamVo.getAdvertisingId());
        int insert = advertisingMapper.updateById(advertising);
        if(insert!=0) return true;
        return false;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 根据活动状态查询活动信息
    * @Date  11:56
    * @Param [getListAdvertisingByAuditAll]
    * @return java.util.List<com.woniu.car.marketing.model.dtoVo.GetAdvertisingByAuditAll>
    **/
    @Override
    public List<GetAdvertisingByAuditAllDtoVo> listAdvertisingByAuditAll(AdvertisingByAuditParamVo getListAdvertisingByAuditAll) {
        QueryWrapper<Advertising> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("advertising_audit",getListAdvertisingByAuditAll.getAdvertisingAudit());
        List<Advertising> advertisings = advertisingMapper.selectList(queryWrapper);
        List<GetAdvertisingByAuditAllDtoVo> getAdvertisingByAuditAllDtoVos = new ArrayList<>();

        //将数据库查询的数据进行遍历
        advertisings.forEach(advertising -> {
            //feign通过门店id拿门店名称
            ShopIdParamVo shopIdParamVo = new ShopIdParamVo();
            shopIdParamVo.setShopId(advertising.getAdvertisingSourceId());
            ResultEntity<ShopNameDtoVo> shopNameByShopId = feignShopClient.getShopNameByShopId(shopIdParamVo);
            ShopNameDtoVo shopNameDtoVo = shopNameByShopId.getData();

            //feign通过优惠卷类别id拿优惠券名称
            GetCouponIdParamVo couponIdParamVo = new GetCouponIdParamVo();
            couponIdParamVo.setCouponId(advertising.getAdvertisingSourceId());
            ResultEntity<GetCouponNameDtoVo> couponNameByCouponId = feignMarketingClient.getCouponNameByCouponId(couponIdParamVo);
            GetCouponNameDtoVo couponNameDtoVo = couponNameByCouponId.getData();

            //将数据存到Dto类里面
            GetAdvertisingByAuditAllDtoVo advertisingByAuditDtoVo = new GetAdvertisingByAuditAllDtoVo();
            advertisingByAuditDtoVo.setShopName(shopNameDtoVo.getShopName());
            advertisingByAuditDtoVo.setAdvertisingBeginTime(advertising.getAdvertisingBeginTime());
            advertisingByAuditDtoVo.setAdvertisingExplain(advertising.getAdvertisingExplain());
            advertisingByAuditDtoVo.setAdvertisingEndTime(advertising.getAdvertisingEndTime());
            advertisingByAuditDtoVo.setAdvertisingId(advertising.getAdvertisingId());
            advertisingByAuditDtoVo.setAdvertisingImage(advertising.getAdvertisingImage());
            advertisingByAuditDtoVo.setCouponName(couponNameDtoVo.getCouponName());

            getAdvertisingByAuditAllDtoVos.add(advertisingByAuditDtoVo);
        });

        return getAdvertisingByAuditAllDtoVos;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 前端根据来源展示已审核通过活动信息逻辑
    * @Date  16:25
    * @Param [advertisingBySourceParamVo]
    * @return java.util.List<com.woniu.car.marketing.model.dtoVo.AdvertisingInfoBySourceAllDtoVo>
    **/
    @Override
    public List<AdvertisingInfoBySourceAllDtoVo> listAdvertisingBySource(AdvertisingBySourceParamVo advertisingBySourceParamVo) {
        QueryWrapper<Advertising> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("advertising_source_id",advertisingBySourceParamVo.getAdvertisingSourceId());
        queryWrapper.eq("advertising_audit",1);
        List<Advertising> advertisings = advertisingMapper.selectList(queryWrapper);

        List<AdvertisingInfoBySourceAllDtoVo> advertisingInfoBySourceAllDtoVoList = new ArrayList<>();

        advertisings.forEach(advertising -> {
            GetCouponIdParamVo couponIdParamVo = new GetCouponIdParamVo();
            couponIdParamVo.setCouponId(advertising.getCouponId());
            ResultEntity<GetCouponBySourceDtoVo> couponById = feignMarketingClient.getCouponById(couponIdParamVo);
            GetCouponBySourceDtoVo couponByIdData = couponById.getData();

            AdvertisingInfoBySourceAllDtoVo advertisingInfoBySourceAllDtoVo = new AdvertisingInfoBySourceAllDtoVo();
            advertisingInfoBySourceAllDtoVo.setAdvertisingBeginTime(advertising.getAdvertisingBeginTime());
            advertisingInfoBySourceAllDtoVo.setAdvertisingEndTime(advertising.getAdvertisingEndTime());
            advertisingInfoBySourceAllDtoVo.setAdvertisingExplain(advertising.getAdvertisingExplain());
            advertisingInfoBySourceAllDtoVo.setAdvertisingImage(advertising.getAdvertisingImage());
            advertisingInfoBySourceAllDtoVo.setCouponId(couponByIdData.getCouponId());
            advertisingInfoBySourceAllDtoVo.setCouponInfo(couponByIdData.getCouponInfo());
            advertisingInfoBySourceAllDtoVo.setCouponMoney(couponByIdData.getCouponMoney());
            advertisingInfoBySourceAllDtoVo.setCouponName(couponByIdData.getCouponName());
            advertisingInfoBySourceAllDtoVoList.add(advertisingInfoBySourceAllDtoVo);

        });

        return advertisingInfoBySourceAllDtoVoList;
    }


}
