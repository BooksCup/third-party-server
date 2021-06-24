package com.bc.third.party.server.entity.mail;

import java.util.List;

/**
 * 邮件实体
 *
 * @author zhou
 */
public class MailEntity {

    private List<String> toList;
    private List<String> ccList;
    private String subject;
    private String text;
    private boolean isTextHtml = false;
    private List<Attachment> attachmentList;

    public List<String> getToList() {
        return toList;
    }

    public void setToList(List<String> toList) {
        this.toList = toList;
    }

    public List<String> getCcList() {
        return ccList;
    }

    public void setCcList(List<String> ccList) {
        this.ccList = ccList;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isTextHtml() {
        return isTextHtml;
    }

    public void setTextHtml(boolean textHtml) {
        isTextHtml = textHtml;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

}