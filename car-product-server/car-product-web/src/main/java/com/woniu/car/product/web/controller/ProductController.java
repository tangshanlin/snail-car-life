package com.woniu.car.product.web.controller;


import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.product.model.dto.ShowProductDto;
import com.woniu.car.product.model.parame.ShowProductParame;
import com.woniu.car.product.web.domain.Product;
import com.woniu.car.product.model.dto.HotProductDto;
import com.woniu.car.product.model.dto.ProductDtoTwo;
import com.woniu.car.product.model.dto.ProductOrderDto;
import com.woniu.car.product.model.parame.ProductParame;
import com.woniu.car.product.model.parame.ProductTwoParame;
import com.woniu.car.product.web.service.ProductCateService;
import com.woniu.car.product.web.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 先查商品的一级分类，提供给前端一个URL，在查二级分类，每一个二级分类提供一个URL，供前端调用，最后查根据商品id查商 前端控制器
 * </p>
 *
 * @author cx
 * @since 2021-04-06
 */
@RestController
@RequestMapping("/product")
@Api(tags = "关于商品详情的接口")
public class ProductController {

    @Resource
    private ProductService productService;
    @Resource
    private ProductCateService productCateService;


    /**
     * 添加商品
     * @param parame
     * @return
     */
    @PostMapping("addProduct")
    @ApiOperation(value = "添加商品",notes = "<span style='color:red;'>用来添加商品的接口</span>")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "productName",value = "商品名字"),
            @ApiImplicitParam(name="cateId",value = "商品的父id"),
            @ApiImplicitParam(name="file",value = "上传商品图片"),
            @ApiImplicitParam(name="productDetail",value = "商品详情"),
            @ApiImplicitParam(name="productStock",value = "商品库存"),
            @ApiImplicitParam(name="productPrice",value = "商品价格"),
            @ApiImplicitParam(name="productBrand",value = "商品品牌"),
    })
    public ResultEntity addProduct(ProductParame parame){
        System.out.println("---------------"+parame);
        Boolean flag=productService.addProduct(parame);
        if(flag){
            return new ResultEntity<>().buildSuccessEntity().setMessage("添加商品成功");
        }
        return new ResultEntity<>().buildFailEntity().setMessage("添加商品失败");

    }

    /**
     * 订单调用商品信息
     * @param id
     * @return
     */
    @GetMapping("getProductById")
    @ApiOperation(value = "订单调用商品信息",notes = "<span style='color:red;'>订单调用商品信息的接口</span>")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "id",value = "商品id"),

    })

    public ResultEntity<Product> getProductById(Integer id){
        Product product = productService.getProductById(id);

        return new ResultEntity<>().buildSuccessEntity(Product.class).
                setMessage("查询成功").setData(product);

    }

    /**
     * 订单修改商品减库存
     * @param orderDto
     * @return
     */

    @PutMapping("updateStock")
    @ApiOperation(value = "订单修改商品减库存",notes = "<span style='color:red;'>订单修改商品减库存的接口</span>")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "productId",value = "商品id"),
            @ApiImplicitParam(name = "buyNumber",value = "购买商品的数量"),

    })
    public ResultEntity<Product> updateProductStock(@RequestBody ProductOrderDto orderDto){
        productService.updeteProductStock(orderDto);
        return new ResultEntity<>().buildSuccessEntity(Product.class).
                setMessage("减库存成功");
    }
    /**
     * 订单修改商品加库存
     * @param orderDto
     * @return
     */
    @PutMapping("addStock")
    @ApiOperation(value = "订单修改商品加库存",notes = "<span style='color:red;'>订单修改商品加库存的接口</span>")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "productId",value = "商品id"),
            @ApiImplicitParam(name = "buyNumber",value = "购买商品的数量"),

    })
    public ResultEntity<Product> addStock(@RequestBody ProductOrderDto orderDto){
        productService.updateStock(orderDto);
        return new ResultEntity<>().buildSuccessEntity(Product.class).
                setMessage("加库存成功");
    }

    /**
     * 查询精选商品
     * @return
     */
    @GetMapping("listHotProduct")
    @ApiOperation(value = "查询精选商品",notes = "<span style='color:red;'>用来查询精选商品的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "查询精选商品成功"),
            @ApiResponse(code=500,message = "查询精选商品失败")
    })
    public ResultEntity<List<HotProductDto>> getHotProduct(){
        List<HotProductDto> hotProduct = productService.getHotProduct();

        return ResultEntity.buildListSuccessEntity(HotProductDto.class).
                setMessage("精选商品查询成功").setData(hotProduct).setCode(200);

    }

    /**
     * 根据二级分类id查询商品列表
     * @param parame
     * @return
     */
    @GetMapping("getProductTwo")
    @ApiOperation(value = "根据二级分类id查询商品列表",notes = "<span style='color:red;'>根据二级分类id查询商品列表的接口</span>")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "cateId",value = "二级分类id"),
    })
    public ResultEntity<List<ProductDtoTwo>> getProductTwo(ProductTwoParame parame){
        List<ProductDtoTwo> productTwo = productService.getProductTwo(parame);

        return ResultEntity.buildListSuccessEntity(ProductDtoTwo.class)
                .setMessage("查询商品列表成功").setData(productTwo).setCode(200);
    }


    @GetMapping("ShowProduct")
    @ApiOperation(value = "根据商品id查询商品详情",notes = "<span style='color:red;'>根据商品id查询商品详情的接口</span>")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "productId",value = "商品id"),
    })
    public ResultEntity<ShowProductDto> ShowProduct(ShowProductParame parame){
        ShowProductDto showProductDto = productService.ShowProduct(parame);
        return ResultEntity
                .buildSuccessEntity(ShowProductDto.class)
                .setMessage("查询商品详情成功")
                .setData(showProductDto);

    }


}

