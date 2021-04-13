package com.woniu.car.commons.core.code;

import lombok.Data;

@Data
public class ConstCode {

    /**
     *   返回数据的code编号为失败
     */
    public static final Integer LAST_STAGE=500;
    /**
     * 返回数据的code编号为成功
     */
    public static final Integer ACCESS_SUCCESS=200;
    /**
     *名称已存在
     */
    public static final Integer NAME_ALREADY_EXISTS = 5001;

    /**
     * 返回标签代码成功
     */
    public static final Integer Get_Tags_SUCCESS=1601;

    /**
     * 返回标签代码失败
     */
    public static final Integer Get_Tags_Fail=1602;

    /**
     * 添加标签代码失败
     */
    public static final Integer ADD_Tags_Fail=1603;

    /**
     * 添加标签代码失败
     */
    public static final Integer ADD_Tags_SUCCESS=1604;

    /**
     * 该评论已经添加过标签
     */
    public static final Integer Last_ADD_Tags=1605;

    /**
     * 没有该商品标签的评论
     */
    public static final Integer No_Peoduct_BY_Tags=1606;
    /**
     * 没有该服务标签的评论
     */
    public static final Integer No_Service_BY_Tags=1607;
    /**
     * 没有该电站标签的评论
     */
    public static final Integer No_Power_BY_Tags=1608;


    /*登陆成功*/
    public static final Integer LOGIN_SUCCESS=1301;
    /*登陆失败账户未找到*/
    public static final Integer LOGIN_FAIL_ACCOUNTNOTFOUND=1302;
    /*登陆失败密码错误*/
    public static final Integer LOGIN_FAIL_PASSWORDWRONG=1303;
    /*登陆失败验证码错误*/
    public static final Integer LOGIN_FAIL_CODEWRONG=1305;
    /*注册成功*/
    public static final Integer REGISTER_SUCCESS=1306;
    /*注册失败*/
    public static final Integer REGISTER_FAIL=1307;
    /*电话号码校验成功*/
    public static final Integer CHECKTEL_SUCCESS=1308;
    /*电话号码校验失败*/
    public static final Integer CHECKTEL_FAIL=1309;
    /*账户校验成功*/
    public static final Integer CHECKACCOUNT_SUCCESS=1310;
    /*账户校验失败*/
    public static final Integer CHECKACCOUNT_FAIL=1311;
    /*发送验证码成功*/
    public static final Integer SENDCODE_SUCCESS=1312;
    /*发送验证码失败*/
    public static final Integer SENDCODE_FAIL=1313;
    /*更新用户成功*/
    public static final Integer UPDATEUSER_SUCCESS=1314;
    /*更新用户失败*/
    public static final Integer UPDATEUSER_FAIL=1315;
    /*添加驾驶证成功*/
    public static final Integer ADDDRIVERLICENSE_SUCCESS=1320;
    /*添加驾驶证失败*/
    public static final Integer ADDDRIVERLICENSE_FAIL=1321;
    /*查询驾驶证成功*/
    public static final Integer SELECTDRIVERLICENSE_SUCCESS=1322;
    /*查询驾驶证失败*/
    public static final Integer SELECTDRIVERLICENSE_FAIL=1323;
    /*添加行驶证成功*/
    public static final Integer ADDDRIVINGLICENSE_SUCCESS=1324;
    /*添加行驶证失败*/
    public static final Integer ADDDRIVINGLICENSE_FAIL=1325;
    /*查询行驶证成功*/
    public static final Integer SELECTDRIVINGLICENSE_SUCCESS=1326;
    /*查询行驶证失败*/
    public static final Integer SELECTDRIVINGLICENSE_FAIL=1327;
    /*查询用户详情信息成功*/
    public static final Integer SELECTUSERINFORMATION_SUCCESS=1328;
    /*查询用户详情信息失败*/
    public static final Integer SELECTUSERINFORMATION_FAIL=1329;

    /*新增用户详情页成功*/
    public static final Integer ADDUSERINFORMATION_SUCCESS=1330;
    /*新增用户详情页失败*/
    public static final Integer ADDUSERINFORMATION_FAIL=1331;
    /*查询爱车成功*/
    public static final Integer SELECTMYCAR_SUCCESS=1332;
    /*查询爱车失败*/
    public static final Integer SELECTMYCAR_FAIL=1333;
    /*添加爱车成功*/
    public static final Integer ADDMYCAR_SUCCESS=1334;
    /*添加爱车失败*/
    public static final Integer ADDMYCAR_FAIL=1335;
    /*修改爱车成功*/
    public static final Integer UPDATEMYCAR_SUCCESS=1336;
    /*修改爱车失败*/
    public static final Integer UPDATEMYCAR_FAIL=1337;
    /*删除爱车成功*/
    public static final Integer DELETEMYCAR_SUCCESS=1338;
    /*删除爱车失败*/
    public static final Integer DELETEMYCAR_FAIL=1339;

    /*添加地址成功*/
    public static final Integer ADDADDRESS_SUCCESS=1350;
    /*添加地址失败*/
    public static final Integer ADDADDRESS_FAIL=1351;
    /*删除地址成功*/
    public static final Integer DELETEADDRESS_SUCCESS=1352;
    /*删除地址失败*/
    public static final Integer DELETEADDRESS_FAIL=1353;
    /*查询地址成功*/
    public static final Integer SELECTADDRESS_SUCCESS=1354;
    /*查询地址失败*/
    public static final Integer SELECTADDRESS_FAIL=1355;
    /*更新地址成功*/
    public static final Integer UPDATEADDRESS_SUCCESS=1356;
    /*更新地址失败*/
    public static final Integer UPDATEADDRESS_FAIL=1357;
    /*添加钱包成功*/
    public static final Integer ADDWALLET_SUCCESS=1360;
    /*添加钱包失败*/
    public static final Integer ADDWALLET_FAIL=1361;
    /*删除钱包失败*/
    public static final Integer DELETEWALLET_FAIL=1363;
    /*删除钱包成功*/
    public static final Integer DELETEWALLET_SUCCESS=1362;
    /*更新钱包成功*/
    public static final Integer UPDATEWALLET_SUCCESS=1364;
    /*更新钱包失败*/
    public static final Integer UPDATEWALLET_FAIL=1365;
    /*查询钱包成功*/
    public static final Integer SELECTWALLET_SUCCESS=1366;
    /*查询钱包失败*/
    public static final Integer SELECTWALLET_FAIL=1367;
    /*添加钱包日志成功*/
    public static final Integer ADDWALLETLOG_SUCCESE=1370;
    /*添加钱包日志失败*/
    public static final Integer ADDWALLETLOG_FAIL=1371;
    /*查询钱包日志成功*/
    public static final Integer SELECTWALLETLOG_SUCCESS=1372;
    /*查询钱包日志失败*/
    public static final Integer SELECTWALLETLOG_FAIL=1373;
    /*添加积分日志成功*/
    public static final Integer ADDSCORE_SUCCESS=1380;
    /*添加积分日志失败*/
    public static final Integer ADDSCORE_FAIL=1381;
    /*查询积分日志失败*/
    public static final Integer SELECTSCORE_FAIL=1383;
    /*查询积分日志成功*/
    public static final Integer SELECTSCORE_SUCESS=1382;

   /*输入参数错误*/
    public static final Integer PARAM_ERROR=1400;



    /*订单不存在*/
    public static final Integer ORDER_NOT_EXIST=1003;

    /*查询电站订单成功*/
    public static final Integer FIND_POWERPLANT_ORDER_SUCCESS=1002;

    /*查询服务订单成功*/
    public static final Integer FIND_CARSERVICE_ORDER_SUCCESS=1001;
    /*查询商品订单成功*/
    public static final Integer FIND_PRODUCT_ORDER_SUCCESS=1004;

    /*门店名称重复*/
    public static final Integer ADD_SHOP_NAME_FAIL= 1501;


    /*门店地址重复*/
    public static final Integer ADD_SHOP_ADDRESS_FAIL= 1502;

    /*门店联系方式*/
    public static final Integer ADD_SHOP_TEL_FAIL= 1503;



}
