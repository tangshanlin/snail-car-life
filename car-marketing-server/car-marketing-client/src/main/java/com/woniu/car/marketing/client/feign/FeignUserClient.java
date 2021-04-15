package com.woniu.car.marketing.client.feign;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.user.param.AddScoreParam;
import com.woniu.car.user.web.domain.Score;
import com.woniu.car.user.web.domain.UserInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/14/13:13
 * @Description:
 */
@FeignClient(name = "user-server")
public interface FeignUserClient {

    @PostMapping("/user/score/addScore")
    ResultEntity addScore(@RequestBody AddScoreParam addScoreParam);//积分修改

    @GetMapping("/user/user-information/selectUerInformation")
    ResultEntity<UserInformation> selectUerInformation();//查询积分
}
