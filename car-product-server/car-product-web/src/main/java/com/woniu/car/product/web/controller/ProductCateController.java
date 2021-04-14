package com.woniu.car.product.web.controller;


import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.product.model.dto.ProductCateDto;
import com.woniu.car.product.model.dto.ProductCateOneDto;
import com.woniu.car.product.web.service.ProductCateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cx
 * @since 2021-04-06
 */
@RestController
@RequestMapping("/productCate")
@Api(tags = "关于商品分类的接口")
public class ProductCateController {
    @Resource
    private ProductCateService productCateService;



    /**
     * 查询商品一级分类
     *
     * @return
     */
    @GetMapping("getProductCate")
    @ApiOperation(value = "查询商品一级分类", notes = "<span style='color:red;'>用来查询商品一级分类的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询商品一级分类成功"),
            @ApiResponse(code = 500, message = "查询商品一级分类失败")
    })
    public ResultEntity<List<ProductCateDto>> getProductCate() {
        List<ProductCateDto> productCate = productCateService.getProductCate();
        return ResultEntity.buildListSuccessEntity(ProductCateDto.class)
                .setMessage("查询一级分类成功").setData(productCate);

    }

    @GetMapping("getAllCate")
    @ApiOperation(value = "查询商品二级分类", notes = "<span style='color:red;'>用来查询商品二级分类的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询商品二级分类成功"),
            @ApiResponse(code = 500, message = "查询商品二级分类失败")
    })
    public ResultEntity<List<ProductCateOneDto>> getAllProductCate() {
        List<ProductCateOneDto> twoProductCate = productCateService.getTwoProductCate1();
        return ResultEntity.buildListSuccessEntity(ProductCateOneDto.class)
                .setMessage("分类查询成功").setData(twoProductCate);

    }





}

