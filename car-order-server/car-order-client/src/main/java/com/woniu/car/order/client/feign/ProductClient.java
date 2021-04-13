package com.woniu.car.order.client.feign;//package com.woniu.car.order.web.config;

import com.woniu.car.commons.core.dto.ResultEntity;

import com.woniu.car.product.model.dto.ProductOrderDto;
import com.woniu.car.product.web.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName OrderClient
 * @Desc TODO feign接口
 * @Author 21174
 * @Date 2021/4/8 20:22
 * @Version 1.0
 */
@FeignClient("car-product-server")
public interface ProductClient {
    /*
     * @Author WangPeng
     * @Description TODO 订单修改商品减库存
     * @Date  2021/4/8
     * @Param [orderDto]
     * @return com.woniu.car.commons.core.dto.ResultEntity<com.woniu.car.product.web.domain.Product>
     **/

    @PutMapping("product/product/updateStock")
    public ResultEntity<Product> updateProductStock(@RequestBody ProductOrderDto orderDto);

    /*
     * @Author WangPeng
     * @Description TODO 订单修改商品加库存
     * @Date  2021/4/8
     * @Param [orderDto]
     * @return com.woniu.car.commons.core.dto.ResultEntity<com.woniu.car.product.web.domain.Product>
     **/

    @PutMapping("product/addStock")
    public ResultEntity<Product> addStock(ProductOrderDto orderDto);

    /*
     * @Author WangPeng
     * @Description TODO 根据商品id查询商品信息
     * @Date  2021/4/8
     * @Param [id]
     * @return com.woniu.car.commons.core.dto.ResultEntity<com.woniu.car.product.web.domain.Product>
     **/

    @GetMapping("product/product/getProductById")
    public ResultEntity<Product> getProductById(@RequestParam("id") Integer id);

}
