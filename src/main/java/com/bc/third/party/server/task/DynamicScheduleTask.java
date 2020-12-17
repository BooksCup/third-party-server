package com.bc.third.party.server.task;

import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.Cron;
import com.bc.third.party.server.entity.SmsResponse;
import com.bc.third.party.server.entity.alisms.SmsSendDetailDTO;
import com.bc.third.party.server.service.CronService;
import com.bc.third.party.server.service.SmsService;
import com.bc.third.party.server.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 动态配置定时任务
 *
 * @author zhou
 */
@Configuration
@EnableScheduling
public class DynamicScheduleTask implements SchedulingConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(DynamicScheduleTask.class);

    @Resource
    private CronService cronService;

    @Resource
    private SmsService smsService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        List<Cron> cronList = cronService.getCronList();
        for (Cron cron : cronList) {

            Runnable task = () -> {
                logger.info(cron.getName() + " : " + LocalDateTime.now().toLocalTime());
                switch (cron.getServiceType()) {
                    // 获取短信发送记录和发送状态
                    case Constant.CRON_SERVICE_TYPE_QUERY_SMS_SEND_DETAILS:
                        List<SmsResponse> smsResponseList = smsService.getSmsResponseListByState(Constant.SMS_RESPONSE_STATE_PENDING);
                        for (SmsResponse smsResponse : smsResponseList) {
                            String sendDate = TimeUtil.formatSendTime(smsResponse.getCreateTime());
                            List<SmsSendDetailDTO> smsSendDetailDTOList = smsService.querySendDetails(smsResponse.getPhone(),
                                    smsResponse.getBizId(), sendDate, Constant.DEFAULT_PAGE, Constant.DEFAULT_PAGE_SIZE);

                            if (!CollectionUtils.isEmpty(smsSendDetailDTOList)) {
                                SmsSendDetailDTO smsSendDetailDTO = smsSendDetailDTOList.get(0);
                                smsResponse.setSendDate(smsSendDetailDTO.getSendDate());
                                smsResponse.setReceiveDate(smsSendDetailDTO.getReceiveDate());
                                smsResponse.setTemplateCode(smsSendDetailDTO.getTemplateCode());
                                smsResponse.setContent(smsSendDetailDTO.getContent());
                                smsResponse.setStatus(smsSendDetailDTO.getSendStatus());
                                smsResponse.setErrCode(smsSendDetailDTO.getErrCode());
                            }
                            // 更新短信回执处理状态
                            smsResponse.setState(Constant.SMS_RESPONSE_STATE_SOLVED);
                            smsService.updateSmsResponse(smsResponse);
                        }
                        break;
                    default:
                        break;
                }
            };

            Trigger trigger = triggerContext -> {
                // 实时获取定时任务
                Cron realTimeCron = cronService.getCronById(cron.getId());
                CronTrigger cronTrigger = new CronTrigger(realTimeCron.getRule());
                return cronTrigger.nextExecutionTime(triggerContext);
            };

            scheduledTaskRegistrar.addTriggerTask(task, trigger);
        }


    }
}
