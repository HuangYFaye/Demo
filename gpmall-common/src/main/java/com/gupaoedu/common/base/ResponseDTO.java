package com.gupaoedu.common.base;

import com.gupaoedu.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author huangyifei
 * @version V1.0
 * @ClassName: ResponseDTO
 * @Description: 返回前端 -- 非分页
 * @date 2019/4/4
 */

@Data
@ApiModel(value = "ResponseDTO")
public class ResponseDTO<T> implements Serializable {
    @ApiModelProperty(value = "返回状态：SUCCESS-成功，FAILURE-失败", required = true)
    private String status;

    @ApiModelProperty(value = "返回时间", required = true)
    private String Time;

    @ApiModelProperty(value = "返回码", required = true)
    private String msgCd;

    @ApiModelProperty(value = "返回提示信息")
    private String msgInfo;

    @ApiModelProperty(value = "返回数据")
    private T body;

    public static <T> ResponseDTO<T> newSuccessInstance(String msgCd, String msgInfo, T body) {
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.setStatus("SUCCESS");
        responseDTO.setMsgCd(msgCd);
        responseDTO.setMsgInfo(msgInfo);
        responseDTO.setBody(body);
        responseDTO.setTime(DateUtils.getDate());
        return responseDTO;
    }

    public static <T> ResponseDTO<T> newFailureInstance(String msgCd, String msgInfo, T body) {
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.setStatus("FAILURE");
        responseDTO.setMsgCd(msgCd);
        responseDTO.setMsgInfo(msgInfo);
        responseDTO.setBody(body);
        responseDTO.setTime(DateUtils.getDate());
        return responseDTO;
    }

//    public static <T> ResponseDTO<T> newSuccessInstance(ResponseDTO responseDTO,T body){
//        return newSuccessInstance(responseDTO.getMsgCd(), responseDTO.getMsgInfo(), body);
//    }
}
