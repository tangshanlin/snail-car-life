package com.woniu.car.shop.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.auth.model.params.InsertAccountByTypeParams;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.core.exception.CarException;
import com.woniu.car.commons.web.util.BeanCopyUtil;

import com.woniu.car.shop.client.feign.FeignAuthClient;
import com.woniu.car.shop.model.dtoVo.*;
import com.woniu.car.shop.model.paramVo.*;
import com.woniu.car.shop.web.domain.EsShopWoniuCar;
import com.woniu.car.shop.web.domain.Shop;


import com.woniu.car.shop.web.mapper.ShopMapper;
import com.woniu.car.shop.web.service.ShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.shop.web.utils.Change;
import com.woniu.car.shop.web.utils.ShopFileUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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

    @Resource
    private FeignAuthClient feignAuthClient;

    @Resource
    private ElasticsearchOperations elasticsearchOperations;//es
    /*
    * @Author TangShanLin
    * @Description TODO 门店申请逻辑
    * @Date  14:24
    * @Param [addShopParamVo]
    * @return int
    **/
    @Override
    public void addShopParamVo(AddShopParamVo addShopParamVo) {

        String shopName = addShopParamVo.getShopName();//要添加的门店名称
        String shopAddress = addShopParamVo.getShopAddress();//要添加的门店地址
        String shopTel = addShopParamVo.getShopTel();//要添加的门店电话

        List<Shop> shops = shopMapper.selectList(null);
        for (Shop shop:shops){
            if(shop.getShopName().equals(shopName)){
                throw new CarException("门店名称已存在",ConstCode.ADD_SHOP_NAME_FAIL);
            }
            if(shop.getShopAddress().equals(shopAddress)){
                throw new CarException("门店地址已存在",ConstCode.ADD_SHOP_ADDRESS_FAIL);
            }
            if(shop.getShopTel().equals(shopTel)){
                throw new CarException("联系方式已存在",ConstCode.ADD_SHOP_TEL_FAIL);
            }
        }
        MultipartFile[] file = addShopParamVo.getFile();
        ArrayList<String> shopImage = shopFileUpload.upload(file);//将文件上传到minlo服务器上面
        String shopImages = shopImage.get(0);//获取上传的链接地址
        Shop shop = BeanCopyUtil.copyOne(addShopParamVo, Shop::new);
        shop.setShopImage(shopImages);

        String shopTag = Change.arrayChangeString(addShopParamVo.getShopTag(), ",");

        shop.setShopTag(shopTag);
        shop.setShopProportion(0.8);//设置默认的门店收益比例
        int i = shopMapper.insert(shop);
        if(i!=1) throw new CarException("添加门店失败",500);

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
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopId.getShopId());
        queryWrapper.eq("shop_account_start",1);

        Shop shop = shopMapper.selectOne(queryWrapper);

        if (ObjectUtils.isEmpty(shop)) {
            throw new CarException("没有该门店信息",500);
        }
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
        queryWrapper.eq("shop_class",1);//查询条件必须是4s门店
        queryWrapper.eq("shop_account_start",1);
        if(findShopByClass.getShopBrand()==null){ //没传品牌查所有4s门店信息
            List<Shop> listShop = null;
            try {
                listShop = shopMapper.selectList(queryWrapper);
            }catch (Exception e){
                throw new CarException("未查询到是4s门店的信息",500);
            }
            List<FindShopByClassDtoVo> listFindShopByClassDtoVo = BeanCopyUtil.copyList(listShop, FindShopByClassDtoVo::new);
            return listFindShopByClassDtoVo;
        }else {
            queryWrapper.eq("shop_brand",findShopByClass.getShopBrand());
            List<Shop> listShop = null;
            try{
                listShop =  shopMapper.selectList(queryWrapper);
            }catch (Exception e){
                throw new CarException("未查询到爱车品牌的4s门店的信息",500);
            }
            List<FindShopByClassDtoVo> listFindShopByClassDtoVo = BeanCopyUtil.copyList(listShop, FindShopByClassDtoVo::new);
            return listFindShopByClassDtoVo;
        }

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
        queryWrapper.eq("shop_account_start",1);
        List listShop = shopMapper.selectList(queryWrapper);
        if (ObjectUtils.isEmpty(listShop)) {
            throw new CarException("暂无优选好店",500);
        }
        List list = BeanCopyUtil.copyList(listShop, FindShopByIntegralDtoVo::new);
        return list;
    }

    /**
     * 查询附近门店逻辑把所有门店数据存到es里面
     * @param meLngLat
     * @return
     */
    @Override
    public SearchHits<EsShopWoniuCar> findShopInfoAll(FindShopInfoByMeLngLat meLngLat) {
        //创建高亮显示器
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //设置高亮字段
        highlightBuilder.field("type");
        //设置为false，匹配字段都会高亮显示
        highlightBuilder.requireFieldMatch(false);
        //设置如何高亮显示
        highlightBuilder.preTags("<span style=\"color:red\">");
        highlightBuilder.postTags("</span>");
        //设置高亮显示范围以字符为单位
        highlightBuilder.fragmentSize(800);
        //设置高亮显示的开始位置
        highlightBuilder.numOfFragments(0);
        // 间接实现了QueryBuilder接口
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //构建NativeSearchQuery建造器
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // 以某点为中心，搜索指定范围
        GeoDistanceQueryBuilder distanceQueryBuilder = new GeoDistanceQueryBuilder("location");
        distanceQueryBuilder.point(meLngLat.getLat(),meLngLat.getLng());
        // 定义查询单位：公里
        distanceQueryBuilder.distance(meLngLat.getDistance(), DistanceUnit.KILOMETERS);
        boolQueryBuilder.filter(distanceQueryBuilder);
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withHighlightBuilder(highlightBuilder);
        //注册bool查询条件
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withHighlightBuilder(highlightBuilder).build();
        //构建NativeSearch实例
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        //执行查询方法
        SearchHits<EsShopWoniuCar> search = elasticsearchOperations.search(nativeSearchQuery, EsShopWoniuCar.class);
        if(search.hasSearchHits()){

            return search;
        }else {
            throw new CarException("未查询到附近门店信息",100010);
        }
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
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopIdParamVo.getShopId());
        queryWrapper.eq("shop_account_start",1);

        Shop shop = null;
        try {
            shop = shopMapper.selectOne(queryWrapper);
        }catch (Exception e){
            throw new CarException("没查到门店信息",500);
        }

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
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopIdParamVo.getShopId());
        queryWrapper.eq("shop_account_start",1);

        Shop shop = null;
        try {
            shop = shopMapper.selectOne(queryWrapper);
        }catch (Exception e){
            throw new CarException("未查询到门店信息",500);
        }
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
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopIdParamVo.getShopId());
        queryWrapper.eq("shop_account_start",1);

        Shop shop = null;
        try {
            shop = shopMapper.selectOne(queryWrapper);
        }catch (Exception e){
            throw new CarException("未查询到门店信息",500);
        }
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
        List<Shop> shops = null;
        try {
            shops = shopMapper.selectList(queryWrapper);
        }catch (Exception e){
            throw new CarException("没查到该门店信息，修改失败",500);
        }


        List<FindShopInfoByStateDtoVo> findShopInfoByStateDtoVos = BeanCopyUtil.copyList(shops, FindShopInfoByStateDtoVo::new);
        return findShopInfoByStateDtoVos;
    }

    /*
    * @Author TangShanLin
    * @Description TODO 门店审核逻辑
    * @Date  16:55
    * @Param [shopId]
    * @return java.lang.Boolean
    **/
    @Override
    public Integer updateShopAccountStart(ShopIdParamVo shopId) {
        Shop shopDB = shopMapper.selectById(shopId.getShopId());
        if(!ObjectUtils.isEmpty(shopDB)){
            InsertAccountByTypeParams insertAccountByTypeParams = new InsertAccountByTypeParams();
            insertAccountByTypeParams.setType(2);
            ResultEntity<String> stringResultEntity = feignAuthClient.insertAccountByType(insertAccountByTypeParams);
            Integer code = stringResultEntity.getCode();
            if(code.equals(ConstCode.ACCESS_SUCCESS)){
                String account = stringResultEntity.getData();//拿到账号
                Shop shop = new Shop();
                shop.setShopId(shopId.getShopId());
                shop.setShopAccount(account);
                shop.setShopAccountStart(1);
                try {
                    shopMapper.updateById(shop);
                }catch (Exception e){
                    throw new CarException("添加门店账号失败",500);
                }
                //审核通过将门店数据存入es中



                GeoPoint location= new GeoPoint(Double.valueOf(shopDB.getShopLatitude()),Double.valueOf(shopDB.getShopLongitude()));
                //将数据复制到Es模板类中
                EsShopWoniuCar esShopWoniuCar = BeanCopyUtil.copyOne(shopDB, EsShopWoniuCar::new);
                System.out.println(esShopWoniuCar);
                //将数据存入Es，方便后期进行附近门店搜索
                esShopWoniuCar.setLocation(location);
                System.out.println(esShopWoniuCar);
                elasticsearchOperations.save(esShopWoniuCar);






                return ConstCode.ACCESS_SUCCESS;
            }else if (code.equals(ConstCode.GET_ACCOUNT_ROLE_FAIL)){
                throw new CarException("账号授予角色失败",ConstCode.GET_ACCOUNT_ROLE_FAIL);
            }else {
                throw new CarException("新增后台账户失败",ConstCode.ADD_END_ACCOUNT_FAIL);
            }
        }else {
            throw new CarException("未查询到门店信息",ConstCode.ADD_END_ACCOUNT_FAIL);
        }



    }

    /*
    * @Author TangShanLin
    * @Description TODO 每次评论完成就更新门店总评分
    * @Date  12:07
    * @Param [amendShopGradeByShopIdParamVo]
    * @return java.lang.Boolean
    **/
    @Override
    public Boolean updateShopGrade(AmendShopGradeByShopIdParamVo amendShopGradeByShopIdParamVo) {
        Shop shop = new Shop();
        shop.setShopId(amendShopGradeByShopIdParamVo.getShopId());
        shop.setShopGrade(amendShopGradeByShopIdParamVo.getShopGrade());
        int i = 0;
        try {
            i = shopMapper.updateById(shop);
        }catch (Exception e){
            throw new CarException("修改门店总评分失败",500);
        }
        if(i==1) return true;
        return false;
    }
    /*
    * @Author TangShanLin
    * @Description TODO 服务完成就将总成交数加一
    * @Date  12:17
    * @Param [shopIdParamVo]
    * @return java.lang.Boolean
    **/
    @Override
    public Boolean updateShopOrderNumber(ShopIdParamVo shopIdParamVo) {
        Shop shopDB = shopMapper.selectById(shopIdParamVo.getShopId());
        shopDB.setShopOrderNumber(shopDB.getShopOrderNumber()+1);
        int i = shopMapper.updateById(shopDB);
        if(i==1) return true;
        return false;
    }
}
