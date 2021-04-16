package com.woniu.car.product.model.parame;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright (C), 2021, 温天宇
 *
 * @author WTY
 * Date: 2021/4/15 19:30
 * FileName: AddProductImageParam
 */
@Data
public class AddProductImageParam {
    private MultipartFile[] productImg;
}
