package com.woniu.car.marketing.model.paramVo;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 */
@Data
@ApiModel(value = "添加优惠券类别填写的信息")
public class AddCouponParamVo {

    @ApiModelProperty(value = "优惠券名称")
    @NotEmpty(message = "优惠券名称不能为空")
    private String couponName;

    @ApiModelProperty(value = "优惠券说明")
    private String couponInfo;

    @ApiModelProperty(value = "优惠券面额(元)")
    @NotNull(message = "优惠券面额不能为空")
    private BigDecimal couponMoney;

    @ApiModelProperty(value = "使用门槛（满多少可用）")
    @NotNull(message = "使用门槛不能为空")
    private BigDecimal couponCondition;

    @ApiModelProperty(value = "发行来源(0平台-其他对应门店id)")
    @Min(value = 0,message = "发行来源必须大于等于0")
    @NotNull(message = "发行来源不能为空")
    private Integer couponGoods;

    @ApiModelProperty(value = "总发行量")
    @Min(value = 50,message = "总发行量必须大于等于50")
    @NotNull(message = "总发行量不能为空")
    private Integer couponNumber;

    @ApiModelProperty(value = "生效时间")
    @Future(message = "传入时间必须是将来的日期")
    @NotNull(message = "生效时间不能为空")
    private Date couponBeginTime;

    @ApiModelProperty(value = "失效时间")
    @Future(message = "传入时间必须是将来的日期")
    @NotNull(message = "失效时间不能为空")
    private Date couponEndTime;
}
