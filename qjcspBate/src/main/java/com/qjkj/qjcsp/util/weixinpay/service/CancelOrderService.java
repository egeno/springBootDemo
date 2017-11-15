package com.qjkj.qjcsp.util.weixinpay.service;

import com.qjkj.qjcsp.util.weixinpay.config.Configure;
import com.qjkj.qjcsp.util.weixinpay.protocol.CancelOrderReqData;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:03
 */
public class CancelOrderService extends BaseService{

    public CancelOrderService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.CANCOL_API);
    }

    /**
     * 请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(CancelOrderReqData cancolOrderReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(cancolOrderReqData);

        return responseString;
    }
}
