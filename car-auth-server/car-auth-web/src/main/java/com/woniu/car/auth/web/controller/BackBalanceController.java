package com.woniu.car.auth.web.controller;


import com.woniu.car.auth.model.params.BackBalanceParams;
import com.woniu.car.auth.web.entity.BackBalance;
import com.woniu.car.auth.web.service.BackBalanceService;
import com.woniu.car.commons.core.dto.ResultEntity;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author WTY
 * @since 2021-04-13
 */
@RestController
@RequestMapping("/back-balance")
@Api(tags = "平台余额模块")
public class BackBalanceController {
    @Resource
    private BackBalanceService backBalanceService;

    /**
     * 修改平台余额
     * @param bigDecimal
     * @return
     */
    @PutMapping("/update-back-add-balance")
    @ApiOperation(value = "修改后台余额",notes = "通过金额修改后台余额")
//    @ApiImplicitParam(name = "bigDecimal", value = "余额", dataType = "BackBalanceParams", required = true)

    public ResultEntity updateBackAddBalance(@Validated @RequestBody  BackBalanceParams bigDecimal) {
        System.out.println(bigDecimal.getBackBalance());
        if (bigDecimal != null) {
            BackBalance byId = backBalanceService.getById(1);
            byId.setBackBalance(byId.getBackBalance().add(bigDecimal.getBackBalance()));
            boolean update = backBalanceService.updateById(byId);
            if (update) {
                return ResultEntity.buildSuccessEntity().setMessage("修改余额成功");
            }
        }
        return ResultEntity.buildFailEntity().setMessage("修改余额失败");
    }

    /**
     * 查询平台余额
     * @return
     */
    @GetMapping("balance")
    @ApiOperation(value = "查询后台余额", notes = "查询平台余额，无需参数")
    public ResultEntity<BigDecimal> getBackBalance() {
        return ResultEntity.buildSuccessEntity(BigDecimal.class).setData(backBalanceService.getById(1).getBackBalance());
    }
}

