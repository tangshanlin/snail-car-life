package com.woniu.car.user.web.controller;


import cn.hutool.Hutool;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.code.ResultEnum;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.core.exception.CarException;
import com.woniu.car.user.dto.MsgParam;
import com.woniu.car.user.param.*;
import com.woniu.car.user.web.domain.User;
import com.woniu.car.user.web.domain.UserInformation;
import com.woniu.car.user.web.service.UserInformationService;
import com.woniu.car.user.web.service.UserService;
import com.woniu.car.user.web.service.WalletService;
import com.woniu.car.user.web.token.JwtToken;
import com.woniu.car.user.web.util.GetTokenUtil;
import com.woniu.car.user.web.util.JwtUtils;
import io.swagger.annotations.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.aspectj.weaver.ast.Var;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * 用户表 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-07
 */
@RestController

@Api(tags = "用户服务接口")
@RequestMapping("/user")
@Slf4j

public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private  UserInformationService userInformationService;


    @PostMapping("/register")

    @ApiOperation(value = "注册方法", notes = "<span style='color:red;'>用来注册用户的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1306, message = "注册成功"),
            @ApiResponse(code = 1307, message = "注册失败"),
            @ApiResponse(code = 1309, message = "手机号已经存在，请重新输入"),
            @ApiResponse(code = 1311, message = "账户已经存在，请重新输入")
    })
//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//            @ApiImplicitParam(name = "userAccount", value = "用户账号", dataType = "String",  example = "tom"),
//            @ApiImplicitParam(name = "userPassword", value = "密码", dataType = "String",  example = "111"),
//            @ApiImplicitParam(name = "userTel", value = "用户手机号", dataType = "String",  example = "15578491131")
//    })
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity register(@RequestBody @Valid RegisterParam userParam) {
        int register = userService.register(userParam);
        if (register > 0) {
            return ResultEntity.buildEntity().setCode(ConstCode.REGISTER_SUCCESS).setMessage("注册成功");
        }
        if (register == -2)
            return ResultEntity.buildEntity().setCode(ConstCode.CHECKTEL_FAIL).setMessage("手机号已经存在，请重新输入");
        if (register == -3)
            return ResultEntity.buildEntity().setCode(ConstCode.CHECKACCOUNT_FAIL).setMessage("账户已经存在，请重新输入");
        return ResultEntity.buildEntity().setCode(ConstCode.REGISTER_FAIL).setFlag(false).setMessage("注册失败");
    }

    @PostMapping("/login_password")
    @ApiOperation(value = "密码登陆方法", notes = "<span style='color:red;'>用来用户密码登陆的接口，登陆成功会返回jwttoken</span>")
    @ApiResponses({
            @ApiResponse(code = 1301, message = "登陆成功"),
            @ApiResponse(code = 1303, message = "密码错误"),
            @ApiResponse(code = 1400, message = "输入参数错误")
    })
//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//            @ApiImplicitParam(name = "userAccount", value = "用户账号", dataType = "String",  example = "tom"),
//            @ApiImplicitParam(name = "userPassword", value = "密码", dataType = "String", example = "111"),
//
//    })
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity loginByPassword(@RequestBody @Valid LoginPasswordParam loginPasswordParam) {
        //校验传入参数
        String userAccount = loginPasswordParam.getUserAccount();
        System.out.println(loginPasswordParam);
        if (loginPasswordParam != null & userAccount != null) {
            User userDb = userService.getOne(new QueryWrapper<User>().eq("user_account", userAccount));
            System.out.println("数据库查询的userdb" + userDb);
            if (!ObjectUtils.isEmpty(userDb)) {
                //直接校验
                Md5Hash md5Hash = new Md5Hash(loginPasswordParam.getUserPassword(), userDb.getUserSalt(), 2048);
                System.out.println("加密后的MD5" + md5Hash);//
                if (md5Hash.toHex().equals(userDb.getUserPassword())) {
                    System.out.println("密码校验成功");
                    //与数据库校验成功，创建jwttoken
                    Map<String, String> tokenmap = new LinkedHashMap<>();
                    tokenmap.put("userAccount", userDb.getUserAccount());
                    tokenmap.put("userId", userDb.getUserId().toString());


                    String s = JwtUtils.careatToken(tokenmap);
                    System.out.println("加密后的token" + s);

                    //记录本次的登陆时间
                    long l = System.currentTimeMillis();

                    userDb.setUserLastLoginTime(l);
                    System.out.println(userDb);
                    boolean b = userService.updateById(userDb);

                    //登陆成功返回jwttoken
                    if (b) return ResultEntity.buildEntity(String.class).setCode(ConstCode.LOGIN_SUCCESS).setFlag(true)
                            .setMessage("登陆成功").setData(s);


                }
                return ResultEntity.buildEntity().setCode(ConstCode.LOGIN_FAIL_PASSWORDWRONG).setFlag(false).setMessage("密码错误");

            }

        }
        return ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }

    @PostMapping("/sendcode")

    @ApiOperation(value = "发送验证码方法", notes = "<span style='color:red;'>用来发送验证码的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1312, message = "发送成功"),
            @ApiResponse(code = 1313, message = "发送失败")

    })
//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//            @ApiImplicitParam(name = "userTel", value = "用户电话号", dataType = "String", paramType = "path", example = "15578491030"),
//
//
//    })
    public ResultEntity<CloseableHttpResponse> sendcode(@RequestBody @Valid TelParam telParam) {
        //取出电话号码
        String userTel = telParam.getUserTel();
        System.out.println(userTel);
        //获取http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = null;
        try {
            uri = new
                    URIBuilder().setScheme("http").setHost("pub.woniulab.com").setPort(8081)
                    .setPath("/msg/sendMsg").build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        MsgParam param = new MsgParam();

        int i = RandomUtil.randomInt(1000, 9999);
        System.out.println(i);
        int l =0;
        //存到redis
        //存在redis 再存一个校验code时长的值 限制发送次数的值
        Object o1 = redisTemplate.opsForValue().get("com:woniu:car:user-server:car-user-web:sendcode:"
                + userTel + ":number");
       if (ObjectUtils.isEmpty(o1)){
           //次数为空 验证码失效一定为空
           l++;
           redisTemplate.opsForValue().set("com:woniu:car:user-server:car-user-web:sendcode:" + userTel+":number",l,24,TimeUnit.HOURS);
           redisTemplate.opsForValue().set("com:woniu:car:user-server:car-user-web:sendcode:" + userTel+":time",l,60,TimeUnit.SECONDS);
//           redisTemplate.opsForHash().put("com:woniu:car:user-server:car-user-web:sendcode:" + userTel+":number",userTel,l);
//           redisTemplate.expire("com:woniu:car:user-server:car-user-web:sendcode:" + userTel+":number", 24, TimeUnit.HOURS);
//           redisTemplate.opsForHash().put("com:woniu:car:user-server:car-user-web:sendcode:" + userTel+":time",userTel,l);
//           redisTemplate.expire("com:woniu:car:user-server:car-user-web:sendcode:" + userTel+":time", 60, TimeUnit.SECONDS);
       }else {

           Integer number = Integer.valueOf(o1.toString());
           System.out.println(number);
           if (number < 8) {
               Object o = redisTemplate.opsForValue().get("com:woniu:car:user-server:car-user-web:sendcode:" + userTel + ":time");
               if (ObjectUtils.isEmpty(o)) {
                   number++;
                   redisTemplate.opsForValue().set("com:woniu:car:user-server:car-user-web:sendcode:" + userTel + ":time",number, 60, TimeUnit.SECONDS);
                   redisTemplate.opsForValue().set("com:woniu:car:user-server:car-user-web:sendcode:" + userTel + ":number", number, 24, TimeUnit.HOURS);
               } else {
                   throw new CarException("你请求验证码太过频繁，请稍后再试", 500);
               }

           }
           if (number >8) {
               throw new CarException("你当日请求验证码的次数超过10次,请隔日再试", 500);
           }
       }
        param.setCode(Integer.toString(i));
        param.setPhoneNum(userTel);
        //post请求
        HttpPost httpPost = new HttpPost(uri);
        StringEntity entity = new StringEntity(JSON.toJSONString(param),
                "UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        //响应模型
        CloseableHttpResponse response = null;

        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" +
                        responseEntity.getContentLength());
                System.out.println("响应内容为:" +
                        EntityUtils.toString(responseEntity));
                redisTemplate.opsForValue().set("com:woniu:car:user-server:car-user-web:sendcode:" + userTel+":code", i, 15, TimeUnit.MINUTES);

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return ResultEntity.buildEntity(CloseableHttpResponse.class).setCode(ConstCode.SENDCODE_SUCCESS).setFlag(true).setMessage("发送成功")
                ;
    }

    @GetMapping("/checkByTel")
    @ApiOperation(value = "手机短信验证码校验接口", notes = "<span style='color:red;'>用来校验短信验证码的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1301, message = "登陆成功"),
            @ApiResponse(code = 1305, message = "验证码错误")

    })
//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//            @ApiImplicitParam(name = "userTel", value = "用户电话号", dataType = "String", paramType = "path", example = "15578491030"),
//            @ApiImplicitParam(name = "code", value = "用户填入的4位纯数字验证码", dataType = "String", paramType = "path", example = "15578491030"),
//
//    })
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity<String> checkByTel(@Valid LoginTelParam loginTelParam) {

        //取出code 与redis的比较
        String userTel = loginTelParam.getUserTel();
        String code = loginTelParam.getCode();
        log.info("传入参数"+"userTel:"+userTel+"code:"+code);
        String codeDb = (String) redisTemplate.opsForValue().get("com:woniu:car:user-server:car-user-web:sendcode:" + userTel+":code");
        System.out.println(codeDb);
        if (code.equals(codeDb)) {
            log.info("code校验成功");


            //校验成功
            //校验电话号码是否存在
            User userDb = userService.getOne(new QueryWrapper<User>().eq("user_tel", loginTelParam.getUserTel()));

            if (ObjectUtils.isEmpty(userDb)) {
                //如果不存在，则直创建一个账户
                log.info("手机号不存在，根据手机号创建账号");
                String userAccountReg;
                while (true){
                userAccountReg = RandomUtil.randomString(6);
                    User user_account = userService.getOne(new QueryWrapper<User>().eq("user_account",userAccountReg));
                    if (ObjectUtils.isEmpty(user_account))
                        log.info(userAccountReg);
                        break;
                }

                User userReg = new User();
                userReg.setUserTel(userTel);
                userReg.setUserAccount(userAccountReg);

                userService.save(userReg);

                UserInformation userInformation = new UserInformation();
                userInformation.setUserTel(userTel);
                //从数据库查询到userID
                User user_telDb = userService.getOne(new QueryWrapper<User>().eq("user_tel", userTel));

                userInformation.setUserId(user_telDb.getUserId());
                userInformation.setUserName(userAccountReg);
                //和前端沟通 头像写死
                userInformation.setUserImage("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.soutu123.cn%2Felement_origin_min_pic%2F01%2F54%2F05%2F335746fd1e7f644.jpg%21%2Ffw%2F700%2Fquality%2F90%2Funsharp%2Ftrue%2Fcompress%2Ftrue&refer=http%3A%2F%2Fpic.soutu123.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1620364494&t=853cc8f45366a9ec51424cf84e72b5bd");
                boolean save = userInformationService.save(userInformation);
                if (save){
                    return ResultEntity.buildEntity(String.class).setCode(ConstCode.REGISTER_SUCCESS).setFlag(false)
                            .setMessage("用户电话号码注册成功");
                }


            }
            //登陆成功存Jwttoken
            //与数据库校验成功，创建jwttoken
            User userDb2 = userService.getOne(new QueryWrapper<User>().eq("user_tel", loginTelParam.getUserTel()));
            Map<String, String> tokenmap = new LinkedHashMap<>();
            tokenmap.put("userAccount", userDb2.getUserAccount());
            tokenmap.put("userId", userDb2.getUserId().toString());
            String s = JwtUtils.careatToken(tokenmap);
            System.out.println("加密后的token" + s);
            //记录本次的登陆时间
            long l = System.currentTimeMillis();
            userDb.setUserLastLoginTime(l);
            System.out.println(userDb2);
            boolean b = userService.updateById(userDb2);
            if (b)
                return ResultEntity.buildEntity(String.class).setCode(ConstCode.LOGIN_SUCCESS).setFlag(true).setMessage("登陆成功").setData(s);
        }

        return ResultEntity.buildEntity(String.class).setCode(ConstCode.LOGIN_FAIL_CODEWRONG).setFlag(false).setMessage("验证码错误，请重新输入");
    }

    //手机号查重检验
    @GetMapping("/checkTel")
    @ApiOperation(value = "手机号码查重校验接口", notes = "<span style='color:red;'>用来手机号码查重校验接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1308, message = "该手机号未注册，可以使用"),
            @ApiResponse(code = 1309, message = "手机号已经存在，请更换手机号"),
            @ApiResponse(code = 1400, message = "输入参数错误")

    })
//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//            @ApiImplicitParam(name = "userTel", value = "用户电话号", dataType = "String", paramType = "path", example = "15578491030"),
//
//
//    })
    public ResultEntity checkTel(TelParam telParam) {
        if (!ObjectUtils.isEmpty(telParam)) {

            User user = userService.selectByTel(telParam);
            if (user != null)
                return ResultEntity.buildEntity().setCode(ConstCode.CHECKTEL_FAIL).setFlag(false).setMessage("手机号已经存在，请更换手机号");

            return ResultEntity.buildEntity().setCode(ConstCode.CHECKTEL_SUCCESS).setFlag(true).setMessage("该手机号未注册，可以使用");
        }
        return ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }

    //账号查重校验
    @GetMapping("/checkAccount")
    @ApiOperation(value = "账号名查重校验接口", notes = "<span style='color:red;'>用来账号查重校验接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1323, message = "账户已经存在，请重新输入"),
            @ApiResponse(code = 1322, message = "账户名可以使用"),
            @ApiResponse(code = 1400, message = "输入参数错误")

    })
//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//            @ApiImplicitParam(name = "userAccount", value = "用户账户名", dataType = "String", paramType = "path", example = "15578491030"),
//
//
//    })

    public ResultEntity checkAccount(@Valid AccountParam accountParam) {
        if (!ObjectUtils.isEmpty(accountParam)) {


            User user_account = userService.getOne(new QueryWrapper<User>().eq("user_account", accountParam.getUserAccount()));
            if (user_account != null)
                return ResultEntity.buildEntity().setCode(ConstCode.CHECKACCOUNT_FAIL).setFlag(false)
                        .setMessage("账户已经存在，请重新输入");
            return ResultEntity.buildEntity().setCode(ConstCode.CHECKACCOUNT_SUCCESS).setFlag(true).setMessage("账户名可以使用");
        }
        return ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }

    //修改密码
    @PutMapping("/update_user")
    @ApiOperation(value = "账户密码修改接口", notes = "<span style='color:red;'>用来账户密码修改接口,需要接受手机验证码</span>")
    @ApiResponses({
            @ApiResponse(code = 1314, message = "修改成功"),
            @ApiResponse(code = 1315, message = "修改失败，请重试")

    })

//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//
//            @ApiImplicitParam(name = "userAccount", value = "用户账户名", dataType = "String", paramType = "path", example = "tom"),
//            @ApiImplicitParam(name = "userPassword", value = "用户新密码", dataType = "String", paramType = "path", example = "110"),
//            @ApiImplicitParam(name = "userTel", value = "用户电话号码", dataType = "String", paramType = "path", example = "15578491030"),
//            @ApiImplicitParam(name = "code", value = "验证码", dataType = "String", paramType = "path", example = "1234"),
//
//    })
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity updateUser(@RequestBody @Valid UserUpdateParam userUpdateParam) {
        //校验传入参数的合法性

        if (userUpdateParam != null) {

            //从jwt中获取userid
            Integer userId = GetTokenUtil.getUserId();
            System.out.println(userId);

            User userDb = userService.getById(userId);
            //从jwt中取出account
            String userAccount = GetTokenUtil.getUserAccount();
            System.out.println(userAccount);

            if (!ObjectUtils.isEmpty(userDb) & userDb.getUserAccount().equals(userUpdateParam.getUserAccount())) {
                //从redis取出验证码
                String codeDb = (String) redisTemplate.opsForValue().get("com:woniu:car:user-server:car-user-web:sendcode:" + userDb.getUserTel());
                //校验验证码
                if (userUpdateParam.getCode().equals(codeDb)) {
                    //校验成功执行修改
                    if (userUpdateParam.getUserTel() != null) {
                        userDb.setUserTel(userUpdateParam.getUserTel());
                    }
                    String userPassword = userUpdateParam.getUserPassword();
                    if (userPassword != null) {
                        Md5Hash md5Hash = new Md5Hash(userPassword, userDb.getUserSalt(), 2048);
                        String newPassword = md5Hash.toHex();
                        userDb.setUserPassword(newPassword);

                    }
                    boolean b = userService.updateById(userDb);
                    if (b)
                        return ResultEntity.buildEntity().setCode(ConstCode.UPDATEUSER_SUCCESS).setFlag(true).setMessage("更新账户成功");
                }
            }
        }
        return ResultEntity.buildEntity().setCode(ConstCode.UPDATEUSER_FAIL).setFlag(false).setMessage("修改失败，请重试");

    }
    @DeleteMapping("/delete_user")
    @ApiOperation(value = "用户注销接口", notes = "<span style='color:red;'>用来执行用户注销接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1384,message = "注销用户成功"),
            @ApiResponse(code = 1385,message = "注销用户失败")
    })
    public ResultEntity deleteUser(DeleteUserParam deleteUserParam){
        //从token李获取userid
        Integer userId = GetTokenUtil.getUserId();
        User userDb = userService.getById(userId);
        if (ObjectUtils.isEmpty(deleteUserParam)){


        if (!ObjectUtils.isEmpty(userDb)){
            if (userDb.getUserPassword().equals(deleteUserParam.getUserPassword())){
                //校验成功开始删除
                boolean b = userService.removeById(userId);
                if (b){
                    //删除关联的表
                    boolean remove = userInformationService.remove(new QueryWrapper<UserInformation>().eq("user_id", userId));
                    if (remove) return ResultEntity.buildSuccessEntity().setCode(ConstCode.DELETEUSER_SUCESS).setFlag(true)
                            .setMessage("删除用户成功");
                    throw new CarException("删除用户失败",500);
                }
            }
        }
        throw  new CarException("未登陆，请登陆后执行该操作",500);

    } throw new CarException("输入参数错误",500);
    }

}

