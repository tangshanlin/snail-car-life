package com.woniu.car.order.model.param;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Data
@Accessors(chain = true)
public class UserVo {
    public Integer userId;
}
