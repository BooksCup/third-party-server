package com.bc.third.party.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.bc.third.party.server.cons.Constant;
import com.bc.third.party.server.entity.SystemConfig;
import com.bc.third.party.server.entity.config.Kuaidi100Config;
import com.bc.third.party.server.entity.express.kuaidi100.QueryTrackParam;
import com.bc.third.party.server.entity.express.kuaidi100.QueryTrackResult;
import com.bc.third.party.server.mapper.SystemConfigMapper;
import com.bc.third.party.server.service.ExpressService;
import com.bc.third.party.server.utils.HttpUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLEncoder;

/**
 * 快递
 *
 * @author zhou
 */
@Service("expressService")
public class ExpressServiceImpl implements ExpressService {

    @Resource
    private SystemConfigMapper systemConfigMapper;

    /**
     * 根据快递公司编码和快递单号实时请求快递公司单号获取物流轨迹信息
     *
     * @param com 快递公司编码
     * @param num 快递单号
     * @return 物流轨迹信息
     */
    @Override
    public QueryTrackResult getExpressTrack(String com, String num) {
        QueryTrackResult queryTrackResult;
        SystemConfig systemConfig = systemConfigMapper.getSystemConfig(Constant.CONFIG_KEY_KUAIDI100);
        Kuaidi100Config kuaidi100Config = JSON.parseObject(systemConfig.getValue(), Kuaidi100Config.class);
        String key = kuaidi100Config.getKey();
        String customer = kuaidi100Config.getCustomer();
        QueryTrackParam queryTrackParam = new QueryTrackParam();
        queryTrackParam.setCom(com);
        queryTrackParam.setNum(num);
        queryTrackParam.setFrom("");
        queryTrackParam.setTo("");
        queryTrackParam.setResultv2("0");
        queryTrackParam.setShow("0");
        queryTrackParam.setOrder("desc");
        String param = JSON.toJSONString(queryTrackParam);
        try {
            String sign = DigestUtils.md5Hex(param + key + customer).toUpperCase();
            param = URLEncoder.encode(param, "UTF-8");
            String url = "http://poll.kuaidi100.com/poll/query.do?customer=" + customer + "&sign=" + sign
                    + "&param=" + param;
            String result = HttpUtil.doGet(url);
            queryTrackResult = JSON.parseObject(result, QueryTrackResult.class);
        } catch (Exception e) {
            e.printStackTrace();
            queryTrackResult = null;
        }
        return queryTrackResult;
    }

}
