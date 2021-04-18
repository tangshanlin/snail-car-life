package com.woniu.car.shop.model.paramVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Data
@ApiModel(value = "申请成为门店传入参数")
public class AddShopParamVo {

    @ApiModelProperty(value = "门店名称")
    @NotBlank(message = "门店名称不能为空")
    private String shopName;//门店名称

    @ApiModelProperty(value = "门店图片")
    //@NotEmpty(message = "门店图片不能为空")
    private MultipartFile[] file;//门店图片

    @ApiModelProperty(value = "营业时间")
    @NotBlank(message = "营业时间不能为空")
    private String shopTime;

    @ApiModelProperty(value = "经度")
    @NotBlank(message = "经度不能为空")
    private String shopLongitude;

    @ApiModelProperty(value = "纬度")
    @NotBlank(message = "纬度不能为空")
    private String shopLatitude;

    @ApiModelProperty(value = "门店地址")
    @NotBlank(message = "门店地址不能为空")
    private String shopAddress;//门店地址

    @ApiModelProperty(value = "标签")
    @NotEmpty(message = "标签不能为空")
    private String[] shopTag;

    @ApiModelProperty(value = "所属类型（0非4s 1是4s店）",example = "1")
    @NotNull(message = "所属类型不能为空")
    //@Size(min = 0,max = 1,message = "数字范围在0~1之间")
    private Integer shopClass;//所属类型（0非4s 1是4s店）

    @ApiModelProperty(value = "所属品牌")
    private String shopBrand;//所属品牌

    @ApiModelProperty(value = "联系电话",example = "18888888888")
    @NotBlank(message = "联系电话不能为空")
    @Pattern(regexp = "/^((0\\d{2,3}-\\d{7,8})|(1[3584]\\d{9}))$/",message = "不是正确的11位手机号")
    private String shopTel;//联系电话

}
