package com.bc.third.party.server.service;

import com.bc.third.party.server.entity.express.kuaidi100.QueryTrackResult;

/**
 * 快递
 *
 * @author zhou
 */
public interface ExpressService {

    /**
     * 根据快递公司编码和快递单号实时请求快递公司单号获取物流轨迹信息
     *
     * @param com 快递公司编码
     * @param num 快递单号
     * @return 物流轨迹信息
     */
    QueryTrackResult getExpressTrack(String com, String num);

}
