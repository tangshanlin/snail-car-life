package com.woniu.car.shop.model.dtoVo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/12/9:50
 * @Description: 根据状态返回门店数据
 */
@Data
public class FindShopInfoByStateDtoVo {
    private String shopName;//门店名称
    private String shopImage;//门店图片
    private String shopTime;//营业时间(json)
    private String shopAddress;//门店地址
    private String shopTag;//标签（json存服务标签）
    private Integer shopClass;//所属类型（0非4s 1是4s店）
    private String shopBrand;//所属品牌
    private Long shopTel;//联系电话
}
