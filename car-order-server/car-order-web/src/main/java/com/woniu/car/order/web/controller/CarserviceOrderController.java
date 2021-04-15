package com.woniu.car.order.web.controller;


import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.code.PayParam;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.core.exception.CarException;
import com.woniu.car.commons.web.config.AlipayTemplate;
import com.woniu.car.items.model.entity.CarService;
import com.woniu.car.items.model.param.carservice.GetOneCarServiceParam;
import com.woniu.car.marketing.model.dtoVo.GetCouponInfoByIdDtoVo;
import com.woniu.car.marketing.model.paramVo.GetCouponInfoByIdParamVo;
import com.woniu.car.marketing.model.paramVo.UpdatePaySuccessCouponParamVo;
import com.woniu.car.order.client.feign.MarketingClient;
import com.woniu.car.order.client.feign.OrderServiceClient;
import com.woniu.car.order.client.feign.OrderShopClient;
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
import com.woniu.car.order.web.util.OrderFileUpload;
import com.woniu.car.order.web.util.OrderUtil;
import com.woniu.car.order.web.vo.AllOrderDto;
import com.woniu.car.order.web.vo.AllOrderParam;
import com.woniu.car.shop.model.dtoVo.FindShopInfoVo;
import com.woniu.car.shop.model.paramVo.ShopIdParamVo;
import com.woniu.car.user.param.AddWalletLogParam;
import com.woniu.car.user.web.util.GetTokenUtil;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

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

    @Resource
    private OrderShopClient orderShopClient;

    @Resource
    private OrderServiceClient orderServiceClient;

    @Resource
    private UserClient userClient;

    @Resource
    private OrderFileUpload orderFileUpload;




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
    public ResultEntity<AllOrderDto> findCarserviceOrdersByUserId(@Validated UserVo userVo){
        userVo.setUserId(GetTokenUtil.getUserId());
        /*调用根据用户id查询当前用户的服务订单信息方法*/
        List<CarserviceOrder> caserviceOrders = carserviceOrderService.findCarserviceOrdersByUserId(userVo);
        //查询商品订单
        List<ProductOrder> productOrders = productOrderService.findProductOrderByUserId(userVo);
        //根据订单编号查询商品详细信息
        for (int i = 0; i < productOrders.size(); i++) {
            List<ProductOrderDetail> productOrderDerails
                    = productOrderDetailService.findProductOrderDerailByProductOrderNo(productOrders.get(i).getProductOrderNo());

            ArrayList<ProductOrderDetail> productOrderDetailss = new ArrayList<>();
            for (int n = 0; n < productOrderDerails.size(); n++){
                // 将数据转成json字符串
                String jsonObject= JSON.toJSONString(productOrderDerails.get(n));
                //将json转成需要的对象
                ProductOrderDetail productOrderDetail = JSONObject.parseObject(jsonObject,ProductOrderDetail.class);
                productOrderDetailss.add(productOrderDetail);
            }
            /*把查询的结果添加进集合*/
            productOrders.get(i).setProductOrderDetails(productOrderDetailss);
        }

        /*调用根据user_id查询电站订单方法*/
        List<PowerplantOrder> powerplantOrders = powerplantOrderService.findpowerplantOrderByUserId(userVo);

        /*创建对象,传入查询的结果*/
        AllOrderDto allOrderDto = new AllOrderDto(caserviceOrders,productOrders,powerplantOrders);
        return new ResultEntity<AllOrderDto>()
                .setCode(ConstCode.ACCESS_SUCCESS)
                .setFlag(true)
                .setData(allOrderDto)
                .setMessage("查询用户所有订单信息成功");
    }

    /**
     * @Author WangPeng
     * @Description TODO 查询当前用户的所有订单（后端token取userId）
     * @Date  15:12
     * @Param [userVo]
     * @return com.woniu.car.commons.core.dto.ResultEntity<com.woniu.car.order.web.vo.AllOrderDto>
     **/
    @ApiOperation("查询当前用户的所有订单（后端token取userId）")
    @RequestMapping(value="find_new_user_all_order",  method= RequestMethod.GET )
    public ResultEntity<AllOrderDto> findNewUserAllOrder(){
        UserVo userVo = new UserVo();
        userVo.setUserId(GetTokenUtil.getUserId());
        /*调用根据用户id查询当前用户的服务订单信息方法*/
        List<CarserviceOrder> caserviceOrders = carserviceOrderService.findCarserviceOrdersByUserId(userVo);
        //查询商品订单
        List<ProductOrder> productOrders = productOrderService.findProductOrderByUserId(userVo);
        //根据订单编号查询商品详细信息
        for (int i = 0; i < productOrders.size(); i++) {
            List<ProductOrderDetail> productOrderDerails
                    = productOrderDetailService.findProductOrderDerailByProductOrderNo(productOrders.get(i).getProductOrderNo());

            ArrayList<ProductOrderDetail> productOrderDetailss = new ArrayList<>();
            for (int n = 0; n < productOrderDerails.size(); n++){
                // 将数据转成json字符串
                String jsonObject= JSON.toJSONString(productOrderDerails.get(n));
                //将json转成需要的对象
                ProductOrderDetail productOrderDetail = JSONObject.parseObject(jsonObject,ProductOrderDetail.class);
                productOrderDetailss.add(productOrderDetail);
            }

            /*把查询的结果添加进集合*/
            productOrders.get(i).setProductOrderDetails(productOrderDetailss);
        }

        /*调用根据user_id查询电站订单方法*/
        List<PowerplantOrder> powerplantOrders = powerplantOrderService.findpowerplantOrderByUserId(userVo);
        /*创建对象,传入查询的结果*/
        AllOrderDto allOrderDto = new AllOrderDto(caserviceOrders,productOrders,powerplantOrders);
        return new ResultEntity<AllOrderDto>()
                .setCode(ConstCode.ACCESS_SUCCESS)
                .setFlag(true)
                .setData(allOrderDto)
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
    public ResultEntity updateOrderStatusForCOMPLETED(@RequestBody @Valid OrderVo orderVo){
        CarserviceOrder carserviceOrder = carserviceOrderService.findCarserviceOrderByOrderNo(orderVo);
        if(!ObjectUtils.isEmpty(carserviceOrder)){
            if(OrderCode.ORDER_COMPLETED.equals(orderVo.getOrderStatus())){
                carserviceOrderService.updateOrderStatus(orderVo);
                return ResultEntity.buildSuccessEntity()
                        .setFlag(true)
                        .setMessage("修改订单状态成功");
            }else{
                throw new CarException("操作失败，当前状态无法修改",500);
            }
        }else{
            throw new CarException("操作失败，此订单不存在",500);
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
    public ResultEntity deleteOrderByOrderNo(@RequestBody @Valid FindOrder findOrder){
        OrderVo v =new OrderVo().setOrderNo(findOrder.getOrderNo());
            /*删除订单*/
            Boolean b = carserviceOrderService.deleteOrderByOrderNo(v);
            if(b) {
                return ResultEntity.buildSuccessEntity()
                        .setFlag(true)
                        .setMessage("删除订单成功");
            }else{
                return ResultEntity.buildFailEntity().setMessage("删除订单失败");
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
    public ResultEntity updateOrderStatus(@RequestBody @Valid OrderVo orderVo){
        int samll = Integer.parseInt(OrderCode.ORDER_COMPLETION_PAY);
        int max = Integer.parseInt(OrderCode.ORDER_NOT_SHIPPED);
        int statusCode = Integer.parseInt(orderVo.getOrderStatus());
        /*判断状态是否在范围内避免错误的情况发生*/
        if(samll<=statusCode&&statusCode<=max){
            Boolean result = carserviceOrderService.updateOrderStatus(orderVo);
            if(result){
                return ResultEntity.buildSuccessEntity()
                        .setFlag(true)
                        .setMessage("修改状态成功");
            }
            throw new CarException("修改状态失败",500);
        }
        throw new CarException("修改状态失败，未知状态错误!",500);
    }
    
    /**
     * @Author WangPeng
     * @Description TODO 根据订单编号查询订单详情(三种订单可查)
     * @Date  2021/4/9
     * @Param []
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
//    @GlobalTransactional(timeoutMills = 50000, name = "prex-create-order")
    @RequestMapping(value = "find_orderinfo_by_orderno",method = RequestMethod.GET)
    @ApiOperation(value = "根据订单编号查询订单详情(三种订单可查)",notes = "<span style='color:red;'>此接口可查服务订单和电站订单</span>")
    public ResultEntity<AllOrderParam> findOrderInfoByOrderNo(@Valid FindOrder findOrder){
        /*截取订单前两位，判断订单类型*/
        String str = findOrder.getOrderNo().substring(0, 2);
        log.info("订单前两位为："+str);
        log.info("根据截取的订单号，判断订单类型，调用对应的订单查询方法");
        if(str.equals("po")){
            log.info(findOrder.getOrderNo()+"：订单为电站订单");
            PowerplantOrder powerplantOrder
                    = powerplantOrderService.findpowerplantOrderByOrderCode(findOrder);
            if(!ObjectUtils.isEmpty(powerplantOrder)){
                return ResultEntity.buildEntity(AllOrderParam.class)
                        .setCode(ConstCode.FIND_POWERPLANT_ORDER_SUCCESS)
                        .setFlag(true)
                        .setMessage("查询订单成功")
                        .setData(new AllOrderParam().setPowerplantOrder(powerplantOrder));
            }
        }else if (str.equals("se")){
            log.info(findOrder.getOrderNo()+"：订单为服务订单");
            CarserviceOrder carserviceOrder
                    = carserviceOrderService.findCarserviceOrderByOrderNo(new OrderVo().setOrderNo(findOrder.getOrderNo()));
            System.err.println(carserviceOrder);
            if(!ObjectUtils.isEmpty(carserviceOrder)){
                return ResultEntity.buildEntity(AllOrderParam.class)
                        .setCode(ConstCode.FIND_CARSERVICE_ORDER_SUCCESS)
                        .setFlag(true)
                        .setMessage("查询订单成功")
                        .setData(new AllOrderParam().setCarserviceOrder(carserviceOrder));
            }
        }else if(str.equals("pr")){
           /*根据订单单号查询商品信息*/
            ProductOrder productOrder = productOrderService.findProductOrderByProductOrderNo(new OrderVo().setOrderNo(findOrder.getOrderNo()));

            /*判断是否存在*/
            if(!ObjectUtils.isEmpty(productOrder)){
                /*根据订单编号查询商品详细信息*/
                List<ProductOrderDetail> productOrderDeyails = productOrderDetailService.findProductOrderDerailByProductOrderNo(findOrder.getOrderNo());
                productOrder.setProductOrderDetails(productOrderDeyails);
                return ResultEntity.buildEntity(AllOrderParam.class)
                        .setCode(ConstCode.FIND_PRODUCT_ORDER_SUCCESS)
                        .setFlag(true)
                        .setMessage("查询订单成功")
                        .setData(new AllOrderParam().setProductOrder(productOrder));
            }
        }
        throw new CarException("查询订单失败,此订单不存在",ConstCode.ORDER_NOT_EXIST);
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
    @GlobalTransactional(timeoutMills = 10000, name = "prex-create-order")
    public ResultEntity insertCarServiceOrder(@RequestBody @Valid AddCarServiceOrderVo addCarServiceOrderVo){
        Integer userId = addCarServiceOrderVo.getUserId();
        addCarServiceOrderVo.setUserId(GetTokenUtil.getUserId());
        /*记录是否使能用优惠券*/
        Boolean useCouponInfo = false;

        GetCouponInfoByIdDtoVo getCouponInfoByIdDtoVo = null;
        /*优惠券信息*/
        if(addCarServiceOrderVo.getCouponInfoId()!=0){
            /*根据优惠券id查询信息*/
            GetCouponInfoByIdParamVo getCouponInfoByIdParamVo = new GetCouponInfoByIdParamVo();
            getCouponInfoByIdParamVo.setCouponInfoId(addCarServiceOrderVo.getCouponInfoId());
            Object data = marketingClient.getCouponInfoById(getCouponInfoByIdParamVo).getData();

            if(!ObjectUtils.isEmpty(data)){
                // 将数据转成json字符串
                String jsonObject= JSON.toJSONString(data);
                //将json转成需要的对象
                getCouponInfoByIdDtoVo = JSONObject.parseObject(jsonObject,GetCouponInfoByIdDtoVo.class);
            }

        }




        /*根据门店id查询信息*/
        ShopIdParamVo shopIdParamVo = new ShopIdParamVo();
        shopIdParamVo.setShopId(addCarServiceOrderVo.getShopId());
        FindShopInfoVo data1 = orderShopClient.findShopInfo(shopIdParamVo).getData();
        if(ObjectUtils.isEmpty(shopIdParamVo)){
            throw new CarException("未知的门店信息",500);
        }
        // 将数据转成json字符串
        String jsonObject1= JSON.toJSONString(data1);
        //将json转成需要的对象
        FindShopInfoVo findShopInfoVo= JSONObject.parseObject(jsonObject1,FindShopInfoVo.class);



        /*根据服务id查询信息*/
        GetOneCarServiceParam getOneCarServiceParam = new GetOneCarServiceParam();
        getOneCarServiceParam.setCarServiceId(addCarServiceOrderVo.getCarserviceId());
        CarService data2 = orderServiceClient.getOneCarService(getOneCarServiceParam).getData();
        if(ObjectUtils.isEmpty(getOneCarServiceParam)){
            throw new CarException("未知的服务id信息",500);
        }
        // 将数据转成json字符串
        String jsonObject2= JSON.toJSONString(data2);
        //将json转成需要的对象
        CarService carService = JSONObject.parseObject(jsonObject2,CarService.class);

        if(addCarServiceOrderVo.getCouponInfoId()!=0){
            /*判断优惠券是否满足门槛需求*/
            if (carService.getCarServicePrice().compareTo(getCouponInfoByIdDtoVo.getCouponMoney()) == -1) {
                useCouponInfo = true;
            }
        }

        /*生成订单对象*/
        CarserviceOrder carserviceOrder = new CarserviceOrder();

        /*判断优惠券真实性*/
        if (!useCouponInfo) {
            /*未使用优惠券*/
            carserviceOrder.setCouponInfoId(0);
            /*实际付款金额*/
            carserviceOrder.setCartserviceOrderAmountTotal(carService.getCarServicePrice());
            /*优惠券面额*/
            carserviceOrder.setCouponMoney(new BigDecimal(0));
        }else{
            /*使用优惠券*/
            carserviceOrder.setCouponInfoId(addCarServiceOrderVo.getCouponInfoId());
            /*优惠券面额*/
            carserviceOrder.setCouponMoney(getCouponInfoByIdDtoVo.getCouponMoney());
            /*实际付款金额*/
            carserviceOrder.setCartserviceOrderAmountTotal(carService.getCarServicePrice().subtract(getCouponInfoByIdDtoVo.getCouponMoney()));
            /*调用改变优惠券状态接口（改成已使用）*/
//            UpdatePaySuccessCouponParamVo updatePaySuccessCouponParamVo = new UpdatePaySuccessCouponParamVo();
//            updatePaySuccessCouponParamVo.setCouponInfoId(addCarServiceOrderVo.getCouponInfoId());
//            updatePaySuccessCouponParamVo.setCouponId(getCouponInfoByIdDtoVo.getCouponId());
//            marketingClient.updateCouponByPaySuccess(updatePaySuccessCouponParamVo);
        }
        /*用户id*/
        carserviceOrder.setUserId(userId);
        /*门店图片*/
        carserviceOrder.setShopImage(findShopInfoVo.getShopImage());
        /*门店id*/
        carserviceOrder.setShopId(addCarServiceOrderVo.getShopId());
        /*服务id*/
        carserviceOrder.setCarserviceId(addCarServiceOrderVo.getCarserviceId());
        /*服务名称*/
        carserviceOrder.setCarserviceName(carService.getCarServiceName());
        /*服务价格*/
        carserviceOrder.setCarservicePrice(carService.getCarServicePrice());
        /*订单单号*/
        carserviceOrder.setCarserviceOrderNo(InsertOrderNoUtil.InsertCarServiceOrderNo());
        /*订单状态(未支付)*/
        carserviceOrder.setCarserviceOrderStatus(OrderCode.ORDER_NON_PAYMENT);
        /*预约时间*/
        carserviceOrder.setAppointTime(addCarServiceOrderVo.getAppointTime());
        /*门店地址*/
        carserviceOrder.setShopAddress(findShopInfoVo.getShopAddress());
        /*门店名称*/
        carserviceOrder.setShopName(findShopInfoVo.getShopName());
        /*门店电话*/
        carserviceOrder.setShopTel(findShopInfoVo.getShopTel()+"");
        /*生成订单券码*/
        carserviceOrder.setOrderCode(InsertOrderNoUtil.InsertCarserviceUseCode());
        /*生成二维码图片*/
        InputStream inputStream = carserviceOrderService.insertQRCode(carserviceOrder.getOrderCode());
        MultipartFile multipartFile = null;
        try {
            multipartFile = new MockMultipartFile(carserviceOrder.getOrderCode()+"",UUID.randomUUID()+".png",".png", inputStream);
            MultipartFile[] multipartFiles =  new MultipartFile[1];
            multipartFiles[0] = multipartFile;
            orderFileUpload.upload(multipartFiles);
        } catch (IOException e) {
            throw new CarException("上传图片失败",500);
        }

        /*二维码图片*/
        carserviceOrder.setUseQrCode(multipartFile.getOriginalFilename());
        /*调用生成订单方法*/
        Boolean aBoolean = carserviceOrderService.insertCarServiceOrder(carserviceOrder);

        /*调用增加门店销售量接口*/
        if(aBoolean){
            return ResultEntity.buildSuccessEntity()
                    .setFlag(true)
                    .setMessage("生成服务订单成功");
        }else{
            throw new CarException("生成服务订单失败",500);
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
    @GlobalTransactional(timeoutMills = 5000, name = "prex-create-order")
    public ResultEntity updateCarserViceOrderStatusForServiceingByUseCode(@RequestBody @Valid UseCodeVo useCodeVo){
        /*根据服务使用码查询服务订单*/
        CarserviceOrder carserviceOrder
                = carserviceOrderService.findCarserviceOrderByUseCode(useCodeVo);
        if(!ObjectUtils.isEmpty(carserviceOrder)){
            if(OrderCode.ORDER_COMPLETION_PAY.equals(carserviceOrder.getCarserviceOrderStatus())){
                Boolean aBoolean = carserviceOrderService.updateCarserviceOrderStatusByUseCode(useCodeVo);
                return ResultEntity.buildSuccessEntity()
                        .setFlag(true)
                        .setMessage("修改状态成功，当前订单状态为服务中");
            }else{
             throw new CarException("当前状态无法修改",500);
            }
        }else{
            throw new CarException("无效的使用码",500);
        }
    }

    /**
     * @Author WangPeng
     * @Description TODO 支付接口
     * @Date  15:01
     * @Param [order]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @RequestMapping(value = "carservice_pay",method = RequestMethod.PUT)
    @ApiOperation(value = "支付接口")
    @GlobalTransactional(timeoutMills = 10000, name = "prex-create-order")
    public ResultEntity carservicePay(@RequestBody @Validated OrderPayParam orderPayParam){
        String orderNo = orderPayParam.getOrderNo();
        String orderStatus = "";
        Object order = null;
        String name = "";
        log.info("开始验证订单是否存在");
        Object o = carserviceOrderService.findCarserviceOrderByOrderNo(new OrderVo().setOrderNo(orderPayParam.getOrderNo()));
        log.info("订单存在，开始判断订单类型");
        if(ObjectUtils.isEmpty(o)){
            throw new CarException("订单不存在",500);
        }
        if("se".equals(orderNo.substring(0,2))){
            /*根据订单编号查询订单信息*/
            log.info("系统判断为服务订单");
            order  =  (CarserviceOrder)carserviceOrderService.findCarserviceOrderByOrderNo(new OrderVo().setOrderNo(orderPayParam.getOrderNo()));
            orderStatus = ((CarserviceOrder) order).getCarserviceOrderStatus();
            name = ((CarserviceOrder) order).getShopName();
        }else if("po".equals(orderNo.substring(0,2))){
            log.info("系统判断为电站订单");
            order = (PowerplantOrder)powerplantOrderService.findpowerplantOrderByOrderCode(new FindOrder().setOrderNo(orderNo));
            orderStatus = ((CarserviceOrder) order).getCarserviceOrderStatus();
            name = ((PowerplantOrder) order).getPowerplanName();
        }else if("pr".equals(orderNo.substring(0,2))){
            log.info("系统判断为商品订单");
            order = (ProductOrder)productOrderService.findProductOrderByProductOrderNo(new OrderVo().setOrderNo(orderNo));
            orderStatus = ((ProductOrder) order).getProductOrderStatus();
            name = "平台商城";
        }

        /*判断订单是否存在*/
        log.info("判断订单是否存在");
        if(ObjectUtils.isEmpty(order)){
            throw new CarException("订单不存在",500);
        }
        /*判断订单状态*/
        log.info("订单状态为："+orderStatus);
        if(!OrderCode.ORDER_NON_PAYMENT.equals(orderStatus)){
            throw new CarException("当前订单状态错误，状态为"+orderStatus,500);
        }
        /*1代表钱包，2代表支付宝*/
        log.info("开始判断支付方式");

        String orderType = "";
        BigDecimal cartserviceOrderAmountTotal = null;

        if (order instanceof CarserviceOrder) {
            log.info("开始执行服务订单支付");
            CarserviceOrder carserviceOrder = (CarserviceOrder) order;
            cartserviceOrderAmountTotal = carserviceOrder.getCartserviceOrderAmountTotal();
            orderType = OrderCode.CARSERVICE_ORDER;
        } else if (order instanceof ProductOrder) {
            log.info("开始执行商品订单支付");
            ProductOrder productOrder = (ProductOrder) order;
            cartserviceOrderAmountTotal = productOrder.getProductOrderAmountTotal();
            orderType = OrderCode.PRODUCT_ORDER;
        } else if (order instanceof PowerplantOrder) {
            log.info("开始执行电站订单支付");
            PowerplantOrder powerplantOrder = (PowerplantOrder) order;
            cartserviceOrderAmountTotal = powerplantOrder.getAmountPaid();
            orderType = OrderCode.POWERPLANT_ORDER;
        }

        /*1代表钱包，2代表支付宝*/
        if (OrderCode.ORDER_PAY_ALIPAY.equals(orderPayParam.getPayChannel())) {

            PayParam payParam = new PayParam();
            /*商品订单号*/
            payParam.setOut_trade_no(orderPayParam.getOrderNo());
            /*订单名称*/
            payParam.setSubject("订单");
            /*付款金额*/
            payParam.setTotal_amount(cartserviceOrderAmountTotal);

            /*调用订单服务*/
            try {
                alipayTemplate.pay(payParam);
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            return ResultEntity.buildSuccessEntity().setMessage("进入支付界面").setFlag(true);
        } else if (OrderCode.ORDER_PAY_WALLET_PAYMENT.equals(orderPayParam.getPayChannel())) {
            /*调用钱包日志接口*/
            AddWalletLogParam addWalletLogParam = new AddWalletLogParam();
            /*钱包支付密码*/
            addWalletLogParam.setWalletPassword(orderPayParam.getWalletPassword());
            /*钱包修改类型*/
            addWalletLogParam.setWalletlogType(2);
            /*钱包密码*/
            addWalletLogParam.setWalletPassword(orderPayParam.getWalletPassword());
            /*支付金额*/
            addWalletLogParam.setWalletChange(cartserviceOrderAmountTotal);
            /*描述*/
            addWalletLogParam.setWalletlogEvent(name);
            ResultEntity resultEntity = userClient.addWalletLog(addWalletLogParam);
            if(resultEntity.getFlag()){
                log.info("支付成功");
                OrderVo orderVo = new OrderVo();
                /*订单号*/
                orderVo.setOrderNo(orderNo);
                /*订单状态*/
                if(orderType.equals(OrderCode.CARSERVICE_ORDER)||orderType.equals(OrderCode.POWERPLANT_ORDER)){
                    orderVo.setOrderStatus(OrderCode.ORDER_COMPLETION_PAY);
                }else{
                    orderVo.setOrderStatus(OrderCode.ORDER_NOT_SHIPPED);
                }
                /*修改状态订单*/
                Boolean aBoolean = carserviceOrderService.updateOrderStatus(orderVo);
                if(aBoolean){
                    return resultEntity;
                }else{
                    throw new CarException("修改状态失败",500);
                }
            }
            log.info("支付失败");
            throw new CarException("支付失败",500);
        }

       throw new CarException("支付类型错误",500);
    }

    /**
     * @Description: 支付宝异步 通知页面
     */
    @RequestMapping(value = "alipay_notify_notice",method = RequestMethod.POST)
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
                /*判断订单类型*/
                String orderType = OrderUtil.judgeOrderType(out_trade_no);
                OrderVo orderVo = new OrderVo();
                /*订单号*/
                orderVo.setOrderNo(out_trade_no);
                /*订单状态*/
                if(orderType.equals(OrderCode.CARSERVICE_ORDER)||orderType.equals(OrderCode.POWERPLANT_ORDER)){
                    orderVo.setOrderStatus(OrderCode.ORDER_COMPLETION_PAY);
                }else{
                    orderVo.setOrderStatus(OrderCode.ORDER_NOT_SHIPPED);
                }
                /*修改状态订单*/
                carserviceOrderService.updateOrderStatus(orderVo);
                System.out.println(("********************** 支付成功(支付宝异步通知) **********************"));
            }
            System.out.println(("支付成功..."));
            return ResultEntity.buildSuccessEntity().setMessage("支付成功").setFlag(true);
        } else {//验证失败
            System.out.println(("支付, 验签失败..."));
            throw new CarException("支付失败",500);
        }
    }
}

