package com.woniu.car.commons.web.swagger.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Lints
 * @Date 2021/4/6/006 13:09
 * @Description swagger公共参数
 * @Since version-1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private String name;
    private String email;
    private String host;

}
