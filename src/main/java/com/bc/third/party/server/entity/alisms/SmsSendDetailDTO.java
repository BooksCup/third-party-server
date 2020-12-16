package com.bc.third.party.server.entity.alisms;

/**
 * 短信发送明细
 *
 * @author zhou
 */
public class SmsSendDetailDTO {

    private String TemplateCode;
    private String ReceiveDate;
    private String PhoneNum;
    private String Content;
    private String SendStatus;
    private String SendDate;
    private String ErrCode;

    public String getTemplateCode() {
        return TemplateCode;
    }

    public void setTemplateCode(String templateCode) {
        TemplateCode = templateCode;
    }

    public String getReceiveDate() {
        return ReceiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        ReceiveDate = receiveDate;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getSendStatus() {
        return SendStatus;
    }

    public void setSendStatus(String sendStatus) {
        SendStatus = sendStatus;
    }

    public String getSendDate() {
        return SendDate;
    }

    public void setSendDate(String sendDate) {
        SendDate = sendDate;
    }

    public String getErrCode() {
        return ErrCode;
    }

    public void setErrCode(String errCode) {
        ErrCode = errCode;
    }

    @Override
    public String toString() {
        return "SmsSendDetailDTO{" +
                "TemplateCode='" + TemplateCode + '\'' +
                ", ReceiveDate='" + ReceiveDate + '\'' +
                ", PhoneNum='" + PhoneNum + '\'' +
                ", Content='" + Content + '\'' +
                ", SendStatus='" + SendStatus + '\'' +
                ", SendDate='" + SendDate + '\'' +
                ", ErrCode='" + ErrCode + '\'' +
                '}';
    }
}
