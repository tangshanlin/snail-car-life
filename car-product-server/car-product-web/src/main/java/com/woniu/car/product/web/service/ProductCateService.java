package com.woniu.car.product.web.service;

import com.woniu.car.product.web.domain.ProductCate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.product.model.dto.ProductCateDto;
import com.woniu.car.product.model.dto.ProductCateOneDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2021-04-06
 */
public interface ProductCateService extends IService<ProductCate> {
        /**
         * 查询一级分类
         * @return
         */
        public List<ProductCateDto> getProductCate();

        /**
         *商品二级分类
         * @param
         * @return
         */
       // public List<ProductCateOneDto> getTwoProductCate();

        public List<ProductCateOneDto> getTwoProductCate1();


}
