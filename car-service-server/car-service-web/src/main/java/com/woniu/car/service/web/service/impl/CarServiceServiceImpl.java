package com.woniu.car.service.web.service.impl;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.items.model.dto.CarServiceDto;
import com.woniu.car.items.model.dto.CarServiceImagsDto;
import com.woniu.car.items.model.entity.CarService;
import com.woniu.car.service.web.feign.ShopFeignClient;
import com.woniu.car.service.web.mapper.CarServiceMapper;
import com.woniu.car.service.web.service.CarServiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.service.web.util.ServiceFileUpload;
import com.woniu.car.shop.model.paramVo.AddShopServiceEarningsParamVo;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.redis.core.RedisTemplate;
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
 * @author Sokyo
 * @since 2021-04-05
 */
@Service
@Slf4j
public class CarServiceServiceImpl extends ServiceImpl<CarServiceMapper, CarService> implements CarServiceService {
    @Resource
    private CarServiceMapper carServiceMapper;
    @Resource
    private ServiceFileUpload serviceFileUpload;
    @Resource
    private ShopFeignClient shopFeignClient;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    /**
     * @Author HuangZhengXing
     * @Description TODO 新增具体服务信息
     * @Date  2021/4/10
     * @Param [carServiceDto]
     * @return int
     **/
    @Override
    @GlobalTransactional(timeoutMills = 5000)
    public int addCarService(CarService carService) {
        log.info("开始查重");
        int i = 0;
        QueryWrapper<CarService> wrapper = new QueryWrapper<>();
        wrapper.eq("car_service_name",carService.getCarServiceName());
        CarService carService1 = carServiceMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(carService1)){
            log.info("开始新增具体服务:{}",carService);
            carService.setCarServiceSold(0);
            i = carServiceMapper.insert(carService);
//            log.info("开始处理上传要新增的具体服务图片");
//            CarService carService = new CarService();
//            BeanUtils.copyProperties(carServiceDto,carService);
//            System.out.println("CarService"+":"+carService);
//            if(carServiceDto.getCarServiceImage().length>0){
//                MultipartFile[] files = carServiceDto.getCarServiceImage();
//                //将文件上传到minio服务器上
//                ArrayList<String> stationImage = serviceFileUpload.upload(files);
//                //返回图片地址
//                String stationimg = stationImage.get(0);
//                System.out.println(stationimg);
//                carService.setCarServiceImage(stationimg);
//                log.info("图片上传完毕返回图片路径:{}",stationimg);
            System.out.println("1111111111");
            if (i>0){
                System.out.println("2222222222222");
                log.info("开始调用feign接口");
                AddShopServiceEarningsParamVo addShopServiceEarningsParamVo = new AddShopServiceEarningsParamVo();
                addShopServiceEarningsParamVo.setShopId(carService.getShopId());
                addShopServiceEarningsParamVo.setCarServiceName(carService.getCarServiceName());
                shopFeignClient.addShopServiceEarnings(addShopServiceEarningsParamVo);
                log.info("开始调用feign接口");
            }

        }else {
                i=-10;
            }

//            if (carServiceDto.getCarServiceInfo().length>0){
//                log.info("开始处理上传要新增的具体服务详情多图图片");
//                MultipartFile[] files = carServiceDto.getCarServiceInfo();
//                //将文件上传到minio服务器上
//                ArrayList<String> stationImage = serviceFileUpload.upload(files);
//                JSONObject jsonObject = new JSONObject();
//                for (int a = 0;a<stationImage.size();a++){
//                    String time = String.valueOf(System.currentTimeMillis());
//                    jsonObject.put("service"+ UUID.randomUUID().toString()+time,stationImage.get(a));
//                }
//                System.out.println(jsonObject);
//                //返回图片地址
//                String stationimgs = JSONObject.toJSONString(jsonObject);
//                System.out.println(stationimgs);
//                carService.setCarServiceInfo(stationimgs);
////                log.info("图片上传完毕返回图片路径:{}",stationimgs);
//            }else {
//                i = -20;
//                return i;
//            }
//
//            if (carServiceDto.getCarServiceImage().length>0&&carServiceDto.getCarServiceInfo().length>0){
//                log.info("开始新增具体服务:{}",carService);
//                i = carServiceMapper.insert(carService);
//            }
//        }else {
//            i = -30;
//        }
        return i;
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据服务id查询具体服务信息
     * @Date  2021/4/12
     * @Param [carService]
     * @return com.woniu.car.items.model.entity.CarService
     **/
    @Override
    public CarService getCarServiceById(CarService carService) {
        log.info("开始根据具体服务id查询具体服务信息");
        QueryWrapper<CarService> wrapper = new QueryWrapper<>();
        wrapper.eq("car_service_id",carService.getCarServiceId());
        CarService carService1 = carServiceMapper.selectOne(wrapper);
//        CarServiceImagsDto carServiceImagsDto = new CarServiceImagsDto();
//        if (!ObjectUtils.isEmpty(carService1)){
//            //转换对象
//            BeanUtils.copyProperties(carService1,carServiceImagsDto);
//            //把图片存入list集合中
//            JSONArray jsonArray = JSONArray.parseArray(carService1.getCarServiceInfo());
//            List<String> images = new ArrayList<>();
//            for(int i=0;i<jsonArray.size();i++){
//                String s = jsonArray.get(i).toString();
//                images.add(s.toString().substring(s.indexOf(":")+2,s.indexOf("}")-1));
//            }
//            for (String ssss : images){
//                System.out.println(ssss);
//            }
//            carServiceImagsDto.setCarServiceInfo(images);
//        }

        log.info("查询完毕返回结果值:{}",carService1);
        return carService1;
    }

    /**
     * @Author HuangZhengXing
     * @Description TODO 根据门店id查询所有服务信息
     * @Date  2021/4/10
     * @Param [carService]
     * @return java.util.List<com.woniu.car.items.model.entity.CarService>
     **/
    @Override
    public List<CarService> listCarServiceByShopId(CarService carService) {
        log.info("开始接收要查询的门店id:{}",carService.getShopId());
        QueryWrapper<CarService> wrapper = new QueryWrapper<>();
        wrapper.eq("shop_id",carService.getShopId());
        List<CarService> carServices = carServiceMapper.selectList(wrapper);
//        List<CarServiceImagsDto> carServiceImagsDtoList = new ArrayList<>();
//        if (!ObjectUtils.isEmpty(carServices)){
//            //遍历集合
//            for (CarService carService1: carServices){
//                CarServiceImagsDto carServiceImagsDto = new CarServiceImagsDto();
//                //转换对象
//                BeanUtils.copyProperties(carService1,carServiceImagsDto);
//                //把图片存入list集合中
//                JSONArray jsonArray = JSONArray.parseArray(carService1.getCarServiceInfo());
//                List<String> images = new ArrayList<>();
//                for(int i=0;i<jsonArray.size();i++){
//                    String s = jsonArray.get(i).toString();
//                    images.add(s.toString().substring(s.indexOf(":")+2,s.indexOf("}")-1));
//                }
//                for (String ssss : images){
//                    System.out.println(ssss);
//                }
//                carServiceImagsDto.setCarServiceInfo(images);
//                carServiceImagsDtoList.add(carServiceImagsDto);
//            }
//        }
        return carServices;
    }
    /*
     * @Author HuangZhengXing
     * @Description TODO 根据二级分类id查询二级分类下的具体服务信息
     * @Date  2021/4/12
     * @Param [carService]
     * @return java.util.List<com.woniu.car.items.model.entity.CarService>
     **/
    @Override
    public List<CarService> listCarServiceByTwoClassify(CarService carService) {
        QueryWrapper<CarService> wrapper = new QueryWrapper<>();
        wrapper.eq("two_classify_id",carService.getTwoClassifyId());
        List<CarService> carServiceList = carServiceMapper.selectList(wrapper);
        return carServiceList;
    }

    /**
     * @Author HuangZhengXing
     * @Description TODO 查询所有服务信息
     * @Date  2021/4/10
     * @Param []
     * @return java.util.List<com.woniu.car.items.model.entity.CarService>
     **/
    @Override
    public List<CarService> listCarServiceAll() {
        log.info("开始查询所有服务信息");
        List<CarService> carServices = carServiceMapper.selectList(null);
        log.info("返回值:{}",carServices);
        return carServices;
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据服务id修改服务基本信息
     * @Date  2021/4/10
     * @Param [carServiceDto]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCarService(CarServiceDto carServiceDto) {
        log.info("开始接收要修改的门店信息",carServiceDto);
        CarService carService = new CarService();
        BeanUtils.copyProperties(carServiceDto,carService);
        boolean b = false;
        if (!ObjectUtils.isEmpty(carServiceDto.getCarServiceImage())){
            log.info("开始处理上传要修改的汽车具体服务图片");
            MultipartFile[] files = carServiceDto.getCarServiceImage();
            //将文件上传到minio服务器上
            ArrayList<String> stationImage = serviceFileUpload.upload(files);
            //返回图片地址
            String stationimg = stationImage.get(0);
            System.out.println(stationimg);
            log.info("图片上传完毕返回图片路径:{}",stationimg);

            if (!ObjectUtils.isEmpty(carServiceDto.getCarServiceInfo())){
                log.info("开始处理上传要修改的具体服务详情多图图片");
                MultipartFile[] files2 = carServiceDto.getCarServiceInfo();
                //将文件上传到minio服务器上
                ArrayList<String> stationImageInfo = serviceFileUpload.upload(files2);
                JSONObject jsonObject = new JSONObject();
                for (int a = 0;a<stationImageInfo.size();a++){
                    String time = String.valueOf(System.currentTimeMillis());
                    jsonObject.put("service"+ UUID.randomUUID().toString()+time,stationImageInfo.get(a));
                }
                System.out.println(jsonObject);
                //返回图片地址
                String stationimgs = JSONObject.toJSONString(jsonObject);
                System.out.println(stationimgs);
                carService.setCarServiceInfo(stationimgs);
                log.info("图片上传完毕返回图片路径:{}",stationimgs);
            }

            System.out.println("CarService"+":"+carService);
            carService.setCarServiceImage(stationimg);
            UpdateWrapper<CarService> wrapper = new UpdateWrapper<>();
            wrapper.eq("car_service_id",carService.getCarServiceId());
            int update = carServiceMapper.update(carService, wrapper);
            if (update>0) b=true;
        }else {
            System.out.println("CarService"+":"+carService);
            UpdateWrapper<CarService> wrapper = new UpdateWrapper<>();
            wrapper.eq("car_service_id",carService.getCarServiceId());
            int update = carServiceMapper.update(carService, wrapper);
            if (update>0) b=true;
        }
        return b;
    }
    /**
      * @Author HuangZhengXing
      * @Description TODO 根据服务id修改服务状态
      * @Date  2021/4/10
      * @Param [carService]
      * @return boolean
      **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCarServiceStatus(CarService carService) {
        log.info("开始接收要修改的服务状态",carService);
        UpdateWrapper<CarService> wrapper = new UpdateWrapper<>();
        wrapper.eq("car_service_id",carService.getCarServiceId());
        int update = carServiceMapper.update(carService, wrapper);
        boolean b = false;
        if (update>0) b=true;
        return b;
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据服务id修改已售数量
     * @Date  2021/4/10
     * @Param [carService]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCarServiceSold(CarService carService) {
        log.info("开始接收要修改的已售数量信息",carService.getCarServiceId());
//        UpdateWrapper<CarService> wrapper = new UpdateWrapper<>();
//        wrapper.eq("car_service_id",carService.getCarServiceId());
//        carService.setCarServiceSold(0+carService.getCarServiceSold());

//        int updateSold = carServiceMapper.update(carService, wrapper);
        String o1 = (String) redisTemplate.opsForHash().get("car:service:server:updatesold:"+carService.getCarServiceId(), String.valueOf(carService.getCarServiceId()));
        Integer o = -1;
        System.out.println(o1);
        if (!ObjectUtils.isEmpty(o1)){
            o = Integer.valueOf(o1);
        }
        if (o == -1){
            carService.setCarServiceSold(0);
            redisTemplate.opsForHash().put("car:service:server:updatesold:"+carService.getCarServiceId(),
                    String.valueOf(carService.getCarServiceId()),String.valueOf(carService.getCarServiceSold()+1));
        }else {
            carService.setCarServiceSold(o);
            redisTemplate.opsForHash().put("car:service:server:updatesold:"+carService.getCarServiceId(),
                    String.valueOf(carService.getCarServiceId()),String.valueOf(carService.getCarServiceSold()+1));
        }
        String o2 = (String) redisTemplate.opsForHash().get("car:service:server:updatesold:"+carService.getCarServiceId(), String.valueOf(carService.getCarServiceId()));
        Integer oo = Integer.valueOf(o2);
        System.out.println("o1"+":"+o1);
        System.out.println("o2"+":"+o2);
        boolean b = false;
        if (oo > o){
            b = true;
        }
//        if (updateSold>0) b=true;
        log.info("返回值:{}",b);
        return b;
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据服务id删除服务
     * @Date  2021/4/10
     * @Param [carService]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCarServiceById(CarService carService) {
        log.info("要删除的服务id为:{}",carService.getCarServiceId());
        int i = carServiceMapper.deleteById(carService.getCarServiceId());
        boolean b = false;
        if (i>0) b=true;
        log.info("返回值:{}",b);
        return b;
    }
}
