package com.qjkj.qjcsp.service.join;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//import com.alibaba.dubbo.container.page.Page;
import com.qjkj.qjcsp.entity.TblCustomerApplyAddress;
import com.qjkj.qjcsp.entity.TblCustomerApplyAddressList;
import com.qjkj.qjcsp.entity.enums.DataStatusEnum;
import com.qjkj.qjcsp.mapper.TblCustomerApplyAddressMapper;
import com.qjkj.qjcsp.util.ExportExcelUtil;

import net.sf.json.JSONObject;

@Service
@Transactional
public class ApplyAddressService {
	@Autowired
	private TblCustomerApplyAddressMapper tblCustomerApplyAddressMapper;

	public Map<String, Object> applyAddress(JSONObject res) {
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
  
	public Page<TblCustomerApplyAddress> findCustomerAddress(Map<String,Object> param, int pageNumber, int pageSize){
		 param.put("state", DataStatusEnum.NORMAL.getValue()) ;		 
		 PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		 param.put("offset", (pageNumber - 1) * pageSize);
		 param.put("limit", pageSize);
		 Long total = tblCustomerApplyAddressMapper.findByCount(param) ;
		 List<TblCustomerApplyAddress> list = new ArrayList<TblCustomerApplyAddress>() ;			
		 list = tblCustomerApplyAddressMapper.findCustomerAddress(param) ;			
		 Page<TblCustomerApplyAddress> page = new PageImpl<TblCustomerApplyAddress>(list,pageRequest,total);
		return page;
	}
	
    /**
     * 查询返回的条数
     * @param tblCustomerApplyAddressList
     * @return
     */
	public int findCustomerCount(TblCustomerApplyAddressList tblCustomerApplyAddressList){
		return tblCustomerApplyAddressMapper.findCustomerCount(tblCustomerApplyAddressList);
	};	
	/**
	 * 查询列表
	 * @param tblCustomerApplyAddressList
	 * @return
	 */
    public List<TblCustomerApplyAddress> findCustomerList(TblCustomerApplyAddressList tblCustomerApplyAddressList){
    			
		return tblCustomerApplyAddressMapper.findCustomerList(tblCustomerApplyAddressList);
	}
	
    /**
     * 导出Excel
     * @param request
     * @param response
     */
    public void JoinCustomerListExport(ServletRequest request, HttpServletResponse response) {
		String customerName=request.getParameter("customerName");
		try {
			customerName=new String(customerName.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("customerName", customerName);
		param.put("telephone", request.getParameter("telephone"));
		/*param.put("applicantTel", request.getParameter("applicantTel"));*/
		param.put("sort", Sort.Direction.DESC);
		// 根据条件查询订单
		List<Map<String, Object>> resultList = tblCustomerApplyAddressMapper.JoinCustomerListExport(param);
		String title = "客户申请信息";
		String[] titles = { "客户id", "客户名称", "客户手机号", "客户电话", "地区", "详细地址", "创建时间"};
		Integer[] sheets = { 100, 150, 150, 150, 150, 200, 200};
		String[] keys = { "customerId", "customerName", "customerMobile", "telephone", "region", "detailAddress", "createTime"};
		List<List<Integer>> rows = new ArrayList<List<Integer>>();
		ExportExcelUtil.exportExcel(response, title, sheets, titles, rows, keys, resultList);
	}
	
	
}
