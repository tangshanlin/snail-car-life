package com.woniu.car.commons.web.message;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;

public class SendMessage {
    //定义静态变量 互亿无线发送短信的接口地址
    private static String Url = "http://106.ihuyi.com/webservice/sms.php?method=Submit";

    /**
     * @Author Lints
     * @Date 2021/4/6/006 13:08
     * @Description 发送消息公共接口
     * @Param [user_tel]
     * @Return java.lang.String
     * @Since version-1.0
     */


    public static String sendMessage(String user_tel) {
        //创建HttpClient对象
        HttpClient client = new HttpClient();
        //创建请求对象
        PostMethod method = new PostMethod(Url);
        //设置编码
        client.getParams().setContentCharset("GBK");
        //设置请求头
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");
        //随机生成一个6位数的验证码
        int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
        //使用随机的验证码拼接一条短信信息
        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
        //调用接口时传入的参数
        NameValuePair[] data = {//提交短信
                new NameValuePair("account", "C50532791"), //查看用户名 登录用户中心->验证码通知短信>产品总览->API接口信息->APIID
                new NameValuePair("password", "912b499f8c27a6f2a172f8d20a421be2"), //查看密码 登录用户中心->验证码通知短信>产品总览->API接口信息->APIKEY
                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                new NameValuePair("mobile", user_tel), //接收短信的手机号码
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);//将参数添加到POST请求中
        try {
            client.executeMethod(method);//调接口
            String SubmitResult = method.getResponseBodyAsString();//获取响应结果
            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();
            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");
            System.out.println(code);
            System.out.println(msg);
            System.out.println(smsid);
            if ("2".equals(code)) {
                System.out.println("短信提交成功");
                System.out.println("验证码为:" + mobile_code);
                return mobile_code+"";
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            return null;
        }

    }

}
