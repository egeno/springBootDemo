package com.qjkj.qjcsp.service.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.GoodsCommentInfo;
import com.qjkj.qjcsp.entity.order.OrderDeviceEvaluateRes;
import com.qjkj.qjcsp.mapper.BasicsGoodsMapper;
import com.qjkj.qjcsp.mapper.BasicsUserMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsUsersMapper;
import com.qjkj.qjcsp.mapper.GoodsCommentMapper;

import net.sf.json.JSONObject;

/**
 * 商品评论显示
 * 
 * @author yehx
 * @date 2016年1月4日 下午8:26:16
 */
@Controller
public class GoodsCommentService {
	private Logger logger = LoggerFactory.getLogger(GoodsCommentService.class);
	@Autowired
	private GoodsCommentMapper goodsCommentMapper;
	@Autowired
	private BasicsUsersMapper basicsUsersMapper;
	@Autowired
	private BasicsGoodsMapper basicsGoodsMapper;
	@Autowired
	private BasicsUserMachineMapper basicsUserMachineMapper;

	// 2.3.7根据设备id号获取有评论内容的设备菜品评论
	@Transactional
	public Map<String, Object> getGoodsCommentsWithContentByMachineId(Long machineId) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 根据设备id得到菜品评价分值
		Long comGrade = goodsCommentMapper.getMachineGoodsAvgSore(machineId);
		// 得到这个设备下的所有商品的评分信息
		List<Map<String, Object>> goodsCommentList = goodsCommentMapper
				.getGoodsCommentsWithContentByMachineId(machineId);
		OrderDeviceEvaluateRes res = new OrderDeviceEvaluateRes();
		res.setComGrade(String.valueOf(comGrade));
		res.setGoodsCommentList(goodsCommentList);
		map.put("returnCode", "1");
		map.put("returnContent", res);
		return map;

	}

	// //2.3.9根据菜品id号和设备id号获取该设备下的菜品评论
	@Transactional
	public Map<String, Object> getGoodsCommentsByPGM(Long machineId, Long goodsId) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("machineId", machineId);
		map1.put("goodsId", goodsId);
		Map<String, Object> map = new HashMap<String, Object>();
		// 根据设备id和商品id得到商品评价数量
		Long gradeCount = goodsCommentMapper.getMachineGoodsGradeCount(map1);
		// 得到这个设备下的所有商品的评分信息
		List<Map<String, Object>> goodsCommentList = goodsCommentMapper.getGoodsCommentsWithMachineIdGoodsId(map1);
		OrderDeviceEvaluateRes res = new OrderDeviceEvaluateRes();
		res.setGradeCount(String.valueOf(gradeCount));
		res.setGoodsCommentList(goodsCommentList);
		map.put("returnCode", "1");
		map.put("returnContent", res);
		return map;

	}

	//
	// //2.3.10. 根据菜品id号和设备id号获取该设备下有评论内容的菜品评论
	@Transactional
	public Map<String, Object> getGoodsCommentsWithContentByPGM(int machineId, int goodsId) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("machineId", machineId);
		map1.put("goodsId", goodsId);
		Map<String, Object> map = new HashMap<String, Object>();
		// 根据设备id和商品id得到商品评价数量
		Long gradeCount = goodsCommentMapper.getMachineGoodsGradeCountHasComment(map1);
		// 得到这个设备下的所有商品的评分信息
		List<Map<String, Object>> goodsCommentList = goodsCommentMapper
				.getGoodsCommentsWithMachineIdGoodsIdHasComment(map1);
		OrderDeviceEvaluateRes res = new OrderDeviceEvaluateRes();
		res.setGradeCount(String.valueOf(gradeCount));
		res.setGoodsCommentList(goodsCommentList);
		map.put("returnCode", "1");
		map.put("returnContent", res);
		return map;

	}

	public Map<String, Object> getGoodsDelivery(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		try {
			String userId = res.getString("userId");
			String specialRroleNum = res.getString("specialRoleNum");
			String timeType = null;
			if (res.has("timeType"))
				timeType = res.getString("timeType");
			if (StringUtils.isNoneBlank(userId, specialRroleNum)) {
				BasicsUsers basicsUsers = basicsUsersMapper.selectByPrimaryKey(Long.valueOf(userId));
				Long companyId = basicsUsers.getCompanyId();
				/* 商户查看的 */
				if (specialRroleNum.equals("3")) {
					List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
					Map<String, Object> user = new HashMap<String, Object>();
					user.put("merchantName", basicsUsers.getUserName());
					user.put("merchantAddress", basicsUsers.getUserMemo());
					List<Map<String, Object>> goodsList = basicsGoodsMapper
							.findAllReplementByMerchantNameAndCompanyId(basicsUsers.getUserName(), companyId, timeType);
					user.put("goodsList", goodsList);
					users.add(user);
					returnContent.put("returnCode", "1");
					returnContent.put("returnContent", users);
				}
				/* 维修人员和补货人员 */
				else if (specialRroleNum.equals("1") || specialRroleNum.equals("2")) {
					List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
					List<Map<String, Object>> machines = basicsUserMachineMapper
							.findMachineUserByUserId(Long.valueOf(userId));
					if (machines != null && machines.size() > 0) {
						List<Map<String, Object>> userNames = basicsUserMachineMapper
								.findmerchantNameListByMachineIds(machines);
						for (Map<String, Object> map : userNames) {
							String userName = map.get("goodsName").toString();
							BasicsUsers baUser = basicsUsersMapper.findAllUserByUserName(userName);
							if (baUser != null) {
								Map<String, Object> user = new HashMap<String, Object>();
								user.put("merchantName", baUser.getUserName());
								user.put("merchantAddress", baUser.getUserMemo());
								List<Map<String, Object>> goodsList = basicsGoodsMapper
										.findAllReplementByMerchantNameAndCompanyId(userName, baUser.getCompanyId(),
												timeType);
								user.put("goodsList", goodsList);
								users.add(user);
							}
						}
					}
					returnContent.put("returnCode", "1");
					returnContent.put("returnContent", users);
				}
				/* 众包者查看的 */
				else if (specialRroleNum.equals("4")) {
					List<Map<String, Object>> users = basicsUsersMapper.findAllUserByCompanyId(companyId);
					for (Map<String, Object> map : users) {
						String merchantName = map.get("merchantName").toString();
						List<Map<String, Object>> goodsList = basicsGoodsMapper
								.findAllReplementByMerchantNameAndCompanyId(merchantName, companyId, timeType);
						map.put("goodsList", goodsList);
					}
					returnContent.put("returnCode", "1");
					returnContent.put("returnContent", users);
				}
			} else {
				returnContent.put("returnCode", "0");
				returnContent.put("returnContent", "请求参数异常");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "服务器异常");
		}
		return returnContent;
	}

}
