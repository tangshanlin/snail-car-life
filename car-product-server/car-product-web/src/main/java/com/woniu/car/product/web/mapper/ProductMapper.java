package com.woniu.car.product.web.mapper;

import com.woniu.car.product.web.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 先查商品的一级分类，提供给前端一个URL，在查二级分类，每一个二级分类提供一个URL，供前端调用，最后查根据商品id查商 Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2021-04-06
 */
public interface ProductMapper extends BaseMapper<Product> {

}
