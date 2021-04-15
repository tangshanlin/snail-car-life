package com.woniu.car.station.web.service;

import com.woniu.car.station.model.dto.PowerplantApplyforDto;
import com.woniu.car.station.model.dto.PowerplantApplyforVoDto;
import com.woniu.car.station.model.entity.PowerplantApplyfor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HZX
 * @since 2021-04-05
 */
public interface PowerplantApplyforService extends IService<PowerplantApplyfor> {
    //新增电站申请信息
    public int addPowerplantApplyfor(PowerplantApplyfor powerplantApplyfor);
    //根据电站申请表的id修改电站审核状态
    public boolean updatePowerplantApplyforStatusById(PowerplantApplyfor powerplantApplyfor);
    //根据电站申请表id查询某一个电站信息
    public PowerplantApplyforVoDto getOnePowerplantApplyforById(PowerplantApplyfor powerplantApplyfor);
    //查询所有电站申请表信息
    public List<PowerplantApplyforVoDto> listPowerplantApplyforAll();
    //根据电站申请表id删除电站申请表
    public boolean deletePowerplantApplyforById(PowerplantApplyfor powerplantApplyfor);

}
