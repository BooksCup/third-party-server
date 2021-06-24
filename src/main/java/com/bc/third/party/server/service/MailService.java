package com.bc.third.party.server.service;

import com.bc.third.party.server.entity.mail.MailEntity;

/**
 * 邮件
 *
 * @author zhou
 */
public interface MailService {

    /**
     * 发送带附件的邮件
     *
     * @param mailEntity 邮件实体
     * @throws Exception 异常
     */
    void sendAttachmentMail(MailEntity mailEntity) throws Exception;

}