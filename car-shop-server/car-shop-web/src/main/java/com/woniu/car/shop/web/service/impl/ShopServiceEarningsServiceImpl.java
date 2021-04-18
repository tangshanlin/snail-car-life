package com.woniu.car.shop.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.shop.model.dtoVo.FindShopServiceEarningAllByShopIdDtoVo;
import com.woniu.car.shop.model.paramVo.AddShopServiceEarningsParamVo;
import com.woniu.car.shop.model.paramVo.ShopIdParamVo;
import com.woniu.car.shop.web.domain.ShopServiceEarnings;
import com.woniu.car.shop.web.mapper.ShopServiceEarningsMapper;
import com.woniu.car.shop.web.service.ShopServiceEarningsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Service;
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
public class ShopServiceEarningsServiceImpl extends ServiceImpl<ShopServiceEarningsMapper, ShopServiceEarnings> implements ShopServiceEarningsService {

    @Resource
    private ShopServiceEarningsMapper shopServiceEarningsMapper;

    /*
    * @Author TangShanLin
    * @Description TODO 新增服务时添加对应门店对应服务总收益数据
    * @Date  17:14
    * @Param [addShopServiceEarningsParamVo]
    * @return java.lang.Boolean
    **/
    @Override
    public Boolean addShopServiceEarnings(AddShopServiceEarningsParamVo addShopServiceEarningsParamVo) {
        ShopServiceEarnings shopServiceEarnings = new ShopServiceEarnings();
        shopServiceEarnings.setCarServiceName(addShopServiceEarningsParamVo.getCarServiceName());//服务名称
        shopServiceEarnings.setShopId(addShopServiceEarningsParamVo.getShopId());//关联门店id
        shopServiceEarnings.setShopServiceEarningsMoney(new BigDecimal(0));//该服务门店收益总金额
        shopServiceEarnings.setShopServicePlatformMoney(new BigDecimal(0));
        int insert = shopServiceEarningsMapper.insert(shopServiceEarnings);
        if (insert==1) return true;
        return false;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 查看某个门店下各个服务总收益
    * @Date  10:24
    * @Param [shopIdParamVo]
    * @return java.util.List<com.woniu.car.shop.model.dtoVo.FindShopServiceEarningAllByShopIdDtoVo>
    **/
    @Override
    public List<FindShopServiceEarningAllByShopIdDtoVo> getShopServiceEarnings(ShopIdParamVo shopIdParamVo) {
        QueryWrapper<ShopServiceEarnings> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopIdParamVo.getShopId());
        List<ShopServiceEarnings> shopServiceEarnings = shopServiceEarningsMapper.selectList(queryWrapper);
        if (ObjectUtils.isEmpty(shopServiceEarnings)) {
            return null;
        }

        List<FindShopServiceEarningAllByShopIdDtoVo> findShopServiceEarningAllByShopIdDtoVoList = BeanCopyUtil.copyList(shopServiceEarnings, FindShopServiceEarningAllByShopIdDtoVo::new);
        return findShopServiceEarningAllByShopIdDtoVoList;
    }
}
