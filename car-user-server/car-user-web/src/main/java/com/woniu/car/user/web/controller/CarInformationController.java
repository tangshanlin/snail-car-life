package com.woniu.car.user.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.user.param.SelectCarinforamtionParam;
import com.woniu.car.user.web.domain.CarInformation;
import com.woniu.car.user.web.domain.Carseries;
import com.woniu.car.user.web.service.CarInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 车型信息表 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/car-information")
@Api(tags = "车型号信息的接口")
public class CarInformationController {
    @Resource
    private CarInformationService carInformationService;
    @Autowired
    private ElasticsearchOperations operations;

    /*通过车系查询车型号的接口*
     * @Author SuShanHu
     * @Description TODO Administrator
     * @Date  2021/4/14
     * @Param
     * @return
     **/
    @GetMapping("/select_by_carseriesId")
    @ApiOperation(value = "查询车型的接口",notes = "<span style='color:red;'>根据车系ID查询车型的接口</span>" )
    public ResultEntity<List<CarInformation>> selectByCarseriesIdByES(SelectCarinforamtionParam selectCarinforamtionParam){
        ArrayList<CarInformation> carInformations=new ArrayList<>();
        if (!ObjectUtils.isEmpty(selectCarinforamtionParam)) {
            final boolean carInfo = operations.indexExists("car_information");
            if(!carInfo){
                //从数据库中存储所有车详情
                final List<CarInformation> lists = carInformationService.list(null);
                if(!ObjectUtils.isEmpty(lists)){
                    lists.forEach(list->{
                        operations.save(list);
                    });
                }
            }
            Criteria c = new Criteria("carseriesId").is(selectCarinforamtionParam.getCarseriesId());
            CriteriaQuery cq = new CriteriaQuery(c);
            final SearchHits<CarInformation> search = operations.search(cq, CarInformation.class);
            search.getSearchHits().forEach(student -> {
                carInformations.add(student.getContent());
            });
            if(!ObjectUtils.isEmpty(carInformations)&&carInformations.size()>0){
                System.out.println("----------------------------------------ES查询");
                return ResultEntity.buildListEntity(CarInformation.class).setCode(ConstCode.SELECTCARINFORMATION_SUCESS)
                        .setFlag(true).setMessage("查询车型成功").setData(carInformations);
            }
            return  ResultEntity.buildListEntity(CarInformation.class).setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("不存在该车详情信息");
        }
        return  ResultEntity.buildListEntity(CarInformation.class).setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }



}



