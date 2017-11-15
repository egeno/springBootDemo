package com.qjkj.qjcsp.web.api.device;


import com.qjkj.qjcsp.service.machine.ThisMachineOfGoodsService;
import com.qjkj.qjcsp.util.RequestData;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/21.
 */
@RestController
@RequestMapping("/api/device")
public class ThisMachineOfGoodsController {
    @Autowired
    private ThisMachineOfGoodsService thisMachineOfGoodsService;
    @RequestMapping("/getThisMachineOfGoods")
    public Map<String,Object> getThisMachineOfGoods(HttpServletRequest request)
    {
        String postJson= RequestData.getRequestPostJson(request);
        Map<String,Object> returnJson=new HashMap<String, Object>();
        try {
            JSONObject req = JSONObject.fromObject(postJson);
            //设备硬件ID
            String deviceCode = req.getString("deviceCode");
            //调用业务层的getThisMachineOfGoodsService方法
            if(StringUtils.isNotEmpty(deviceCode) ){
                returnJson= thisMachineOfGoodsService.getThisMachineOfGoodsService(req);
                return returnJson;
            }else
            {
            	returnJson.put("requestMethod", "getThisMachineOfGoods");
            	returnJson.put("returnCode", "0");
                returnJson.put("returnContent","参数错误");
            }
        } catch (Exception e) {
        	returnJson.put("requestMethod", "getThisMachineOfGoods");
        	returnJson.put("returnCode", "0");
            returnJson.put("returnContent","参数错误");
            return  returnJson;
        }
        return  returnJson;


    }
}
