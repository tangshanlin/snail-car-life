package com.woniu.car.station.web.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.station.model.dto.LongitudeAndLatitude;
import com.woniu.car.station.model.dto.PowerplantApplyforVoDto;
import com.woniu.car.station.model.dto.PowerplantDto;
import com.woniu.car.station.model.entity.Powerplant;
import com.woniu.car.station.model.entity.PowerplantApplyfor;
import com.woniu.car.station.model.entity.Station;
import com.woniu.car.station.model.param.DeletePowerplantParam;
import com.woniu.car.station.web.mapper.PowerplantMapper;
import com.woniu.car.station.web.mapper.StationMapper;
import com.woniu.car.station.web.service.PowerplantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class PowerplantServiceImpl extends ServiceImpl<PowerplantMapper, Powerplant> implements PowerplantService {

    @Resource
    private PowerplantMapper powerplantMapper;
    @Resource
    private StationMapper stationMapper;
    /*
     * @Author HuangZhengXing
     * @Description 新增电站信息
     * @Date  2021/4/9
     * @Param [powerplant]
     * @return int
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)//开启事务回滚
    public int addPowerplant(Powerplant powerplant) {
        log.info("电站申请表的审核通过之后就会开始新增电站信息,新增参数为:{}",powerplant);
        int i = 0;
        log.info("开始进行名称查重");
        //根据名称查重,如果返回值为null则开始新增
        QueryWrapper<Powerplant> wrapper = new QueryWrapper<>();
        wrapper.eq("powerplan_name",powerplant.getPowerplanName());
        Powerplant powerplant1 = powerplantMapper.selectOne(wrapper);
        log.info("根据名称查询的返回值为:{}",powerplant1);
        if (ObjectUtils.isEmpty(powerplant1)){
            i = powerplantMapper.insert(powerplant);
            log.info("新增结果的返回值:{}",i);
        }else {
            i = -10;
        }
        log.info("最后返回i的参数值:{}",i);
        return i;
    }
    /*
     * @Author HuangZhengXing
     * @Description 根据电站id查询对应电站信息
     * @Date  2021/4/9
     * @Param [powerplant]
     * @return com.woniu.car.station.model.entity.Powerplant
     **/
    @Override
    public PowerplantDto getOnePowerplant(Powerplant powerplant) {
        log.info("开始接收要查询的电站id,参数为:{}",powerplant);
        QueryWrapper<Powerplant> wrapper = new QueryWrapper<>();
        wrapper.eq("powerplant_id",powerplant.getPowerplantId());
        Powerplant powerplant1 = powerplantMapper.selectOne(wrapper);
        //new一个新的对象
        PowerplantDto powerplantDto = new PowerplantDto();
        //复制旧实体类的属性
        BeanUtils.copyProperties(powerplant1,powerplantDto);
        //把经纬度字符串单独提出来
        String lal = powerplant1.getPowerplantCoordinate();
        //将经纬度字符串转换为经纬度对象
        LongitudeAndLatitude longitudeAndLatitude = JSONUtil.toBean(lal, LongitudeAndLatitude.class);
        powerplantDto.setPowerplantCoordinate(longitudeAndLatitude);
        //查询电桩价格
        QueryWrapper<Station> wrapper1 = new QueryWrapper<>();
        //此处有问题
        wrapper1.eq("powerplant_id",powerplant.getPowerplantId());
        List<Station> stationList = stationMapper.selectList(wrapper1);
        BigDecimal stationPrice = stationList.get(0).getStationPrice();
        powerplantDto.setStationPrice(stationPrice);
        log.info("将查询的结果值返回:{}",powerplantDto);
        System.out.println(powerplantDto);
        return powerplantDto;
    }
    /*
     * @Author HuangZhengXing
     * @Description 查询所有电站信息系
     * @Date  2021/4/9
     * @Param []
     * @return java.util.List<com.woniu.car.station.model.entity.Powerplant>
     **/
    @Override
    public List<PowerplantDto> listPowerplantAll() {
        log.info("开始查询所有电站信息，不需要携带参数查询");
        List<Powerplant> powerplantList = powerplantMapper.selectList(null);
        ArrayList<PowerplantDto> powerplantDtos = new ArrayList<>();
        for (Powerplant powerplant : powerplantList){
            PowerplantDto powerplantApplyforVoDto = new PowerplantDto();
            BeanUtils.copyProperties(powerplant,powerplantApplyforVoDto);
            String lal = powerplant.getPowerplantCoordinate();
            LongitudeAndLatitude longitudeAndLatitude = JSONUtil.toBean(lal, LongitudeAndLatitude.class);
            //查询电桩价格
            QueryWrapper<Station> wrapper = new QueryWrapper<>();
            wrapper.eq("powerplant_id",powerplant.getPowerplantId());
            List<Station> stationList = stationMapper.selectList(wrapper);
            BigDecimal stationPrice = stationList.get(0).getStationPrice();
            powerplantApplyforVoDto.setPowerplantCoordinate(longitudeAndLatitude);
            powerplantApplyforVoDto.setStationPrice(stationPrice);
            powerplantDtos.add(powerplantApplyforVoDto);
        }
        log.info("将查询的结果值返回:{}",powerplantList);
        return powerplantDtos;
    }
    /*
     * @Author HuangZhengXing
     * @Description 根据电站id修改电站信息
     * @Date  2021/4/9
     * @Param [powerplant]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)//开启事务回滚
    public boolean updatePowerplantById(Powerplant powerplant) {
        log.info("接收要修改对应电站的id和要修改的信息,参数为:{}",powerplant);
        UpdateWrapper<Powerplant> wrapper = new UpdateWrapper<>();
        wrapper.eq("powerplant_id",powerplant.getPowerplantId());
        int update = powerplantMapper.update(powerplant, wrapper);
        log.info("返回数据库修改操作的返回值:{}",update);
        boolean b = false;
        if (update>0) b=true;
        log.info("最后返回布尔类型判断是否修改成功:{}",b);
        return b;
    }
    /*
     * @Author HuangZhengXing
     * @Description 根据电站id删除电站信息
     * @Date  2021/4/9
     * @Param [powerplant]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)//开启事务回滚
    public boolean deletePowerplantById(Powerplant powerplant) {
        log.info("接收删除电站的id:{}",powerplant.getPowerplantId());
        int i = powerplantMapper.deleteById(powerplant.getPowerplantId());
        log.info("返回数据库删除操作的返回值:{}",i);
        boolean b = false;
        if (i>0) b = true;
        log.info("最后返回布尔类型判断是否删除成功:{}",b);
        return b;
    }


}
