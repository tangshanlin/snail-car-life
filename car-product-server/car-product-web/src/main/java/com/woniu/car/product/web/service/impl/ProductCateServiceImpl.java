package com.woniu.car.product.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.product.web.domain.ProductCate;
import com.woniu.car.product.model.dto.ProductCateDto;
import com.woniu.car.product.model.dto.ProductCateOneDto;
import com.woniu.car.product.web.mapper.ProductCateMapper;
import com.woniu.car.product.web.service.ProductCateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cx
 * @since 2021-04-06
 */
@Service
public class ProductCateServiceImpl extends ServiceImpl<ProductCateMapper, ProductCate> implements ProductCateService {
    @Resource
    private ProductCateMapper productCateMapper;


    /**
     * 查询一级分类
     *
     * @return
     */
    @Override
    public List<ProductCateDto> getProductCate() {
        QueryWrapper<ProductCate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        List<ProductCate> productCates = productCateMapper.selectList(queryWrapper);
        List<ProductCateDto> productCateDtos = BeanCopyUtil.copyList(productCates, ProductCateDto::new);


        return productCateDtos;
    }

    @Override
    public List<ProductCateOneDto> getTwoProductCate1() {

        List<ProductCate> productCates = productCateMapper.selectList(null);
        List<ProductCateOneDto> dtos = BeanCopyUtil.copyList(productCates, ProductCateOneDto::new);

        for (int i = 0; i < productCates.size(); i++) {
            JSONObject jsonObject = JSONObject.parseObject(productCates.get(i).getCateImage());
            dtos.get(i).setCateImage(null);
            dtos.get(i).setCateImages(jsonObject);
        }
        ArrayList<ProductCateOneDto> needproductCatess = new ArrayList<>();
        dtos.forEach(productCate -> {
            if (productCate.getLevel() == 1) {
                needproductCatess.add(productCate);
                productCate.setChildcateList(new ArrayList<ProductCateOneDto>());
            }
        });

        dtos.forEach(productCate -> {
            needproductCatess.forEach(first -> {
                if (productCate.getParentId().equals(first.getCateId())) {
                    first.getChildcateList().add(productCate);
                }
            });
        });

        return needproductCatess;

    }


//    @Override
//    public List<ProductCateOneDto> getTwoProductCate() {
//        /**
//         * 先查一级分类
//         */
//        QueryWrapper<ProductCate> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("parent_id",0);
//        List<ProductCate> productCates = productCateMapper.selectList(queryWrapper);
//        List<ProductCateOneDto> productCateDtos = BeanCopyUtil.copyList(productCates, ProductCateOneDto::new);
//        /**
//         * 在查一级分类下的二级分类
//         */
//        QueryWrapper<ProductCate> queryWrapper2 = new QueryWrapper<>();
//        queryWrapper.ne("parent_id",0);
//        List<ProductCate> productCates2 = productCateMapper.selectList(queryWrapper);
//        List<ProductCateDto> productCateDtos2 = BeanCopyUtil.copyList(productCates2, ProductCateDto::new);
//        /**
//         * 创建List集合，用于存放最终封装数据
//         */
//
//        List<ProductCateOneDto> list=new ArrayList<>();
//
//        /**
//         * 封装一级分类
//         * 查询出来所有的一级分类list集合遍历，
//         * 得到每个一级分类对象，获取每个一级分类对象值，
//         * 封装到要求的list集合里面
//         */
//
//        for (int i=0;i<productCates.size();i++){
//            ProductCate productCate=productCates.get(i);
//            ProductCateOneDto productCateOneDto=new ProductCateOneDto();
//            BeanUtils.copyProperties(productCate,productCateOneDto);
//            list.add(productCateOneDto);
//
//            List<ProductCateDto> list2=new ArrayList<>();
//            for (int m=0;m<productCates2.size();m++){
//                ProductCate productCate2=productCates2.get(m);
//                if (productCate2.getParentId().equals(productCate.getCateId())){
//                    ProductCateDto productCateDto=new ProductCateDto();
//                    BeanUtils.copyProperties(productCate2,productCateDto);
//                    list2.add(productCateDto);
//                }
//            }
//
//            productCateOneDto.setChildcateList(list2);
//        }
//
//        return list;
//    }


//    @Override
//    public List<ProductCateDto> getCateProduct() {
//
//        QueryWrapper<ProductCate> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("parent_id",);
////      List<ProductCate> productCates = productCateMapper.selectList(queryWrapper);
////        BeanCopyUtil.copyList(productCates,ProductCateDto::new);
////
//
//        return null;
//    }
}
