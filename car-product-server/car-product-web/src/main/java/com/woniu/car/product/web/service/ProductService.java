package com.woniu.car.product.web.service;

import com.woniu.car.product.model.dto.ShowProductDto;
import com.woniu.car.product.model.parame.ShowProductParame;
import com.woniu.car.product.web.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.product.model.dto.HotProductDto;
import com.woniu.car.product.model.dto.ProductDtoTwo;
import com.woniu.car.product.model.dto.ProductOrderDto;
import com.woniu.car.product.model.parame.ProductParame;
import com.woniu.car.product.model.parame.ProductTwoParame;

import java.util.List;

/**
 * <p>
 * 先查商品的一级分类，提供给前端一个URL，在查二级分类，每一个二级分类提供一个URL，供前端调用，最后查根据商品id查商 服务类
 * </p>
 *
 * @author cx
 * @since 2021-04-06
 */
public interface ProductService extends IService<Product> {

        /**
         * 增加商品
         * @param parame
         * @return
         */
        public boolean addProduct(ProductParame parame);

        /**
         * 订单调用商品信息
         * @param id
         */
        public Product getProductById(Integer id);

        /**
         * 订单修改商品减库存
         * @param orderDto
         */
        public void updeteProductStock(ProductOrderDto orderDto);

        /**
         * 订单修改商品加库存
         * @param orderDto
         */
        public void updateStock(ProductOrderDto orderDto);

        /**
         * 查询精选商品
         * @return
         */
        public List<HotProductDto> getHotProduct();

        /**
         * 二级分类查出的商品列表
         * @return
         */
        public List<ProductDtoTwo> getProductTwo(ProductTwoParame parame);

        /**
         * 查询单个商品详情
         * @param parame
         * @return
         */
        public ShowProductDto ShowProduct(ShowProductParame parame);

}
