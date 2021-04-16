package com.woniu.car.marketing.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.marketing.model.dtoVo.GetCouponAllDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponBySourceDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponNameDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponSourceAndMoneyByIdDtoVo;
import com.woniu.car.marketing.model.paramVo.AddCouponParamVo;
import com.woniu.car.marketing.model.paramVo.GetCouponBySourceParamVo;
import com.woniu.car.marketing.model.paramVo.GetCouponIdParamVo;
import com.woniu.car.marketing.model.paramVo.UpdatePaySuccessCouponParamVo;
import com.woniu.car.marketing.web.domain.Coupon;
import com.woniu.car.marketing.web.domain.CouponInfo;
import com.woniu.car.marketing.web.mapper.CouponInfoMapper;
import com.woniu.car.marketing.web.mapper.CouponMapper;
import com.woniu.car.marketing.web.service.CouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private CouponInfoMapper couponInfoMapper;

    @Resource
    private RedisTemplate redisTemplate;


    String couponKey = "car:marketing:coupon:addCoupon";


    /*
    * @Author TangShanLin
    * @Description TODO 添加优惠 券类别逻辑
    * @Date  17:00
    * @Param [addCouponParamVo]
    * @return void
    **/
    @Override
    public void addCoupon(AddCouponParamVo addCouponParamVo) {
        Coupon coupon = BeanCopyUtil.copyOne(addCouponParamVo, Coupon::new);
        coupon.setCouponBeginTime(addCouponParamVo.getCouponBeginTime().getTime());//生效时间
        coupon.setCouponEndTime(addCouponParamVo.getCouponEndTime().getTime());//失效时间
        coupon.setCouponNoGetNumber(addCouponParamVo.getCouponNumber());//待领取数量
        coupon.setCouponGetNumber(0);//领取数量
        coupon.setCouponUseNumber(0);//已使用数量
        coupon.setCouponNoUseNumber(0);//未使用数量
        int insert = couponMapper.insert(coupon);
        if (insert!=0){
            //存入redis中
            Long time = addCouponParamVo.getCouponEndTime().getTime()-System.currentTimeMillis();//设置过期时间
            HashOperations hashOperations = redisTemplate.opsForHash();
            SetOperations setOperations = redisTemplate.opsForSet();//从redis里面拿所有优惠券类别id

            String key = couponKey+coupon.getCouponId();//key值
            hashOperations.put(key,"couponId",coupon.getCouponId()+"");
            hashOperations.put(key,"couponName",coupon.getCouponName()+"");
            hashOperations.put(key,"couponInfo",coupon.getCouponInfo()+"");
            hashOperations.put(key,"couponMoney",coupon.getCouponMoney()+"");
            hashOperations.put(key,"couponCondition",coupon.getCouponCondition()+"");
            hashOperations.put(key,"couponGoods",coupon.getCouponGoods()+"");
            hashOperations.put(key,"couponNumber",coupon.getCouponNumber()+"");
            hashOperations.put(key,"couponBeginTime",coupon.getCouponBeginTime()+"");
            hashOperations.put(key,"couponEndTime",coupon.getCouponEndTime()+"");//过期时间

            /*hashOperations.put(key,"couponGetNumber",coupon.getCouponGetNumber()+"");
            hashOperations.put(key,"couponNoGetNumber",coupon.getCouponNoGetNumber()+"");
            hashOperations.put(key,"couponUseNumber",coupon.getCouponUseNumber()+"");
            hashOperations.put(key,"couponNoUseNumber",coupon.getCouponNoUseNumber()+"");*/

            //设置过期时间
            redisTemplate.expire("car:marketing:coupon:addCoupon"+coupon.getCouponId(), time, TimeUnit.MILLISECONDS);

            setOperations.add("car:marketing:coupon:couponIds",coupon.getCouponId());
        }

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
        List<GetCouponAllDtoVo> getCouponInfoAllDtoVoList = new ArrayList<>();

        HashOperations hashOperations = redisTemplate.opsForHash();
        SetOperations setOperations = redisTemplate.opsForSet();//从redis里面拿所有优惠券类别id

        Long nowTime = System.currentTimeMillis();//当前时间

        //redis里面拿所有优惠券主键id
        Set<Integer> couponIds = setOperations.members("car:marketing:coupon:couponIds");


        if(ObjectUtils.isEmpty(couponIds)){
            List<Coupon> coupons = couponMapper.selectList(null);
            /**
             * 把每个优惠券类别的失效时间和当前时间进行比较
             * 大于当前时间就复制到GetCouponInfoAllDtoVo类中并添加到getCouponInfoAllDtoVoList集合对象里面
             * 返回该集合对象
             */
            coupons.forEach(coupon -> {

                if(coupon.getCouponEndTime()>nowTime){
                    GetCouponAllDtoVo getCouponInfoAllDtoVo = BeanCopyUtil.copyOne(coupon, GetCouponAllDtoVo::new);
                    getCouponInfoAllDtoVoList.add(getCouponInfoAllDtoVo);
                }
            });
            return getCouponInfoAllDtoVoList;
        }else{
            for(Integer couponId:couponIds){
                String key = couponKey+couponId;//redis主键值

                Long size = hashOperations.size(key);
                //拿到redis里面优惠券数据
                if(size>0){//redis里面某个hashKe有值
                    Long couponBeginTime = Long.valueOf(hashOperations.get(key, "couponBeginTime").toString()) ;
                    if(nowTime<couponBeginTime){//优惠券未失效的数据
                        List values = hashOperations.values(key);//拿到redis里面某个hashKey的所有值
                        GetCouponAllDtoVo getCouponAllDtoVo = new GetCouponAllDtoVo();
                        getCouponAllDtoVo.setCouponId(Integer.valueOf(values.get(0).toString()) );
                        getCouponAllDtoVo.setCouponName(values.get(1).toString());
                        getCouponAllDtoVo.setCouponInfo(values.get(2).toString());
                        getCouponAllDtoVo.setCouponMoney(new BigDecimal(values.get(3).toString()));
                        getCouponAllDtoVo.setCouponCondition(new BigDecimal(values.get(4).toString()));
                        getCouponAllDtoVo.setCouponGoods(Integer.valueOf(values.get(5).toString()));
                        getCouponAllDtoVo.setCouponNumber(Integer.valueOf(values.get(6).toString()));
                        getCouponAllDtoVo.setCouponBeginTime(Long.valueOf(values.get(7).toString()));
                        getCouponAllDtoVo.setCouponEndTime(Long.valueOf(values.get(8).toString()));

                        /*getCouponAllDtoVo.setCouponGetNumber(Integer.valueOf(values.get(9).toString()));
                        getCouponAllDtoVo.setCouponNoGetNumber(Integer.valueOf(values.get(10).toString()));
                        getCouponAllDtoVo.setCouponUseNumber(Integer.valueOf(values.get(10).toString()));
                        getCouponAllDtoVo.setCouponNoUseNumber(Integer.valueOf(values.get(12).toString()));*/
                        Coupon coupon = couponMapper.selectById(couponId);
                        getCouponAllDtoVo.setCouponGetNumber(coupon.getCouponGetNumber());
                        getCouponAllDtoVo.setCouponNoGetNumber(coupon.getCouponNoUseNumber());
                        getCouponAllDtoVo.setCouponUseNumber(coupon.getCouponUseNumber());
                        getCouponAllDtoVo.setCouponNoUseNumber(coupon.getCouponNoUseNumber());

                        getCouponInfoAllDtoVoList.add(getCouponAllDtoVo);
                    }
                }
            }


            return getCouponInfoAllDtoVoList;
        }
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
        CouponInfo couponInfo = new CouponInfo();
        couponInfo.setCouponInfoId(updatePaySuccessCouponParamVo.getCouponInfoId());
        couponInfo.setCouponInfoState(1);
        couponInfo.setCouponInfoUseTime(now);
        couponInfoMapper.updateById(couponInfo);

        Coupon couponDB = couponMapper.selectById(updatePaySuccessCouponParamVo.getCouponId());
        Coupon coupon = new Coupon();
        coupon.setCouponId(updatePaySuccessCouponParamVo.getCouponId());
        coupon.setCouponUseNumber(couponDB.getCouponUseNumber()+1);
        coupon.setCouponNoUseNumber(couponDB.getCouponNoUseNumber()-1);
        couponMapper.updateById(coupon);
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
        List<GetCouponBySourceDtoVo> getCouponBySourceDtoVoList = new ArrayList<>();
        HashOperations hashOperations = redisTemplate.opsForHash();
        SetOperations setOperations = redisTemplate.opsForSet();

        //redis里面拿所有优惠券主键id
        Set<Integer> couponIds = setOperations.members("car:marketing:coupon:couponIds");
        Long nowTime = System.currentTimeMillis();//当前时间
        if(ObjectUtils.isEmpty(couponIds)){
            List<Coupon> couponList = couponMapper.selectList(new QueryWrapper<Coupon>().eq("coupon_goods", getCouponBySourceParamVo.getCouponGoods()));
            for (Coupon coupon : couponList) {
                //拿到门店未失效的所有优惠券
                if(coupon.getCouponEndTime()>nowTime){
                    getCouponBySourceDtoVoList.add(BeanCopyUtil.copyOne(coupon,GetCouponBySourceDtoVo::new));
                }
            }
            return getCouponBySourceDtoVoList;
        }else{
            for(Integer couponId:couponIds){
                String key = couponKey+couponId;//redis主键值

                Long size = hashOperations.size(key);
                //拿到redis里面优惠券数据
                if(size>0){//redis里面某个hashKe有值
                    //从缓存里面拿每个优惠券的过期时间
                    Long couponBeginTime = Long.valueOf(hashOperations.get(key, "couponBeginTime").toString());
                    //从缓存里面拿优惠券来源
                    Integer couponGoods = Integer.valueOf(hashOperations.get(key, "couponGoods").toString());

                    //某个门店下优惠券未失效的数据
                    if(nowTime<couponBeginTime && couponGoods.equals(getCouponBySourceParamVo.getCouponGoods())){
                        List values = hashOperations.values(key);//拿到redis里面某个hashKey的所有值

                        GetCouponBySourceDtoVo getCouponAllDtoVo = new GetCouponBySourceDtoVo();
                        getCouponAllDtoVo.setCouponId(Integer.valueOf(values.get(0).toString()) );
                        getCouponAllDtoVo.setCouponName(values.get(1).toString());
                        getCouponAllDtoVo.setCouponInfo(values.get(2).toString());
                        getCouponAllDtoVo.setCouponMoney(new BigDecimal(values.get(3).toString()));
                        getCouponBySourceDtoVoList.add(getCouponAllDtoVo);
                    }
                }
            }
            return getCouponBySourceDtoVoList;
        }

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
        String key = couponKey+getCouponIdParamVo.getCouponId();//redis主键值

        HashOperations hashOperations = redisTemplate.opsForHash();

        Object couponName = hashOperations.get(key, "couponName");
        if (StringUtils.isEmpty(couponName)) {
            Coupon coupon = couponMapper.selectById(getCouponIdParamVo.getCouponId());
            if (ObjectUtils.isEmpty(coupon)) {
                return null;
            }
            GetCouponNameDtoVo getCouponNameDtoVo = BeanCopyUtil.copyOne(coupon, GetCouponNameDtoVo::new);
            System.out.println(getCouponNameDtoVo);
            return getCouponNameDtoVo;
        }

        GetCouponNameDtoVo couponNameDtoVo = new GetCouponNameDtoVo();
        couponNameDtoVo.setCouponName((String)couponName);
        return couponNameDtoVo;
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
        HashOperations hashOperations = redisTemplate.opsForHash();

        String key = couponKey+getCouponIdParamVo.getCouponId();//redis主键值
        Long nowTime = System.currentTimeMillis();//当前时间

        Long size = hashOperations.size(key);
        //拿到redis里面优惠券数据
        if(size>0) {//redis里面某个hashKe有数据
            List values = hashOperations.values(key);//拿到redis里面某个hashKey的所有值
            GetCouponBySourceDtoVo getCouponBySourceDtoVo = new GetCouponBySourceDtoVo();
            getCouponBySourceDtoVo.setCouponName(values.get(1).toString());
            getCouponBySourceDtoVo.setCouponId(Integer.valueOf(values.get(0).toString()));
            getCouponBySourceDtoVo.setCouponInfo(values.get(2).toString());
            getCouponBySourceDtoVo.setCouponMoney(new BigDecimal(values.get(3).toString()));

            return getCouponBySourceDtoVo;
        }else{
            Coupon coupon = couponMapper.selectById(getCouponIdParamVo.getCouponId());
            if(nowTime<coupon.getCouponBeginTime()){//优惠券未失效的数据
                GetCouponBySourceDtoVo getCouponNameDtoVo = BeanCopyUtil.copyOne(coupon, GetCouponBySourceDtoVo::new);
                return getCouponNameDtoVo;
            }
            return null;
        }

    }

    /*
    * @Author TangShanLin
    * @Description TODO 服务完成时，生成服务收益数据需要调用的feign接口逻辑，通过id查询面额和来源
    * @Date  1:22
    * @Param []
    * @return com.woniu.car.marketing.model.dtoVo.GetCouponSourceAndMoneyByIdDtoVo
    **/
    @Override
    public GetCouponSourceAndMoneyByIdDtoVo getCouponSourceAndMoneyByIdDtoVoResultEntity(GetCouponIdParamVo getCouponIdParamVo) {
        Coupon coupon = couponMapper.selectById(getCouponIdParamVo.getCouponId());
        //如果判断查出来是否为空
        if (ObjectUtils.isEmpty(coupon)) {
            return null;
        }else {
            GetCouponSourceAndMoneyByIdDtoVo getCouponSourceAndMoneyByIdDtoVo = BeanCopyUtil.copyOne(coupon, GetCouponSourceAndMoneyByIdDtoVo::new);
            return getCouponSourceAndMoneyByIdDtoVo;
        }

    }
}
