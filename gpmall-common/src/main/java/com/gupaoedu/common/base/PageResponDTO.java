package com.gupaoedu.common.base;

import com.gupaoedu.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huangyifei
 * @version V1.0
 * @ClassName: ResponseDTO
 * @Description: 返回前端 -- 分页
 * @date 2019/4/4
 */

@Data
@ApiModel(value = "PageResponDTO")
public class PageResponDTO<T> extends ResponseDTO<T> {

    @ApiModelProperty(value = "总记录数")
    private long totalCount;

    @ApiModelProperty(value = "总页数")
    private int pages;

    public static <T> PageResponDTO<T> newSuccessInstance(String msgCd, String msgInfo, T body, long totalCount, int pages) {

        PageResponDTO<T> pageResponDTO = new PageResponDTO<>();
        if (pages >= 0) {
            pageResponDTO.setPages(pages);
        }
        if (totalCount >= 0) {
            pageResponDTO.setTotalCount(totalCount);
        }
        pageResponDTO.setStatus("SUCCESS");
        pageResponDTO.setMsgCd(msgCd);
        pageResponDTO.setMsgInfo(msgInfo);
        pageResponDTO.setBody(body);
        pageResponDTO.setTime(DateUtils.getDateTime());

        return pageResponDTO;
    }

    public static <T> ResponseDTO<T> newSuccessInstance(String msgCd, String msgInfo, T body) {
        return newSuccessInstance(msgCd, msgInfo, body, 0, 0);
    }

//    public static <NoBody> ResponseDTO<NoBody> newSuccessInstance() {
//        return newSuccessInstance(null, 0, 0L);
//    }
}
