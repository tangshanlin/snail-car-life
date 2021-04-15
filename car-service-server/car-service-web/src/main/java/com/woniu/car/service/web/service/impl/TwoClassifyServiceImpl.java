package com.woniu.car.service.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.car.items.model.entity.TwoClassify;
import com.woniu.car.service.web.mapper.TwoClassifyMapper;
import com.woniu.car.service.web.service.TwoClassifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sokyo
 * @since 2021-04-05
 */
@Service
@Slf4j
public class TwoClassifyServiceImpl extends ServiceImpl<TwoClassifyMapper, TwoClassify> implements TwoClassifyService {

    @Resource
    private TwoClassifyMapper twoClassifyMapper;

    /*
     * @Author HuangZhengXing
     * @Description TODO 新增二级分类
     * @Date  2021/4/10
     * @Param [twoClassify]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertTwoClassifyService(TwoClassify twoClassify) {
        log.info("开始接收要新增的二级分类参数:{}",twoClassify);
        //查询名称是否重复
        QueryWrapper<TwoClassify> wrapper = new QueryWrapper<>();
        wrapper.eq("two_classify_name",twoClassify.getTwoClassifyName());
        TwoClassify twoClassify1 = twoClassifyMapper.selectOne(wrapper);
        int insert = 0;
        boolean b = false;
        if (ObjectUtils.isEmpty(twoClassify1)){
            insert = twoClassifyMapper.insert(twoClassify);
            if (insert>0) b=true;
        }else {
            insert = -10;
        }
        log.info("新增完成之后的返回值:{}",insert);
        return insert;
    }
    /*
     * @Author HuangZhengXing
     * @Description TODO 根据分类id修改二级分类名称
     * @Date  2021/4/10
     * @Param [twoClassify_id]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTwoClassifyServiceById(TwoClassify twoClassify_id) {
        log.info("开始接收要修改的二级服务分类参数:{}",twoClassify_id);
        UpdateWrapper<TwoClassify> wrapper = new UpdateWrapper<>();
        wrapper.eq("two_classify_id",twoClassify_id.getTwoClassifyId());
        int update = twoClassifyMapper.update(twoClassify_id, wrapper);
        boolean b = false;
        if (update>0) b=true;
        log.info("修改操作完成返回值:{}",b);
        return b;
    }
    /*
     * @Author HuangZhengXing
     * @Description TODO 根据分类id删除二级分类
     * @Date  2021/4/10
     * @Param [twoClassify_id]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteTwoClassifyServiceById(TwoClassify twoClassify_id) {
        log.info("开始接收要删除的二级服务分类id:{}",twoClassify_id);
        int i = twoClassifyMapper.deleteById(twoClassify_id.getTwoClassifyId());
        boolean b = false;
        if (i>0) b=true;
        log.info("删除操作完成返回值:{}",b);
        return b;
    }
    /*
     * @Author HuangZhengXing
     * @Description TODO 根据一级分类id查询所有二级分类
     * @Date  2021/4/10
     * @Param []
     * @return java.util.List<com.woniu.car.items.model.entity.TwoClassify>
     **/
    @Override
    public List<TwoClassify> selectTwoClassifyServiceAll(TwoClassify twoClassify) {
        log.info("开始接收要查询的一级服务分类下所有二级服务分类:{}",twoClassify);
        QueryWrapper<TwoClassify> wrapper = new QueryWrapper<>();
        wrapper.eq("one_classify_id",twoClassify.getOneClassifyId());
        List<TwoClassify> twoClassifyList = twoClassifyMapper.selectList(wrapper);
        log.info("查询操作完成返回值:{}",twoClassifyList);
        return twoClassifyList;
    }
}
