package com.woniu.car.user.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.user.param.SelectCarseirsParam;
import com.woniu.car.user.web.domain.Carseries;
import com.woniu.car.user.web.service.CarseriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 车系表 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/carseries")
@Api(tags = "车系信息的接口")
@Slf4j
public class CarseriesController {
    @Resource
    private CarseriesService carseriesService;

    @Autowired
    private ElasticsearchOperations operations;
    /*根据品牌id查询车系信息*/
    @GetMapping("/select_by_brand_id")
    @ApiOperation(value ="根据品牌ID查询车系信息的接口",notes = "<span style='color:red;'>根据品牌ID查询车系信息的接口</span>" )
    public ResultEntity<List<Carseries>> selectByCarBrandIdBYES(SelectCarseirsParam selectCarseirsParam){
        System.out.println(selectCarseirsParam);
        ArrayList<Carseries> carseries=new ArrayList<>();
        if (!ObjectUtils.isEmpty(selectCarseirsParam)) {
            final boolean carInfo = operations.indexExists("carseries");
            if(!carInfo){
                //从数据库中存储所有车详情
                log.info("从数据库查询");
                System.out.println("从数据库查询");
                final List<Carseries> lists = carseriesService.list(null);
                log.info("车系条数：{}",lists.size());
                if(!ObjectUtils.isEmpty(lists)){
                    lists.forEach(list->{
                        operations.save(list);
                    });
                }
            }
            Criteria c = new Criteria("carbrand_id").is(selectCarseirsParam.getCarbrandId());
            CriteriaQuery cq = new CriteriaQuery(c);
            final SearchHits<Carseries> search = operations.search(cq, Carseries.class);
            search.getSearchHits().forEach(student -> {
                carseries.add(student.getContent());
            });
            if(!ObjectUtils.isEmpty(carseries)&&carseries.size()>0){
                System.out.println("----------------------------------------ES查询");
                log.info("从Es数据库查询");
                return ResultEntity.buildListEntity(Carseries.class).setCode(ConstCode.SELECTCARSERIES_SUCESS).setFlag(true)
                        .setMessage("查询车系成功").setData(carseries);
            }
            return  ResultEntity.buildListEntity(Carseries.class).setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("不存在该车详情信息");
        }
        return  ResultEntity.buildListEntity(Carseries.class).setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }


}

