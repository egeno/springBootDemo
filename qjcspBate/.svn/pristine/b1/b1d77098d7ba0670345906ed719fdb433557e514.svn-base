package com.qjkj.qjcsp.service.wechatapi;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblCustomerApplyAddress;
import com.qjkj.qjcsp.mapper.TblCustomerApplyAddressMapper;

import net.sf.json.JSONObject;

@Service
@Transactional
public class WeChatlyAddressService {
	@Autowired
	private TblCustomerApplyAddressMapper tblCustomerApplyAddressMapper;

	public Map<String, Object> applyAddressWX(JSONObject res) {
		Map<String, Object> content = new HashMap<String, Object>();
		Long customerId=res.getLong("customerId");
		String customerMobile=res.getString("customerMobile");
		String region=res.getString("region");
		String detailAddress=res.getString("detailAddress");
		String customerName=null;
		String telephone=null;
		Date createTime=new Date();
		if(res.has("customerName")){
			customerName=res.getString("customerName");
		}
		if(res.has("telephone")){
			telephone=res.getString("telephone");
		}
		if((StringUtils.isAnyBlank(customerMobile,region,detailAddress))||customerId==0){
			content.put("returnCode", "0");
			content.put("returnContent", "参数错误");
			return content;
		}
		TblCustomerApplyAddress tblCustomerApplyAddress=tblCustomerApplyAddressMapper.selectForrepeat(customerMobile,region,detailAddress);
		if(tblCustomerApplyAddress!=null){
			content.put("returnCode", "0");
			content.put("returnContent", "该手机号码已经申请该地址");
			return content;
		}
		TblCustomerApplyAddress record=new TblCustomerApplyAddress();
		record.setCustomerId(customerId);
		record.setCustomerMobile(customerMobile);
		record.setRegion(region);
		record.setDetailAddress(detailAddress);
		record.setCustomerName(customerName);
		record.setTelephone(telephone);
		record.setCreateTime(createTime);
		int count=tblCustomerApplyAddressMapper.insert(record);
		if(count==0){
			content.put("returnCode", "0");
			content.put("returnContent", "添加失败");
			return content;
		}
		content.put("returnCode", "1");
		content.put("returnContent", "添加成功");
		
		return content;
	}

}
