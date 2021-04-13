package com.woniu.car.shop.model.paramVo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/10/1:39
 * @Description: 用户上传自己经纬度
 */
@Data
public class FindShopInfoByMeLngLat {
    private String lng;//经度
    private String Lat;//纬度
}
