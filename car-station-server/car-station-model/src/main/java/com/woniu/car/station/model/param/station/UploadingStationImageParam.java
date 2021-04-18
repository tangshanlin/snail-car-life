package com.woniu.car.station.model.param.station;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UploadingStationImageParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/13 13:17
 * @Version 1.0
 */
@Data
public class UploadingStationImageParam {
    @NotNull(message = "stationImage不能为空")
    @ApiModelProperty(value = "接收图片")
    private MultipartFile[] stationImage;
}
