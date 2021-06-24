package com.bc.third.party.server.service.impl;

import com.bc.third.party.server.entity.mail.Attachment;
import com.bc.third.party.server.entity.mail.MailEntity;
import com.bc.third.party.server.service.MailService;
import com.bc.third.party.server.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * 邮件
 *
 * @author zhou
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    String from;

    @Resource
    JavaMailSender javaMailSender;

    /**
     * 发送带附件的邮件
     *
     * @param mailEntity 邮件实体
     * @throws Exception 异常
     */
    @Override
    public void sendAttachmentMail(MailEntity mailEntity) throws Exception {
        // multipart模式
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // 收件人
        String[] tos = CommonUtil.listToArray(mailEntity.getToList());
        // 抄送人
        String[] ccs = CommonUtil.listToArray(mailEntity.getCcList());

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(tos);
        messageHelper.setCc(ccs);
        messageHelper.setFrom(from);
        messageHelper.setSubject(mailEntity.getSubject());
        messageHelper.setText(mailEntity.getText(), mailEntity.isTextHtml());
        List<Attachment> attachmentList = mailEntity.getAttachmentList();
        if (!CollectionUtils.isEmpty(attachmentList)) {
            for (Attachment attachment : attachmentList) {
                messageHelper.addAttachment(attachment.getFileName(), attachment.getFile());
            }
        }
        // 发送邮件
        javaMailSender.send(mimeMessage);
    }

}