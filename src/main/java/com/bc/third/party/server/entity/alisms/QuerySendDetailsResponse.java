package com.bc.third.party.server.entity.alisms;

import java.util.List;

/**
 * 短信发送记录和发送状态
 *
 * @author zhou
 */
public class QuerySendDetailsResponse {

    private Integer TotalCount;
    private String Message;
    private String RequestId;
    private String Code;
    private List<SmsSendDetailDTO> SmsSendDetailDTOs;

    public Integer getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(Integer totalCount) {
        TotalCount = totalCount;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public List<SmsSendDetailDTO> getSmsSendDetailDTOs() {
        return SmsSendDetailDTOs;
    }

    public void setSmsSendDetailDTOs(List<SmsSendDetailDTO> smsSendDetailDTOs) {
        SmsSendDetailDTOs = smsSendDetailDTOs;
    }

}
