package com.qjkj.qjcsp.service.discountactivity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblCustomerDiscountActivity;
import com.qjkj.qjcsp.mapper.TblCustomerDiscountActivityMapper;
@Service
public class CustomerDiscountActivityService {
	@Autowired
	private  TblCustomerDiscountActivityMapper  tblCustomerDiscountActivityMapper;
	
	/**
	 * 得到所有数据
	 * 
	 * @author yehx
	 * @date 2016年1月25日
	 * @return
	 *
	 */
	public List<TblCustomerDiscountActivity> findAllDiscountActivityList(TblCustomerDiscountActivity tblCustomerDiscountActivity) {
		return tblCustomerDiscountActivityMapper.findAllDiscountActivityList(tblCustomerDiscountActivity);
	}
	@Transactional
	public int insertDiscountActivity(TblCustomerDiscountActivity tblCustomerDiscountActivity){
		return tblCustomerDiscountActivityMapper.insertSelective(tblCustomerDiscountActivity);
	}
	public int getHasCount(){
		return tblCustomerDiscountActivityMapper.getCount();
	}
	@Transactional
	public void updateDiscountActivity(TblCustomerDiscountActivity tblCustomerDiscountActivity){
		tblCustomerDiscountActivityMapper.updateDiscountActivity(tblCustomerDiscountActivity);
	}
   
}
