package com.woniu.car.user.param;

import lombok.Data;

/**
 * @ClassName UpdateAddress
 * @Desc 修改地址的输入参数类
 * @Author Administrator
 * @Date 2021/4/9 9:52
 * @Version 1.0
 */
@Data
public class UpdateAddress {
    private Integer userId;
    private Integer addressId;
    private String addressContactName;
    private String addressContactTel;
    private String addressZip;
    private String addressProvince;
    private String addressCity;
    private String addressDistrict;
    private String addressStreet;
    private String addressDetail;
    private Integer isDefaultAddress;
}
