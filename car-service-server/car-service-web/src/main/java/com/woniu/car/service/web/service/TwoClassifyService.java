package com.woniu.car.service.web.service;

import com.woniu.car.items.model.entity.TwoClassify;
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
public interface TwoClassifyService extends IService<TwoClassify> {
    //新增二级分类
    public int insertTwoClassifyService(TwoClassify twoClassify);
    //根据分类id修改二级分类名称
    public boolean updateTwoClassifyServiceById(TwoClassify twoClassify_id);
    //根据分类id删除二级分类
    public boolean deleteTwoClassifyServiceById(TwoClassify twoClassify_id);
    //根据一级分类id查询所有二级分类
    public List<TwoClassify> selectTwoClassifyServiceAll(TwoClassify twoClassify);
}
