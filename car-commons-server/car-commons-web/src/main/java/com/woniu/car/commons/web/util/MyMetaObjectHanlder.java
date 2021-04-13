package com.woniu.car.commons.web.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @Author Lints
 * @Date 2021/4/6/006 13:12
 * @Description 数据库自动填充数据
 * @Since version-1.0
 */
@Slf4j
@Component
public class MyMetaObjectHanlder implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("正在开始新增的填充。。。。。。");
        this.setFieldValByName("gmtCreate",new Date().getTime(),metaObject);
        this.setFieldValByName("gmtModified",new Date().getTime(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("正在开始修改的填充。。。。。。");
        this.setFieldValByName("gmtModified",new Date().getTime(),metaObject);
    }
}
