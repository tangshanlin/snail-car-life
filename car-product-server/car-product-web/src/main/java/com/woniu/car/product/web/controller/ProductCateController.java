package com.woniu.car.product.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.product.model.dto.ProductCateDto;
import com.woniu.car.product.model.dto.ProductCateOneDto;
import com.woniu.car.product.web.domain.ProductCate;
import com.woniu.car.product.web.service.ProductCateService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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


    /**
     * 通过一级id查询二级分类
     * @param parentId
     * @return
     */
    @GetMapping("get_two")
    @ApiOperation(value = "通过一级id查询二级分类", notes = "<span style='color:red;'>用来查询商品二级分类的接口</span>")
    @ApiImplicitParam(name = "parentId", value = "一级分类id", dataType = "Integer", required = true)
    public ResultEntity<List<ProductCate>> getSecondaryClassificationById(Integer parentId){
        QueryWrapper<ProductCate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",parentId);
        List<ProductCate> list = productCateService.list(queryWrapper);
        return ResultEntity.buildListSuccessEntity(ProductCate.class).setData(list);
    }

}

