package com.qjkj.qjcsp.service.wechatapi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.TblCustomer;
import com.qjkj.qjcsp.entity.TblGoodsEvaluate;
import com.qjkj.qjcsp.entity.TblGoodsIngredients;
import com.qjkj.qjcsp.entity.TblGoodsIngredientsType;
import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.entity.TblOrderChild;
import com.qjkj.qjcsp.entity.TblOrderDetail;
import com.qjkj.qjcsp.mapper.BasicsGoodsMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.TblCustomerMapper;
import com.qjkj.qjcsp.mapper.TblGoodsEvaluateMapper;
import com.qjkj.qjcsp.mapper.TblGoodsIngredientsMapper;
import com.qjkj.qjcsp.mapper.TblGoodsIngredientsTypeMapper;
import com.qjkj.qjcsp.mapper.TblIssueMapper;
import com.qjkj.qjcsp.mapper.TblOrderChildMapper;
import com.qjkj.qjcsp.mapper.TblOrderDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.util.CheckWordsUtil;
import com.qjkj.qjcsp.util.EvaluateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class GoodsEvaluateWeChatService {
	@Autowired
	private TblGoodsEvaluateMapper tblGoodsEvaluateMapper;
	@Autowired
	private TblOrderMapper tblOrderMapper;
	@Autowired
	private TblOrderDetailMapper tblOrderDetailMapper;
	@Autowired
	private TblOrderChildMapper tblOrderChildMapper;
	@Autowired
	private BasicsGoodsMapper basicsGoodsMapper;
	@Autowired
	private TblIssueMapper tblIssueMapper;
	@Autowired
	private TblGoodsIngredientsTypeMapper tblGoodsIngredientsTypeMapper;
	@Autowired
	private TblGoodsIngredientsMapper tblGoodsIngredientsMapper;
	@Autowired
	private TblCustomerMapper tblCustomerMapper;
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	

	public Map<String, Object> getSayEvaluationGoodsWX(String orderNum) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> tblOrderChildlistmapList = new ArrayList<Map<String, Object>>();
		TblOrder tblOrder = tblOrderMapper.searchOrder(orderNum);
		map.put("orderId", tblOrder.getOrderId());
		map.put("machineId", tblOrder.getMachineId());
		List<TblOrderChild> tblOrderChildlist = tblOrderChildMapper.getOrderChildListByOrderId(tblOrder.getOrderId());
		for (TblOrderChild tblOrderChild : tblOrderChildlist) {
			List<Map<String, Object>> tblOrderDetailmapList = new ArrayList<Map<String, Object>>();
			Map<String, Object> tblOrderChildlistmap = new HashMap<String, Object>();
			tblOrderChildlistmap.put("orderChildId", tblOrderChild.getOrderChildId());
			List<TblOrderDetail> tblOrderDetailList = tblOrderDetailMapper
					.selectByOrderChildId(tblOrderChild.getOrderChildId());
			for (TblOrderDetail tblOrderDetail : tblOrderDetailList) {
				Map<String, Object> tblOrderDetailmap = new HashMap<String, Object>();
				tblOrderDetailmap.put("orderDetailId", tblOrderDetail.getOrderDetailId());
				tblOrderDetailmap.put("goodsId", tblOrderDetail.getGoodsId());
				tblOrderDetailmap.put("goodsName", tblOrderDetail.getGoodsName());
				tblOrderDetailmapList.add(tblOrderDetailmap);
			}
			tblOrderChildlistmap.put("orderDetails", tblOrderDetailmapList);
			tblOrderChildlistmapList.add(tblOrderChildlistmap);
		}
		map.put("orderChilds", tblOrderChildlistmapList);
		returnJson.put("returnCode", "1");
		returnJson.put("returnContent", map);
		return returnJson;
	}

	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> commitEvaluationGoodsWX(JSONObject res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String orderNum = null;
		if (res.has("orderNum")) {
			orderNum = res.getString("orderNum");
		}
		String machineId = null;
		if (res.has("machineId")) {
			machineId = res.getString("machineId");
		}
		String orderChilds = null;
		if (res.has("orderChilds")) {
			orderChilds = res.getString("orderChilds");
		}
		if (StringUtils.isNoneBlank(orderNum, machineId, orderChilds)) {
			long orderId = tblOrderMapper.getOrderIdbyOrderNum(orderNum);
			int count=tblGoodsEvaluateMapper.getCountByOrderId(orderId);
			if(count>0){
				returnData.put("returnCode", "2");
				returnData.put("returnContent", "该订单已经评价");
				return returnData;
			}
			JSONArray orderChilditems = res.getJSONArray("orderChilds");
			/* 遍历取出的子订单列表 */
			for (int i = 0; i < orderChilditems.size(); i++) {
				JSONObject orderChilditem = orderChilditems.getJSONObject(i);
				Long orderChildId = orderChilditem.getLong("orderChildId");
				JSONArray orderDetails = orderChilditem.getJSONArray("orderDetails");
				for (int j = 0; j < orderDetails.size(); j++) {
					TblGoodsEvaluate tge = new TblGoodsEvaluate();
					JSONObject orderDetailitem = orderDetails.getJSONObject(j);
					if(CheckWordsUtil.checkWord(orderDetailitem.getString("goodsEvaluateContent"))==0){
						throw new RuntimeException("0");
					}
					if(CheckWordsUtil.checkWord(orderDetailitem.getString("goodsEvaluateContent"))==1){
						throw new RuntimeException("1");
					}
					String GoodsEvaluateScore = orderDetailitem.getString("goodsEvaluateScore");
					Short Scoreshort = new Short(GoodsEvaluateScore);
					tge.setOrderChildId(orderChildId);
					tge.setGoodsEvaluateContent(orderDetailitem.getString("goodsEvaluateContent"));
					tge.setGoodsEvaluateScore(Scoreshort);
					tge.setGoodsId(orderDetailitem.getLong("goodsId"));
					tge.setOrderDetailId(orderDetailitem.getLong("orderDetailId"));
					tge.setMachineId(Long.valueOf(machineId));
					tge.setOrderId(Long.valueOf(orderId));
					tge.setCreateTime(new Date());
					tblGoodsEvaluateMapper.insertSelective(tge);
				}

			}
			returnData.put("returnCode", "1");
			returnData.put("returnContent", "成功");
		} else {
			returnData.put("returnCode", "2");
			returnData.put("returnContent", "请求参数错误");
		}
		return returnData;
	}

	public Map<String, Object> getEvaluationDetailsWX(JSONObject res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String orderNum = null;
		if (res.has("orderNum")) {
			orderNum = res.getString("orderNum");
		}
		if (StringUtils.isNoneBlank(orderNum)) {
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			long orderId = tblOrderMapper.getOrderIdbyOrderNum(orderNum);
			List<TblGoodsEvaluate> TblGoodsEvaluateList = tblGoodsEvaluateMapper.selectByorderId(Long.valueOf(orderId));
			int size = TblGoodsEvaluateList.size();
			for (int i = 0; i < size; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				BasicsGoods basicsGoods = basicsGoodsMapper
						.selectByPrimaryKey(TblGoodsEvaluateList.get(i).getGoodsId());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(TblGoodsEvaluateList.get(i).getCreateTime());
				map.put("goodsEvaluateContent", TblGoodsEvaluateList.get(i).getGoodsEvaluateContent());
				map.put("goodsEvaluateScore", TblGoodsEvaluateList.get(i).getGoodsEvaluateScore());
				map.put("evaluateType", TblGoodsEvaluateList.get(i).getIsverify());
				map.put("evaluateTime", dateString);
				map.put("goodsName", basicsGoods.getGoodsName());
				mapList.add(map);
			}
			returnData.put("returnCode", "1");
			returnData.put("returnContent", mapList);
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
		}
		return returnData;
	}

	public Map<String, Object> getGoodsDetailsWX(JSONObject res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String machineId = null;
		if (res.has("machineId")) {
			machineId = res.getString("machineId");
		}
		String selectDate = null;
		if (res.has("selectDate")) {
			selectDate = res.getString("selectDate");
		}
		String areaModelId = null;
		if (res.has("areaModelId")) {
			areaModelId = res.getString("areaModelId");
		}
		String goodsId = null;
		if (res.has("goodsId")) {
			goodsId = res.getString("goodsId");
		}
		String orderType = null;
		if (res.has("orderType")) {
			orderType = res.getString("orderType");
		}
		String pageCurrent = null;
		if (res.has("pageCurrent")) {
			pageCurrent = res.getString("pageCurrent");
		}
		String isdel = null;
		if (res.has("isdel")) {
			isdel = res.getString("isdel");
		}

		if (StringUtils.isNoneBlank(goodsId, orderType, pageCurrent, machineId,isdel)) {
			Map<String, Object> map = new HashMap<String, Object>();
			int pagecount;
			// 根据商品ID查找该商品评论数量
			Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("goodsId", goodsId);
			map3.put("isdel", isdel);
			int evaluateNum = tblGoodsEvaluateMapper.selectCountBygoodsId(map3);
			pagecount = (int) Math.ceil((evaluateNum) / EvaluateUtil.getEvaluatepage());
//			if (evaluateNum <= 3) {
//				pagecount = 1;
//			}
			BasicsGoods basicsGoods = basicsGoodsMapper.selectByPrimaryKey(Long.valueOf(goodsId));
			List<TblGoodsEvaluate> tblGoodsEvaluateList = tblGoodsEvaluateMapper.selectBygoodsId(Long.valueOf(goodsId));
			double goodsEvaluateScoreAvg = 0;
			int tblGoodsEvaluateListsize = tblGoodsEvaluateList.size();
			if (tblGoodsEvaluateListsize > 0) {
				for (int i = 0; i < tblGoodsEvaluateListsize; i++) {
					goodsEvaluateScoreAvg = goodsEvaluateScoreAvg + tblGoodsEvaluateList.get(i).getGoodsEvaluateScore();
				}
				// 评价星级向上取整
				goodsEvaluateScoreAvg = Math.ceil(goodsEvaluateScoreAvg / tblGoodsEvaluateListsize);
			}
			int goodsSurpleNum;
			// 获取预定剩余份数
			if ("0".equals(orderType)) {
				
				if(StringUtils.isNoneBlank(selectDate, areaModelId)){
				goodsSurpleNum = tblIssueMapper.findSurplusPreissueNum(Long.valueOf(machineId), selectDate,
						Long.valueOf(areaModelId));}
				else {
					returnData.put("returnCode", "0");
					returnData.put("returnContent", "请求参数错误");
					return returnData;
				}
			}
			// 获取零售剩余份数
			else {
				Map<String, Object> map1 = new HashMap<String, Object>();
				BasicsMachine basicsMachine=basicsMachineMapper.selectByMachineId(Long.valueOf(machineId));
				map1.put("machineId", machineId);
				map1.put("areaModelId", basicsMachine.getAreaModelId());
				map1.put("selectDate", (new Date()));
				map1.put("goodsId", goodsId);
				goodsSurpleNum = tblGoodsEvaluateMapper.findgoodscountbygoods(map1);
			}
			// 根据商品ID查出食品类型名称与ID
			List<TblGoodsIngredientsType> tblGoodsIngredientsTypeList = tblGoodsIngredientsTypeMapper
					.selectByGoodsId(Long.valueOf(goodsId));
			List<Map<String, Object>> tblGoodsIngredientsTypemapList = new ArrayList<Map<String, Object>>();
			for (TblGoodsIngredientsType tblGoodsIngredientsType : tblGoodsIngredientsTypeList) {
				Map<String, Object> tblGoodsIngredientsTypemap = new HashMap<String, Object>();
				tblGoodsIngredientsTypemap.put("ingredientsTypeName", tblGoodsIngredientsType.getIngredientsTypeName());
				List<TblGoodsIngredients> tblGoodsIngredientsList = tblGoodsIngredientsMapper
						.selectByTypeId(tblGoodsIngredientsType.getIngredientsTypeId());
				List<Map<String, Object>> tblGoodsIngredientsmapList = new ArrayList<Map<String, Object>>();
				for (TblGoodsIngredients tblGoodsIngredients : tblGoodsIngredientsList) {
					Map<String, Object> tblGoodsIngredientsmap = new HashMap<String, Object>();
					tblGoodsIngredientsmap.put("ingredientsName", tblGoodsIngredients.getIngredientsName());
					tblGoodsIngredientsmap.put("ingredientsContent", tblGoodsIngredients.getIngredientsContent());
					tblGoodsIngredientsmapList.add(tblGoodsIngredientsmap);
				}
				tblGoodsIngredientsTypemap.put("ingredients", tblGoodsIngredientsmapList);
				tblGoodsIngredientsTypemapList.add(tblGoodsIngredientsTypemap);
			}
			// 根据页数查找用户评论, isdel 0代表查看所有，1代表只查看有内容的
			//long evaluatecount;
			long start;
//			if ("1".equals(pageCurrent)) {
//				evaluatecount = 3;
//				start=0;
//			} else {
				//evaluatecount = (long) (Long.valueOf(pageCurrent) * EvaluateUtil.getEvaluatepage());
				start=(long) ((Long.valueOf(pageCurrent)-1)*EvaluateUtil.getEvaluatepage());
//			}
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("start", start);
			map2.put("end", (long)EvaluateUtil.getEvaluatepage());
			map2.put("goodsId", goodsId);
			map2.put("isdel", isdel);
			List<TblGoodsEvaluate> tblGoodsEvaluateList2 = tblGoodsEvaluateMapper.selectEvaluateByGoodsId(map2);
			List<Map<String, Object>> tblGoodsEvaluateListmap = new ArrayList<Map<String, Object>>();
			for (TblGoodsEvaluate tblGoodsEvaluate : tblGoodsEvaluateList2) {
				Map<String, Object> tblGoodsEvaluatemap = new HashMap<String, Object>();
				BasicsGoods basicsGoods2 = basicsGoodsMapper
						.selectByPrimaryKey(tblGoodsEvaluate.getGoodsId());
				TblCustomer tblCustomer = tblCustomerMapper.selectByOrderId(tblGoodsEvaluate.getOrderId());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(tblGoodsEvaluate.getCreateTime());
				String customerName;
				if(tblCustomer==null){
					customerName="匿名用户";
				}
				else{
					customerName=tblCustomer.getCustomerName();
				}
				tblGoodsEvaluatemap.put("customerName", customerName);
				tblGoodsEvaluatemap.put("goodsName",basicsGoods2.getGoodsName());
				tblGoodsEvaluatemap.put("goodsEvaluateScore", tblGoodsEvaluate.getGoodsEvaluateScore()+"");
				tblGoodsEvaluatemap.put("goodsEvaluateContent", tblGoodsEvaluate.getGoodsEvaluateContent());
				tblGoodsEvaluatemap.put("evaluateTime", dateString);
				tblGoodsEvaluateListmap.add(tblGoodsEvaluatemap);
			}
			map.put("evaluateDetails", tblGoodsEvaluateListmap);
			map.put("ingredientsTypes", tblGoodsIngredientsTypemapList);
			map.put("evaluateNum", evaluateNum+"");
			map.put("goodsMemo", basicsGoods.getGoodsMemo());
			map.put("price", basicsGoods.getRetailPrice().toString());
			map.put("goodsSurpleNum", goodsSurpleNum+"");
			map.put("goodsEvaluateScoreAvg", goodsEvaluateScoreAvg+"");
			map.put("pageCount", pagecount+"");
			map.put("goodsName", basicsGoods.getGoodsName());
			returnData.put("returnCode", "1");
			returnData.put("returnContent", map);
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
		}

		return returnData;
	}

}
