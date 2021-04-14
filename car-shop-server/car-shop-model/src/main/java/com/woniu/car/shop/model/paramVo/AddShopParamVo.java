package com.woniu.car.shop.model.paramVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "申请成为门店传入参数")
public class AddShopParamVo {

    @ApiModelProperty(value = "门店名称")
    @NotNull(message = "门店名称不能为空")
    private String shopName;//门店名称

    @ApiModelProperty(value = "门店图片")
    @NotNull(message = "门店图片不能为空")
    private MultipartFile[] file;//门店图片

    @ApiModelProperty(value = "营业时间")
    @NotNull(message = "营业时间不能为空")
    private String shopTime;//营业时间(json)

    @ApiModelProperty(value = "经纬度")
    @NotNull(message = "经纬度不能为空")
    private String shopLngLat;//经纬度(json)

    @ApiModelProperty(value = "门店地址")
    @NotNull(message = "门店地址不能为空")
    private String shopAddress;//门店地址

    @ApiModelProperty(value = "标签")
    @NotNull(message = "标签不能为空")
    private String shopTag;//标签（json存服务标签）

    @ApiModelProperty(value = "所属类型（0非4s 1是4s店）",example = "1")
    @NotNull(message = "所属类型不能为空")
    @Size(min = 0,max = 1,message = "数字范围在0~1之间")
    private Integer shopClass;//所属类型（0非4s 1是4s店）

    @ApiModelProperty(value = "所属品牌")
    private String shopBrand;//所属品牌

    @ApiModelProperty(value = "联系电话",example = "18888888888")
    @NotNull(message = "联系电话不能为空")
    @Pattern(regexp = "^1([38][0-9]|4[579]|5[^4]|6[6]|7[0135678]|9[89])\\d{8}$",message = "不是正确的11位手机号")
    private Long shopTel;//联系电话

}
