package com.qjkj.qjcsp.web.api.device;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.code.APPQrcodePicturesService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
/**
 * 二维码
 * @author Administrator
 *
 */
@RequestMapping("api/device")
@RestController
public class APPQrcodePicturesController {

	@Autowired
	private APPQrcodePicturesService APPQrcodePicturesService;
	
	@RequestMapping("/getAPPQrcodePictures")
	public Map<String,Object> getAPPQrcodePictures(HttpServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getAPPQrcodePictures");
		try {
			Map<String, Object> returnData =APPQrcodePicturesService.findQrcodePictures();
			returnJson.put("returnCode", "1");
			returnJson.put("returnContent", returnData);
		} catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}
}
