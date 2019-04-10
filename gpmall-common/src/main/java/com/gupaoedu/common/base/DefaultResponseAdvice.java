package com.gupaoedu.common.base;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.common.constants.MsgCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.File;

/**
 * @author huangyifei
 * @version V1.0
 * @ClassName: DefaultResponseAdvice
 * @Description: 用于统一处理Controller的返回格式
 * @date 2019/4/8
 */

@Slf4j
@ControllerAdvice
public class DefaultResponseAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 哪些方法需要拦截
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        log.debug("DefaultResponseAdvice==>supports:" + aClass);
        log.debug("DefaultResponseAdvice==>supports:" + methodParameter.getClass());
        log.debug("DefaultResponseAdvice==>supports:"
                + MappingJackson2HttpMessageConverter.class.isAssignableFrom(aClass));
        return true;
    }

    /**
     * 拦截后做什么
     *
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.debug("请示返回的数据类型class={}", o.getClass().getName());
        if (null == o || o instanceof PageResponDTO || o instanceof ResponseDTO || o instanceof File) {
            return o;
        } else if (o instanceof String) {
            Object obj = PageResponDTO.newSuccessInstance(MsgCode.SUCCESS.getCode(), MsgCode.SUCCESS.getMsg(), o);
            String result = JSON.toJSONString(obj);
            return result;
        } else {
            return PageResponDTO.newSuccessInstance(MsgCode.SUCCESS.getCode(), MsgCode.SUCCESS.getMsg(), o);
        }
    }
}
