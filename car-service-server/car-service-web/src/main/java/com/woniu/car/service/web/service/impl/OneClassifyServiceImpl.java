package com.woniu.car.service.web.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.car.items.model.entity.OneClassify;
import com.woniu.car.service.web.mapper.OneClassifyMapper;
import com.woniu.car.service.web.service.OneClassifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sokyo
 * @since 2021-04-05
 */
@Service
public class OneClassifyServiceImpl extends ServiceImpl<OneClassifyMapper, OneClassify> implements OneClassifyService {

    @Resource
    private OneClassifyMapper oneClassifyMapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /*
     * @Author HuangZhengXing
     * @Description TODO 新增一级分类
     * @Date  2021/4/10
     * @Param [oneClassify]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)//开启事务回滚
    public int addOneClassifyService(OneClassify oneClassify) {
        QueryWrapper<OneClassify> wrapper = new QueryWrapper<>();
        wrapper.eq("one_classify_name",oneClassify.getOneClassifyName());
        OneClassify oneClassify1 = oneClassifyMapper.selectOne(wrapper);
        int i = 0;
        if (ObjectUtils.isEmpty(oneClassify1)){
            //新增数据之后返回一个int类型的值
            i = oneClassifyMapper.insert(oneClassify);
        }else {
            i = -10;
        }
        return i;
    }

    /*
     * @Author HuangZhengXing
     * @Description TODO 根据分类id修改一级分类名称
     * @Date  2021/4/10
     * @Param [oneClassify]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)//开启事务回滚
    public boolean updateOneClassifyServiceById(OneClassify oneClassify) {
        boolean b = false;
        UpdateWrapper<OneClassify> wrapper = new UpdateWrapper();
        wrapper.eq("one_classify_id",oneClassify.getOneClassifyId());
            //修改数据之后返回一个int类型的值
            int update = oneClassifyMapper.update(oneClassify,wrapper);
            //如果值大于1则修改成功
            if (update>0){
                b = true;
            }
        return b;
    }

    /*
     * @Author HuangZhengXing
     * @Description TODO 根据分类id删除一级分类
     * @Date  2021/4/10
     * @Param [oneClassify]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class) //开启事务回滚
    public boolean deleteOneClassifyServiceById(OneClassify oneClassify) {
        boolean b = false;
           //修改数据之后返回一个int类型的值
           int i = oneClassifyMapper.deleteById(oneClassify.getOneClassifyId());
           //如果值大于1则删除成功
           if (i>0){
               b = true;
           }
        return b;
    }

    /*
     * @Author HuangZhengXing
     * @Description TODO 查询所有一级分类
     * @Date  2021/4/10
     * @Param []
     * @return java.util.List<com.woniu.car.items.model.entity.OneClassify>
     **/
    @Override
    public List<OneClassify> listOneClassifyServiceAll() {
//        String oneclassifyall = (String) redisTemplate.opsForHash().get("com:woniu:car:service:oneclassify:t_one_classify", "oneclassifyall");
//        String str = JSONUtil.toJsonStr(oneclassifyall);
//        if (ObjectUtils.isEmpty(oneclassifyall)){
            //从数据库中查询所有一级分类信息
            List<OneClassify> oneClassifyList = oneClassifyMapper.selectList(null);
//            String str1 = JSONUtil.toJsonStr(oneClassifyList);
//            redisTemplate.opsForHash().put("com:woniu:car:service:oneclassify:t_one_classify","oneclassifyall",str1);
//            return oneClassifyList;
//        }else {
//            List<OneClassify> oneClassifies = JSONUtil.toList(str, OneClassify.class);
//            System.out.println("111111."+oneClassifies);
//            for (OneClassify oneClassify : oneClassifies) {
//                System.out.println(oneClassify);
//
//            }
            return oneClassifyList;
//        }

    }
}
