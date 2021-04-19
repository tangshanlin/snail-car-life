package com.woniu.car.user.web.controller;


import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.core.exception.CarException;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.user.param.*;
import com.woniu.car.user.web.domain.Address;
import com.woniu.car.user.web.domain.User;
import com.woniu.car.user.web.service.AddressService;
import com.woniu.car.user.web.service.UserService;
import com.woniu.car.user.web.util.GetTokenUtil;
import com.woniu.car.user.web.util.JwtUtils;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 地址表 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/address")
@Api(tags = "地址接口")

public class AddressController {
    @Resource
    private AddressService addressService;
    @Resource
    private UserService userService;
    @PostMapping("/add_address")

    @ApiOperation(value = "新增地址接口", notes = "<span style='color:red;'>用来新增地址接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1350, message = "新增地址成功"),
            @ApiResponse(code = 1351, message = "新增地址失败"),
            @ApiResponse(code = 1400, message = "输入参数错误")

    })

//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//
//            @ApiImplicitParam(name = "addressContactName", value = "收件人名字", dataType = "String", example = "tom"),
//            @ApiImplicitParam(name = "addressContactTel", value = "收件人电话", dataType = "String",  example = "15578491030"),
//            @ApiImplicitParam(name = "addressZip" , value = "邮编", dataType = "String",  example = "400020"),
//            @ApiImplicitParam(name = "addressProvince", value = "省份", dataType = "String",  example = "重庆市"),
//            @ApiImplicitParam(name = "addressCity", value = "城市", dataType = "String", example = "重庆市"),
//            @ApiImplicitParam(name = "addressDistrict", value = "分区", dataType = "String", example = "江北区"),
//            @ApiImplicitParam(name = "addressStreet", value = "街道", dataType = "String",  example = "红锦大道"),
//            @ApiImplicitParam(name = "addressDetail", value = "详细地址", dataType = "String",  example = "理想大厦蜗牛"),
//            @ApiImplicitParam(name = "isDefaultAddress", value = "是否为默认地址（0为默认，1为非默认）", dataType = "String", example = "1"),
//
//
//
//    })
    public ResultEntity addAddress(@RequestBody @Valid AddressParam addressParam){
        if (!ObjectUtils.isEmpty(addressParam)){
            //复制到address对象
            Address address = BeanCopyUtil.copyOne(addressParam, Address::new);
            //获取用户ID
            Integer userId = GetTokenUtil.getUserId();
            address.setUserId(userId);
            //测试用
            System.out.println(userId);
            boolean save = addressService.save(address);
            if (save) return ResultEntity.buildEntity().setCode(ConstCode.ADDADDRESS_SUCCESS).setFlag(true)
                    .setMessage("新增地址成功");
            return ResultEntity.buildEntity().setCode(ConstCode.ADDADDRESS_FAIL).setFlag(false).
                    setMessage("新增地址失败");
        }
        return  ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }

  /*
 * @Author WangPeng
 * @Description 删除地址接口
 * @Date  2021/4/8
 * @Param []
 * @return com.woniu.car.commons.core.dto.ResultEntity
 **/
  @DeleteMapping("/delete_address")
  @ApiOperation(value = "删除地址接口", notes = "<span style='color:red;'>用来删除地址接口</span>")
  @ApiResponses({
          @ApiResponse(code = 1352, message = "删除地址成功"),
          @ApiResponse(code = 1353, message = "删除地址失败"),
          @ApiResponse(code = 1400, message = "输入参数错误")

  })

//  @ApiImplicitParams({
//          //dataType:参数类型
//          //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//
//          @ApiImplicitParam(name = "addressId", value = "地址id", dataType = "integer", example = "110")
//
//
//
//  })
    public ResultEntity deleteAddress(@RequestBody @Valid deleteAddress deleteAddress){
        //校验
      //获取UserId
      Integer userId = GetTokenUtil.getUserId();
      if (ObjectUtils.isEmpty(userId)){
          throw new CarException("未登陆，请重新登陆",500);
      }


      Address addressDb = addressService.getById(deleteAddress.getAddressId());
        if (addressDb.getUserId()==userId){

            boolean b = addressService.removeById(deleteAddress.getAddressId());
            if (b){
                return ResultEntity.buildEntity().setCode(ConstCode.DELETEADDRESS_SUCCESS).setFlag(true)
                        .setMessage("删除地址成功");
            }else {

                throw new CarException("删除地址失败",500);
            }



        }
      throw new CarException("输入参数错误",500);

    }

    @GetMapping("/selectByAddressId")
    @ApiOperation(value = "根据地址ID查询用户地址接口", notes = "<span style='color:red;'>用来根据地址ID查询地址接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1354, message = "查询用户所有地址成功"),
            @ApiResponse(code = 1355, message = "查询用户地址失败"),
            @ApiResponse(code = 1400, message = "输入参数错误")

    })

//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//
//            @ApiImplicitParam(name = "addressId;", value = "地址Id", dataType = "Integer",example = "110")
//
//
//
//    })
    public ResultEntity selectByAddressId( @Valid SlectAddressByAdressIdParam selectAddressParam){
      //校验输入参数
        //从jwt中获取userId；
        Integer userId = GetTokenUtil.getUserId();

        //校验
        Address addressDb = addressService.getById(selectAddressParam.getAddressId());
        if (!ObjectUtils.isEmpty(addressDb)&addressDb.getUserId()==userId){


            //根据userid查询账户

                //校验成功

                return ResultEntity.buildEntity(Address.class).setCode(ConstCode.SELECTADDRESS_SUCCESS).setFlag(true).setMessage("查询用户所有地址成功")
                        .setData(addressDb);
            }
        return ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }

    @GetMapping("/select_by_userId")
    @ApiOperation(value = "查询用户地址接口", notes = "<span style='color:red;'>用来查询地址接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1354, message = "查询用户所有地址成功"),
            @ApiResponse(code = 1355, message = "查询用户地址失败"),
            @ApiResponse(code = 1400, message = "输入参数错误")

    })

//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//
//            @ApiImplicitParam(name = "userAccount", value = "用户账号", dataType = "String", example = "110")
//
//
//
//    })
    public ResultEntity<List<Address>>selectByUserId( ){
        //校验输入参数
        //从jwt中获取userId；
        Integer userId = GetTokenUtil.getUserId();

        if (ObjectUtils.isEmpty(userId)){


            //根据userid查询账户
            User userDb = userService.getOne(new QueryWrapper<User>().eq("user_id", userId));
            if (!ObjectUtils.isEmpty(userDb)) {
                //校验成功
                List<Address> addressList = addressService.list(new QueryWrapper<Address>().eq("user_id", userId));
                return ResultEntity.buildListEntity(Address.class).setCode(ConstCode.SELECTADDRESS_SUCCESS).setFlag(true).setMessage("查询用户所有地址成功")
                        .setData(addressList);

            }
        }
        return ResultEntity.buildListEntity(Address.class).setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }

    @PutMapping("/update_address")
    @ApiOperation(value = "修改地址接口", notes = "<span style='color:red;'>用来修改地址接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1356, message = "修改地址成功"),
            @ApiResponse(code = 1357, message = "修改地址失败"),
            @ApiResponse(code = 1400, message = "输入参数错误")

    })

//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//            @ApiImplicitParam(name = "addressId", value = "地址ID", dataType = "integer", paramType = "path", example = "110"),
//
//            @ApiImplicitParam(name = "addressContactName", value = "收件人名字", dataType = "String", example = "tom"),
//            @ApiImplicitParam(name = "addressContactTel", value = "收件人电话", dataType = "String",  example = "15578491030"),
//            @ApiImplicitParam(name = "addressZip" , value = "邮编", dataType = "String",example = "400020"),
//            @ApiImplicitParam(name = "addressProvince", value = "省份", dataType = "String",  example = "重庆市"),
//            @ApiImplicitParam(name = "addressCity", value = "城市", dataType = "String",  example = "重庆市"),
//            @ApiImplicitParam(name = "addressDistrict", value = "分区", dataType = "String",  example = "江北区"),
//            @ApiImplicitParam(name = "addressStreet", value = "街道", dataType = "String",  example = "红锦大道"),
//            @ApiImplicitParam(name = "addressDetail", value = "详细地址", dataType = "String", example = "理想大厦蜗牛"),
//            @ApiImplicitParam(name = "isDefaultAddress", value = "是否为默认地址（0为默认，1为非默认）", dataType = "String", example = "1"),
//
//
//
//    })
    public ResultEntity updateAddress(@RequestBody @Valid UpdateAddress updateAddress){
      //校验@RequestBody
        //从jwt中获取userId；
        Integer userId = GetTokenUtil.getUserId();
        @NotNull Integer addressId = updateAddress.getAddressId();
        Address addresdb = addressService.getById(addressId);
        if (addresdb.getUserId()==userId){
            //校验成功开始修改

            Address address = BeanCopyUtil.copyOne(updateAddress, Address::new);
            address.setUserId(userId);
            boolean b = addressService.updateById(address);
            if (b) return ResultEntity.buildEntity().setCode(ConstCode.UPDATEADDRESS_SUCCESS).setFlag(true).setMessage("修改地址成功");
            return ResultEntity.buildEntity().setCode(ConstCode.UPDATEADDRESS_FAIL).setFlag(true)
                    .setMessage("修改地址失败");

        }
        return ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }


}