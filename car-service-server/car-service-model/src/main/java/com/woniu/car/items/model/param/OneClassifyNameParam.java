package com.woniu.car.items.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class OneClassifyNameParam {
    @NotNull
    @ApiModelProperty(name = "oneClassifyName",value = "一级分类名称",example = "汽车美容")
    private String oneClassifyName;

    @NotNull
    @ApiModelProperty(value = "一级分类图片")
    private String oneClassifyImage;

}
