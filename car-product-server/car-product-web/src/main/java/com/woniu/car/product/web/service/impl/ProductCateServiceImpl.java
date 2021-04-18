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

//        for (int i = 0; i < productCates.size(); i++) {
//            JSONObject jsonObject = JSONObject.parseObject(productCates.get(i).getCateImage());
//            dtos.get(i).setCateImage(null);
//            dtos.get(i).setCateImages(jsonObject);
//        }
        ArrayList<ProductCateOneDto> needproductCatess = new ArrayList<>();
        dtos.forEach(productCate -> {
            //System.out.println(productCate);
            if (productCate.getLevel() == 1) {
                productCate.setChildcateList(new ArrayList<ProductCateOneDto>());
                needproductCatess.add(productCate);
            }
        });
        System.out.println("aaa0"+needproductCatess);
        dtos.forEach(productCate -> {
            System.out.println(111);
            needproductCatess.forEach(first -> {
                if (productCate.getParentId().equals(first.getCateId())) {
                    first.getChildcateList().add(productCate);
                }
            });
        });
        return needproductCatess;
    }




}
