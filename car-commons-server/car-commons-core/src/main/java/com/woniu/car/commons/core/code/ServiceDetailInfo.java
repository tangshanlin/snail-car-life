package com.woniu.car.commons.core.code;

import lombok.Data;

/**
 * @Author Lints
 * @Date 2021/4/6/006 11:53
 * @Description  主要针对ribbon做的权重算法参数
 * @Since version-1.0
 */
@Data
public class ServiceDetailInfo {
    /**
     * 服务名
     */
    private String serverName;

    /**
     * 组名
     */
    private String groupName;
    /**
     * 集群名
     */
    private String clusterName;


}
