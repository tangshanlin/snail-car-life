package com.woniu.car.commons.web.ribbon;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.ribbon.ExtendBalancer;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import com.woniu.car.commons.core.code.ServiceDetailInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Lints
 * @Date 2021/4/6/006 13:09
 * @Description Ribbon自定义权重算法
 * @Since version-1.0
 */

//@Component
@Slf4j
public class MyWeightRibbon extends AbstractLoadBalancerRule {

    //自身的端点信息
    @Resource
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Autowired
    private NacosServiceManager nacosServiceManager;

    private List<Instance> getInstance(List<Instance> instances, ServiceDetailInfo info){
        List<Instance> clusterInstances = instances.stream().filter(x -> {
            if(StringUtils.isNotEmpty(info.getClusterName()) && StringUtils.isNotEmpty(x.getClusterName())) {
                if(info.getClusterName().equals(x.getClusterName())) return true;
            }
            return false;
        }).collect(Collectors.toList());
        instances = clusterInstances != null && clusterInstances.size() > 0 ? clusterInstances:instances;
        return instances;
    }

    private List<Instance> getNeedInstances(ServiceDetailInfo info, NamingService namingService) throws NacosException {
        //判断服务名相同，与自身的组名相同的组的相同集群的实例
        List<Instance> instances = namingService.selectInstances(info.getServerName(),info.getGroupName(),true);
        //过滤出相同集群的节点
        if(CollectionUtils.isEmpty(instances)){
            //判断服务名相同，与默认组的相同集群的实例
            instances=namingService.selectInstances(info.getServerName(),true);
            log.info("{}服务默认组{}集群下面的实例：{}",info.getServerName(),info.getClusterName(),instances);
            return getInstance(instances, info);
        }else{
            log.info("{}服务{}组{}集群下面的实例：{}",info.getServerName(),info.getGroupName(),info.getClusterName(),instances);
            return getInstance(instances, info);
        }
    }
    @Override
    public Server choose(Object o) {

        BaseLoadBalancer loadBalancer = (BaseLoadBalancer) getLoadBalancer();
        //获取需要访问的服务名
        String serverName = loadBalancer.getName();
        //获取需要访问的组名
        String groupName = nacosDiscoveryProperties.getGroup();
        //获取需要访问的集群名
        String clusterName = nacosDiscoveryProperties.getClusterName();

        ServiceDetailInfo info = new ServiceDetailInfo();

        info.setClusterName(clusterName);
        info.setGroupName(groupName);
        info.setServerName(serverName);

        //通过服务名调用权重算法中去多个节点中拉取一个
        NamingService namingService = nacosServiceManager.getNamingService(nacosDiscoveryProperties.getNacosProperties());
//        namingService.getAllInstances()
        //通过权重算法中拉取其中一个服务实例
        List<Instance> needInstances = null;
        try {
            needInstances = getNeedInstances(info,namingService);
        } catch (NacosException e) {
            e.printStackTrace();
            log.warn("NacosRule发生异常", e);
        }
        return new NacosServer(ExtendBalancer.getHostByRandomWeight2(needInstances));
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
    }
}
