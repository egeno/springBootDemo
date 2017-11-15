package com.qjkj.qjcsp.web.api.wechat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.OrderNum;
import com.qjkj.qjcsp.entity.order.Goods;
import com.qjkj.qjcsp.service.order.app.OrderAppService;
import com.qjkj.qjcsp.service.wechatapi.OrderWeChatService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * 类名:AppController
 * 版本号：V1.0
 * 日期：2016-04-13
 * APP端相关api
 */
@Controller
@RequestMapping("/api/wechat/")
public class OrderWeChatController {

	private static Logger logger = LoggerFactory.getLogger(OrderWeChatController.class);
	@Autowired
	private OrderWeChatService orderWeChatService;	
	
	
	/**
	 * 2.3.14. 根据登录用户用户id和订单状态获取相应条件订单列表
	 * @param req
	 * @return
	 *
	 */
	@RequestMapping(value = "/getOrdersByStatusWX", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOrdersByStatus(ServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String str = RequestData.getRequestPostJson(req);
			JSONObject json = JSONObject.fromObject(str);
			if (json.has("customerId") && json.has("orderStatus")){
				String customerIdstr = json.getString("customerId");// 用户id
				String orderStatus = json.getString("orderStatus");
				if (customerIdstr != null && (!customerIdstr.isEmpty()) && orderStatus != null
						&& (!orderStatus.isEmpty())) {
					int customerId = Integer.valueOf(customerIdstr);// 用户id
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("customerId", customerId);
					map1.put("orderStatus", orderStatus);
					map = orderWeChatService.getOrdersByStatusWX(map1);
				}else {
					map.put("returnCode", "0");
					map.put("returnContent", "请求参数错误");
				}
			} else {
				map.put("returnCode", "0");
				map.put("returnContent", "请求参数错误");
			}

		} catch (Exception e) {
			logger.error("根据登录用户用户id和订单状态获取相应条件订单列表失败：" + e.getMessage());
			e.printStackTrace();
			map.put("returnCode", "0");
			map.put("returnContent", "服务器错误");
		}
		map.put("requestMethod", "getOrdersByStatusWX");
		return map;
	}
	
	/**
	 * 2.3.13. 根据登录用户用户id和订单状态获取订单分状态数量
	 * 
	 * @param req
	 * @return
	 *
	 */
	@RequestMapping(value = "/getOrderCountByStatusWX", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOrderCountByStatusWX(ServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String str = RequestData.getRequestPostJson(req);
			JSONObject json = JSONObject.fromObject(str);
			if (json.has("customerId")){
				String customerIdstr = json.getString("customerId");// 用户id
				if (customerIdstr != null && (!customerIdstr.isEmpty())) {
					int customerId = Integer.valueOf(customerIdstr);// 用户id
					map = orderWeChatService.getOrderCountByStatusWX(customerId);
				} else {
					map.put("requestMethod", "getOrderCountByStatusWX");
					map.put("returnCode", "0");
					map.put("returnContent", "请求参数错误");
					return map;
				}
			}else {
				map.put("requestMethod", "getOrderCountByStatusWX");
				map.put("returnCode", "0");
				map.put("returnContent", "请求参数错误");
				return map;
			}
		} catch (Exception e) {
			logger.error("根据登录用户用户id和订单状态获取订单分状态数量失败：" + e.getMessage());
			e.printStackTrace();
			map.put("requestMethod", "getOrderCountByStatusWX");
			map.put("returnCode", "0");
			map.put("returnContent", "服务器错误");
			return map;
		}
		map.put("requestMethod", "getOrderCountByStatusWX");
		return map;
	}
	
	/**
	 * 2.3.20. 提交订单评价
	 * 
	 * @author yehx
	 * @date 2016年1月4日
	 * @return
	 *
	 */
	@RequestMapping(value = "/commitOrderComment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> commitOrderComment(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("requestMethod", "commitOrderComment");
		try {
			String str = RequestData.getRequestPostJson(req);
			JSONObject json = JSONObject.fromObject(str);
			String orderNum = json.getString("orderNum");// 订单编号
			String customerIdstr = json.getString("customerId");// 用户id
			String orderGrade = json.getString("orderGrade");// 订单评分
			String orderComment = json.getString("orderComment");// 订单评论
			JSONArray array = json.getJSONArray("orderItemCommentList");
			if (orderNum != null && (!orderNum.isEmpty()) && customerIdstr != null && (!customerIdstr.isEmpty())
					&& orderGrade != null && (!orderGrade.isEmpty()) && orderComment != null
					&& (!orderComment.isEmpty()) && array != null && (!array.isEmpty())) {
				// 得到订单明细评论 关于商品的信息
				Long customerId = Long.valueOf(customerIdstr);// 用户id
				List<Goods> goods = (List<Goods>) array.toCollection(array, Goods.class);
				orderWeChatService.commitOrderComment(orderNum, customerId, orderGrade, orderComment, goods);
			} else {
				map.put("returnCode", "0");
				map.put("returnContent", "请求参数错误");
				return map;

			}

		} catch (Exception e) {
			logger.error("订单提交评论出错：" + e.getMessage());
			e.printStackTrace();
			map.put("returnCode", "0");
			map.put("returnContent", "服务器错误");
			return map;

		}
		map.put("returnCode", "1");
		map.put("returnContent", "成功");
		return map;
	}
	
	/**
	 * 2.3.22. 用户订单删除
	 * 
	 * @return
	 *
	 */
	@RequestMapping(value = "/customerOrderDeleteWX", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> customerOrderDeleteWX(ServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("requestMethod", "customerOrderDeleteWX");
		try {
			String str = RequestData.getRequestPostJson(req);
			JSONObject json = JSONObject.fromObject(str);
			String customerIdstr = json.getString("customerId");// 用户id
			JSONArray array = json.getJSONArray("orderNumList");
			// String orderNum=json.getString("orderNum");
			if (customerIdstr != null && (!customerIdstr.isEmpty()) && array != null && (!array.isEmpty())) {
				int customerId = Integer.valueOf(customerIdstr);// 用户id
				List<String> orderNums=new ArrayList<String>();
				for (int i = 0; i < array.size(); i++) {
					String orderNum=JSONObject.fromObject(array.get(i)).getString("orderNum");
					orderNums.add(orderNum);
				}
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("customerId", customerId);
				map1.put("orderNums", orderNums);
				orderWeChatService.customerOrderDeleteWX(map1);
				map.put("returnCode", "1");
				map.put("returnContent", "成功");

			} else {
				map.put("returnCode", "0");
				map.put("returnContent", "请求参数错误");
				return map;
			}

		} catch (Exception e) {
			logger.error("用户订单删除失败：" + e.getMessage());
			e.printStackTrace();
			map.put("returnCode", "0");
			map.put("returnContent", "服务器错误");
			return map;
		}

		return map;

	}
	
	/**
	 * 实时订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getRealTimeOrders", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getRealTimeOrders(ServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getRealTimeOrders");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = orderWeChatService.getRealTimeOrders(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}
}
