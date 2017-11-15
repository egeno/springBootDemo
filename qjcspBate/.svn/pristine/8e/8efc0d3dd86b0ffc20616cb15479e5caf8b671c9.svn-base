package com.qjkj.qjcsp.service.luckymoney;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblCustomer;
import com.qjkj.qjcsp.entity.TblLotteryGradeActivity;
import com.qjkj.qjcsp.entity.TblLuckyMoney;
import com.qjkj.qjcsp.entity.TblPrideGrade;
import com.qjkj.qjcsp.mapper.TblCustomerMapper;
import com.qjkj.qjcsp.mapper.TblLotteryGradeActivityMapper;
import com.qjkj.qjcsp.mapper.TblLuckyMoneyMapper;
import com.qjkj.qjcsp.mapper.TblPendingOrderMapper;
import com.qjkj.qjcsp.mapper.TblPrideGradeMapper;
import com.qjkj.qjcsp.service.alisms.AliSmsService;

/**
 * 
 * @author wsk 2016年3月31日09:46:37
 *
 */
@Service
@Transactional
public class LuckyMoneyPcService {
	
	@Autowired //奖励等级
	private TblPrideGradeMapper prideGradeMapper;
	@Autowired  //用户信息
	private TblCustomerMapper tblCustomerMapper;
	@Autowired  //活动信息
	private TblLotteryGradeActivityMapper lotteryGradeActivityMapper;
	@Autowired  //红包信息
	private TblLuckyMoneyMapper tblLuckyMoneyMapper;
	@Autowired
	private AliSmsService aliSmsService;
	
	public Map<String, Object> findluckyMoney(Map<String, Object> map){
		Map<String, Object> map1 = new HashMap<String, Object>();
		List<TblLuckyMoney> TblLuckyMoneys = tblLuckyMoneyMapper.selectluckyMoney(map);
		int count = tblLuckyMoneyMapper.getluckycount(map);
		
		map1.put("rows", TblLuckyMoneys);
		map1.put("total", count); 
		return map1;
	}
	/*
	 * 得到奖励等级
	 */
	public List<TblPrideGrade> findprideGrade(){
	   return prideGradeMapper.selectpridegrades();
	}
	
	public Map<String, Object> saveLuckyMoney(HttpServletRequest res) throws ParseException {
		TblLuckyMoney luckyMoney = new TblLuckyMoney();
		Map<String, Object> map = new HashMap<String, Object>();
	    String mobile = res.getParameter("mobile");
	    String effectiveday =  res.getParameter("effectiveday");
	    String id =	res.getParameter("id");
	    
	    int num=0;
	    /* 根据电话号码的到客户信息*/
	    TblCustomer tblCustomer = tblCustomerMapper.selectByMobileNum(mobile);
	    if(tblCustomer == null){
	    	map.put("status", false);
			map.put("message", "此用户不存在，需要重新注册");
	    	return map;
	    }
	    /* 判断当前客户信息是否完善*/
	    if(tblCustomer.getInfoCompleteSymbol() != null && !tblCustomer.getInfoCompleteSymbol().equals("1") ){
	    	map.put("status", false);
			map.put("message", "此用户信息完善不完整");
	    	return map;
	    }
	    /* 得到活动信息 */
	   TblLotteryGradeActivity tblLotteryGradeActivity = 
			   lotteryGradeActivityMapper.findlotterygradeactivity();
	   if(tblLotteryGradeActivity == null){
		   map.put("status", false);
	       map.put("message", "此活动不存在");
	       return map;
	    }
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   /* 判断用户创建时间在活动开始时间和活动结束时间之间*/
	    if(!(format.parse(tblLotteryGradeActivity.getStartTime()).before(tblCustomer.getCreateTime()) &&
	    		format.parse(tblLotteryGradeActivity.getEndTime()).after(tblCustomer.getCreateTime()))){
	    	map.put("status", false);
		    map.put("message", "此用户不是在活动期间注册");
		    return map;
	    }
	    /* 判断当前时间在活动开始时间和活动结束时间之间*/
	    Date date = new Date();
	    if(!(format.parse(tblLotteryGradeActivity.getStartTime()).before(date) &&
	    		format.parse(tblLotteryGradeActivity.getEndTime()).after(date))){
	    	map.put("status", false);
		    map.put("message", "当前时间不在活动时间内");
		    return map;
	    }
	    List<TblLuckyMoney> list = tblLuckyMoneyMapper.getNumByMobileAndType(mobile);
	    if(list.size() > 0){
	    	map.put("status", false);
		    map.put("message", "此用户以发过红包");
		    return map;
	    }
	    /* 根据奖励等级id 奖励*/
	    TblPrideGrade tblPrideGrade = prideGradeMapper.selectByPrimaryKey(Long.valueOf(id));
	    if(tblPrideGrade != null){
	    	luckyMoney.setLuckyMoney(tblPrideGrade.getPrideSingleMoney());//红包金额
	    	luckyMoney.setCustomerMobile(mobile);//号码
	    	luckyMoney.setCustomerId(tblCustomer.getCustomerId());
	    	int effday = Integer.parseInt(effectiveday);
	    	
	    	try {
				luckyMoney.setValidEndDate(getEndDate(date, effday));//截止时间
				luckyMoney.setValidStartDate(getDate(date,1));//开始时间
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//截止时间
	    	
	    	luckyMoney.setLuckyMoneyType("0");//红包类型（抽奖红包为0）
	    	luckyMoney.setIsUsed("0");//红包是否被使用
	    	/* 循环红包个数单个添加*/
	    	for (int i = 0; i < tblPrideGrade.getPrideMount(); i++) {
	    		num =num+tblLuckyMoneyMapper.insert(luckyMoney);
			}
	    	if( num == tblPrideGrade.getPrideMount()){
	    		aliSmsService.luckyMoneyMessage(tblCustomer.getCustomerMobile(),tblPrideGrade.getPrideMoney().toString());
	    		map.put("status", true);
			    map.put("message", "红包充值成功");
			    return map;
	    	}else{
	    		map.put("status", false);
			    map.put("message", "红包充值失败");
			    return map;
	    	}
	    }
	    
		return map;
	}
	/*根据当前日期得到次日    */
	public Date getDate(Date date,int daynum) throws ParseException{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
		String strdate = format.format(date);
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(strdate));
		c.add(Calendar.DATE, daynum);
		
		return c.getTime();
	}
	/*得到红包截止时间*/
	public Date getEndDate(Date date,int daynum) throws ParseException{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
		SimpleDateFormat formatbb = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strdate = format.format(date);
		strdate +="23:59:59";
		Calendar c = Calendar.getInstance();
		c.setTime(formatbb.parse(strdate));
		c.add(Calendar.DATE, daynum);
		
		return c.getTime();
	}

}
