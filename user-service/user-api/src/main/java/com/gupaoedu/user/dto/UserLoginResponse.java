package com.gupaoedu.user.dto;

import com.gupaoedu.common.base.ResponseDTO;
import com.gupaoedu.user.abs.AbstractResponse;
import lombok.Data;

import java.io.Serializable;

@Data
    public class UserLoginResponse implements Serializable{
    private static final long serialVersionUID = -4339900472381840119L;

    private Integer uid;
    private String avatar;
    private String mobile;

    private String token;

    @Override
    public String toString() {
        return "UserLoginResponse{" +
                "uid=" + uid +
                ", avatar='" + avatar + '\'' +
                ", mobile='" + mobile + '\'' +
                ", token='" + token + '\'' +
                "} " + super.toString();
    }
}
