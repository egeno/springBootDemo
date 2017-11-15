package com.qjkj.qjcsp.service.machine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.TblItemDetail;
import com.qjkj.qjcsp.entity.TblOrderChild;
import com.qjkj.qjcsp.entity.enums.OrderStatus;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.TblItemDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderChildMapper;
import com.qjkj.qjcsp.mapper.TblOrderDetailMapper;

@Component
public class VerifyTakingCodeService {

	@Autowired
	private TblOrderChildMapper tblOrderChildMapper;
	@Autowired
	private TblOrderDetailMapper tblOrderDetailMapper;
	@Autowired
	private TblItemDetailMapper tblItemDetailMapper;
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;

	/**
	 * 验证取餐码
	 * 
	 * @param deviceId,idCode
	 * @return
	 */
	public Map<String, Object> verifyTakingCode(String deviceCode, String idCode) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Map<String, Object> returnData = new HashMap<String, Object>();
		BasicsMachine basicsMachine = basicsMachineMapper.selectByDeviceCode(deviceCode);
		if(basicsMachine == null){ //找不到设备
			returnData.put("returnCode", "2");
			returnData.put("returnContent", "无该设备");
			return returnData;
		}
		TblOrderChild tblOrderChild = tblOrderChildMapper.selectByDeviceCode(basicsMachine.getMachineId()+"", idCode);
		/*System.err.println(df.format(tblOrderChild.getBeginTime()));
		System.err.println(df.format(tblOrderChild.getEndTime()));
		System.err.println(tblOrderChild.getOrderChildNum());*/
		if (tblOrderChild != null) {
			Date currentTime = new Date();
			if (currentTime.before(tblOrderChild.getEndTime())) { //当前时间大于取餐截止时间
				
				if (StringUtils.equals(tblOrderChild.getOrderChildStatus(), OrderStatus.NO_TAKED.value)) { //订单是已支付未取货状态
					List<Map<String, Object>> orderDetail = tblOrderDetailMapper.findDetailByChildNumAndMachineId(tblOrderChild.getOrderChildNum(), basicsMachine.getMachineId()+"");
					
					for (Map<String,Object> map2 : orderDetail) {
						List<Map<String,Object>> itemList= tblItemDetailMapper.findItemDetailBydeviceCodeAndOrderNum(Long.valueOf(map2.get("orderDetailId").toString()),basicsMachine.getMachineId());
						if(itemList.size() == 0){
							//重复取餐
							List<TblItemDetail> tblItemDetail = tblItemDetailMapper.selectRepeatOrder(basicsMachine.getMachineId(), idCode);
							if(tblItemDetail == null){
								returnData.put("returnCode", "2");
								returnData.put("returnContent", "订单异常");
								return returnData;
							}
							for (TblItemDetail tblItemDetail2 : tblItemDetail) {
								if(tblItemDetail2.getTakenTime() != null){
									returnData.put("returnCode", "2");
									returnData.put("returnContent", "重复取餐");
									return returnData;
								}
							}
							
							//当前设备没有可以取的餐品
							Date date = new Date();
							if(tblOrderChild.getBeginTime().after(date)){
								returnData.put("returnCode", "2");
								returnData.put("returnContent", "取餐时间为" + df.format(tblOrderChild.getBeginTime()) + "至" + df.format(tblOrderChild.getEndTime()));
								return returnData;
							}
							else{
								//子订单状态：1是已支付待取餐，3是已取餐
								//0是预定/1是零售，零售itemDetail表内不会为空
								if("1".equals(tblOrderChild.getOrderChildStatus())){
									returnData.put("returnCode", "2");
									returnData.put("returnContent", "该订单补货未完成，无法取餐，请联系工作人员");
									/*returnData.put("returnCode", "2");
									returnData.put("returnContent", "取餐时间为" + df.format(tblOrderChild.getBeginTime()) + "至" + df.format(tblOrderChild.getEndTime()));*/
									return returnData;
								}
								if("3".equals(tblOrderChild.getOrderChildStatus())){
									returnData.put("returnCode", "2");
									returnData.put("returnContent", "该订单已取");
									return returnData;
								}
							}
						}
						else{
							Map<String, String> numMap = tblItemDetailMapper.selectBuyNumAndReplenishmentNum(tblOrderChild.getOrderChildNum());
							if(!numMap.get("gNum").equals(numMap.get("bNum"))){
								returnData.put("returnCode", "0");
								returnData.put("returnContent", "该订单补货未完成，无法取餐，请联系工作人员");
								return returnData;
							}
							
							map2.put("itemList", itemList);
						}
					}
				
					returnData.put("returnCode", "1");
					returnData.put("returnContent", orderDetail);
				} else if (StringUtils.equals(tblOrderChild.getOrderChildStatus(), OrderStatus.TAKED.value)){
					returnData.put("returnCode", "2");
					returnData.put("returnContent", "该订单已取");
				}
				else{
					returnData.put("returnCode", "2");
					returnData.put("returnContent", "该订单状态异常");
				}
			} else {
				returnData.put("returnCode", "2");
				returnData.put("returnContent", "取餐超时");
			}
		} else {
			returnData.put("returnCode", "2");
			returnData.put("returnContent", "验证码有误");
		}

		return returnData;
	}

}
