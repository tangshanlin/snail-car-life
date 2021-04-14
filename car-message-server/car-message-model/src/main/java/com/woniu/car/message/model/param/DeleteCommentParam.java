package com.woniu.car.message.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "评论编号参数信息")
public class DeleteCommentParam implements Serializable {

    @NotNull(message = "评论编号不能为空")
    @ApiModelProperty(value = "评论编号")
    private String commentPCode;
}
