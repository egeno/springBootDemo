package com.qjkj.qjcsp.service.wechatapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.mapper.TblRechargeActivityMapper;
/**
 * 充值面额查询接口(APP)
 * @author carpeYe 2016-03-09
 *
 */
@Service
public class RechargeActivityWeChatService {

	@Autowired
	private TblRechargeActivityMapper tblRechargeActivityMapper;
	
	
	public Map<String,Object> getRechargeActivityWX(){
		Map<String,Object> returnContent=new HashMap<String, Object>();
		List<Map<String,Object>> list=tblRechargeActivityMapper.selectAllRechargeActivity();
		if(list!=null&&list.size()>0){
			returnContent.put("returnCode", "1");
			returnContent.put("returnContent", list);
		}else{
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "沒有符合条件活动");
		}
		return returnContent;
	}
}
