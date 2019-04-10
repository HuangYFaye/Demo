package com.gupaoedu.sso.controller;

/**
 * @author huangyifei
 * @version V1.0
 * @ClassName: BaseController
 * @Description: Controller父类
 * @date 2019/4/4
 */
public class BaseController {

    //线程安全
    static ThreadLocal<String> uidThreadLocal = new ThreadLocal<>();

    public void setUid(String uid) {
        uidThreadLocal.set(uid);
    }

    public String getUid() {
        return uidThreadLocal.get();
    }

}
