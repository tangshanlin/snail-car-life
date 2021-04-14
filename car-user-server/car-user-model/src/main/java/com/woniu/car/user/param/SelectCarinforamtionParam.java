package com.woniu.car.user.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName SelectCarinforamtionParam
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/14 15:17
 * @Version 1.0
 */
@Data
public class SelectCarinforamtionParam {
    @ApiModelProperty(value = "车系id")
    @NotNull(message = "参数不能为空")
    private Integer carseriesId;
}
