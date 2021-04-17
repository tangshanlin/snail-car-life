package com.woniu.car.items.model.param.carservice;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName AddCarServiceParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/10 16:17
 * @Version 1.0
 */
@Data
public class AddCarServiceParam {
    @NotNull(message = "twoClassifyId不能为空")
    @ApiModelProperty(value = "关联二级分类id",example = "2")
    private Integer twoClassifyId;

    @NotNull(message = "shopId不能为空")
    @ApiModelProperty(value = "关联门店id",example = "1")
    private Integer shopId;

    @NotNull(message = "carServiceName不能为空")
    @ApiModelProperty(value = "服务名称",example = "标准洗车")
    private String carServiceName;

    @NotNull(message = "carServicePrice不能为空")
    @ApiModelProperty(value = "服务价格",example = "54")
    private BigDecimal carServicePrice;

    @NotNull(message = "carServiceImage不能为空")
    @ApiModelProperty(value = "服务图片")
    private String carServiceImage;

    @NotNull(message = "carServiceIntroduce不能为空")
    @ApiModelProperty(value = "服务介绍",example = "标准清洗汽车")
    private String carServiceIntroduce;

    @NotNull(message = "carServiceInfo不能为空")
    @ApiModelProperty(value = "服务详情")
    private String carServiceInfo;

    @NotNull(message = "carServiceType不能为空")
    @ApiModelProperty(value = "适用车型",example = "五座")
    private String carServiceType;
}
