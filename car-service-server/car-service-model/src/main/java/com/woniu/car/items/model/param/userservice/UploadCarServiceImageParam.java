package com.woniu.car.items.model.param.userservice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UploadCarServiceImageParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/13 13:46
 * @Version 1.0
 */
@Data
public class UploadCarServiceImageParam {
    @NotNull
    @ApiModelProperty(value = "服务图片")
    private MultipartFile[] carServiceImage;
}
