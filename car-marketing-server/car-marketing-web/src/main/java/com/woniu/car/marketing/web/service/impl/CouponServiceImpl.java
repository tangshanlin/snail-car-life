package com.woniu.car.marketing.web.service.impl;

import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.marketing.model.dtoVo.GetCouponAllDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponBySourceDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponNameDtoVo;
import com.woniu.car.marketing.model.paramVo.AddCouponParamVo;
import com.woniu.car.marketing.model.paramVo.GetCouponBySourceParamVo;
import com.woniu.car.marketing.model.paramVo.GetCouponIdParamVo;
import com.woniu.car.marketing.model.paramVo.UpdatePaySuccessCouponParamVo;
import com.woniu.car.marketing.web.domain.Coupon;
import com.woniu.car.marketing.web.mapper.CouponMapper;
import com.woniu.car.marketing.web.service.CouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {

    @Resource
    private CouponMapper couponMapper;

    /*
    * @Author TangShanLin
    * @Description TODO 添加优惠券类别逻辑
    * @Date  17:00
    * @Param [addCouponParamVo]
    * @return void
    **/
    @Override
    public void addCoupon(AddCouponParamVo addCouponParamVo) {
        Coupon coupon = BeanCopyUtil.copyOne(addCouponParamVo, Coupon::new);
        couponMapper.insert(coupon);
    }

    /*
    * @Author TangShanLin
    * @Description TODO 查询所有未失效优惠券类别信息逻辑
    * @Date  17:00
    * @Param []
    * @return java.util.List<com.woniu.car.marketing.model.dtoVo.GetCouponAllDtoVo>
    **/
    @Override
    public List<GetCouponAllDtoVo> listCouponInfoAll() {
        List<GetCouponAllDtoVo> getCouponInfoAllDtoVoList = new ArrayList<GetCouponAllDtoVo>();
        List<Coupon> coupons = couponMapper.selectList(null);
        /**
         * 把每个优惠券类别的失效时间和当前时间进行比较
         * 大于当前时间（+1h）就复制到GetCouponInfoAllDtoVo类中并添加到getCouponInfoAllDtoVoList集合对象里面
         * 返回该集合对象
         */
        coupons.forEach(coupon -> {
            Long nowTime = System.currentTimeMillis()+1000*60*60;
            if(coupon.getCouponEndTime()>nowTime){
                GetCouponAllDtoVo getCouponInfoAllDtoVo = BeanCopyUtil.copyOne(coupon, GetCouponAllDtoVo::new);
                getCouponInfoAllDtoVoList.add(getCouponInfoAllDtoVo);
            }
        });
        return getCouponInfoAllDtoVoList;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 订单支付成功后优惠券更新
    * @Date  0:42
    * @Param [updatePaySuccessCouponParamVo]
    * @return void
    **/
    @Override
    public void updateCouponByPaySuccess(UpdatePaySuccessCouponParamVo updatePaySuccessCouponParamVo) {
        Long now = System.currentTimeMillis();
        couponMapper.updateCouponByPaySuccess(updatePaySuccessCouponParamVo,now);
    }

    /*
    * @Author TangShanLin
    * @Description TODO 查询某个门店可用优惠券逻辑
    * @Date  12:11
    * @Param [getCouponBySourceParamVo]
    * @return java.util.List<com.woniu.car.marketing.model.dtoVo.GetCouponBySourceDtoVo>
    **/
    @Override
    public List<GetCouponBySourceDtoVo> getCouponBySource(GetCouponBySourceParamVo getCouponBySourceParamVo) {
        Coupon coupon = couponMapper.selectById(getCouponBySourceParamVo.getCouponGoods());

        return null;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 通过优惠券类别主键id查询优惠券名称逻辑
    * @Date  1:03
    * @Param [getCouponIdParamVo]
    * @return com.woniu.car.marketing.model.dtoVo.GetCouponNameDtoVo
    **/
    @Override
    public GetCouponNameDtoVo getCouponNameByCouponId(GetCouponIdParamVo getCouponIdParamVo) {
        Coupon coupon = couponMapper.selectById(getCouponIdParamVo.getCouponId());
        GetCouponNameDtoVo getCouponNameDtoVo = BeanCopyUtil.copyOne(coupon, GetCouponNameDtoVo::new);
        return getCouponNameDtoVo;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 通过优惠券类别主键id查询优惠券信息逻辑
    * @Date  16:59
    * @Param [getCouponIdParamVo]
    * @return com.woniu.car.marketing.model.dtoVo.GetCouponBySourceDtoVo
    **/
    @Override
    public GetCouponBySourceDtoVo getCouponById(GetCouponIdParamVo getCouponIdParamVo) {
        Coupon coupon = couponMapper.selectById(getCouponIdParamVo.getCouponId());
        GetCouponBySourceDtoVo getCouponBySourceDtoVo = BeanCopyUtil.copyOne(coupon, GetCouponBySourceDtoVo::new);
        return getCouponBySourceDtoVo;
    }
}
