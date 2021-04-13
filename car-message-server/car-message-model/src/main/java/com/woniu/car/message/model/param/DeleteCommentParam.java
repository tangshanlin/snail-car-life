package com.woniu.car.message.model.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeleteCommentParam implements Serializable {
    private String commentPCode;
}
