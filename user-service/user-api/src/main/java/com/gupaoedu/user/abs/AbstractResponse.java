package com.gupaoedu.user.abs;

import java.io.Serializable;

/**
 * @author huangyifei
 * @version V1.0
 * @ClassName: AbstractResponse
 * @Description: Response抽象类
 * @date 2019/4/4
 */
public abstract class AbstractResponse implements Serializable {

    private static final long serialVersionUID = 7505997295595095971L;
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
