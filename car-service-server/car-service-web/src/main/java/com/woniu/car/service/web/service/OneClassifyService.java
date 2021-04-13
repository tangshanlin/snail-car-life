package com.woniu.car.service.web.service;

import com.woniu.car.items.model.entity.OneClassify;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sokyo
 * @since 2021-04-05
 */
public interface OneClassifyService extends IService<OneClassify> {
    //新增一级分类
    public boolean addOneClassifyService(OneClassify oneClassify);
    //根据分类id修改一级分类名称
    public boolean updateOneClassifyServiceById(OneClassify OneClassify);
    //根据分类id删除一级分类
    public boolean deleteOneClassifyServiceById(OneClassify OneClassify);
    //查询所有一级分类
    public List<OneClassify> listOneClassifyServiceAll();

}
