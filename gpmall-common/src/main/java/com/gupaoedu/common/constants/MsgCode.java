package com.gupaoedu.common.constants;

import lombok.Getter;

/**
 * @author huangyifei
 * @version V1.0
 * @ClassName: MsgCode
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/4/8
 */

public enum MsgCode {
    /**
     * 成功
     */
//    public final static String SUCCESS = "0000000";
    SUCCESS("000000","成功"),

    /**
     * 失败
     */
    FAILURE("000001","失败"),

    /**
     * 异常
     */
    EXCEPTION("000002","异常");

    @Getter
    private String code;

    @Getter
    private String msg;

    MsgCode(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }

}
