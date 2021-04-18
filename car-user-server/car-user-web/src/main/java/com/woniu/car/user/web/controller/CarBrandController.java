package com.woniu.car.user.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.user.dto.CarBrandDto;
import com.woniu.car.user.dto.CarBrandListDto;
import com.woniu.car.user.web.domain.CarBrand;
import com.woniu.car.user.web.service.CarBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 车品牌表 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/car-brand")
@Api(tags = "车品牌查询")
@Slf4j
public class CarBrandController {
    @Resource
    private CarBrandService carBrandService;
    @Autowired

    private ElasticsearchOperations operations;
    /*查询所有车品牌*/
    @GetMapping("/select_all_brand")
    @ApiOperation(value = "查询所有车品牌的接口",notes = "<span style='color:red;'>查询所有车品牌的接口</span>")
    @ApiResponse(code = 1344,message = "查询车品牌成功")
    public ResultEntity<List<CarBrandListDto>> selectAllBrand(){
        List<CarBrandListDto> ListCarBrandlist = new ArrayList<CarBrandListDto>();
        final boolean carbrand = operations.indexExists("carbrand");
        CriteriaQuery cq = new CriteriaQuery(new Criteria());
        if (!carbrand){
            for (int i=65;i<91;i++){
                String s = String.valueOf((char) i);
                List<CarBrand> carband_firstletter = carBrandService.list(new QueryWrapper<CarBrand>().eq("carbrand_firstletter", s));
                CarBrandListDto carBrandListDto = new CarBrandListDto();
                carBrandListDto.setKey(s);
                List<CarBrandDto> carBrandDtos = BeanCopyUtil.copyList(carband_firstletter, CarBrandDto::new);
                carBrandListDto.setValue(carBrandDtos);
                ListCarBrandlist.add(carBrandListDto);
                operations.save(ListCarBrandlist);
            }if(!ObjectUtils.isEmpty(ListCarBrandlist)&&ListCarBrandlist.size()>0){
                System.out.println("数据库查出");
                log.info("车品牌条数：{}",ListCarBrandlist.size());
                return ResultEntity.buildListEntity(CarBrandListDto.class).setCode(ConstCode.SELECTCARBRAND_SUCESS).setFlag(true)
                        .setMessage("查询所有车品牌成功").setData(ListCarBrandlist);
            }
        }
        SearchHits<CarBrandListDto> search = operations.search(cq, CarBrandListDto.class);
        search.getSearchHits().forEach(student->{
            //从es中查
            ListCarBrandlist.add(student.getContent());
        });
            if (!ObjectUtils.isEmpty(ListCarBrandlist)&&ListCarBrandlist.size()>0){
                //从es中查出
                return ResultEntity.buildListEntity(CarBrandListDto.class).setCode(ConstCode.SELECTCARBRAND_SUCESS).setFlag(true)
                        .setMessage("查询所有车品牌成功").setData(ListCarBrandlist);
            }
        return ResultEntity.buildListFailEntity(CarBrandListDto.class).setMessage("查询所有车品牌失败");

    }

}

