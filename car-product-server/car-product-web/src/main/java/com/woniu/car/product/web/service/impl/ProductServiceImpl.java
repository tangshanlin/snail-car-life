package com.woniu.car.product.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.exception.CarException;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.product.model.dto.ShowProductDto;
import com.woniu.car.product.model.parame.ShowProductParame;
import com.woniu.car.product.web.domain.Product;
import com.woniu.car.product.model.dto.HotProductDto;
import com.woniu.car.product.model.dto.ProductDtoTwo;
import com.woniu.car.product.model.dto.ProductOrderDto;
import com.woniu.car.product.web.mapper.ProductMapper;
import com.woniu.car.product.model.parame.ProductParame;
import com.woniu.car.product.model.parame.ProductTwoParame;
import com.woniu.car.product.web.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.product.web.util.ProductMyFileUpload;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 先查商品的一级分类，提供给前端一个URL，在查二级分类，每一个二级分类提供一个URL，供前端调用，最后查根据商品id查商 服务实现类
 * </p>
 *
 * @author cx
 * @since 2021-04-06
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductMyFileUpload myFileUpload;

    @Override
    public boolean addProduct(ProductParame parame) {
        if (!ObjectUtils.isEmpty(parame)) {

            MultipartFile[] file = parame.getFile();
            ArrayList<String> uploads = myFileUpload.upload(file);
            JSONObject jsonObject = new JSONObject();
            if (uploads.size() == 0) {
                System.out.println("没有上传商品图片");
            } else {
                uploads.forEach(upload -> {
                    jsonObject.put("P" + UUID.randomUUID().toString(), upload);
                });
            }
            String commentImages = JSONObject.toJSONString(jsonObject);
            Product product = BeanCopyUtil.copyOne(parame, Product::new);
            product.setProductImage(commentImages);
            int insert = productMapper.insert(product);
            if (insert > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Product getProductById(Integer id) {
        Product product = productMapper.selectById(id);
        return product;
    }

    /**
     * 订单修改商品减库存
     *
     * @param orderDto
     */
    @Override
    public void updeteProductStock(ProductOrderDto orderDto) {
        Product product = productMapper.selectById(orderDto.getProductId());
        Integer productStock = product.getProductStock();
        if (productStock < orderDto.getBuyNumber()) {
            throw new CarException("商品库存不足", 1500);
        }
        Integer i = productStock - orderDto.getBuyNumber();
        product.setProductStock(i);
        productMapper.updateById(product);


    }

    /**
     * 订单修改商品加库存
     *
     * @param orderDto
     */
    @Override
    public void updateStock(ProductOrderDto orderDto) {
        Product product = productMapper.selectById(orderDto.getProductId());
        Integer productStock = product.getProductStock();
        Integer i = productStock + orderDto.getBuyNumber();
        product.setProductStock(i);
        productMapper.updateById(product);
    }

    /**
     * 查询精选商品
     *
     * @return
     */

    @Override
    public List<HotProductDto> getHotProduct() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_isHot", 1);
        List<Product> products = productMapper.selectList(queryWrapper);
        List<HotProductDto> hotProductDtos = BeanCopyUtil.copyList(products, HotProductDto::new);

        return hotProductDtos;
    }

    /**
     * 根据二级分类id查询商品列表
     *
     * @param parame
     * @return
     */

    @Override
    public List<ProductDtoTwo> getProductTwo(ProductTwoParame parame) {
        QueryWrapper<Product> obj = new QueryWrapper<>();
        obj.eq("cate_id", parame.getCateId());
        List<Product> products = productMapper.selectList(obj);
        List<ProductDtoTwo> pro = BeanCopyUtil.copyList(products, ProductDtoTwo::new);

        /**
         * 将图片类型转换为JSONObject
         */
        for (int i = 0; i < products.size(); i++) {
            JSONObject jsonObject = JSONObject.parseObject(products.get(i).getProductImage());

            pro.get(i).setCateImages(jsonObject);
        }
        return pro;
    }

    /**
     * 查询单个商品
     *
     * @param parame
     * @return
     */
    @Override
    public ShowProductDto ShowProduct(ShowProductParame parame) {
        Product product = productMapper.selectById(parame.getProductId());
        ShowProductDto one = BeanCopyUtil.copyOne(product, ShowProductDto::new);
        /**
         * 将图片类型转换为JSONObject
         */

        JSONObject jsonObject = JSONObject.parseObject(product.getProductImage());

        one.setCateImages(jsonObject);


        return one;
    }


}
