package com.woniu.car.items.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @ClassName AddOneClassifyImageParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/15 17:40
 * @Version 1.0
 */
@Data
public class AddOneClassifyImageParam {
    @NotNull
    @ApiModelProperty(value = "一级分类图片")
    private MultipartFile[] oneClassifyImage;
}
