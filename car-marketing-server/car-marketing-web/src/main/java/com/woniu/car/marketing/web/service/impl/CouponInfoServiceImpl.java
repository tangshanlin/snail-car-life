package com.woniu.car.marketing.web.service.impl;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.discributelock.MyLock;
import com.woniu.car.marketing.client.feign.FeignUserClient;
import com.woniu.car.marketing.model.dtoVo.GetCouponInfoByIdDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponInfoByUserIdDtoVo;
import com.woniu.car.marketing.model.paramVo.AddUserGetCoupon;
import com.woniu.car.marketing.model.paramVo.GetCouponInfoByIdParamVo;
import com.woniu.car.marketing.model.paramVo.GetCouponInfoByUserIdAndSourceParamVo;

import com.woniu.car.marketing.web.domain.Coupon;
import com.woniu.car.marketing.web.domain.CouponInfo;
import com.woniu.car.marketing.web.mapper.CouponInfoMapper;
import com.woniu.car.marketing.web.mapper.CouponMapper;
import com.woniu.car.marketing.web.service.CouponInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.user.param.AddScoreParam;
import com.woniu.car.user.web.domain.UserInformation;
import com.woniu.car.user.web.util.GetTokenUtil;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {

    @Resource
    private CouponInfoMapper couponInfoMapper;
    @Resource
    private CouponMapper couponMapper;
    @Resource
    private FeignUserClient feignUserClient;


    /*
    * @Author TangShanLin
    * @Description TODO 查询某订单下用户可使用的优惠券
    * @Date  0:44
    * @Param [getCouponInfoByUserIdAndSourceParamVo]
    * @return java.util.List<com.woniu.car.marketing.model.dtoVo.GetCouponInfoByIdDtoVo>
    **/
    @Override
    public List<GetCouponInfoByIdDtoVo> getCouponInfoByUserIdAll(GetCouponInfoByUserIdAndSourceParamVo getCouponInfoByUserIdAndSourceParamVo) {
        Long nowTime = System.currentTimeMillis();
        Integer userId = GetTokenUtil.getUserId();
        List<GetCouponInfoByIdDtoVo> getCouponInfoByIdDtoVoList = couponInfoMapper.getCouponInfoByUserIdAll(getCouponInfoByUserIdAndSourceParamVo,userId,nowTime);
        return getCouponInfoByIdDtoVoList;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 通过用户领取优惠券表主键id查询优惠券信息
    * @Date  0:44
    * @Param [getCouponInfoByIdParamVo]
    * @return com.woniu.car.marketing.model.dtoVo.GetCouponInfoByIdDtoVo
    **/
    @Override
    public GetCouponInfoByIdDtoVo getCouponInfoById(GetCouponInfoByIdParamVo getCouponInfoByIdParamVo) {
        GetCouponInfoByIdDtoVo getCouponInfoByIdDtoVo = couponInfoMapper.getCouponInfoById(getCouponInfoByIdParamVo.getCouponInfoId());
        return getCouponInfoByIdDtoVo;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 用户领取优惠券逻辑
    * @Date  10:52
    * @Param [addUserGetCoupon]
    * @return java.lang.Boolean
    **/
    @Override
    @Transactional
    @Synchronized
//    @MyLock(key="com:woniu:car:car-marketing-server:coupon:t_coupon_info:couponId",methodParam = "couponId")
    public Boolean addUserGetCoupon(AddUserGetCoupon addUserGetCoupon) {
        Coupon coupon = couponMapper.selectById(addUserGetCoupon.getCouponId());
        if(coupon.getCouponNoGetNumber()>0){
            Long nowTime = System.currentTimeMillis();//获取当前时间作为领取时间

            CouponInfo couponInfo = new CouponInfo();
            couponInfo.setCouponInfoGetTime(nowTime);//传领取时间
            couponInfo.setCouponId(addUserGetCoupon.getCouponId());//优惠卷分类id
            couponInfo.setCouponInfoUserId(GetTokenUtil.getUserId());//用户领取优惠券id
            couponInfo.setCouponInfoUserAccount(GetTokenUtil.getUserAccount());//添加用户账号
            couponInfoMapper.insert(couponInfo);
            //修改优惠券类别的领取数量
            coupon.setCouponGetNumber(coupon.getCouponGetNumber()+1);
            coupon.setCouponNoGetNumber(coupon.getCouponNoUseNumber()-1);
            couponMapper.updateById(coupon);
            return true;
        }else{
            return false;
        }

    }

    /*
    * @Author TangShanLin
    * @Description TODO 根据用户id查询未过期未使用的优惠券信息
    * @Date  12:15
    * @Param [couponInfoByUserIdParamVo]
    * @return java.util.List<com.woniu.car.marketing.model.dtoVo.GetCouponInfoByUserIdDtoVo>
    **/
    @Override
    public List<GetCouponInfoByUserIdDtoVo> listCouponInfoByUserId() {
        Long now = System.currentTimeMillis();
        List<GetCouponInfoByUserIdDtoVo> getCouponInfoByUserIdDtoVoList = couponInfoMapper.listCouponInfoByUserId(GetTokenUtil.getUserId(),now);
        return getCouponInfoByUserIdDtoVoList;
    }


    /*
    * @Author TangShanLin
    * @Description TODO 用户消耗积分领取优惠券逻辑
    * @Date  13:11
    * @Param [addUserGetCoupon]
    * @return java.lang.Boolean
    **/
    @Override
    @GlobalTransactional
    @MyLock(key="com:woniu:car:car-marketing-server:coupon:t_coupon_info:couponId",methodParam = "couponId",timeoutGain = 20,timeoutLive = 50)
    public Integer addUserGetCouponByCredits(AddUserGetCoupon addUserGetCoupon) {

        Coupon coupon = couponMapper.selectById(addUserGetCoupon.getCouponId());
        if(coupon.getCouponNoGetNumber()>0){
            //查询用户积分
            ResultEntity<UserInformation> userInformationResultEntity = feignUserClient.selectUerInformation();
            UserInformation data = userInformationResultEntity.getData();
            Integer userScore = data.getUserScore();//拿到用户积分
            System.out.println("拿到用户积分:"+userScore);


            BigDecimal couponMoney = coupon.getCouponMoney();//拿到优惠券面额

            BigDecimal subtract = couponMoney.subtract(new BigDecimal(userScore * 10));//积分的10倍必须大于面额
            if (subtract.compareTo(BigDecimal.ZERO)==1) {
                //积分满足兑换条件
                //减少积分
                AddScoreParam addScoreParam = new AddScoreParam();
                addScoreParam.setScoreNumber(couponMoney.intValue()*10);
                addScoreParam.setScoreChange("兑换面额为"+couponMoney+"的优惠券");
                addScoreParam.setScoreChangeType(2);
                feignUserClient.addScore(addScoreParam);
                System.out.println("减少积分");

                //领取优惠券
                Long nowTime = System.currentTimeMillis();//获取当前时间作为领取时间

                CouponInfo couponInfo = new CouponInfo();
                couponInfo.setCouponInfoGetTime(nowTime);//传领取时间
                couponInfo.setCouponId(addUserGetCoupon.getCouponId());//优惠卷分类id
                couponInfo.setCouponInfoUserId(GetTokenUtil.getUserId());//用户领取优惠券id
                couponInfo.setCouponInfoUserAccount(GetTokenUtil.getUserAccount());//添加用户账号
                couponInfoMapper.insert(couponInfo);
                //修改优惠券类别的领取数量
                coupon.setCouponGetNumber(coupon.getCouponGetNumber()+1);
                coupon.setCouponNoGetNumber(coupon.getCouponNoUseNumber()-1);
                couponMapper.updateById(coupon);

                return 1;
            }else {
                return 2;
            }

        }else{
            return 0;
        }

    }
}
