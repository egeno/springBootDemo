package com.qjkj.qjcsp.web.api.app;


import com.alibaba.druid.util.StringUtils;
import com.qjkj.qjcsp.service.order.api.OrderDetailService;
import com.qjkj.qjcsp.util.RequestData;
import net.sf.json.JSONObject;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/13.
 * 根据订单ID获取订单明细
 */
@RestController
@RequestMapping("/api/app")
public class OrderDetailController {
    public static Logger logger = LoggerFactory.getLogger(OrderDetailController.class);

    @Autowired
    private OrderDetailService orderDetailService;
    
    @RequestMapping(value = "/getOrderDetail", method = RequestMethod.POST)
    public Map<String, Object> getOrderDetail(HttpServletRequest req) {
        Map<String, Object> returnJson = new HashMap<String, Object>();
        try {
            String str = RequestData.getRequestPostJson(req);
            JSONObject json = JSONObject.fromObject(str);
            String orderNum = json.getString("orderNum");
            if (!StringUtils.isEmpty(orderNum)) {
                Map<String,Object> returnMap=orderDetailService.getOrderDetail(orderNum);
                if (returnMap.size()>0)
                {
                    returnJson.put("returnCode", "1");
                    returnJson.put("returnContent", returnMap);
                }else
                {
                    returnJson.put("returnCode", "2");
                    returnJson.put("returnContent", "该订单编号不存在");
                }
            }else
            {
                returnJson.put("returnCode", "0");
                returnJson.put("returnContent", "请求参数错误");
            }
        } catch (Exception e) {
        	
            returnJson.put("returnCode", "0");
            returnJson.put("returnContent", "服务器错误");
            return returnJson;
        }
        return returnJson;
    }
    
    
    /**
	 * 根据APP用户id返回用户详细信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getCustomerDetail", method = RequestMethod.POST)
	public Map<String, Object> getCustomerDetail(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "getCustomerDetail");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String customerId = res.getString("customerId");
			if (customerId != null && !customerId.isEmpty()&&NumberUtils.isNumber(customerId)) {
				
				Map<String, Object> returnData = orderDetailService.getCustomerDetail(customerId);

				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			} else {
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}
		} catch (Exception e) {
			logger.error("发送验证码异常", e);
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;

	}

}
