package com.woniu.car.shop.model.paramVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/12/17:02
 * @Description: 门店新填服务时生成门店服务类别收益数据
 */
@Data
@ApiModel(value = "生成门店服务类别收益数据需要的参数")
public class AddShopServiceEarningsParamVo {

    @ApiModelProperty(value = "门店id")
    @Min(value = 1,message = "门店id必须大于等于1")
    @NotNull(message = "门店id不能为空")
    private Integer shopId;//关联门店id

    @ApiModelProperty(value = "服务名称")
    @NotNull(message = "服务名称不能为空")
    private String carServiceName;//服务名称
}
