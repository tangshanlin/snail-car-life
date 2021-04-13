package com.woniu.car.station.web.service;

import com.woniu.car.station.model.dto.StationDto;
import com.woniu.car.station.model.dto.UpdateStationDto;
import com.woniu.car.station.model.entity.Station;
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
public interface StationService extends IService<Station> {
    //新增充电桩
    public boolean addStation(StationDto station);
    //根据充电桩id查询对应充电桩信息
    public Station getOneStation(Station station);
    //根据电站id查询该电站下的所有充电桩信息
    public List<Station> listStationAll(Station station);
    //根据充电桩id修改基本信息
    public boolean updateBasicStationInfo(UpdateStationDto station);
    //根据充电桩id修改充电状态
    public boolean updataStationStatus(Station station);
    //根据充电桩id修改电桩电流类型
    public boolean updataStationType(Station station);
    //根据充电桩id删除充电桩
    public boolean deleteStation(Station station);

}
