package com.woniu.car.shop.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.web.util.BeanCopyUtil;

import com.woniu.car.shop.model.dtoVo.*;
import com.woniu.car.shop.model.paramVo.AddShopParamVo;
import com.woniu.car.shop.model.paramVo.FindShopByClassParamVo;
import com.woniu.car.shop.model.paramVo.FindShopInfoByMeLngLat;
import com.woniu.car.shop.model.paramVo.ShopIdParamVo;
import com.woniu.car.shop.web.domain.Shop;


import com.woniu.car.shop.web.mapper.ShopMapper;
import com.woniu.car.shop.web.service.ShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.shop.web.utils.ShopFileUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
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
 * @since 2021-04-05
 */
@Service
@Slf4j
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Resource
    private ShopMapper shopMapper;


    @Resource
    private ShopFileUpload shopFileUpload;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /*
    * @Author TangShanLin
    * @Description TODO 门店申请逻辑
    * @Date  14:24
    * @Param [addShopParamVo]
    * @return int
    **/
    @Override
    public int addShopParamVo(AddShopParamVo addShopParamVo) {

        Integer insertCode = 0;
        String shopName = addShopParamVo.getShopName();//要添加的门店名称
        String shopAddress = addShopParamVo.getShopAddress();//要添加的门店地址
        Long shopTel = addShopParamVo.getShopTel();//要添加的门店电话

        List<Shop> shops = shopMapper.selectList(null);
        System.out.println(shops);
        for (Shop shop:shops){
            System.out.println(shop.getShopName()+"-----");
            if(shop.getShopName().equals(shopName)){
                insertCode = ConstCode.ADD_SHOP_NAME_FAIL;
            }
            if(shop.getShopAddress().equals(shopAddress)){
                insertCode = ConstCode.ADD_SHOP_ADDRESS_FAIL;
            }
            if(shop.getShopTel().equals(shopTel)){
                insertCode = ConstCode.ADD_SHOP_TEL_FAIL;
            }
        }
        if(insertCode!=ConstCode.ADD_SHOP_NAME_FAIL&&insertCode!=ConstCode.ADD_SHOP_ADDRESS_FAIL&&insertCode!=ConstCode.ADD_SHOP_TEL_FAIL){
            System.out.println(insertCode!=ConstCode.ADD_SHOP_NAME_FAIL&&insertCode!=ConstCode.ADD_SHOP_ADDRESS_FAIL&&insertCode!=ConstCode.ADD_SHOP_TEL_FAIL);
            MultipartFile[] file = addShopParamVo.getFile();
            ArrayList<String> shopImage = shopFileUpload.upload(file);//将文件上传到minlo服务器上面
            String shopImages = shopImage.get(0);//获取上传的链接地址
            Shop shop = BeanCopyUtil.copyOne(addShopParamVo, Shop::new);
            shop.setShopImage(shopImages);
            shop.setShopProportion(0.8);//设置默认的门店收益比例
            insertCode = shopMapper.insert(shop);
        }

        MultipartFile[] file = addShopParamVo.getFile();
        /**
         * 将文件上传到minlo服务器上面
         */
        ArrayList<String> shopImage = shopFileUpload.upload(file);
        String shopImages = shopImage.get(0);
        Shop shop = BeanCopyUtil.copyOne(addShopParamVo, Shop::new);
        shop.setShopImage(shopImages);
        int insert = shopMapper.insert(shop);
        return insert;

    }

    /*
    * @Author TangShanLin
    * @Description TODO 通过门店id查询门店详细信息逻辑
    * @Date  15:34
    * @Param [shopId]
    * @return com.woniu.car.shop.model.dtoVo.FindShopInfoVo
    **/
    @Override
    public FindShopInfoVo findShopInfo(ShopIdParamVo shopId) {
        Shop shop = shopMapper.selectById(shopId.getShopId());
        FindShopInfoVo findShopInfoVo = BeanCopyUtil.copyOne(shop, FindShopInfoVo::new);
        return findShopInfoVo;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 查询对应品牌的4s门店逻辑
    * @Date  15:35
    * @Param [findShopByClass]
    * @return java.util.List<com.woniu.car.shop.model.dtoVo.FindShopByClassDtoVo>
    **/
    @Override
    public List<FindShopByClassDtoVo> findShopByClass(FindShopByClassParamVo findShopByClass) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("shop_class",findShopByClass.getShopClass());
        //queryWrapper.eq("shop_brand",findShopByClass.getShopBrand());
        List<Shop> listShop = shopMapper.selectList(queryWrapper);
        List<FindShopByClassDtoVo> listFindShopByClassDtoVo = BeanCopyUtil.copyList(listShop, FindShopByClassDtoVo::new);
        return listFindShopByClassDtoVo;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 查询所有优选好店逻辑
    * @Date  15:36
    * @Param []
    * @return java.util.List<com.woniu.car.shop.model.dtoVo.FindShopByIntegralDtoVo>
    **/
    @Override
    public List<FindShopByIntegralDtoVo> findShopByIntegral() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ge("shop_integral",90);
        List listShop = shopMapper.selectList(queryWrapper);
        List list = BeanCopyUtil.copyList(listShop, FindShopByIntegralDtoVo::new);
        return list;
    }

    /**
     * 查询附近门店逻辑把所有门店数据存到es里面
     * @param meLngLat
     * @return
     */
    @Override
    public List<FindShopInfoAll> findShopInfoAll(FindShopInfoByMeLngLat meLngLat) {

        return null;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 查询门店名称逻辑
    * @Date  17:55
    * @Param [shopIdParamVo]
    * @return com.woniu.car.shop.model.dtoVo.ShopNameDtoVo
    **/
    @Override
    public ShopNameDtoVo getShopNameByShopId(ShopIdParamVo shopIdParamVo) {
        Shop shop = shopMapper.selectById(shopIdParamVo.getShopId());
        ShopNameDtoVo shopNameDtoVo = BeanCopyUtil.copyOne(shop, ShopNameDtoVo::new);
        return shopNameDtoVo;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 查询门店信誉分逻辑
    * @Date  17:56
    * @Param [shopIdParamVo]
    * @return com.woniu.car.shop.model.dtoVo.ShopIntegralDtoVo
    **/
    @Override
    public ShopIntegralDtoVo getShopIntegralByShopId(ShopIdParamVo shopIdParamVo) {
        Shop shop = shopMapper.selectById(shopIdParamVo.getShopId());
        ShopIntegralDtoVo shopIntegralDtoVo = BeanCopyUtil.copyOne(shop, ShopIntegralDtoVo::new);
        return shopIntegralDtoVo;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 修改门店信誉分逻辑
    * @Date  15:38
    * @Param [shopIdParamVo]
    * @return java.lang.Boolean
    **/
    @Override
    public Boolean updateShopIntegralByShopId(ShopIdParamVo shopIdParamVo) {
        System.out.println(shopIdParamVo.getShopId()+"aaaaaaaaaaaaaaaaaa");
        Shop shop = shopMapper.selectById(shopIdParamVo.getShopId());
        shop.setShopIntegral(shop.getShopIntegral()-1);
        int i = shopMapper.updateById(shop);
        if(i!=0) return true;
        return false;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 查询所有未审核的门店信息
    * @Date  14:06
    * @Param []
    * @return java.util.List<com.woniu.car.shop.model.dtoVo.FindShopInfoByStateDtoVo>
    **/
    @Override
    public List<FindShopInfoByStateDtoVo> listShopInfoByState() {
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_account_start","0");
        List<Shop> shops = shopMapper.selectList(queryWrapper);
        List<FindShopInfoByStateDtoVo> findShopInfoByStateDtoVos = BeanCopyUtil.copyList(shops, FindShopInfoByStateDtoVo::new);
        return findShopInfoByStateDtoVos;
    }
}
