package com.woniu.car.order.web.code;

/*
  订单状态类
 */
public class OrderCode {

    /*
    *已支付
    * */
    public static final String ORDER_COMPLETION_PAY="100";

    /*
    *服务进行中
    * */
    public static final String ORDER_SERVICEING="101";

    /*
    *订单已完成
    * */
    public static final String ORDER_COMPLETED="102";

    /*
     *退款中
     * */
    public static final String ORDER_ING_MONEY_BACK="103";

    /*
     *已退款
     * */
    public static final String ORDER_ED_MONEY_BACK="104";

    /*
     *取消交易
     * */
    public static final String ORDER_TRANSACTION_CANCELLED="105";

    /*
     *未付款
     * */
    public static final String ORDER_NON_PAYMENT="106";

    /*
     *已发货
     * */
    public static final String ORDER_SENT="107";

    /*
     *已收货
     * */
    public static final String ORDER_RECEIPT_GOODS="108";

    /*
     *退货中
     * */
    public static final String ORDER_ING_GOODS_BACK="109";

    /*
     *已退货
     * */
    public static final String ORDER_ED_GOODS_BACK="110";

    /*
     *待发货
     * */
    public static final String ORDER_NOT_SHIPPED="111";

    /*
    * 钱包支付
    * */
    public static final String ORDER_PAY_WALLET_PAYMENT="1";

    /*
     * 支付宝支付
     * */
    public static final String ORDER_PAY_ALIPAY="2";

    /*
     * 订单类型为服务
     * */
    public static final String CARSERVICE_ORDER= "120";

    /*
     * 订单类型为电站
     * */
    public static final String POWERPLANT_ORDER= "121";

    /*
     * 订单类型为商品
     * */
    public static final String PRODUCT_ORDER= "122";

}
