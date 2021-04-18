package com.woniu.car.shop.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.auth.model.dto.BackBalanceDto;
import com.woniu.car.auth.model.params.BackBalanceParams;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.core.exception.CarException;
import com.woniu.car.commons.web.discributelock.MyLock;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.marketing.model.dtoVo.GetCouponSourceAndMoneyByIdDtoVo;
import com.woniu.car.marketing.model.paramVo.GetCouponIdParamVo;
import com.woniu.car.shop.client.feign.FeignAuthClient;
import com.woniu.car.shop.client.feign.FeignMarketingClient;
import com.woniu.car.shop.model.dtoVo.FindShopServiceEarningInfoByEarningIdDtoVo;
import com.woniu.car.shop.model.paramVo.AddShopEarningsInfoParamVo;
import com.woniu.car.shop.model.paramVo.FindShopEarningIdParamVo;
import com.woniu.car.shop.web.domain.Shop;
import com.woniu.car.shop.web.domain.ShopEarningsInfo;
import com.woniu.car.shop.web.domain.ShopServiceEarnings;
import com.woniu.car.shop.web.mapper.ShopEarningsInfoMapper;
import com.woniu.car.shop.web.mapper.ShopMapper;
import com.woniu.car.shop.web.mapper.ShopServiceEarningsMapper;
import com.woniu.car.shop.web.service.ShopEarningsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.user.web.util.GetTokenUtil;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tsl
 * @since 2021-04-05
 */
@Service
public class ShopEarningsInfoServiceImpl extends ServiceImpl<ShopEarningsInfoMapper, ShopEarningsInfo> implements ShopEarningsInfoService {

    @Resource
    private ShopEarningsInfoMapper shopEarningsInfoMapper;
    @Resource
    private ShopServiceEarningsMapper shopServiceEarningsMapper;
    @Resource
    private ShopMapper shopMapper;

    @Resource
    private FeignAuthClient feignAuthClient;
    @Resource
    private FeignMarketingClient feignMarketingClient;


    /*
    * @Author TangShanLin
    * @Description TODO 某次服务完成后新增服务具体收益数据
    * @Date  0:23
    * @Param [addShopEarningsInfoParamVo]
    * @return java.lang.Boolean
    **/
    @Override
    public Integer addShopEarningsInfo(AddShopEarningsInfoParamVo addShopEarningsInfoParamVo) {
        //先通过门店id和服务名称查询服务收益类型主键id值
        QueryWrapper<ShopServiceEarnings> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",addShopEarningsInfoParamVo.getShopId());
        queryWrapper.eq("car_service_name",addShopEarningsInfoParamVo.getCarServiceName());
        ShopServiceEarnings shopServiceEarnings = shopServiceEarningsMapper.selectOne(queryWrapper);

        if (ObjectUtils.isEmpty(shopServiceEarnings)) {
            return ConstCode.Service_Revenue_Is_Empty;
        }

        //根据门店id拿到提现比例
        Shop shop = shopMapper.selectById(addShopEarningsInfoParamVo.getShopId());
        Double shopProportion = shop.getShopProportion();//获取门店提成比例

        //拿到某次服务具体添加数据
        Integer shopServiceEarningsId = shopServiceEarnings.getShopServiceEarningsId();//关联的服务收益主键id
        Long payTime = addShopEarningsInfoParamVo.getPayTime().getTime();//支付时间
        BigDecimal carServiceMoney = addShopEarningsInfoParamVo.getCarServiceMoney();//支付金额

        BigDecimal carServicePrice = carServiceMoney.multiply(new BigDecimal(shopProportion));//该次服务门店收益
        BigDecimal shopServiceInfoPlatformMoney = carServiceMoney.subtract(carServicePrice);//该次服务平台抽成金额


        ShopEarningsInfo entity = new ShopEarningsInfo();
        entity.setShopServiceEarningsId(shopServiceEarningsId);
        entity.setPayTime(payTime);
        entity.setCarServicePrice(carServicePrice);
        entity.setShopServiceInfoPlatformMoney(shopServiceInfoPlatformMoney);
        //entity.setUserAccount(GetTokenUtil.getUserAccount());//添加用户账号
        try {
            entity.setUserAccount(GetTokenUtil.getUserAccount());//添加用户账号
        }catch (Exception e){
            throw new CarException("Token为空",500);
        }



        //调用修改门店余额的方法
        updateShopBalance(addShopEarningsInfoParamVo,carServicePrice,shopServiceInfoPlatformMoney);

        Integer i = shopEarningsInfoMapper.insert(entity);
        if (i==1) return 200;
        return 500;
    }

    @Override
    public List<FindShopServiceEarningInfoByEarningIdDtoVo> getShopEarningsInfo(FindShopEarningIdParamVo findShopEarningIdParamVo) {
        QueryWrapper<ShopEarningsInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_service_earnings_id",findShopEarningIdParamVo.getShopServiceEarningsId());
        List<ShopEarningsInfo> shopEarningsInfos = shopEarningsInfoMapper.selectList(queryWrapper);
        if (ObjectUtils.isEmpty(shopEarningsInfos)) {
            return null;
        }
        List<FindShopServiceEarningInfoByEarningIdDtoVo> findShopServiceEarningInfoByEarningIdDtoVos = BeanCopyUtil.copyList(shopEarningsInfos, FindShopServiceEarningInfoByEarningIdDtoVo::new);
        return findShopServiceEarningInfoByEarningIdDtoVos;
    }

    public void updateShopBalance(AddShopEarningsInfoParamVo addShopEarningsInfoParamVo,BigDecimal carServicePrice,BigDecimal shopServiceInfoPlatformMoney){
        Shop shopDB = shopMapper.selectById(addShopEarningsInfoParamVo.getShopId());

        GetCouponIdParamVo couponIdParamVo = new GetCouponIdParamVo();
        couponIdParamVo.setCouponId(addShopEarningsInfoParamVo.getCouponId());

        ResultEntity<GetCouponSourceAndMoneyByIdDtoVo> couponSourceAndMoneyByIdDtoVoResultEntity = feignMarketingClient.getCouponSourceAndMoneyByIdDtoVoResultEntity(couponIdParamVo);
        GetCouponSourceAndMoneyByIdDtoVo data = couponSourceAndMoneyByIdDtoVoResultEntity.getData();
        BigDecimal couponMoney = data.getCouponMoney();
        Integer couponGoods = data.getCouponGoods();
        //如果没有使用优惠券,传过来的优惠券相关参数就是空
        if(couponGoods==null||couponMoney==null){
            //修改门店余额
            Shop shop = new Shop();
            shop.setShopId(addShopEarningsInfoParamVo.getShopId());
            shop.setShopBalance(shopDB.getShopBalance().add(carServicePrice));
            shopMapper.updateById(shop);

            //修改平台余额
            BackBalanceParams backBalanceParams = new BackBalanceParams();
            backBalanceParams.setBackBalance(shopServiceInfoPlatformMoney);
            feignAuthClient.updateBackAddBalance(backBalanceParams);
        }else {
            //使用的是平台优惠券，平台余额减少值为优惠券面额，门店余额新增值为提成比例后的钱
            if(couponGoods==0){
                //平台余额减少
                BackBalanceParams backBalanceParams = new BackBalanceParams();
                //将优惠券面额设置为负数进行修改平余额
                BigDecimal multiply = couponMoney.multiply(new BigDecimal(-1));
                backBalanceParams.setBackBalance(multiply.add(shopServiceInfoPlatformMoney));
                feignAuthClient.updateBackAddBalance(backBalanceParams);

                //修改门店余额
                Shop shop = new Shop();
                shop.setShopId(addShopEarningsInfoParamVo.getShopId());
                shop.setShopBalance(shopDB.getShopBalance().add(carServicePrice));
                shopMapper.updateById(shop);
            }else{
                //使用的是门店优惠券
                //平台余额增加
                BackBalanceParams backBalanceParams = new BackBalanceParams();
                //将平台提成金额添加进去
                System.out.println("平台提成金额"+shopServiceInfoPlatformMoney);
                backBalanceParams.setBackBalance(shopServiceInfoPlatformMoney);
                feignAuthClient.updateBackAddBalance(backBalanceParams);
                System.out.println("aaaaaa");


                //修改门店余额
                Shop shop = new Shop();
                //先减去优惠券面额
                BigDecimal subtract = shopDB.getShopBalance().subtract(couponMoney);

                shop.setShopId(addShopEarningsInfoParamVo.getShopId());
                shop.setShopBalance(subtract.add(carServicePrice));
                shopMapper.updateById(shop);
            }
        }
    }
}
