package com.woniu.car.service.web.util;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.car.items.model.entity.CarService;
import com.woniu.car.service.web.mapper.CarServiceMapper;
import com.woniu.car.service.web.service.CarServiceService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RegularlyUpdateSold
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/15 16:24
 * @Version 1.0
 */
@Component
public class RegularlyUpdateSold {
    @Resource
    private CarServiceService carServiceService;
    @Resource
    private CarServiceMapper carServiceMapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
//    @Scheduled(fixedRate = 5000)
    @Scheduled(cron = "0 28 01 ? * *")
    public void updateSold(){
        List<CarService> carServices = carServiceService.listCarServiceAll();
//        ArrayList<String> arrayList = new ArrayList<>();
        for (CarService carService : carServices){
//            arrayList.add(String.valueOf(carService.getCarServiceId()));
            String soldlast = (String) redisTemplate.opsForHash().get("car:service:server:updatesold:" + carService.getCarServiceId(), String.valueOf(carService.getCarServiceId()));
            System.out.println("soldlast"+":"+soldlast);
            Integer i = -1;
            if (!ObjectUtils.isEmpty(soldlast)){
                i = Integer.valueOf(soldlast);
            }
            if (i != -1){
                UpdateWrapper<CarService> wrapper = new UpdateWrapper<>();
                wrapper.eq("car_service_id",carService.getCarServiceId());
                CarService service = new CarService();
                service.setCarServiceSold(i);
                int update = carServiceMapper.update(service, wrapper);
                System.out.println(update);
            }

        }

    }
}
