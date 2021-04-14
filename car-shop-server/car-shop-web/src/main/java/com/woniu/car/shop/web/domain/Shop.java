package com.woniu.car.shop.web.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 门店信息表
 * </p>
 *
 * @author tsl
 * @since 2021-04-05
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("t_shop")
@ApiModel(value="Shop对象", description="")
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "门店id")
        @TableId(value = "shop_id", type = IdType.AUTO)
      private Integer shopId;

      @ApiModelProperty(value = "门店名称")
      private String shopName;

      @ApiModelProperty(value = "关联门店账号")
      private String shopAccount;

      @ApiModelProperty(value = "账户状态(1已审核0未审核)")
      private Integer shopAccountStart;

      @ApiModelProperty(value = "门店图片")
      private String shopImage;

      @ApiModelProperty(value = "营业时间(json)")
      private String shopTime;

      @ApiModelProperty(value = "信誉积分")
      private Integer shopIntegral;

      @ApiModelProperty(value = "经纬度(json)")
      private String shopLngLat;

      @ApiModelProperty(value = "门店地址")
      private String shopAddress;

      @ApiModelProperty(value = "标签（json存服务标签）")
      private String shopTag;

      @ApiModelProperty(value = "所属类型（0非4s 1是4s店）")
      private Integer shopClass;

      @ApiModelProperty(value = "所属品牌")
      private String shopBrand;

      @ApiModelProperty(value = "总订单数（根据总成交单数计算）")
      private Integer shopOrderNumber;

      @ApiModelProperty(value = "总评分（从成交总评分/总成交数）")
      private Integer shopGrade;

      @ApiModelProperty(value = "提成比例")
      private Double shopProportion;

      @ApiModelProperty(value = "联系电话")
      private Long shopTel;

      @ApiModelProperty(value = "余额")
      private BigDecimal shopBalance;

      @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)           //新增时自动赋值
      private Long gmtCreate;

      @ApiModelProperty(value = "修改时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)    //新增或修改时自动赋值
      private Long gmtModified;

      @ApiModelProperty(value = "逻辑删除")
      @TableLogic
    private Integer deleted;


}
