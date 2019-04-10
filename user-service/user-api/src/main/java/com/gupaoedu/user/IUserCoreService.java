package com.gupaoedu.user;

import com.gupaoedu.common.base.ResponseDTO;
import com.gupaoedu.user.dto.*;

public interface IUserCoreService {

    /**
     * 用户登录
     * @param request
     * @return
     */
    ResponseDTO<UserLoginResponse> login(UserLoginRequest request);


    /**
     * 校验过程
     * @param request
     * @return
     */
    CheckAuthResponse validToken(CheckAuthRequest request);


    /*
     * 注册
     */
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);
}
