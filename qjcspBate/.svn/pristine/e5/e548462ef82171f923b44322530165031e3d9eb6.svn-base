package com.qjkj.qjcsp.util.weixinpay.service;

import com.qjkj.qjcsp.util.weixinpay.config.Configure;
import com.qjkj.qjcsp.util.weixinpay.protocol.RefundData;

public class RefundService extends BaseService{

	public RefundService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		super(Configure.REFUND_API);
		// TODO Auto-generated constructor stub
	}
	/**
     * 请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(RefundData refundData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
    	//System.out.println(refundData.toString());
        String responseString = sendPost(refundData);
    
        return responseString;
    }
}
