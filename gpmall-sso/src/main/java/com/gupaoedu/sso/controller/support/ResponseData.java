package com.gupaoedu.sso.controller.support;


import com.gupaoedu.user.abs.AbstractResponse;

/**
 * @author huangyifei
 * @version V1.0
 * @ClassName: ResponseData
 * @Description: Response实体类
 * @date 2019/4/4
 */
public class ResponseData extends AbstractResponse {
    
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "code='" + this.getCode() + '\'' +
                ", message='" + this.getMsg() + '\'' +
                ", data=" + data +
                '}';
    }
}
