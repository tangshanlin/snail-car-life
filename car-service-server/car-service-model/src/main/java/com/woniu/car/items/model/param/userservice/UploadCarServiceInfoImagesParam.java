package com.woniu.car.items.model.param.userservice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName UploadCarServiceInfoImagesParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/13 13:51
 * @Version 1.0
 */
@Data
public class UploadCarServiceInfoImagesParam {

    @ApiModelProperty(value = "服务详情")
    private MultipartFile[] carServiceInfo;
}
