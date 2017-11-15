package com.qjkj.qjcsp.web.api.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.goodsmachine.GoodsMachineService;
import com.qjkj.qjcsp.util.RequestData;

import ch.qos.logback.core.joran.spi.ElementSelector;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/api/app")
public class GoodsMachineController {
	public static Logger logger = LoggerFactory.getLogger(GoodsMachineController.class);

	@Autowired
	private GoodsMachineService goodsMachineService;

	@RequestMapping(value = "/getGoodsCommentsWithContentByMachineId", method = RequestMethod.POST)
	public Map<String, Object> getGoodsCommentsWithContentByMachineId(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();

		try {
			String str = RequestData.getRequestPostJson(request);
			JSONObject json = JSONObject.fromObject(str);
			String machineId = json.getString("machineId");
			if (StringUtils.isNotBlank(machineId) && StringUtils.isNotEmpty(machineId)) {

				returnJson = goodsMachineService.getGoodsCommentsWithContentByMachineId(Long.valueOf(machineId));

			} else {
				returnJson.put("returnCode", "0");
				returnJson.put("returnContent", "请求参数错误");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据设备id号获取有评论内容的设备菜品评论出错=" + e.getMessage());
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		returnJson.put("requestMethod", "getGoodsCommentsWithContentByMachineId");
		return returnJson;
	}

	/**
	 * 根据菜品id号和设备id号获取该设备下的菜品明细
	 */
	@RequestMapping(value = "/getGoodsDetailByGoodsId", method = RequestMethod.POST)
	public Map<String, Object> getGoodsDetailByGoodsId(ServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String str = RequestData.getRequestPostJson(request);

			JSONObject json = JSONObject.fromObject(str);
			String machineId = json.getString("machineId");
			String goodsId = json.getString("goodsId");
			if (StringUtils.isNotBlank(machineId) && StringUtils.isNotEmpty(machineId)
					&& StringUtils.isNotBlank(goodsId) && StringUtils.isNotBlank(goodsId)) {
				map = goodsMachineService.getGoodsDetailByGoodsId(machineId, goodsId);

			} else {
				map.put("returnCode", "0");
				map.put("returnContent", "请求参数错误");
			}

		} catch (Exception e) {
			logger.error("请求参数错误", e);
			map.put("returnCode", "0");
			map.put("returnContent", "服务器错误");
		}
		map.put("requestMethod", "getGoodsDetailByGoodsId");
		return map;
	}

	/**
	 * @param request
	 *            json数据 其中有orderNum 用户申请退款返回可退款的订单明细
	 */

	@RequestMapping(value = "/customerOrderRefundDetail", method = RequestMethod.POST)
	public Map<String, Object> customerOrderRefundDetail(ServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String str = RequestData.getRequestPostJson(request);
			JSONObject json = JSONObject.fromObject(str);
			String orderNum = json.getString("orderNum");
			Map<String, Object> returnContent = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(orderNum) && StringUtils.isNotEmpty(orderNum)) {
				returnContent = goodsMachineService.customerOrderRefundDetail(orderNum);
				map.put("returnCode", "1");
				map.put("returnContent", returnContent);
			} else {
				map.put("returnCode", "0");
				map.put("returnContent", "请求参数错误");
			}
		} catch (Exception e) {
			logger.error("请求参数错误", e);
			map.put("returnCode", "0");
			map.put("returnContent", "服务器错误");
		}
		map.put("requestMethod", "customerOrderRefundDetail");
		return map;
	}

	/**
	 * 2.3.10. 根据菜品id号和设备id号获取该设备下有评论内容的菜品评论
	 * 
	 * @author cjw
	 * @date 2016年4月21日
	 * @param machineId
	 * @param goodsId
	 * @return
	 *
	 */
	@RequestMapping(value = "/getGoodsCommentsWithContentByPGM", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getGoodsCommentsWithContentByPGM(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 根据设备id,该设备下的菜品评价分值
		try {
			String str = RequestData.getRequestPostJson(request);
			JSONObject json = JSONObject.fromObject(str);
			String machineId = json.getString("machineId");
			String goodsId = json.getString("goodsId");
			if (StringUtils.isNotBlank(goodsId) && StringUtils.isNotEmpty(goodsId) && StringUtils.isNotBlank(machineId)
					&& StringUtils.isNotEmpty(machineId))

			{

				map = goodsMachineService.getGoodsCommentsWithContentByPGM(Long.valueOf(machineId),
						Long.valueOf(goodsId));
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
		map.put("requestMethod", "getGoodsCommentsWithContentByPGM");
		return map;
	}

	/**
	 * 获取清货
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getGoodsClear", method = RequestMethod.POST)
	public Map<String, Object> getGoodsClear(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getGoodsClear");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = goodsMachineService.getGoodsClear(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}

		return returnJson;
	}

	/**
	 * 2.1.12.未补货
	 */
	@RequestMapping(value = "/getGoodsReplenishment", method = RequestMethod.POST)
	public Map<String, Object> getGoodsReplenishment(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getGoodsReplenishment");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = goodsMachineService.getGoodsReplenishment(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}

		return returnJson;
	}
	/**
	 * 2.1.16.	获取设备餐品对应的格子号
	 */
	@RequestMapping(value = "/getGridNums", method = RequestMethod.POST)
	public Map<String, Object> getGridNums(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getGridNums");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = goodsMachineService.getGridNums(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
	    } catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}
}
