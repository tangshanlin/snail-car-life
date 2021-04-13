package com.woniu.car.order.web.controller;


import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.order.web.entity.OrderInvoice;
import com.woniu.car.order.web.service.OrderInvoiceService;
import com.woniu.car.order.web.service.impl.OrderInvoiceServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 订单发票表 前端控制器
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
@RestController
@RequestMapping("/order_invoice")
public class OrderInvoiceController {

    @Resource
    private OrderInvoiceService orderInvoiceService;


}

