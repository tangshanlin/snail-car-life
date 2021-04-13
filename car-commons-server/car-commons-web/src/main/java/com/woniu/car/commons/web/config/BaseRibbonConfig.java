package com.woniu.car.commons.web.config;

import com.woniu.car.commons.web.ribbon.MyWeightRibbon;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClients(defaultConfiguration = MyWeightRibbon.class)
public class BaseRibbonConfig {
}
