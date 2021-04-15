package com.woniu.car.auth.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.auth.model.params.BackUserLoginParams;
import com.woniu.car.auth.model.params.InsertAccountByTypeParams;
import com.woniu.car.auth.web.entity.BackUser;
import com.woniu.car.auth.web.entity.UserToRole;
import com.woniu.car.auth.web.service.BackUserService;
import com.woniu.car.auth.web.service.UserToRoleService;
import com.woniu.car.auth.web.utils.JWTUtils;
import com.woniu.car.auth.web.utils.SaltUtils;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import io.swagger.annotations.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Random;

/**
 * <p>
 * 后台用户控制层
 * </p>
 *
 * @author WTY
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/back-user")
@Api(tags = "后台用户服务")
public class BackUserController {

    @Resource
    private BackUserService backUserService;

    @Resource
    private UserToRoleService userToRoleService;

    @Resource
    private JWTUtils jwtUtils;

    /**
     * 随机随机账号生成范围字符串
     */
    public static String characters = "0123456789";

    /**
     * 随机生成账号
     */
    private StringBuilder getAccount() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int num = 10;
        while (true) {
            for (int i = 0; i < num; i++) {
                sb.append(characters.charAt(random.nextInt(10)));
            }
            QueryWrapper<BackUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("back_username", sb);
            BackUser backUser = backUserService.getOne(queryWrapper);
            if (ObjectUtils.isEmpty(backUser)) {
                break;
            }
        }
        return sb;
    }

    /**
     * 注册后台账号 账号通过前端的选择赋予角色
     *
     * @param iabt
     * @return
     */
    @ApiOperation(value = "注册后台账号", notes = "通过注册时候选择账号类型 传入type参数到后台进行角色授予,返回用户账号，默认密码123456")
    @ApiImplicitParam(name = "iabt",value = "账户类型 1:管理员 2:门店商家 3:电桩厂商",dataType = "InsertAccountByTypeParams",required = true)
    @PostMapping("/api/insert_account_by_type")
    public ResultEntity<String> insertAccountByType(@RequestBody @Validated InsertAccountByTypeParams iabt) {
        if(iabt.getType().equals(1) || iabt.getType().equals(2) || iabt.getType().equals(3)){
            //获得随机账号
            String account = getAccount().toString(); ;
            //默认密码
            String password = "123456";
            //盐
            String salt = SaltUtils.getSalt(16);
            //密码加密
            Md5Hash md5Hash = new Md5Hash(password, salt, 1024);
            //设置属性
            BackUser backUser = new BackUser();
            backUser.setBackUsername(account);
            backUser.setBackUserPassword(md5Hash.toString());
            backUser.setSalt(salt);
            backUser.setStatus("正常");
            //新增到back_user 返回boolean
            boolean b = backUserService.save(backUser);
            if (b) {
                QueryWrapper<BackUser> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("back_username", account);
                BackUser backUserDb = backUserService.getOne(queryWrapper);
                //调用角色授予方法
                boolean b1 = newRole(iabt.getType(), backUserDb.getBackUserId());
                if (b1) {
                    return ResultEntity.buildSuccessEntity(String.class)
                            .setMessage("账号新增并授予角色成功")
                            .setData(account);
                } else {
                    return ResultEntity.buildFailEntity(String.class)
                            .setCode(ConstCode.GET_ACCOUNT_ROLE_FAIL)
                            .setMessage("账号授予角色失败失败");
                }
            }
        }
        return ResultEntity.buildFailEntity(String.class)
                .setCode(ConstCode.ADD_END_ACCOUNT_FAIL)
                .setMessage("新增后台账户失败");
    }

    /**
     * 用户授予角色方法
     *
     * @param type         角色类型
     * @param backUserId 用户id
     * @return boolean
     */
    public boolean newRole(Integer type, Integer backUserId) {
        UserToRole userToRole = new UserToRole();
        userToRole.setRoleId(type);
        userToRole.setBackUserId(backUserId);
        boolean save = userToRoleService.save(userToRole);
        if(save) {
            return true;
        }
        return false;
    }

    /**
     * 后台用户登录
     *
     * @param backUserLoginParams
     * @return ResultEntity
     */
    @PostMapping("/api/login")
    @ApiOperation(value = "后台用户登录", notes = "通过输入账号密码进行登录，正确后返回token")
    @ApiImplicitParam(name = "backUserLoginParams",value = "后台用户账号",dataType = "BackUserLoginParams", required = true)
    private ResultEntity backUserLogin(@RequestBody @Validated  BackUserLoginParams backUserLoginParams) {
        //查询账号是否存在
        QueryWrapper<BackUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("back_username", backUserLoginParams.getBackUsername());
        BackUser backUserDb = backUserService.getOne(queryWrapper);
        if (!ObjectUtils.isEmpty(backUserDb)) {
            //不为空则开始对前端输入的密码进行加盐，获取一个加盐后的密码
            Md5Hash md5Hash = new Md5Hash(backUserLoginParams.getBackUserPassword(), backUserDb.getSalt(), 1024);
            //得到加盐后的新密码
            String md5password = md5Hash.toHex();
            if (md5password.equals(backUserDb.getBackUserPassword())) {
                //用户密码都正确后 将用户信息存入map集合 方便生成jwtToken
                HashMap<String, Object> map = new HashMap<>(16);
                //将用户名和用户id存入map集合中
                map.put("back_username", backUserDb.getBackUsername());
                map.put("back_user_id", backUserDb.getBackUserId());
                //注入生成token
                String jwt =  jwtUtils.geneJsonWebToken(map);
                return ResultEntity.buildSuccessEntity(String.class)
                        .setMessage("后台用户登录成功")
                        .setData(jwt)
                        .setCode(200);
            }
            return ResultEntity.buildFailEntity().setMessage("用户名或密码不正确");
        }
        return ResultEntity.buildFailEntity().setMessage("账号不存在");
    }


}

