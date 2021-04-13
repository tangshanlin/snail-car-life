package com.woniu.car.order.web.controller;


import cn.hutool.core.lang.UUID;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.code.PayParam;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.config.AlipayTemplate;
import com.woniu.car.commons.web.discributelock.MyLock;
import com.woniu.car.items.model.entity.CarService;
import com.woniu.car.marketing.model.dtoVo.GetCouponInfoByIdDtoVo;
import com.woniu.car.marketing.model.paramVo.GetCouponInfoByIdParamVo;
import com.woniu.car.marketing.model.paramVo.UpdatePaySuccessCouponParamVo;
import com.woniu.car.order.client.feign.MarketingClient;
import com.woniu.car.order.client.feign.UserClient;
import com.woniu.car.order.model.param.*;
import com.woniu.car.order.web.code.OrderCode;
import com.woniu.car.order.web.entity.CarserviceOrder;
import com.woniu.car.order.web.entity.PowerplantOrder;
import com.woniu.car.order.web.entity.ProductOrder;
import com.woniu.car.order.web.entity.ProductOrderDetail;
import com.woniu.car.order.web.service.CarserviceOrderService;
import com.woniu.car.order.web.service.PowerplantOrderService;
import com.woniu.car.order.web.service.ProductOrderDetailService;
import com.woniu.car.order.web.service.ProductOrderService;
import com.woniu.car.order.web.util.InsertOrderNoUtil;
import com.woniu.car.order.web.vo.AllOrder;
import com.woniu.car.order.web.vo.AllOrderParam;
import com.woniu.car.shop.web.domain.Shop;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务订单表 前端控制器
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
@RestController
@RequestMapping("/carservice_order")
@Api(tags = "服务订单")
@Slf4j
public class CarserviceOrderController {

//    //根据容器中的名称来匹配
//    @Resource(name = "rocketMQTemplate")
//    private RocketMQTemplate rocketMQTemplate;

    @Resource
    private CarserviceOrderService carserviceOrderService;

    @Resource
    private PowerplantOrderService powerplantOrderService;

    @Resource
    private ProductOrderDetailService productOrderDetailService;

    @Resource
    private ProductOrderService productOrderService;

    @Resource
    private MarketingClient marketingClient;

    @Resource
    private AlipayTemplate alipayTemplate;

    


//    @Resource
//    private MyFileUpload myFileUpload;
//    //根据订单编号修改状态
//    @GetMapping("/s")
//    public String updateServiceOrderStatus(){
//        Map<String, Object> maps = new HashMap<>();
//        maps.put("KEYS", UUID.randomUUID().toString());
//        //自定义该消息的事务ID
//        String txId = UUID.randomUUID().toString();
//        //处理事务业务逻辑需要用到的对象
//        CarserviceOrder carserviceOrder = new CarserviceOrder();
//        carserviceOrder.setCarserviceOrderNo("123");
//        //发送半消息
//        rocketMQTemplate.sendMessageInTransaction("r:p",
//                MessageBuilder.withPayload(carserviceOrder)
//                        .setHeader("KEYS", UUID.randomUUID().toString())
//                        .setHeader("txId", txId).build(),
//                carserviceOrder);
//        return "发送半消息成功!";
//    }



    @RequestMapping(value="findcarserviceorders_byuserid",  method= RequestMethod.GET )
    @ApiOperation("根据用户id查询当前用户的所有订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",dataType = "Integer",paramType = "query",example = "1"),
    })
    /**
     * @Author WangPeng
     * @Description TODO 根据用户id查询当前用户的所有订单信息
     * @Date  2021/4/8
     * @Param [user]
     * @return com.woniu.car.commons.core.dto.ResultEntity<com.woniu.car.order.web.vo.AllOrder>
     **/
    public ResultEntity<AllOrder> findCarserviceOrdersByUserId(UserVo userVo){
        /*调用根据用户id查询当前用户的服务订单信息方法*/
        List<CarserviceOrder> caserviceOrders = carserviceOrderService.findCarserviceOrdersByUserId(userVo);
        //查询商品订单
        List<ProductOrder> productOrders = productOrderService.findProductOrderByUserId(userVo);
        //根据订单编号查询商品详细信息
        for (int i = 0; i < productOrders.size(); i++) {
            List<ProductOrderDetail> productOrderDerails
                    = productOrderDetailService.findProductOrderDerailByProductOrderNo(productOrders.get(i).getProductOrderNo());
            /*把查询的结果添加进集合*/
            productOrders.get(i).getProductOrderDetails().add((ProductOrderDetail) productOrderDerails);
        }

        /*调用根据user_id查询电站订单方法*/
        List<PowerplantOrder> powerplantOrders = powerplantOrderService.findpowerplantOrderByUserId(userVo);

        /*创建对象,传入查询的结果*/
        AllOrder allOrder = new AllOrder(caserviceOrders,productOrders,powerplantOrders);
        return ResultEntity.buildFailEntity(AllOrder.class)
                .setCode(ConstCode.ACCESS_SUCCESS)
                .setFlag(true)
                .setData(allOrder)
                .setMessage("查询用户所有订单信息成功");
    }

    /**
     * @Author WangPeng
     * @Description TODO 根据订单编号修改服务订单状态为已完成
     * @Date  2021/4/8
     * @Param [order]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @RequestMapping(value="update_order_status_for_completed",  method= RequestMethod.PUT )
    @ApiOperation("根据订单编号修改服务订单状态为已完成")
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity updateOrderStatusForCOMPLETED(@RequestBody OrderVo orderVo){
        CarserviceOrder carserviceOrder = carserviceOrderService.findCarserviceOrderByOrderNo(orderVo);
        if(!ObjectUtils.isEmpty(carserviceOrder)){
            if(OrderCode.ORDER_COMPLETED.equals(orderVo.getOrderStatus())){
                carserviceOrderService.updateOrderStatus(orderVo);
                return ResultEntity.buildFailEntity()
                        .setCode(ConstCode.ACCESS_SUCCESS)
                        .setFlag(true)
                        .setMessage("修改订单状态成功");
            }else{
                return ResultEntity.buildFailEntity()
                        .setCode(ConstCode.LAST_STAGE)
                        .setFlag(false)
                        .setMessage("修改订单状态失败");
            }
        }else{
            return ResultEntity.buildFailEntity()
                    .setCode(ConstCode.LAST_STAGE)
                    .setFlag(false)
                    .setMessage("操作失败，此订单不存在");
        }
    }

    /**
     * @Author WangPeng
     * @Description TODO 根据订单编号删除订单
     * @Date  2021/4/8
     * @Param [order]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @RequestMapping(value="deleteorder_by_orderno",  method= RequestMethod.DELETE )
    @ApiOperation("根据订单编号删除订单(三种订单都可删除)")
    @Transactional
    @ApiImplicitParam(paramType="query", name = "orderNo", value = "订单id", required = true, dataType = "String")
    public ResultEntity deleteOrderByOrderNo(OrderVo orderVo){
        /*根据订单号查询订单*/
        CarserviceOrder carserviceOrder = carserviceOrderService.findCarserviceOrderByOrderNo(orderVo);
        if(!ObjectUtils.isEmpty(carserviceOrder)){
            /*删除订单*/
            Boolean b = carserviceOrderService.deleteOrderByOrderNo(orderVo);
            if(b){
                return ResultEntity.buildFailEntity()
                        .setCode(ConstCode.ACCESS_SUCCESS)
                        .setFlag(true)
                        .setMessage("删除订单成功");
            }
            return ResultEntity.buildFailEntity()
                    .setCode(ConstCode.LAST_STAGE)
                    .setFlag(false)
                    .setMessage("订单当前状态不能删除");
        }else{
            return ResultEntity.buildFailEntity()
                    .setCode(ConstCode.LAST_STAGE)
                    .setFlag(false)
                    .setMessage("操作失败，此订单不存在");
        }
    }

    /**
     * @Author WangPeng
     * @Description TODO 根据订单号和状态码修改服务订单状态
     * @Date  2021/4/8
     * @Param
     * @return
     **/
    @RequestMapping(value = "api/updateOrderStatus",method = RequestMethod.PUT)
    @ApiOperation("根据订单号和状态码修改服务订单状态")
    public ResultEntity updateOrderStatus(@RequestBody OrderVo orderVo){
        int samll = Integer.parseInt(OrderCode.ORDER_COMPLETION_PAY);
        int max = Integer.parseInt(OrderCode.ORDER_NOT_SHIPPED);
        int statusCode = Integer.parseInt(orderVo.getOrderStatus());
        /*判断状态是否在范围内避免错误的情况发生*/
        if(samll<=statusCode&&statusCode<=max){
            Boolean result = carserviceOrderService.updateOrderStatus(orderVo);
            if(result){
                return ResultEntity.buildFailEntity()
                        .setCode(ConstCode.ACCESS_SUCCESS)
                        .setFlag(true)
                        .setMessage("修改状态成功");
            }
            return ResultEntity.buildFailEntity()
                    .setCode(ConstCode.LAST_STAGE)
                    .setFlag(false)
                    .setMessage("修改状态失败");
        }
        return ResultEntity.buildFailEntity()
                .setCode(ConstCode.LAST_STAGE)
                .setFlag(false)
                .setMessage("修改状态失败，未知状态错误!");
    }
    
    /**
     * @Author WangPeng
     * @Description TODO 根据订单编号查询订单详情
     * @Date  2021/4/9
     * @Param []
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
//    @GlobalTransactional(timeoutMills = 50000, name = "prex-create-order")
    @RequestMapping(value = "find_orderinfo_by_orderno",method = RequestMethod.GET)
    @ApiOperation(value = "根据订单编号查询订单详情",notes = "<span style='color:red;'>此接口可查服务订单和电站订单</span>")
    public ResultEntity<AllOrderParam> findOrderInfoByOrderNo(OrderVo orderVo){
        /*截取订单前两位，判断订单类型*/
        String str = orderVo.getOrderNo().substring(0, 2);
        log.info("订单前两位为："+str);
        log.info("根据截取的订单号，判断订单类型，调用对应的订单查询方法");
        if(str.equals("po")){
            log.info(orderVo.getOrderNo()+"：订单为电站订单");
            PowerplantOrder powerplantOrder
                    = powerplantOrderService.findpowerplantOrderByOrderCode(new PowerplantOrder().setOrderCode(orderVo.getOrderNo()));
            if(!ObjectUtils.isEmpty(powerplantOrder)){
                return ResultEntity.buildFailEntity(AllOrderParam.class)
                        .setCode(ConstCode.FIND_POWERPLANT_ORDER_SUCCESS)
                        .setFlag(true)
                        .setMessage("查询订单成功")
                        .setData(new AllOrderParam().setPowerplantOrder(powerplantOrder));
            }
        }else if (str.equals("se")){
            log.info(orderVo.getOrderNo()+"：订单为服务订单");
            CarserviceOrder carserviceOrder
                    = carserviceOrderService.findCarserviceOrderByOrderNo(orderVo);
            System.err.println(carserviceOrder);
            if(!ObjectUtils.isEmpty(carserviceOrder)){
                return ResultEntity.buildFailEntity(AllOrderParam.class)
                        .setCode(ConstCode.FIND_CARSERVICE_ORDER_SUCCESS)
                        .setFlag(true)
                        .setMessage("查询订单成功")
                        .setData(new AllOrderParam().setCarserviceOrder(carserviceOrder));
            }
        }else if(str.equals("pr")){
           /*根据订单单号查询商品信息*/
            ProductOrder productOrder = productOrderService.findProductOrderByProductOrderNo(orderVo);
            if(!ObjectUtils.isEmpty(productOrder)){
                return ResultEntity.buildFailEntity(AllOrderParam.class)
                        .setCode(ConstCode.FIND_PRODUCT_ORDER_SUCCESS)
                        .setFlag(true)
                        .setMessage("查询订单成功")
                        .setData(new AllOrderParam().setProductOrder(productOrder));
            }
        }
        return ResultEntity.buildFailEntity(AllOrderParam.class)
                .setCode(ConstCode.ORDER_NOT_EXIST)
                .setFlag(false)
                .setMessage("查询订单失败,此订单不存在");
    }

    /**
     * @Author WangPeng
     * @Description TODO 生成服务订单接口
     * @Date  2021/4/10
     * @Param [carserviceOrder]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @RequestMapping(value = "insert_carservice_order",method = RequestMethod.POST)
    @ApiOperation(value = "生成服务订单接口")
    public ResultEntity insertCarServiceOrder(@RequestBody AddCarServiceOrderVo addCarServiceOrderVo){
       /*记录是否使用优惠券*/
        Boolean useCouponInfo = false;

        /*根据优惠券id查询信息*/
        GetCouponInfoByIdParamVo getCouponInfoByIdParamVo = new GetCouponInfoByIdParamVo();
        getCouponInfoByIdParamVo.setCouponInfoId(addCarServiceOrderVo.getCouponInfoId());
        GetCouponInfoByIdDtoVo couponInfo = marketingClient.getCouponInfoById(getCouponInfoByIdParamVo).getData();

        /*根据门店id查询信息*/
        Shop shop = new Shop();

        /*根据服务id查询信息*/
        CarService carService = new CarService();

        /*判断优惠券是否存在*/
        if (ObjectUtils.isEmpty(couponInfo)) {
            /*判断优惠券是否满足门槛需求*/
            if (carService.getCarServicePrice().compareTo(couponInfo.getCouponMoney()) == -1) {
                useCouponInfo = true;
            }
        }

        /*生成订单对象*/
        CarserviceOrder carserviceOrder = new CarserviceOrder();

        /*判断优惠券真实性*/
        if (useCouponInfo) {
            /*未使用优惠券*/
            carserviceOrder.setCouponInfoId(0);
            /*实际付款金额*/
            carserviceOrder.setCartserviceOrderAmountTotal(carserviceOrder.getCarservicePrice());
        }else{
            /*使用优惠券*/
            carserviceOrder.setCouponInfoId(addCarServiceOrderVo.getCouponInfoId());
            /*优惠券面额*/
            carserviceOrder.setCouponMoney(couponInfo.getCouponMoney());
            /*实际付款金额*/
            carserviceOrder.setCartserviceOrderAmountTotal(carserviceOrder.getCarservicePrice().subtract(couponInfo.getCouponMoney()));
            /*调用改变优惠券状态接口（改成已使用）*/

        }
        /*服务id*/
        carserviceOrder.setCarserviceId(addCarServiceOrderVo.getCarserviceId());
        /*服务名称*/
        carserviceOrder.setCarserviceName(carService.getCarServiceName());
        /*服务价格*/
        carserviceOrder.setCarservicePrice(carserviceOrder.getCarservicePrice());
        /*订单单号*/
        carserviceOrder.setCarserviceOrderNo(InsertOrderNoUtil.InsertCarServiceOrderNo());
        /*订单状态(未支付)*/
        carserviceOrder.setCarserviceOrderStatus(OrderCode.ORDER_NON_PAYMENT);
        /*预约时间*/
        carserviceOrder.setAppointTime(addCarServiceOrderVo.getAppointTime());
        /*门店地址*/
        carserviceOrder.setShopAddress(shop.getShopAddress());
        /*门店名称*/
        carserviceOrder.setCarserviceName(shop.getShopName());
        /*门店电话*/
        carserviceOrder.setShopTel(shop.getShopTel()+"");
        /*生成订单券码*/
        carserviceOrder.setOrderCode(InsertOrderNoUtil.InsertCarserviceUseCode());
        /*生成二维码图片*/
        InputStream inputStream = carserviceOrderService.insertQRCode(carserviceOrder.getOrderCode());
        MultipartFile multipartFile = null;
        try {
             multipartFile = new MockMultipartFile(UUID.randomUUID()+"",carserviceOrder.getOrderCode()+"",".png", inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*调用生成订单方法*/
        Boolean aBoolean = carserviceOrderService.insertCarServiceOrder(carserviceOrder);

        /*调用增加门店销售量接口*/
        if(aBoolean){
            return ResultEntity.buildFailEntity()
                    .setCode(ConstCode.ACCESS_SUCCESS)
                    .setFlag(true)
                    .setMessage("生成服务订单成功");
        }else{
            return ResultEntity.buildFailEntity()
                    .setCode(ConstCode.LAST_STAGE)
                    .setFlag(false)
                    .setMessage("生成服务订单失败");
        }
    }

    /**
     * @Author WangPeng
     * @Description TODO 根据使用码更改服务状态为服务进行中
     * @Date  0:54
     * @Param [useCodeVo]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @RequestMapping(value = "api/update_carservice_order_status_for_serviceing by_use_code",method = RequestMethod.PUT)
    @ApiOperation(value = "根据使用码更改服务状态为服务进行中")
    @GlobalTransactional(timeoutMills = 10000, name = "prex-create-order")
    public ResultEntity updateCarserViceOrderStatusForServiceingByUseCode(@RequestBody UseCodeVo useCodeVo){
        /*根据服务使用码查询服务订单*/
        CarserviceOrder carserviceOrder
                = carserviceOrderService.findCarserviceOrderByUseCode(useCodeVo);
        if(!ObjectUtils.isEmpty(carserviceOrder)){
            if(OrderCode.ORDER_COMPLETION_PAY.equals(carserviceOrder.getCarserviceOrderStatus())){
                Boolean aBoolean = carserviceOrderService.updateCarserviceOrderStatusByUseCode(useCodeVo);
                return ResultEntity.buildFailEntity()
                        .setCode(ConstCode.ACCESS_SUCCESS)
                        .setFlag(true)
                        .setMessage("修改状态成功，当前订单状态为服务中");
            }else{
                return ResultEntity.buildFailEntity()
                        .setCode(ConstCode.LAST_STAGE)
                        .setFlag(false)
                        .setMessage("当前状态无法修改");
            }
        }else{
            return ResultEntity.buildFailEntity()
                    .setCode(ConstCode.LAST_STAGE)
                    .setFlag(false)
                    .setMessage("无效的使用码");
        }
    }

    /**
     * @Author WangPeng
     * @Description TODO 服务购买
     * @Date  15:01
     * @Param [order]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @RequestMapping(value = "carservice_pay",method = RequestMethod.PUT)
    @ApiOperation(value = "服务购买(钱包展示没写)")
    @GlobalTransactional(timeoutMills = 10000, name = "prex-create-order")
    public ResultEntity carservicePay(@RequestBody OrderPayParam orderPayParam){
        /*根据订单编号查询订单信息*/
        CarserviceOrder carserviceOrder
                = carserviceOrderService.findCarserviceOrderByOrderNo(new OrderVo().setOrderNo(orderPayParam.getOrderNo()));
        if(!ObjectUtils.isEmpty(carserviceOrder)){
            /*判断订单状态*/
            if(carserviceOrder.getCarserviceOrderStatus().equals(OrderCode.ORDER_NON_PAYMENT)){
                /*1代表钱包，2代表支付宝*/
                if(OrderCode.ORDER_PAY_ALIPAY.equals(orderPayParam.getPayChannel())){
                    PayParam payParam = new PayParam();
                    /*商品订单号*/
                    payParam.setOut_trade_no(orderPayParam.getOrderNo());
                    /*订单名称*/
                    payParam.setSubject("商品订单");
                    /*付款金额*/
                    payParam.setTotal_amount(carserviceOrder.getCartserviceOrderAmountTotal());

                    /*调用订单服务*/
                    try {
                        alipayTemplate.pay(payParam);
                    } catch (AlipayApiException e) {
                        e.printStackTrace();
                    }
                }else if(OrderCode.ORDER_PAY_WALLET_PAYMENT.equals(orderPayParam.getPayChannel())){
                    /**
                     * @Author WangPeng
                     * @Description TODO 钱包没写
                     * @Date  3:28
                     * @Param [orderPayParam]
                     * @return com.woniu.car.commons.core.dto.ResultEntity
                     **/
                }
            }else{
                return ResultEntity.buildFailEntity().setMessage("订单信息错误").setFlag(false);
            }
        }else{
            return ResultEntity.buildFailEntity().setMessage("订单不存在").setFlag(false);
        }
        return ResultEntity.buildFailEntity().setMessage("进入支付界面").setFlag(false);
    }

    /**
     * @Description: 支付宝异步 通知页面
     */
    @RequestMapping(value = "api/alipay_notify_notice",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "支付宝异步通知")
    public ResultEntity alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {
        System.out.println(("支付成功, 进入异步通知接口..."));
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            /*valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");*/
            params.put(name, valueStr);
        }

        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayTemplate.alipay_public_key, AlipayTemplate.charset, AlipayTemplate.sign_type);

        //——请在这里编写您的程序（以下代码仅作参考）——

   /* 实际验证过程建议商户务必添加以下校验：
   1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
   2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
   3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
   4、验证app_id是否为该商户本身。
   */
        //验证成功
        if (signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            if (trade_status.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知

                // 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
//                movieOrderService.updateOrderStatus(Long.parseLong(out_trade_no));
                carserviceOrderService.updateOrderStatus(new OrderVo().setOrderStatus(OrderCode.ORDER_COMPLETION_PAY).setOrderNo(out_trade_no));
                System.out.println(("********************** 支付成功(支付宝异步通知) **********************"));
            }
            System.out.println(("支付成功..."));
            return ResultEntity.buildFailEntity().setMessage("支付成功").setFlag(true);
        } else {//验证失败
            System.out.println(("支付, 验签失败..."));
            return ResultEntity.buildFailEntity().setMessage("支付失败").setFlag(false);
        }
    }

}

