package com.woniu.car.order.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.core.exception.CarException;
import com.woniu.car.marketing.model.dtoVo.GetCouponInfoByIdDtoVo;
import com.woniu.car.marketing.model.paramVo.GetCouponInfoByIdParamVo;
import com.woniu.car.marketing.model.paramVo.UpdatePaySuccessCouponParamVo;
import com.woniu.car.order.client.feign.MarketingClient;
import com.woniu.car.order.client.feign.ProductClient;
import com.woniu.car.order.client.feign.UserClient;
import com.woniu.car.order.model.param.AddProductDetailOrderVo;
import com.woniu.car.order.model.param.AddProductOrderVo;
import com.woniu.car.order.web.code.OrderCode;
import com.woniu.car.order.web.entity.OrderLogistics;
import com.woniu.car.order.web.entity.ProductOrder;
import com.woniu.car.order.web.entity.ProductOrderDetail;
import com.woniu.car.order.web.service.OrderLogisticsService;
import com.woniu.car.order.web.service.ProductOrderDetailService;
import com.woniu.car.order.web.service.ProductOrderService;
import com.woniu.car.order.web.util.InsertOrderNoUtil;
import com.woniu.car.product.web.domain.Product;
import com.woniu.car.product.model.dto.ProductOrderDto;
import com.woniu.car.user.param.SlectAddressByAdressIdParam;
import com.woniu.car.user.web.domain.Address;
import com.woniu.car.user.web.util.GetTokenUtil;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 商品详情表 前端控制器
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
@RestController
@RequestMapping("/product-order_detail")
@Slf4j
@Api(tags = "商品详细订单")
public class ProductOrderDetailController {

    @Resource
    private ProductClient productClient;

    @Resource
    private MarketingClient marketingClient;

    @Resource
    private ProductOrderService productOrderService;

    @Resource
    private ProductOrderDetailService productOrderDetailService;

    @Resource
    private OrderLogisticsService orderLogisticsService;

    @Resource
    private UserClient userClient;


    /**
     * @Author WangPeng
     * @Description TODO 新增商品订单
     * @Date  2021/4/9
     * @Param [addProductOrderVo]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
//    @GlobalTransactional(timeoutMills = 200000, name = "prex-create-order")
    @ApiOperation("新增商品订单")
    @RequestMapping(value = "insert_product_order",method = RequestMethod.POST)
    @GlobalTransactional(timeoutMills = 50000, name = "prex-create-order")
    public ResultEntity insertProductOrder(@RequestBody @Valid AddProductOrderVo addProductOrderVo){
       addProductOrderVo.setUserId(GetTokenUtil.getUserId());

        /*生成商品订单编号*/
        String productOrderNo = InsertOrderNoUtil.InsertProductOrderNo();
        /*商品总数量*/
        int productSum = 0;
        /*商品总价格*/
        BigDecimal productAmountTotal = new BigDecimal(0);

        /*获取要生成订单商品订单的商品id*/
        List<AddProductDetailOrderVo> productsInfo = addProductOrderVo.getProductDetailOrders();
        ArrayList<ProductOrderDetail> productOrderDetails = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        if(productsInfo.size()==0){
            throw new CarException("此商品不存在",500);
        }
        for (int i = 0; i < productsInfo.size(); i++){
            /*根据商品id获取商品信息*/
            Product product = productClient.getProductById(productsInfo.get(i).getProductId()).getData();
            System.err.println(product+"----------");
            products.add(product);
            /*计算总数量*/
            productSum = productSum+productsInfo.get(i).getProductCount();
            /*计算总价*/
            productAmountTotal = productAmountTotal.add(product.getProductPrice().multiply(new BigDecimal(productsInfo.get(i).getProductCount())));

            /*生成商品订单详情表*/
            ProductOrderDetail productOrderDetail = new ProductOrderDetail();
            /*商品id*/
            productOrderDetail.setProductId(product.getProductId());
            /*商品订单编号*/
            productOrderDetail.setProductOrderNo(productOrderNo);
            /*商品名称*/
            productOrderDetail.setProductName(product.getProductName());
            /*商品图片*/
            productOrderDetail.setProductImage(product.getProductImage());
            /*商品数量*/
            productOrderDetail.setProductCount(productsInfo.get(i).getProductCount());
            /*商品价格*/
            productOrderDetail.setProductPrice(product.getProductPrice());

            productOrderDetailService.insertProductOrderDetail(productOrderDetail);

        }


        /*根据优惠券id查询优惠券信息*/
        log.info("开始查询id为"+addProductOrderVo.getCouponInfoId()+"的优惠券信息");
        GetCouponInfoByIdParamVo getCouponInfoByIdParamVo = new GetCouponInfoByIdParamVo();
        getCouponInfoByIdParamVo.setCouponInfoId(addProductOrderVo.getCouponInfoId());
        GetCouponInfoByIdDtoVo couponInfo = marketingClient.getCouponInfoById(getCouponInfoByIdParamVo).getData();

        /*判断优惠券是否存在*/
        log.info("查询id为"+addProductOrderVo.getCouponInfoId()+"的优惠券查询成功，并做出判断是否为空");

            log.info("id为"+addProductOrderVo.getCouponInfoId()+"的优惠券存在,开始生成订单");

            ProductOrder productOrder = new ProductOrder();
            productOrder.setUserId(addProductOrderVo.getUserId());
            /*订单编号*/
            productOrder.setProductOrderNo(productOrderNo);
            /*订单状态*/
            productOrder.setProductOrderStatus(OrderCode.ORDER_NON_PAYMENT);
            /*订单数量*/
            productOrder.setProductSum(productSum);
            /*订单总价*/
            productOrder.setProductAmountTotal(productAmountTotal);

            if(!ObjectUtils.isEmpty(couponInfo)) {
                /*判断优惠券的使用范围*/
                if(couponInfo.getCouponId()== 0) {
                    if(couponInfo.getCouponCondition().compareTo(productAmountTotal) == -1) {
                        productOrder.setCouponMoney(couponInfo.getCouponMoney());
                    }
                }else {
                    productOrder.setCouponMoney(new BigDecimal(0));
                }
            }

            /*应付金额*/
            productOrder.setAmountPayable(productAmountTotal.subtract(productOrder.getCouponMoney()));
            /*生成订单*/
            Boolean aBoolean = productOrderService.insertProductOrder(productOrder);
            log.info("生成订单成功");
            log.info("正在改变商品库存信息");

            /*修改商品库存*/
            for(AddProductDetailOrderVo p:productsInfo){
                ProductOrderDto productOrderDto = new ProductOrderDto();
                productOrderDto.setProductId(p.getProductId());
                productOrderDto.setBuyNumber(p.getProductCount());
                productClient.updateProductStock(productOrderDto);
                log.info("商品库存id为"+p.getProductId()+"的商品修改成功");
            }
            /*修改优惠券状态*/
            UpdatePaySuccessCouponParamVo updatePaySuccessCouponParamVo = new UpdatePaySuccessCouponParamVo();
            updatePaySuccessCouponParamVo.setCouponInfoId(couponInfo.getCouponInfoId());
            updatePaySuccessCouponParamVo.setCouponId(couponInfo.getCouponId());
            marketingClient.updateCouponByPaySuccess(updatePaySuccessCouponParamVo);

            /*生成订单物流表*/
            /*1.根据地址id查询用户收获地址*/
            SlectAddressByAdressIdParam slectAddressByAdressIdParam = new SlectAddressByAdressIdParam();
            slectAddressByAdressIdParam.setAddressId(addProductOrderVo.getAddressId());
            log.info("开始根据address_id:"+addProductOrderVo.getAddressId()+"查询信息");
            Object data = userClient.selectByAddressId(slectAddressByAdressIdParam).getData();
            if(ObjectUtils.isEmpty(data)){
                throw new CarException("地址信息错误",500);
            }
            // 将数据转成json字符串
            String jsonObject=JSON.toJSONString(data);
            //将json转成需要的对象
            Address address= JSONObject.parseObject(jsonObject,Address.class);

            log.info("查询到信息"+address);
        Boolean aBoolean1 = orderLogisticsService.insertOrderLogistics(
                new OrderLogistics()
                        /*商品订单表id*/
                        .setProductOrderId(productOrder.getProductOrderId())
                        /*收件人名字*/
                        .setConsigneeRealname(address.getAddressContactName())
                        /*联系电话*/
                        .setConsigneeTelphone(address.getAddressContactTel())
                        /*收货地址*/
                        .setConsigneeAddress(address.getAddressProvince() + address.getAddressCity() + address.getAddressDistrict() + address.getAddressStreet() + address.getAddressDetail())
        );

        if(!aBoolean1){
            throw new CarException("新增订单失败",500);
        }
        return ResultEntity.buildFailEntity()
                .setCode(ConstCode.ACCESS_SUCCESS)
                .setFlag(true)
                .setMessage("新增订单成功");
    }
}

