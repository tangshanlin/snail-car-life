package com.woniu.car.user.web.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.user.param.LoginPasswordParam;
import com.woniu.car.user.param.LoginTelParam;
import com.woniu.car.user.param.RegisterParam;
import com.woniu.car.user.param.TelParam;
import com.woniu.car.user.web.domain.User;
import com.woniu.car.user.web.domain.UserInformation;
import com.woniu.car.user.web.mapper.UserInformationMapper;
import com.woniu.car.user.web.mapper.UserMapper;
import com.woniu.car.user.web.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.user.web.util.JwtUtils;
import com.woniu.car.user.web.util.SaltUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.web.session.HttpServletSession;
import org.aspectj.weaver.ast.Var;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserInformationMapper userInformationMapper;
    //注册方法
    @Override
    public int register(RegisterParam registerParam) {
        if (registerParam!=null){

            //校验电话号码及手机号码
            User user_account = userMapper.selectOne(new QueryWrapper<User>().eq("user_account", registerParam.getUserAccount()));
            User user_tel = userMapper.selectOne(new QueryWrapper<User>().eq("user_tel", registerParam.getUserTel()));
            if (user_account!=null)return -3;
            if (user_tel!=null)return -2;


            System.out.println(registerParam);
            String salt = SaltUtil.getSalt(8);
            System.out.println(salt);
            Md5Hash md5Hash = new Md5Hash(registerParam.getUserPassword(), salt, 2048);
            String newPassword=md5Hash.toHex();
            registerParam.setUserPassword(newPassword);

            User user = BeanCopyUtil.copyOne(registerParam, User::new);
            //存储盐
            user.setUserSalt(salt);
            //设置用户的状态 1为正常，0为异常
            user.setUserStatus(1);
            int insert = userMapper.insert(user);
            
            if (insert>0){
                //新增用户成功，直接新增用户详情表
                UserInformation userInformation = new UserInformation();
                User userDb = userMapper.selectOne(new QueryWrapper<User>().eq("user_tel", user.getUserTel()));
                userInformation.setUserId(userDb.getUserId());
                userInformation.setUserTel(userDb.getUserTel());
                int insert1 = userInformationMapper.insert(userInformation);
              return insert1;
            }
        }
            return -1;


    }

//    //登陆方法
//    @Override
//    public int login(LoginPasswordParam userParam) {
//        String userAccount = userParam.getUserAccount();
//        //根据账户查询账户是否存在
//        User userDb = userMapper.selectOne(new QueryWrapper<User>().eq("user_account", userAccount));
//        if (!ObjectUtils.isEmpty(userDb)){
//            //直接校验
//            Md5Hash md5Hash = new Md5Hash(userParam.getPassword(), userDb.getUserSalt(), 2048);
//            if (md5Hash.equals(userDb.getUserPassword())){
//                //与数据库校验成功，创建jwttoken
//                Map<String, String> tokenmap = new LinkedHashMap<>();
//                tokenmap.put("userAccount",userDb.getUserAccount());
//                tokenmap.put("userId",userDb.getUserId().toString());
//                String s = JwtUtils.careatToken(tokenmap);
//                //存到session中
//
//                if (s!=null){
//                    //登陆成功，记录上次登陆的时间
//                    long l = System.currentTimeMillis();
//                    userDb.setUserLastLoginTime(l);
//                    int up = userMapper.updateById(userDb);
//                    if (up>0)
//                        return 2;
//                }
//
//            }
//            //用户密码不匹配
//            return 3;
//        }
//        //用户不存在
//        return 1;
//    }
    //根据电话号码查询用户
    @Override
    public User selectByTel(TelParam telParam) {
        User user_tel = userMapper.selectOne(new QueryWrapper<User>().eq("user_tel", telParam.getUserTel()));
        return user_tel;
    }
}
