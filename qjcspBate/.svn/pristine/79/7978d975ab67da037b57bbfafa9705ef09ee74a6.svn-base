package com.qjkj.qjcsp.web.goods;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.goods.GoodsRetailService;

/**
 *零售商品分配 
 *
 */
@Controller
@RequestMapping("goodsRetail")
public class GoodsRetailController {

	@Autowired
	private GoodsRetailService goodsRetailService;
	
	@RequestMapping(value="goodsRetailMain")
	public String goodsRetailMain(){
		return "goodsRetail/goodsRetailMain";
	}
	
	/**
	 * 获取模版列表
	 * @return
	 */
	@RequestMapping(value="getAllAreaModelList")
	@ResponseBody
	public Map<String, Object> getAllAreaModelList(){
		
		return goodsRetailService.getAllAreaModelList();
	}
	
	/**
	 * 获取零售发布日期
	 * @return
	 */
	@RequestMapping(value="getGoodsRetailDate")
	@ResponseBody
	public Map<String, Object> getGoodsRetailDate(@RequestParam(value="areaModelId")Long areaModelId){
		
		return goodsRetailService.getGoodsRetailDate(areaModelId);
	}
	
	/**
	 * 根据模型id获取设备零售数量
	 * @param areaModelId
	 * @return
	 */
	@RequestMapping(value="getMachineRetailNum")
	@ResponseBody
	public Map<String, Object> getMachineRetailNum(@RequestParam(value="areaModelId")Long areaModelId, 
			@RequestParam(value="replenishmentTime")String replenishmentTime){
		
		return goodsRetailService.getMachineRetailNum(areaModelId, replenishmentTime);
	}
	
	/**
	 * 根据模型id获取设备
	 * @param areaModelId
	 * @return
	 */
	@RequestMapping(value="getMachine")
	@ResponseBody
	public Map<String, Object> getMachine(@RequestParam(value="areaModelId")Long areaModelId){
		
		return goodsRetailService.getMachine(areaModelId);
	}
	
	/**
	 * 删除零售商品
	 * @param id
	 */
	@RequestMapping(value="delGoodsRetail")
	@ResponseBody
	public void delGoodsRetail(@RequestParam(value="id")Long id){
		goodsRetailService.delGoodsRetail(id);
	}
	
	/**
	 * 获取零售最高份数
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getMostSaleNum")
	@ResponseBody
	public int getMostSaleNum(@RequestParam(value="id")Long id){
		return goodsRetailService.getMostSaleNum(id);
	}
	
	/**
	 * 修改零售数量
	 * @param id
	 * @param newGoodsNum
	 * @return
	 */
	@RequestMapping(value="updateGoodsNum")
	@ResponseBody
	public int updateGoodsNum(@RequestParam(value="id")Long id, @RequestParam(value="newGoodsNum")int newGoodsNum){
		return goodsRetailService.updateGoodsNum(id, newGoodsNum);
	} 
	
	/**
	 * 点击添加餐品是否能跳转
	 * @param machineId
	 * @param machineName
	 * @param areaModelId
	 * @param repTime
	 * @return
	 */
	@RequestMapping(value="whetherToJump")
	@ResponseBody
	public Map<String, Object> whetherToJump(@RequestParam(value="machineId")Long machineId, @RequestParam(value="machineName")String machineName, 
			@RequestParam(value="areaModelId")Long areaModelId, @RequestParam(value="replenishmentTime")String repTime){
		return goodsRetailService.whetherToJump(machineId, machineName, areaModelId, repTime);
	}
	
}
