package com.woniu.car.station.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.car.station.model.dto.PowerplantApplyforDto;
import com.woniu.car.station.model.entity.Powerplant;
import com.woniu.car.station.model.entity.PowerplantApplyfor;
import com.woniu.car.station.model.finalcode.PowerplantApplyforStatus;
import com.woniu.car.station.web.mapper.PowerplantApplyforMapper;
import com.woniu.car.station.web.service.PowerplantApplyforService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.station.web.service.PowerplantService;
import com.woniu.car.station.web.util.StationFileUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HZX
 * @since 2021-04-05
 */
@Service
@Slf4j
public class PowerplantApplyforServiceImpl extends ServiceImpl<PowerplantApplyforMapper, PowerplantApplyfor> implements PowerplantApplyforService {

    @Resource
    private PowerplantApplyforMapper powerplantApplyforMapper;

    @Resource
    private PowerplantService powerplantService;

    @Resource
    private StationFileUpload stationFileUpload;

    /*
     * @Author HuangZhengXing
     * @Description 新增电站申请表
     * @Date  2021/4/9
     * @Param [powerplantApplyforDto]
     * @return int
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)//开启事务回滚
    public int addPowerplantApplyfor(PowerplantApplyforDto powerplantApplyforDto) {
        log.info("开始处理接收的图片，参数为:{}",powerplantApplyforDto);
        MultipartFile[] files = powerplantApplyforDto.getPowerplantApplyforImage();
        //将文件上传到minio服务器上
        ArrayList<String> powerplantApplyforImage = stationFileUpload.upload(files);
        //返回图片地址
        String powerplantApplyforimg = powerplantApplyforImage.get(0);
        System.out.println(powerplantApplyforimg);
        int result = 0;
        QueryWrapper<PowerplantApplyfor> wrapper = new QueryWrapper<>();
        //根据名称查找看是否已重复
        wrapper.eq("powerplan_applyfort_name",powerplantApplyforDto.getPowerplanApplyfortName());
        PowerplantApplyfor powerplantApplyfor1 = powerplantApplyforMapper.selectOne(wrapper);
        System.out.println(powerplantApplyfor1);
        if(ObjectUtils.isEmpty(powerplantApplyfor1)){
            powerplantApplyforDto.setPowerplantApplyforStatus(PowerplantApplyforStatus.UNREVIEWED);
            //新增数据之后返回int类型
            PowerplantApplyfor powerplantApplyfor = new PowerplantApplyfor();
            BeanUtils.copyProperties(powerplantApplyforDto,powerplantApplyfor);
            powerplantApplyfor.setPowerplantApplyforImage(powerplantApplyforimg);
            System.out.println(powerplantApplyfor);
            result = powerplantApplyforMapper.insert(powerplantApplyfor);
        }else {
            log.info("如果名称已存在则返回参数:{}",result);
            result = -10;
        }
        log.info("最后则返回参数:{}",result);
        return result;
    }

    /*
     * @Author HuangZhengXing
     * @Description 修改电站申请表的审核状态
     * @Date  2021/4/9
     * @Param [powerplantApplyfor]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class) //开启事务回滚
    public boolean updatePowerplantApplyforStatusById(PowerplantApplyfor powerplantApplyfor) {
        log.info("开始修改电站申请的审核状态,需要传入的参数为:{}",powerplantApplyfor);
        System.out.println(powerplantApplyfor);
        UpdateWrapper<PowerplantApplyfor> wrapper = new UpdateWrapper();
        //根据电站申请表id修改信息
        wrapper.eq("powerplant_applyfor_id",powerplantApplyfor.getPowerplantApplyforId());
        boolean b = false;
        //修改电站申请表的审核状态
        int update = powerplantApplyforMapper.update(powerplantApplyfor, wrapper);
        log.info("电站申请表的审核状态修改完成,返回值为:{}",update);
        if (update>0){
            log.info("审核通过就新增电站信息");
            b=true;
            QueryWrapper<PowerplantApplyfor> wrapper1 = new QueryWrapper<>();
            wrapper.eq("powerplant_applyfor_id",powerplantApplyfor.getPowerplantApplyforId());
            PowerplantApplyfor powerplantApplyfor1 = powerplantApplyforMapper.selectOne(wrapper);
            //申请通过之后   将申请表的信息新增到电站信息表中
            Powerplant powerplant = new Powerplant();
            powerplant.setPowerplanName(powerplantApplyfor1.getPowerplanApplyfortName());
            powerplant.setPowerplantAddress(powerplantApplyfor1.getPowerplantApplyforAddress());
            powerplant.setPowerplantAlternatingNumber(powerplantApplyfor1.getPowerplantApplyforAlternatingNumber());
            powerplant.setPowerplantApplyforStatus(powerplantApplyfor1.getPowerplantApplyforStatus());
            powerplant.setPowerplantCocurrentNumber(powerplantApplyfor1.getPowerplantApplyforCocurrentNumber());
            powerplant.setPowerplantCoordinate(powerplantApplyfor1.getPowerplantCoordinate());
            powerplant.setPowerplantImage(powerplantApplyfor1.getPowerplantApplyforImage());
            powerplant.setPowerplantIntroduce(powerplantApplyfor1.getPowerplantApplyforIntroduce());
            powerplant.setPowerplantPhone(powerplantApplyfor1.getPowerplantApplyforPhone());
            powerplant.setUserId(powerplantApplyfor1.getUserId());
            System.out.println(powerplant);
            log.info("新增电站信息参数值:{}",powerplant);
            int i = powerplantService.addPowerplant(powerplant);
        }
        log.info("最后返回布尔值:{}",b);
        return b;
    }

    /*
     * @Author HuangZhengXing
     * @Description 根据电站申请id查询对应的申请表
     * @Date  2021/4/9
     * @Param [powerplantApplyfor]
     * @return com.woniu.car.station.model.entity.PowerplantApplyfor
     **/
    @Override
    public PowerplantApplyfor getOnePowerplantApplyforById(PowerplantApplyfor powerplantApplyfor) {
        log.info("开始接收要查询申请表的id参数为:{}",powerplantApplyfor);
        QueryWrapper<PowerplantApplyfor> wrapper = new QueryWrapper<>();
        //对应电站申请表id字段
        wrapper.eq("powerplant_applyfor_id",powerplantApplyfor.getPowerplantApplyforId());
        //查询电站申请表信息
        PowerplantApplyfor powerplantApplyfor1 = powerplantApplyforMapper.selectOne(wrapper);
        log.info("查询完成之后返回值为:{}",powerplantApplyfor1);

        log.info("最后返回查询完成之后的返回值");
        return powerplantApplyfor1;
    }

    /*
     * @Author HuangZhengXing
     * @Description 查询所有电站申请表信息
     * @Date  2021/4/9
     * @Param []
     * @return java.util.List<com.woniu.car.station.model.entity.PowerplantApplyfor>
     **/
    @Override
    public List<PowerplantApplyfor> listPowerplantApplyforAll() {
        log.info("查询所有电站申请信息不需要携带参数");
        //查询所有电站申请表信息
        List<PowerplantApplyfor> powerplantApplyforList = powerplantApplyforMapper.selectList(null);
        log.info("查询所有电站申请信息完成，返回值为:{}",powerplantApplyforList);
        return powerplantApplyforList;
    }

    /*
     * @Author HuangZhengXing
     * @Description 根据电站申请表id删除电站申请表
     * @Date  2021/4/9
     * @Param [powerplantApplyfor]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)//开启事务回滚
    public boolean deletePowerplantApplyforById(PowerplantApplyfor powerplantApplyfor) {
        log.info("开始进行接收要删除申请表的id",powerplantApplyfor.getPowerplantApplyforId());
        boolean b = false;
        int i = powerplantApplyforMapper.deleteById(powerplantApplyfor.getPowerplantApplyforId());
        if (i>0) b=true;
        log.info("删除完成之后返回布尔类型:{}",b);
        return b;
    }
}
