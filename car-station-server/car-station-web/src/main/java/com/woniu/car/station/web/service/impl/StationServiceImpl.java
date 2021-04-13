package com.woniu.car.station.web.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.car.station.model.dto.StationDto;
import com.woniu.car.station.model.dto.UpdateStationDto;
import com.woniu.car.station.model.entity.Station;
import com.woniu.car.station.web.mapper.StationMapper;
import com.woniu.car.station.web.service.StationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.station.web.util.StationFileUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements StationService {

    @Resource
    private StationMapper stationMapper;
    @Resource
    private StationFileUpload stationFileUpload;

    /*
     * @Author HuangZhengXing
     * @Description TODO 新增充电桩
     * @Date  2021/4/9
     * @Param [station]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addStation(StationDto stationDto) {
        log.info("开始处理上传的充电桩图片");
        MultipartFile[] files = stationDto.getStationImage();
        //将文件上传到minio服务器上
        ArrayList<String> stationImage = stationFileUpload.upload(files);
        //返回图片地址
        String stationimg = stationImage.get(0);
        System.out.println(stationimg);
        log.info("图片上传完毕返回图片路径:{}",stationimg);

        log.info("开始生成电桩的随机编码");
        String stationNumber = RandomUtil.randomString("abcdefg", 7);
        System.out.println("随机编码"+":"+stationNumber);
        log.info("开始生成二维码充电编号");
        String stationCode = RandomUtil.randomString("adihw", 5);
        System.out.println("充电编号"+":"+stationCode);
        //复制dto到实体类中
        Station station = new Station();
        BeanUtils.copyProperties(stationDto,station);
        System.out.println(station);
        //将生成的随机编码和编号存入实体类
        station.setStationNumeration(stationNumber);
        station.setStationCode(stationCode);
        station.setStationStatus(0);
        station.setStationImage(stationimg);
        System.out.println("完全的station实体类"+":"+station);
        log.info("开始新增充电桩");
        int insert = stationMapper.insert(station);
        //定义一个Boolean
        Boolean b = false;
        if (insert>0) b=true;
        return b;
    }
    /*
     * @Author HuangZhengXing
     * @Description TODO 根据充电桩id查询对应充电桩信息
     * @Date  2021/4/9
     * @Param [station]
     * @return com.woniu.car.station.model.entity.Station
     **/
    @Override
    public Station getOneStation(Station station) {
        log.info("开始根据充电桩id查询充电桩信息:{}",station.getStationId());
        QueryWrapper<Station> wrapper = new QueryWrapper<>();
        wrapper.eq("station_id",station.getStationId());
        Station station1 = stationMapper.selectOne(wrapper);
        log.info("返回查询值:{}",station1);
        return station1;
    }
    /*
     * @Author HuangZhengXing
     * @Description TODO 根据电站id查询该电站下的所有充电桩信息
     * @Date  2021/4/9
     * @Param []
     * @return java.util.List<com.woniu.car.station.model.entity.Station>
     **/
    @Override
    public List<Station> listStationAll(Station station) {
        log.info("根据电站id查询该电站下的所有充电桩信息");
        QueryWrapper<Station> wrapper = new QueryWrapper<>();
        wrapper.eq("powerplant_id",station.getPowerplantId());
        List<Station> stations = stationMapper.selectList(wrapper);
        log.info("查询完毕返回查询结果:{}",stations);
        return stations;
    }
    /*
     * @Author HuangZhengXing
     * @Description TODO 根据充电桩id修改基本信息
     * @Date  2021/4/9
     * @Param [station]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBasicStationInfo(UpdateStationDto stationDto) {
        log.info("开始处理上传要修改的充电桩图片");
        MultipartFile[] files = stationDto.getStationImage();
        //将文件上传到minio服务器上
        ArrayList<String> stationImage = stationFileUpload.upload(files);
        //返回图片地址
        String stationimg = stationImage.get(0);
        System.out.println(stationimg);
        log.info("图片上传完毕返回图片路径:{}",stationimg);

        log.info("开始接收要修改的充电桩参数:{}",stationDto);
        UpdateWrapper<Station> wrapper = new UpdateWrapper<>();
        wrapper.eq("station_id",stationDto.getStationId());
        //复制dto数据到实体类中
        Station station1 = new Station();
        BeanUtils.copyProperties(stationDto,station1);
        station1.setStationImage(stationimg);
        System.out.println("station1"+":"+station1);
        int update = stationMapper.update(station1, wrapper);
        //定义一个boolean
        boolean b = false;
        if (update>0) b=true;
        log.info("新增结束返回Boolean值:{}",b);
        return b;
    }
    /*
     * @Author HuangZhengXing
     * @Description TODO 根据充电桩id修改充电状态
     * @Date  2021/4/9
     * @Param [station]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updataStationStatus(Station station) {
        log.info("开始接收要修改充电状态的充电桩id:{}",station);
        UpdateWrapper<Station> wrapper = new UpdateWrapper<>();
        wrapper.eq("station_id",station.getStationId());
        int update = stationMapper.update(station, wrapper);
        //定义一个Boolean
        boolean b = false;
        if (update>0) b=true;
        log.info("返回修改充电状态之后的Boolean值:{}",b);
        return b;
    }
    /*
     * @Author HuangZhengXing
     * @Description TODO 根据充电桩id修改电桩电流类型
     * @Date  2021/4/9
     * @Param [station]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updataStationType(Station station) {
        log.info("开始接收要修改电流类型的充电桩id",station.getStationId());
        UpdateWrapper<Station> wrapper = new UpdateWrapper<>();
        wrapper.eq("station_id",station.getStationId());
        int update = stationMapper.update(station, wrapper);
        //定义一个Boolean值
        boolean b = false;
        if (update>0) b=true;
        log.info("返回修改修改电桩电流类型之后的Boolean值:{}",b);
        return b;
    }
    /*
     * @Author HuangZhengXing
     * @Description TODO 根据充电桩id删除充电桩
     * @Date  2021/4/9
     * @Param [station]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteStation(Station station) {
        log.info("开始接收要删除的充电桩id",station.getStationId());
        int i = stationMapper.deleteById(station.getStationId());
        boolean b = false;
        if (i>0) b = true;
        return b;
    }
}
