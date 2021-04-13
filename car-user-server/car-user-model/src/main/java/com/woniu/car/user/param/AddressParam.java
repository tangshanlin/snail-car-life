package com.woniu.car.user.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressParam {
    @NotNull
    private String addressContactName;
    @NotNull
    private String addressContactTel;
    @NotNull
    private String addressZip;
    @NotNull
    private String addressProvince;
    @NotNull
    private String addressCity;
    @NotNull
    private String addressDistrict;
    @NotNull
    private String addressStreet;
    @NotNull
    private String addressDetail;
    @NotNull
    private Integer isDefaultAddress;
}
