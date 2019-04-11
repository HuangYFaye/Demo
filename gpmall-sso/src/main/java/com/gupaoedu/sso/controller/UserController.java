package com.gupaoedu.sso.controller;

import com.gupaoedu.common.annotation.Anoymous;
import com.gupaoedu.common.base.PageResponDTO;
import com.gupaoedu.common.base.ResponseDTO;
import com.gupaoedu.common.constants.GpmallWebConstant;
import com.gupaoedu.sso.controller.support.ResponseData;
import com.gupaoedu.sso.controller.support.ResponseEnum;
import com.gupaoedu.user.IUserCoreService;
import com.gupaoedu.user.constants.ResponseCodeEnum;
import com.gupaoedu.user.dto.UserLoginRequest;
import com.gupaoedu.user.dto.UserLoginResponse;
import com.gupaoedu.user.dto.UserRegisterRequest;
import com.gupaoedu.user.dto.UserRegisterResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author huangyifei
 * @version V1.0
 * @ClassName: UserController
 * @Description: 用户中心控制器
 * @date 2019/4/4
 */

@Api(value = "SSO模块", tags = "SSO模块相关接口")
@Slf4j
@RestController
public class UserController extends BaseController {

    @Autowired
    IUserCoreService userCoreService;

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Anoymous
    @ApiOperation(value = "doLogin", notes = "登录接口")
    @PostMapping("/login")
    public ResponseDTO<UserLoginResponse> doLogin(@RequestBody UserLoginRequest request,
                               HttpServletResponse response) {
        //组装RPC请求
//        UserLoginRequest request = new UserLoginRequest();
//        request.setPassword(password);
//        request.setUserName(username);
        //CALL RPC
        ResponseDTO responseDTO = userCoreService.login(request);

        if (ResponseCodeEnum.SUCCESS.getCode().equals(responseDTO.getMsgCd())) {
            //获取服务返回的业务信息类
            UserLoginResponse userLoginResponse = null;
            userLoginResponse = (UserLoginResponse) responseDTO.getBody();
            //组装返回报文
            response.addHeader("Set-Cookie",
                    "access_token=" + userLoginResponse.getToken() + ";Path=/;HttpOnly");
        } else {
            //记录服务异常日志
            log.info("调用userCoreService.login()异常：{}", responseDTO.getMsgInfo());
        }

        return responseDTO;
    }

    @GetMapping("/register")
    @Anoymous
    public @ResponseBody
    ResponseData register(String username, String password, String mobile) {
        ResponseData data = new ResponseData();

        UserRegisterRequest request = new UserRegisterRequest();
        request.setMobile(mobile);
        request.setUsername(username);
        request.setPassword(password);
        try {
            UserRegisterResponse response = userCoreService.register(request);

            //异步化解耦
            kafkaTemplate.send("test", response.getUid());

            data.setMsg(response.getMsg());
            data.setCode(response.getCode());
        } catch (Exception e) {
            data.setMsg(ResponseEnum.FAILED.getMsg());
            data.setCode(ResponseEnum.FAILED.getCode());
        }
        return data;
    }

}
