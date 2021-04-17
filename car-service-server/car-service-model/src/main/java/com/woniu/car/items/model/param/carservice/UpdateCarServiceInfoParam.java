package com.woniu.car.items.model.param.carservice;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName UpdateCarServiceInfoParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/12 10:57
 * @Version 1.0
 */
@Data
public class UpdateCarServiceInfoParam {
    @NotNull(message = "carServiceId不能为空")
    @ApiModelProperty(value = "要修改的具体服务id",example = "1")
    private Integer carServiceId;


    @ApiModelProperty(value = "服务名称",example = "全车清洗")
    private String carServiceName;


    @ApiModelProperty(value = "服务价格",example = "120")
    private BigDecimal carServicePrice;


    @ApiModelProperty(value = "服务图片,可以为空")
    private MultipartFile[] carServiceImage;


    @ApiModelProperty(value = "服务介绍",example = "对汽车车身内饰完全清洗")
    private String carServiceIntroduce;


    @ApiModelProperty(value = "服务详情")
    private MultipartFile[] carServiceInfo;


    @ApiModelProperty(value = "适用车型",example = "四座")
    private String carServiceType;
}
