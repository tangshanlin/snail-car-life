package com.woniu.car.commons.web.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.woniu.car.commons.core.code.PayParam;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {

    //在支付宝创建的应用的id
    private String app_id = "2021000116666737";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private  String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7fjoRDkRjayif5js9aziOM1iZVbryLwl3LfTyTc1uLfKv+uohziic8CgAGEdSf6eXEczB2Rlk5XiOXZW7/TWBZzhVx4H+HPXYr9hEVQI4F3jDYkPEwjIEsz6VjzNCWeyNivgTQo/rcCP3pbitGkVr9n9j8XTuwSfhS2NEhkiGTjYF1Nbt6Bp/fO2G7NpSq8OzPBb+9PqgeuahwKPLQTo5Q4/966V6Y5/xeZYcJ0JgXdoEdsQ4/JqELzGLSDdWGThuXif/x6G1Tmnl8StD2VZLrRzcEry9YkLSfkDdn0SSHm1pFFSsn7ucvhD5tfl2PdO02/6ZuWrFV03fGJH+pT7fAgMBAAECggEAZ8LShc/cfxy1rdAvZq0o7mFyHeG5BQCM/3zaeyIdl2UsUsHMwwqhpUCvEmZdSkXFTLCYHYt1J0ZrMnaOQCxAaS3jmm0//o6ua83dJOvav4oV1iNSl9hU8A72irRmTMkRRb/rzFTg6w3zicEX9Ax+8OHI3FmrOdqBpIghFrIt/szlHoAZqSH5NrsH1meC/zibL52y+YpYd9kvWjSOsfy7hAcGPdUN0sdiQ6lj0OwqMndeYER6ZEQgw0zPc+JFzlFuVBHqTK+g77k7Jurn72Ma1GcrsZ9GCXgyBChfjtrlC7U/oaBAdqGYJPxSnVsznX91hWLWE9wXTkdmlucZQO/DuQKBgQDkFFuYJUXT97GJhkL0nZ1Y8XXFCr7vt+bsw11exs9iQ82rwPBuO6+dRTS/lR0r/JdwmT4W/BKVTDIElsIHTWKWPwM2nwx+YR/C3nw0B6oI49YJTrP1+z232zvKi3aiJNnixNE1DZtg6d+vSroTxjqsVWHvictnImKriXB8UHz3nQKBgQDScfSf2UmDmf8Ny7ApjBBpYtOJSjVAg/TUXzQibz+5wfywvcmTo+IoYq5HlEYjpBKi4GQv2QqmqWm083BqLT0iHUi6zF1VvolcRdwKSJfV1VDBQpnkhK1EX2OVAlUaWCIGFEzvHByxcSUYWzzbCBJvXpIf5gmBYPgO8IiVA1ZtqwKBgAm0yBUYePlFUDvfB9hrILgZRn7FL0z6UYx/3+hZo5e19Pm7M0AtsBKMUGtayCwICtXS366QuaW64WIKnGU9KNXmBpf7eseiEHz1hOmd7eGE+KXawJPWRlEdtlSvTLQnx1H8OD7+vj8IlOlbz8FprTOKSqmr/oj8EKDmL3BK6JaNAoGARefIxNifDPI4wVJqIEMAqKFFd5OJAPdUer9tSAsAdio0UFma/kowNA4jqm9cpTY5YpV7clF8skQQcyVysDJ63jwwDq0YTAVRB6/FGj1nAocrnicLi9du3wl4wOTXdDrWgoJ30+3YsjDNi7uzCWczCH+3tpTpj2qwu/W2yRkkuqUCgYEAy1RtzA14cqsQtVM7USOky7aNjWDVKQ/uk3nBKcC0G7qMoDst55K11Oan1+x1A893i7IgdiG4dpRUgLXrINv4e7mYAfuyP3AB0TwK60kkW3HKlgfQhVkX8pLAb4Vb4hvLa3wSH+sRDx9Q0SayJmRIX0A91cpGDRf7xl5be5Po7IQ=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAii9gTrwwAGDykd2Gg4doptuB5wEuzaidbK0iCHOEhCbu32nkdD5d2CHZeAW/1P0ka3MYk62lxP7zNlGYsNGQc8oW9OY9FCl7lTA5f3ILngSeHiNhQMJHvUcKQQ5OKBOYo+LfSDc/qJ2nDpp1n0rMRpG24Yvd6vFASInh3R3J0Lrzb7wpy2DCF3zTnrtu6VEVcPEPf9yrqkoSZpQSiG3OKT0plD/XYdb3neNQ5F6DyojrbHoMdJXaUouaPR+CEwFUeAL/XnxFwBwkMDZEMy/RyA+SOiHpOiNNaYb+/G/b2DA1O0Fr1fBK0XwZVgXn11oyWMyP4A3wMQ/7O65wAJ4GewIDAQAB";
    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    private  String notify_url="http://localhost:80/order/alipayNotifyNotice";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    //在本地开发的时候可以使用内网穿透的生成的域名来做测试。
    private  String return_url = "http://127.0.0.1:8080/myorder";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";
    // 订单超时时间，到达超时时间后自动关闭订单不能再继续支付
    public static String timeout = "30m";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayParam vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        BigDecimal total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        // timeout_express 订单支付超时时间
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\"" + timeout + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
//        System.out.println("支付宝的响应："+result);
        return result;
    }
}
