package com.woniu.car.user.param;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName UpdateUserInformationImageParam
 * @Desc TODO 修改用户头像的接口
 * @Author Administrator
 * @Date 2021/4/17 15:23
 * @Version 1.0
 */
@Data
public class UpdateUserInformationImageParam {
    private MultipartFile file;

}
