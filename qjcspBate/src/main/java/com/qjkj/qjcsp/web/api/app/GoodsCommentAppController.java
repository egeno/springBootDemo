package com.qjkj.qjcsp.web.api.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.goods.GoodsCommentService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/**
 * 商品评论显示
 * 
 * @author yehx
 * @date 2016年1月4日 下午8:05:26
 */
@RestController
@RequestMapping("/api/app")
public class GoodsCommentAppController {
	private static Logger logger = LoggerFactory.getLogger(GoodsCommentAppController.class);
	@Autowired
	private GoodsCommentService goodsCommentService;

	/**
	 * 2.3.7 根据设备id号获取有评论内容的设备菜品评论
	 * 
	 * @author yehx
	 * @date 2016年1月4日
	 * @return
	 *
	 */
//	@RequestMapping(value = "/getGoodsCommentsWithContentByMachineId", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> getGoodsCommentsWithContentByMachineId(ServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		// 根据设备id,该设备下的菜品评价分值
//		try {
//			String str = RequestData.getRequestPostJson(request);
//			JSONObject json = JSONObject.fromObject(str);
//			String machineIdstr = json.getString("machineId");
//			if (machineIdstr != null && (!machineIdstr.isEmpty())) {
//				Long machineId = Long.valueOf(machineIdstr);
//				map = goodsCommentService.getGoodsCommentsWithContentByMachineId(machineId);
//			} else {
//				map.put("returnCode", "0");
//				map.put("returnContent", "请求参数错误");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("根据设备id号获取有评论内容的设备菜品评论出错=" + e.getMessage());
//			map.put("returnCode", "0");
//			map.put("returnContent", "服务器错误");
//
//		}
//		map.put("requestMethod", "getGoodsCommentsWithContentByMachineId");
//		return map;
//	}

	/**
	 * 2.3.9. 根据菜品id号和设备id号获取该设备下的菜品评论
	 * 
	 * @author yehx
	 * @date 2016年1月4日
	 * @return
	 *
	 */
	@RequestMapping(value = "/getGoodsCommentsByPGM", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getGoodsCommentsByPGM(ServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 根据设备id,该设备下的菜品评价分值
		try {
			String str = RequestData.getRequestPostJson(request);
			JSONObject json = JSONObject.fromObject(str);
			String machineIdstr = json.getString("machineId");
			String goodsIdstr = json.getString("goodsId");
			if (machineIdstr != null && (!machineIdstr.isEmpty()) && goodsIdstr != null && (!goodsIdstr.isEmpty())) {
				Long machineId = Long.valueOf(machineIdstr);
				Long goodsId = Long.valueOf(goodsIdstr);
				map = goodsCommentService.getGoodsCommentsByPGM(machineId, goodsId);
			} else {
				map.put("returnCode", "0");
				map.put("returnContent", "请求参数错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据菜品id号和设备id号获取该设备下的菜品评论出错=" + e.getMessage());
			map.put("returnCode", "0");
			map.put("returnContent", "服务器错误");
		}
		map.put("requestMethod", "getGoodsCommentsByPGM");
		return map;
	}

	/**
	 * 2.3.10. 根据菜品id号和设备id号获取该设备下有评论内容的菜品评论
	 * 
	 * @author yehx
	 * @date 2016年1月5日
	 * @param machineId
	 * @param goodsId
	 * @return
	 *
	 */
//	@RequestMapping(value = "/getGoodsCommentsWithContentByPGM", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> getGoodsCommentsWithContentByPGM(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		// 根据设备id,该设备下的菜品评价分值
//		try {
//			String str = RequestData.getRequestPostJson(request);
//			JSONObject json = JSONObject.fromObject(str);
//			String machineIdstr = json.getString("machineId");
//			String goodsIdstr = json.getString("goodsId");
//			if (machineIdstr != null && machineIdstr.isEmpty() && goodsIdstr != null && (!goodsIdstr.isEmpty())) {
//
//				int machineId = Integer.valueOf(machineIdstr);
//				int goodsId = Integer.valueOf(goodsIdstr);
//				map = goodsCommentService.getGoodsCommentsWithContentByPGM(machineId, goodsId);
//			} else {
//				map.put("returnCode", "0");
//				map.put("returnContent", "请求参数错误");
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("根据菜品id号和设备id号获取该设备下的菜品评论出错=" + e.getMessage());
//			map.put("returnCode", "0");
//			map.put("returnContent", "服务器错误");
//
//		}
//		map.put("requestMethod", "getGoodsCommentsWithContentByPGM");
//		return map;
//	}
	@RequestMapping("/getGoodsDelivery")
	public Map<String,Object> getGoodsDelivery(HttpServletRequest request){
		String requestJson=RequestData.getRequestPostJson(request); 
		JSONObject json=JSONObject.fromObject(requestJson);
		return goodsCommentService.getGoodsDelivery(json);
	}
}
