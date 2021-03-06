package com.woniu.car.shop.model.dtoVo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/12/9:50
 * @Description: 所有未审核的门店信息
 */
@Data
public class FindShopInfoByStateDtoVo {
    private String shopName;//门店名称
    private String shopImage;//门店图片
    private String shopTime;//营业时间(json)
    private String shopAddress;//门店地址
    private Integer shopClass;//所属类型（0非4s 1是4s店）
    private String shopBrand;//所属品牌
    private String shopTel;//联系电话
}
