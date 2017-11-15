package com.qjkj.qjcsp.service.stacistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.CompanyPo;
import com.qjkj.qjcsp.entity.GoodsSellReportPo;
import com.qjkj.qjcsp.entity.GoodsSellReportSearch;
import com.qjkj.qjcsp.mapper.GoodsSellReportMapper;

@Service
public class GoodsSellReportService {
	@Autowired
	private GoodsSellReportMapper goodsSellReportMapper;

	/**
	 * 查找菜品数量
	 * 
	 * @author yehx
	 * @date 2016年1月25日
	 * @return
	 *
	 */
	/*
	 * public int findAllGoodsSellCount(GoodsSellReportSearch
	 * goodsSellReportSearch) { return
	 * goodsSellReportMapper.findAllGoodsSellCount(goodsSellReportSearch); }
	 */

	/**
	 * 得到菜品数据
	 * 
	 * @author yehx
	 * @date 2016年1月25日
	 * @param goodsSellReportSearch
	 * @return
	 *
	 */
	public List<GoodsSellReportPo> findAllGoodsSellReportList(GoodsSellReportSearch goodsSellReportSearch) {
		// 得到商品销售报表
		List<GoodsSellReportPo> goodsSellReportInfos = goodsSellReportMapper
				.findAllGoodsSellReportList(goodsSellReportSearch);
		//获得改商品的补货数量
		for(GoodsSellReportPo goodsSellReportPo:goodsSellReportInfos){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("goodsId", goodsSellReportPo.getGoodsId());
			map.put("companyId", goodsSellReportSearch.getCompanyId());
			map.put("sellStartTime", goodsSellReportSearch.getSellStartTime());
			map.put("sellEndTime", goodsSellReportSearch.getSellEndTime());
			int count=goodsSellReportMapper.findPutGoodsCount(map);
			goodsSellReportPo.setPutGoodsCount(count);
		}
		// 得到该商品的订单数量
		List<GoodsSellReportPo> goodsSellReportOrders = goodsSellReportMapper
				.findAllGoodsSellReportOrders(goodsSellReportSearch);
		if (goodsSellReportInfos != null && goodsSellReportInfos.size() > 0) {
			for (int i = 0; i < goodsSellReportInfos.size(); i++) {
				GoodsSellReportPo goodsSellReportPo = goodsSellReportInfos.get(i);
				Long goodsId = goodsSellReportPo.getGoodsId();
				if (goodsSellReportOrders != null && goodsSellReportOrders.size() > 0) {
					for (int j = 0; j < goodsSellReportOrders.size(); j++) {
						GoodsSellReportPo goodsSellReportPoOrder = goodsSellReportOrders.get(j);
						if (goodsId.equals(goodsSellReportPoOrder.getGoodsId())) {

							goodsSellReportPo.setOrderCount(goodsSellReportPoOrder.getOrderCount());
						}
					}
				}

			}
		}
		return goodsSellReportInfos;
	}

	public int getAllGoodsSellReportCount(GoodsSellReportSearch goodsSellReportSearch) {
		return goodsSellReportMapper.findAllGoodsSellReportOrders(goodsSellReportSearch).size();
	}

	/**
	 * 导出报表
	 * 
	 * @author yehx
	 * @date 2016年1月27日
	 * @return
	 *
	 */
	public List<Map<String, Object>> goodsSellReportExport(GoodsSellReportSearch goodsSellReportSearch) {
		// 得到商品销售报表
		List<Map<String, Object>> goodsSellReportInfos = goodsSellReportMapper
				.findAllGoodsSellReportListExport(goodsSellReportSearch);
		//获得改商品的补货数量
		for(Map<String, Object> goodsSellReportPo:goodsSellReportInfos){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("goodsId", goodsSellReportPo.get("goodsId"));
			map.put("companyId", goodsSellReportSearch.getCompanyId());
			map.put("sellStartTime", goodsSellReportSearch.getSellStartTime());
			map.put("sellEndTime", goodsSellReportSearch.getSellEndTime());
			int count=goodsSellReportMapper.findPutGoodsCount(map);
			goodsSellReportPo.put("putGoodsCount", count);
		}
		// 得到该商品的订单数量
		List<Map<String, Object>> goodsSellReportOrders = goodsSellReportMapper
				.findAllGoodsSellReportOrdersExport(goodsSellReportSearch);
		if (goodsSellReportInfos != null && goodsSellReportInfos.size() > 0) {
			for (int i = 0; i < goodsSellReportInfos.size(); i++) {
				Map<String, Object> mapSellReport = goodsSellReportInfos.get(i);
				Long goodsId = (Long) mapSellReport.get("goodsId");
				if (goodsSellReportOrders != null && goodsSellReportOrders.size() > 0) {
					for (int j = 0; j < goodsSellReportOrders.size(); j++) {
						Map<String, Object> goodsSellReportPoOrder = goodsSellReportOrders.get(j);
						if (goodsId.equals(goodsSellReportPoOrder.get("goodsId"))) {
							mapSellReport.put("orderCount", goodsSellReportPoOrder.get("orderCount"));
							// goodsSellReportPo.setOrderCount(goodsSellReportPoOrder.getOrderCount());
						}
					}
				}

			}
		}
		return goodsSellReportInfos;

	}

	public List<CompanyPo> getCompanyIdAndName(Map map) {
		return goodsSellReportMapper.getCompanyIdAndName(map);
	}

	/***
	 * 查询每柜商品销售数据
	 * 
	 * @param MachineGoodsSell
	 * @return List<MachineGoodsSell>
	 ***/
	/*
	 * public List<MachineGoodsSell> findListMachineGoodsSell(MachineGoodsSell
	 * machineGoodsSell){ List<MachineGoodsSell>
	 * list=goodsSellReportMapper.findListMachineGoodsSell(machineGoodsSell);
	 * List<MachineGoodsSell> goodsSellReportOrders =
	 * goodsSellReportMapper.findAllMachineGoodsSellReportOrders(
	 * machineGoodsSell); if (list != null && list.size() > 0) { for (int i = 0;
	 * i < list.size(); i++) { MachineGoodsSell machineGoods = list.get(i); Long
	 * goodsId = machineGoods.getGoodsId(); if (goodsSellReportOrders != null &&
	 * goodsSellReportOrders.size() > 0) { for (int j = 0; j <
	 * goodsSellReportOrders.size(); j++) { MachineGoodsSell
	 * goodsSellReportPoOrder = goodsSellReportOrders.get(j); if
	 * (goodsId.equals(goodsSellReportPoOrder.getGoodsId())) {
	 * machineGoods.setOrderCount(goodsSellReportPoOrder.getOrderCount()); } } }
	 * 
	 * } }
	 * 
	 * if (list != null && list.size() > 0) { MachineGoodsSell machineGoods1=new
	 * MachineGoodsSell(); NumberFormat ddf1=NumberFormat.getNumberInstance() ;
	 * ddf1.setMaximumFractionDigits(2); machineGoods1.setMachineName("合计");
	 * machineGoods1.setModelType(list.size()+"");
	 * machineGoods1.setGoodsCode(list.size()+"");
	 * machineGoods1.setGoodsName(list.size()+""); BigDecimal saleMoney=new
	 * BigDecimal(0) ; BigDecimal oneMoney=new BigDecimal(0) ; int
	 * putGoodsCounts=0; int orderCounts=0; int sellCounts=0; for (int i = 0; i
	 * < list.size(); i++) { saleMoney =saleMoney.add(new
	 * BigDecimal(list.get(i).getSellMoney() + "")); oneMoney=oneMoney.add(new
	 * BigDecimal(list.get(i).getUnitPrice()+""));
	 * putGoodsCounts+=list.get(i).getPutGoodsCount();
	 * orderCounts+=list.get(i).getOrderCount();
	 * sellCounts+=list.get(i).getSellCount(); }
	 * machineGoods1.setUnitPrice(oneMoney);
	 * machineGoods1.setPutGoodsCount(putGoodsCounts);
	 * machineGoods1.setOrderCount(orderCounts);
	 * machineGoods1.setSellCount(sellCounts);
	 * machineGoods1.setSellMoney(saleMoney);
	 * machineGoods1.setSellPercent(ddf1.format(((float)sellCounts/(float)
	 * putGoodsCounts)*100)+"%"); list.add(machineGoods1); } return list; }
	 */

	/*
	 * public List<Map<String, Object>>
	 * findListMachineGoodsSellExcept(MachineGoodsSell machineGoodsSell){
	 * List<Map<String, Object>>
	 * list=goodsSellReportMapper.findListMachineGoodsSellExcept(
	 * machineGoodsSell); List<MachineGoodsSell> goodsSellReportOrders =
	 * goodsSellReportMapper.findAllMachineGoodsSellReportOrders(
	 * machineGoodsSell); if (list != null && list.size() > 0) { for (int i = 0;
	 * i < list.size(); i++) { Map<String, Object> machineGoods = list.get(i);
	 * Long goodsId =(Long)machineGoods.get("goodsId"); if
	 * (goodsSellReportOrders != null && goodsSellReportOrders.size() > 0) { for
	 * (int j = 0; j < goodsSellReportOrders.size(); j++) { MachineGoodsSell
	 * goodsSellReportPoOrder = goodsSellReportOrders.get(j); if
	 * (goodsId.equals(goodsSellReportPoOrder.getGoodsId())) {
	 * machineGoods.put("orderCount", goodsSellReportPoOrder.getOrderCount()); }
	 * } }
	 * 
	 * } }
	 * 
	 * if (list != null && list.size() > 0) { Map<String, Object> map=new
	 * HashMap<String, Object>(); NumberFormat
	 * ddf1=NumberFormat.getNumberInstance() ; ddf1.setMaximumFractionDigits(2);
	 * map.put("machineName", "合计"); map.put("modelType", list.size()+"");
	 * map.put("goodsCode", list.size()+""); map.put("goodsName",
	 * list.size()+""); BigDecimal saleMoney=new BigDecimal(0) ; BigDecimal
	 * oneMoney=new BigDecimal(0) ; int putGoodsCounts=0; int orderCounts=0; int
	 * sellCounts=0; for (int i = 0; i < list.size(); i++) { saleMoney
	 * =saleMoney.add(new BigDecimal(list.get(i).get("sellMoney") + ""));
	 * oneMoney=oneMoney.add(new BigDecimal(list.get(i).get("unitPrice")+""));
	 * putGoodsCounts+=Integer.parseInt(list.get(i).get("putGoodsCount").
	 * toString());
	 * orderCounts+=Integer.parseInt(list.get(i).get("orderCount").toString());
	 * sellCounts+=Integer.parseInt(list.get(i).get("sellCount").toString()); }
	 * 
	 * map.put("unitPrice", oneMoney); map.put("putGoodsCount", putGoodsCounts);
	 * map.put("orderCount", orderCounts); map.put("sellCount", sellCounts);
	 * map.put("sellMoney", saleMoney); map.put("sellPercent",
	 * ddf1.format(((float)sellCounts/(float)putGoodsCounts)*100)+"%");
	 * list.add(map); } return list; }
	 */

}
