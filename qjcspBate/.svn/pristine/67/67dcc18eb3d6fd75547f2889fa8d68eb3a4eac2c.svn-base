package com.qjkj.qjcsp.util.weixinpay.protocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.qjkj.qjcsp.util.weixinpay.config.Configure;
import com.qjkj.qjcsp.util.weixinpay.sign.Signature;
import com.qjkj.qjcsp.util.weixinpay.util.RandomStringGenerator;



/**
 * 请求取消订单需要提交的数据
 */
public class CancelOrderReqData {

    //每个字段具体的意思请查看API文档
    private String appid = "";
    private String mch_id = "";
    private String nonce_str = "";
    private String sign = "";
    private String out_trade_no = "";
    
    public String getAppid() {
		return appid;
	}


	public void setAppid(String appid) {
		this.appid = appid;
	}


	public String getMch_id() {
		return mch_id;
	}


	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}


	public String getNonce_str() {
		return nonce_str;
	}


	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}


	public String getOut_trade_no() {
		return out_trade_no;
	}


	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}


	

	/**
     * @param outTradeNo 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
     */
    public CancelOrderReqData(String outTradeNo){


        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(Configure.getMachineAppID());
        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(Configure.getMachineMchID());

        //商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
        setOut_trade_no(outTradeNo);
        
        //随机字符串，不长于32 位
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());
        setSign(sign);//把签名数据设置到Sign这个属性中

    }


    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
