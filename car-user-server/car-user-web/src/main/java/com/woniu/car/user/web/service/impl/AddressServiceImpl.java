package com.woniu.car.user.web.service.impl;

import com.woniu.car.user.web.domain.Address;
import com.woniu.car.user.web.mapper.AddressMapper;
import com.woniu.car.user.web.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 地址表 服务实现类
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-08
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

}
