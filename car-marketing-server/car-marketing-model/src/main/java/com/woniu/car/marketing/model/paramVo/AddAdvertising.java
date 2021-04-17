package com.woniu.car.marketing.model.paramVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/09/14:13
 * @Description: 门店发布活动添加的参数
 */
@Data
@ApiModel(value = "门店发布活动添加的参数")
public class AddAdvertising {

    @NotNull(message = "活动图片不能为空")
    @ApiModelProperty(value = "优惠券名称")
    private MultipartFile[] file;//活动图片

    @Min(value = 0,message = "活动发布来源必须大于等于0")
    @NotNull(message = "活动发布来源不能为空")
    @ApiModelProperty(value = "发布来源(0平台其他对应门店对应id)",example = "1")
    private Integer advertisingSourceId;//

    @NotNull(message = "优惠券id不能为空")
    @ApiModelProperty(value = "优惠券id",example = "1")
    @Min(value = 1,message = "优惠券id必须大于等于1")
    private Integer couponId;//

    @NotNull(message = "活动说明不能为空")
    @ApiModelProperty(value = "活动说明")
    private String advertisingExplain;//

    @Future(message = "必须是一个将来的日期")
    @NotNull(message = "活动开始时间不能为空")
    @ApiModelProperty(value = "活动开始时间")
    private Date advertisingBeginTime;//

    @Future(message = "必须是一个将来的日期")
    @NotNull(message = "活动结束时间不能为空")
    @ApiModelProperty(value = "活动结束时间")
    private Date advertisingEndTime;
}
