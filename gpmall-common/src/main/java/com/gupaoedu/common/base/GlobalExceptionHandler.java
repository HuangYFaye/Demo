package com.gupaoedu.common.base;

import com.gupaoedu.common.constants.MsgCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huangyifei
 * @version V1.0
 * @ClassName: GlobalExceptionHandler
 * @Description: 实现全局的 Controller 层的异常处理
 * @date 2019/4/8
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 兜底所有异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseDTO<Object> handleException(Exception e) {
        log.error(e.getMessage(),e);
        if (StringUtils.isNotBlank(e.getMessage())) {
            return PageResponDTO.newSuccessInstance(MsgCode.EXCEPTION.getCode(), MsgCode.EXCEPTION.getMsg(), e.getMessage());
        }
        return PageResponDTO.newSuccessInstance(MsgCode.EXCEPTION.getCode(), MsgCode.EXCEPTION.getMsg(), "未知异常，请联系管理员");
    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
//    @ExceptionHandler(BusinessException.class)
//    @ResponseBody
//    AppResponse handleBusinessException(BusinessException e){
//        LOGGER.error(e.getMessage(), e);
//
//        AppResponse response = new AppResponse();
//        response.setFail(e.getMessage());
//        return response;
//    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @return
     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    AppResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
//        LOGGER.error(e.getMessage(), e);
//
//        AppResponse response = new AppResponse();
//        response.setFail(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
//        return response;
//    }

}
