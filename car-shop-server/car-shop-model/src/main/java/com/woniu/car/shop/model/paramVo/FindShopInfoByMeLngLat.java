package com.woniu.car.shop.model.paramVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/10/1:39
 * @Description: 用户上传自己经纬度
 */
@Data
@ApiModel(value = "传经纬度")
public class FindShopInfoByMeLngLat {
    @ApiModelProperty(value = "经度")
    private Double lng;//经度

    @ApiModelProperty(value = "纬度")
    private Double Lat;//纬度

    private Double distance;
}
