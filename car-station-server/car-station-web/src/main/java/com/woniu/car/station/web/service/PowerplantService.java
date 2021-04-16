package com.woniu.car.station.web.service;

import com.woniu.car.station.model.dto.PowerplantDto;
import com.woniu.car.station.model.entity.Powerplant;
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
public interface PowerplantService extends IService<Powerplant> {
    //新增电站信息
    public int addPowerplant(Powerplant powerplant);
    //根据电站id查询对应电站信息
    public PowerplantDto getOnePowerplant(Powerplant powerplant);
    //查询所有电站信息系
    public List<PowerplantDto> listPowerplantAll();
    //根据电站id修改电站信息
    public boolean updatePowerplantById(Powerplant powerplant);
    //根据电站id删除电站信息
    public boolean deletePowerplantById(Powerplant powerplant);

}
