package com.bc.third.party.server.controller.mail;

import com.bc.third.party.server.entity.mail.Attachment;
import com.bc.third.party.server.entity.mail.MailEntity;
import com.bc.third.party.server.enums.ResponseMsg;
import com.bc.third.party.server.service.MailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 邮件
 *
 * @author zhou
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @Resource
    MailService mailService;

    /**
     * 发送带附件的邮件
     *
     * @param to      接收人
     * @param subject 主题
     * @param text    正文
     * @return 邮件发送结果
     */
    @ApiOperation(value = "发送带附件的邮件", notes = "发送带附件的邮件")
    @PostMapping(value = "")
    public ResponseEntity<String> sendAttachmentMail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String text) {
        ResponseEntity<String> responseEntity;
        try {
            MailEntity mailEntity = new MailEntity();
            mailEntity.setText(text);
            mailEntity.setTextHtml(false);
            mailEntity.setSubject(subject);
            List<String> toList = new ArrayList<>();
            toList.add(to);
            mailEntity.setToList(toList);
            List<Attachment> attachmentList = new ArrayList<>();
            Attachment attachment = new Attachment();
            String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            File file = new File(filePath + "test.xlsx");
            attachment.setFile(file);
            attachment.setFileName("测试.xlsx");
            attachmentList.add(attachment);
            mailEntity.setAttachmentList(attachmentList);
            mailService.sendAttachmentMail(mailEntity);
            responseEntity = new ResponseEntity<>(
                    ResponseMsg.SEND_MAIL_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(
                    ResponseMsg.SEND_MAIL_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}