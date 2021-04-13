package com.woniu.car.config;

import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.Arrays;
import java.util.List;


@Component("ResourceProvider")
public class ResourceProvider implements SwaggerResourcesProvider {

	/**
	 * @Author Lints
	 * @Date 2021/4/6/006 13:17
	 * @Description 提供swagger的公共接口配置源
	 * @Param []
	 * @Return java.util.List<springfox.documentation.swagger.web.SwaggerResource>
	 * @Since version-1.0
	 */

	@Override
	public List<SwaggerResource> get() {
		return Arrays.asList(
				createSwaggerResource("用户服务","/user/v2/api-docs","2.0"),
				createSwaggerResource("信息服务","/message/v2/api-docs","2.0"),
				createSwaggerResource("认证服务","/auth/v2/api-docs","2.0"),
				createSwaggerResource("订单服务","/order/v2/api-docs","2.0"),
				createSwaggerResource("门店服务","/shop/v2/api-docs","2.0"),
				createSwaggerResource("汽车服务","/service/v2/api-docs","2.0"),
				createSwaggerResource("充电服务","/station/v2/api-docs","2.0"),
				createSwaggerResource("促销服务","/marketing/v2/api-docs","2.0"),
				createSwaggerResource("商品服务","/product/v2/api-docs","2.0")
		);
	}
	private SwaggerResource createSwaggerResource(String name,String url,String version) {
		SwaggerResource sr = new SwaggerResource();
		sr.setName(name);
		sr.setLocation(url);
		sr.setSwaggerVersion(version);
		return sr;
	}

}
