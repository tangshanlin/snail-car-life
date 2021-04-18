package com.woniu.car.station.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UploadPowerplantApplyforImageParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/13 13:32
 * @Version 1.0
 */
@Data
public class UploadPowerplantApplyforImageParam {
    @NotNull(message = "不能为空")
    @ApiModelProperty(value = "电站图片")
    private MultipartFile[] powerplantApplyforImage;

}
