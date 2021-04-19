package com.woniu.car.shop.model.paramVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/17/11:55
 * @Description: 根据门店id和总评分修改该门店总评分
 */
@Data
@ApiModel(value = "根据门店id和总评分修改该门店总评分")
public class AmendShopGradeByShopIdParamVo {

    @ApiModelProperty(value = "门店id",example = "1")
    @Min(value = 1,message = "门店id必须大于等于1")
    @NotNull(message = "门店id不能为空")
    private Integer shopId;

    @ApiModelProperty(value = "门店总评分",example = "4.8")
    @Min(value = 1,message = "门店总评分必须大于等于1")
    @NotNull(message = "门店总评分不能为空")
    private Double shopGrade;
}
