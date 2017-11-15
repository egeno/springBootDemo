package com.qjkj.qjcsp.service.customer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.core.quartz.TimingPush;
import com.qjkj.qjcsp.entity.BasicsLoginRecord;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.TblCustomer;
import com.qjkj.qjcsp.entity.TblCustomerInfo;
import com.qjkj.qjcsp.entity.TblCustomerLock;
import com.qjkj.qjcsp.entity.TblCustomerQuestion;
import com.qjkj.qjcsp.entity.TblVerifyCode;
import com.qjkj.qjcsp.entity.viewmodel.TblCustomerVo;
import com.qjkj.qjcsp.mapper.BasicsLoginRecordMapper;
import com.qjkj.qjcsp.mapper.BasicsUsersMapper;
import com.qjkj.qjcsp.mapper.QuartzMapper;
import com.qjkj.qjcsp.mapper.TblCustomerInfoMapper;
import com.qjkj.qjcsp.mapper.TblCustomerLockMapper;
import com.qjkj.qjcsp.mapper.TblCustomerMapper;
import com.qjkj.qjcsp.mapper.TblCustomerQuestionMapper;
import com.qjkj.qjcsp.mapper.TblVerifyCodeMapper;
import com.qjkj.qjcsp.service.alisms.AliSmsService;
import com.qjkj.qjcsp.util.DateUtils;
import com.qjkj.qjcsp.util.VerifyCode;

import net.sf.json.JSONObject;

@Component
@Transactional
public class CustomerApiService {
    
	@Autowired
	private TblCustomerMapper tblCustomerMapper;
	@Autowired
	private TblVerifyCodeMapper tblVerifyCodeMapper;
	@Autowired
	private TblCustomerInfoMapper tblCustomerInfoMapper;
	@Autowired
	private AliSmsService aliSmsService;
	@Autowired
	private TblCustomerQuestionMapper tblCustomerQuestionMapper;
	@Autowired
	private TblCustomerLockMapper tblCustomerLockMapper;
	@Autowired
	private BasicsUsersMapper basicsUsersMapper;
	@Autowired
	private BasicsLoginRecordMapper basicsLoginRecordMapper;

	@Autowired
	private QuartzMapper quartzMapper;
	private static final int identifyingTime = 3; // 验证码有效期限3分钟
	
	@Autowired
	private CodeCache cachess;
	private Map<Date, Object> mapYanzhen=new ConcurrentHashMap<Date,Object>();
	//private Map<String,String> mapYanzhen=new ConcurrentHashMap<String,String>();

	/**
	 * 众包商预订补货
	 * 
	 */
	/*public HashSet<Map<String, String>> getMachineInfoByZhongBao() {
		HashSet<Map<String, String>> hs = new HashSet<Map<String, String>>();
		List<Map<String, Object>> lists = quartzMapper.getAreaModelId();
		if (lists.size() > 0) {
			List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
			List<Map<String, String>> listMaps = new ArrayList<Map<String, String>>();
			for (int i = 0; i < lists.size(); i++) {
				Map<String, Object> areaModelIdMap = lists.get(i);
				Long areaModelId = Long.valueOf(areaModelIdMap.get("areaModelId").toString());
				listMap = quartzMapper.getMachineInfoByModeId(areaModelId);
				for (Map<String, String> mapList : listMap) {
					listMaps.add(mapList);
				}
			}
			hs = new HashSet<Map<String, String>>(listMaps);
		}
		return hs;
	}*/

	/**
	 * 
	 * 预订补货提醒 中餐，休闲餐，晚餐补货提醒
	 * 
	 * @return
	 */
	public HashSet<Map<String, String>> getMachineInfoByModeId() {
		HashSet<Map<String, String>> hs = new HashSet<Map<String, String>>();
		List<Map<String, Object>> lists = quartzMapper.getAreaModelId();
		if (lists.size() > 0) {
			List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
			List<Map<String, String>> listMaps = new ArrayList<Map<String, String>>();
			for (int i = 0; i < lists.size(); i++) {
				Map<String, Object> areaModelIdMap = lists.get(i);
				Long areaModelId = Long.valueOf(areaModelIdMap.get("areaModelId").toString());
				listMap = quartzMapper.getMachineInfoByModeId(areaModelId);
				for (Map<String, String> mapList : listMap) {					
					String userId = mapList.get("userId");
					String machineId = mapList.get("machineId");
					String specialToleNum = mapList.get("specialToleNum");
					if("2".equals(specialToleNum)){
						//众包
						int replenishmentNum = quartzMapper.getReplenishmentNum(Long.valueOf(machineId),Long.valueOf(areaModelId),null);
						if(replenishmentNum > 0){
							listMaps.add(mapList);
						}				
						
					}else if("3".equals(specialToleNum)) {
						//商户
						int replenishmentNum = quartzMapper.getReplenishmentNum(Long.valueOf(machineId),Long.valueOf(areaModelId),Long.valueOf(userId));
						if(replenishmentNum > 0){
							listMaps.add(mapList);
						}					
					}																	
				}
			}
			hs = new HashSet<Map<String, String>>(listMaps);
		}
		return hs;
	}

	/**
	 * 商户零售补货   中餐，休闲餐，晚餐
	 */
	public HashSet<Map<String, String>> getMachineInfoByRetailAll() {
		HashSet<Map<String, String>> hs = new HashSet<Map<String, String>>();
		List<Map<String, Object>> lists = quartzMapper.getAreaModelIdRetail();
		if (lists.size() > 0) {
			List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
			List<Map<String, String>> listMaps = new ArrayList<Map<String, String>>();
			for (int i = 0; i < lists.size(); i++) {
				Map<String, Object> areaModelIdMap = lists.get(i);
				Long areaModelId = Long.valueOf(areaModelIdMap.get("areaModelId").toString());
				listMap = quartzMapper.getMachineInfoByModeId(areaModelId);
				for (Map<String, String> mapList : listMap) {
					String userId = mapList.get("userId");
					String machineId = mapList.get("machineId");
					String specialToleNum = mapList.get("specialToleNum");
					if("2".equals(specialToleNum)){
						//众包
						//listMaps.add(mapList);
						int replenishmentNum = quartzMapper.getReplenishmentNum(Long.valueOf(machineId),Long.valueOf(areaModelId),null);
						if(replenishmentNum > 0){
							listMaps.add(mapList);
						}
					}else if("3".equals(specialToleNum)){
						//商户
						int replenishmentNum = quartzMapper.getReplenishmentNum(Long.valueOf(machineId),Long.valueOf(areaModelId),Long.valueOf(userId));
						if(replenishmentNum > 0){
							listMaps.add(mapList);
						}
						
					}		
				}
			}
			hs = new HashSet<Map<String, String>>(listMaps);
		}
		return hs;
	}

	/**
	 * 众包零售补货提醒
	 */
	/*public HashSet<Map<String, String>> getMachineInfoByRetail() {
		HashSet<Map<String, String>> hs = new HashSet<Map<String, String>>();
		List<Map<String, Object>> lists = quartzMapper.getAreaModelIdRetail();
		if (lists.size() > 0) {
			List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
			List<Map<String, String>> listMaps = new ArrayList<Map<String, String>>();
			for (int i = 0; i < lists.size(); i++) {
				Map<String, Object> areaModelIdMap = lists.get(i);
				Long areaModelId = Long.valueOf(areaModelIdMap.get("areaModelId").toString());
				listMap = quartzMapper.getMachineInfoByModeId(areaModelId);
				for (Map<String, String> mapList : listMap) {
					listMaps.add(mapList);
				}
			}
			hs = new HashSet<Map<String, String>>(listMaps);
		}
		return hs;
	}*/
	/**
	 * 预订早餐补货提醒 
	 * 
	 * @return
	 */
	public HashSet<Map<String, String>> getBreakfastAreaModelId() {
		HashSet<Map<String, String>> hs = new HashSet<Map<String, String>>();
		List<Map<String, Object>> lists = quartzMapper.getBreakfastAreaModelId();
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		List<Map<String, String>> listMaps = new ArrayList<Map<String, String>>();
		for (int i = 0; i < lists.size(); i++) {
			Map<String, Object> areaModelIdMap = lists.get(i);
			Long areaModelId = Long.valueOf(areaModelIdMap.get("areaModelId").toString());
			listMap = quartzMapper.getMachineInfoByModeId(areaModelId);
			for (Map<String, String> mapList : listMap) {
				String userId = mapList.get("userId");
					String machineId = mapList.get("machineId");
					String specialToleNum = mapList.get("specialToleNum");
					if("2".equals(specialToleNum)){
						//众包
						//listMaps.add(mapList);
						
						int replenishmentNum = quartzMapper.getReplenishmentNum(Long.valueOf(machineId),Long.valueOf(areaModelId),null);
						if(replenishmentNum > 0){
							listMaps.add(mapList);
						}
						
						
					}else if("3".equals(specialToleNum)){
						//商户
						int replenishmentNum = quartzMapper.getReplenishmentNum(Long.valueOf(machineId),Long.valueOf(areaModelId),Long.valueOf(userId));
						if(replenishmentNum > 0){
							listMaps.add(mapList);
						}
						
					}					
			}	
	   }
		hs = new HashSet<Map<String, String>>(listMaps);
		return hs;
   }
	/**
	 * 零售早餐补货提醒
	 */
	public HashSet<Map<String, String>> getBreakfastAreaRetal() {
		HashSet<Map<String, String>> hs = new HashSet<Map<String, String>>();
		List<Map<String, Object>> lists = quartzMapper.getBreakfastAreaRetail();
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		List<Map<String, String>> listMaps = new ArrayList<Map<String, String>>();
		for (int i = 0; i < lists.size(); i++) {
			Map<String, Object> areaModelIdMap = lists.get(i);
			Long areaModelId = Long.valueOf(areaModelIdMap.get("areaModelId").toString());
			listMap = quartzMapper.getMachineInfoByModeId(areaModelId);
			for (Map<String, String> mapList : listMap) {
				String userId = mapList.get("userId");
					String machineId = mapList.get("machineId");
					String specialToleNum = mapList.get("specialToleNum");
					if("2".equals(specialToleNum)){
						//众包
						//listMaps.add(mapList);
						
						int replenishmentNum = quartzMapper.getReplenishmentNum(Long.valueOf(machineId),Long.valueOf(areaModelId),null);
						if(replenishmentNum > 0){
							listMaps.add(mapList);
						}
					}else if("3".equals(specialToleNum)){
						//商户
						int replenishmentNum = quartzMapper.getReplenishmentNum(Long.valueOf(machineId),Long.valueOf(areaModelId),Long.valueOf(userId));
						if(replenishmentNum > 0){
							listMaps.add(mapList);
						}
						
					}											
			}
		}		
		hs = new HashSet<Map<String, String>>(listMaps);
		return hs;
	}

	/**
	 * 获取补货员的userid 作废不用
	 * 
	 * @return
	 */
	/*public List<String> getUserId() {
		List<Map<String, Object>> listAll = quartzMapper.getUser_Id();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < listAll.size(); i++) {
			Map<String, Object> customerMap = listAll.get(i);
			String userId = customerMap.get("userId").toString();
			list.add(userId);
		}
		return list;
	}*/

	/**
	 * 发送用户注册/登录手机验证码
	 * 
	 * @param mobileNum
	 * @return
	 */
	public  Map<String, Object> sendVerifyCodeMessage(String mobileNum) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag = false;
		// Pattern regex =
		// Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
		Pattern regex = Pattern.compile("^1[3|4|5|7|8|9]\\d{9}$");
		Matcher matcher = regex.matcher(mobileNum);
		flag = matcher.matches();
		if (!flag) {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "手机号格式有误");
			return returnData;
		}

		TblCustomer mobileCustomer = tblCustomerMapper.selectByMobileNum(mobileNum);
		Map<String, String> returnContent = new HashMap<String, String>();
		// 生成六位随机数
		String verifyCode = VerifyCode.createVerifyCode();
		// 验证截止时间
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MINUTE, identifyingTime);
		Date endTime = cal.getTime();
		Map<String,String> dmaps=new HashMap<String, String>();
		// 手机用户已存在，则更新验证码
		if (mobileCustomer != null) {
			mobileCustomer.setIdentifyingCode(verifyCode);
			mobileCustomer.setEndTime(endTime);
			mobileCustomer.setIsfirst("1");
			int isSuccessful = tblCustomerMapper.updateByPrimaryKeySelective(mobileCustomer);
			// 更新成功后发送手机验证码
			if (isSuccessful > 0) {
				boolean bool=sendMobileVerifyCode(endTime, mobileNum, verifyCode.toString());
				
				if(bool){
					//mapYanzhen.put(mobileNum, verifyCode);
					dmaps.put(mobileNum, verifyCode);
					mapYanzhen=cachess.put(endTime, dmaps);
				}
			}

		} else {
			// 手机用户不存在，则新增用户
			/*TblCustomer tblCustomer = new TblCustomer();
			tblCustomer.setCustomerMobile(mobileNum);
			tblCustomer.setIdentifyingCode(verifyCode);
			tblCustomer.setEndTime(endTime);
			tblCustomer.setCustomerName("");
			tblCustomer.setCustomerSex("");
			tblCustomer.setCreateTime(new Date());
			tblCustomer.setModifyTime(new Date());
			tblCustomer.setIsfirst("0");
			tblCustomer.setInfoCompleteSymbol("0");
			tblCustomer.setDiscountUsedSymbol("0");
			tblCustomer.setIsdel("N");
			tblCustomer.setCustomerIntegral(0);
			tblCustomer.setCustomerMoney(new BigDecimal(0));*/
			
			/*int isSuccessful = tblCustomerMapper.insertSelective(tblCustomer);
			TblCustomer tblCustomer2 = tblCustomerMapper.selectByMobileNum(mobileNum);
			tblCustomerInfoMapper.insertId(tblCustomer2.getCustomerId());*/
			// 新增成功后发送手机验证码
			//if (isSuccessful > 0) {
			boolean bool =	sendMobileVerifyCode(endTime, mobileNum, verifyCode);
			//}
            if(bool){
            	//mapYanzhen.put(mobileNum, verifyCode);
            	dmaps.put(mobileNum, verifyCode);
				mapYanzhen=cachess.put(endTime, dmaps);
            }
		}
		returnContent.put("verifyCode", verifyCode);
		if (mobileCustomer == null || mobileCustomer.getCustomerName() == null
				|| "".equals(mobileCustomer.getCustomerName())) {
			returnContent.put("nickName", "匿名用户");
		} else {
			returnContent.put("nickName", mobileCustomer.getCustomerName());
		}
		returnData.put("returnCode", "1");
		returnData.put("returnContent", returnContent);
        
		return returnData;
	}

	/**
	 * 发送用户注册/登录手机验证码（商户）
	 * 
	 * @param mobileNum
	 * @return
	 */
	public Map<String, Object> loginUserCodeSend(String mobileNum) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag = false;
		// Pattern regex =
		// Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
		Pattern regex = Pattern.compile("^1[3|4|5|7|8|9]\\d{9}$");
		Matcher matcher = regex.matcher(mobileNum);
		flag = matcher.matches();
		
		Map<String,String> ShangHumaps=new HashMap<String, String>();
		
		if (!flag) {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "手机号格式有误");
			return returnData;
		}
		BasicsUsers basicsUsers = basicsUsersMapper.selectByMobileNum(mobileNum);
		// TblCustomer mobileCustomer =
		// tblCustomerMapper.selectByMobileNum(mobileNum);
		Map<String, String> returnContent = new HashMap<String, String>();
		// 生成六位随机数
		String verifyCode = VerifyCode.createVerifyCode();
		
		// 验证截止时间
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MINUTE, identifyingTime);
		Date endTime = cal.getTime();
		// 手机用户已存在，则更新验证码
		if (basicsUsers != null) {
			String CodeType = basicsUsers.getUserType().toString();
			// 不是众包商从角色表找CodeType
			if (!"3".equals(CodeType)) {
				// 得到当前手机号商户类型
				CodeType = basicsUsersMapper.getSpecialRoleNumByUserMobileNum(basicsUsers.getUserMobile());
				if (CodeType == null) {
					returnData.put("returnCode", "0");
					returnData.put("returnContent", "该用户未分配角色");
					return returnData;
				}
				ShangHumaps.put(mobileNum, verifyCode);
				mapYanzhen=cachess.put(endTime, ShangHumaps);
			}else{
				boolean bool=forgetPasswordSendMessage(endTime, mobileNum, verifyCode);
				if(bool){
					ShangHumaps.put(mobileNum, verifyCode);
					mapYanzhen=cachess.put(endTime, ShangHumaps);
				}
			}

			TblVerifyCode tblVerifyCode = new TblVerifyCode();
			tblVerifyCode.setCodeNum(verifyCode);
			tblVerifyCode.setCodeType(CodeType);
			tblVerifyCode.setMobileNum(mobileNum);
			tblVerifyCode.setCreateTime(new Date());
			tblVerifyCode.setClosingTime(endTime);
			// 添加之前删除已经发送过的验证码信息
			tblVerifyCodeMapper.deleteVerifyCodeByMobileAndType(mobileNum, CodeType);
			int isSuccessful = tblVerifyCodeMapper.insertSelective(tblVerifyCode);
			
			// 新增成功后发送手机验证码
			//密码发送成功
			if (isSuccessful > 0) {
				boolean bool=forgetPasswordSendMessage(endTime, mobileNum, verifyCode);
			}
			returnContent.put("verifyCode", verifyCode);
			returnData.put("returnCode", "1");
			returnData.put("returnContent", returnContent);
		} else {
			// 手机用户不存在，则新增用户
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "当前手机用户不存在");
		}

		return returnData;
	}

	/**
	 * 注册/登录手机验证码验证
	 * 
	 * @param mobileNum
	 * @return
	 */
	public Map<String, Object> verifyMobileNumCode(String mobileNum, String verifyCode) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> returnContent = new HashMap<String, String>();
		if ("15968840426".equals(mobileNum) && "666666".equals(verifyCode)) {
			TblCustomer tblCustomer = tblCustomerMapper.selectByMobileNum(mobileNum);
			returnContent.put("customerId", tblCustomer.getCustomerId() + "");
			returnData.put("returnCode", "1");
			returnData.put("returnContent", returnContent);
			return returnData;
		}
		//TblCustomer tblCustomer = tblCustomerMapper.selectByMobileNum(mobileNum);
		boolean boo = false;
		for (Date str : mapYanzhen.keySet()) {
	            Date key = str;
	            Map<String,String> valueMap = (Map<String, String>) mapYanzhen.get(key);
	            for(String keyVal:valueMap.keySet()){
	            	String keys=keyVal;
	            	String valus=valueMap.get(keys);
	            	 if(keys.equals(mobileNum) && valus.equals(verifyCode) ){
	            		 boo=true;
	 	            	mapYanzhen.remove(key);
	 	            }
	            }	          
	        }	
		// 手机用户存在
		//if (tblCustomer != null) {
		if(boo){
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.MINUTE, identifyingTime);
			Date endTime = cal.getTime();		
			TblCustomer tblCustomer3 = tblCustomerMapper.selectByMobileNumCode(mobileNum, verifyCode);
			if (tblCustomer3 == null) {
				// 新注册用户  插入到数据库
				TblCustomer tblCustomer = new TblCustomer();
				tblCustomer.setCustomerMobile(mobileNum);
				tblCustomer.setIdentifyingCode(verifyCode);
				tblCustomer.setEndTime(endTime);
				tblCustomer.setCustomerName("");
				tblCustomer.setCustomerSex("");
				tblCustomer.setCreateTime(new Date());
				tblCustomer.setModifyTime(new Date());
				tblCustomer.setIsfirst("0");
				tblCustomer.setInfoCompleteSymbol("0");
				tblCustomer.setDiscountUsedSymbol("0");
				tblCustomer.setIsdel("N");
				tblCustomer.setCustomerIntegral(0);
				tblCustomer.setCustomerMoney(new BigDecimal(0));
				int isSuccessful = tblCustomerMapper.insertSelective(tblCustomer);
				TblCustomer tblCustomer2 = tblCustomerMapper.selectByMobileNum(mobileNum);
				tblCustomerInfoMapper.insertId(tblCustomer2.getCustomerId());
				if(isSuccessful>0){					
					// 验证码是否过期
					Date currentTime = new Date();				
					if (currentTime.after(tblCustomer2.getEndTime())) {
						// 已过期
						returnData.put("returnCode", "0");
						returnData.put("returnContent", "验证码过期");	
						//mapYanzhen.remove(mobileNum);
						//return returnData;
					}else{
						
						Date date = new Date();
						Long customerId = tblCustomer2.getCustomerId();
						returnContent.put("customerId", customerId + "");
						BasicsLoginRecord basicsLoginRecord = basicsLoginRecordMapper.selectByCustomerId(customerId);
						if (basicsLoginRecord != null) {
							basicsLoginRecord.setLogintime(date);
							basicsLoginRecordMapper.updateByPrimaryKeySelective(basicsLoginRecord);
						} else {
							BasicsLoginRecord basicsLoginRecord1 = new BasicsLoginRecord();
							basicsLoginRecord1.setCustomerId(customerId);
							basicsLoginRecord1.setLogintime(date);
							basicsLoginRecordMapper.insertSelective(basicsLoginRecord1);
						}
						BasicsLoginRecord basicsLoginRecord1 = basicsLoginRecordMapper.selectByCustomerId(customerId);
						returnContent.put("loginTime", DateUtils.formatString(basicsLoginRecord1.getLogintime()));
						returnData.put("returnCode", "1");
						returnData.put("returnContent", returnContent);
						//mapYanzhen.remove(mobileNum);
						//return returnData;				
					}
					return returnData;	
				}else{
					returnData.put("returnCode", "0");
					returnData.put("returnContent", "注册失败");	
					return returnData;	
				}
				
			}else{
				
				// 验证码是否过期
				Date currentTime = new Date();
				if (currentTime.after(tblCustomer3.getEndTime())) {
					// 已过期
					returnData.put("returnCode", "0");
					returnData.put("returnContent", "验证码过期");
					return returnData;
				}
				// 验证成功
				Date date = new Date();
				Long customerId = tblCustomer3.getCustomerId();
				returnContent.put("customerId", customerId + "");
				BasicsLoginRecord basicsLoginRecord = basicsLoginRecordMapper.selectByCustomerId(customerId);
				if (basicsLoginRecord != null) {
					basicsLoginRecord.setLogintime(date);
					basicsLoginRecordMapper.updateByPrimaryKeySelective(basicsLoginRecord);
				} else {
					BasicsLoginRecord basicsLoginRecord1 = new BasicsLoginRecord();
					basicsLoginRecord1.setCustomerId(customerId);
					basicsLoginRecord1.setLogintime(date);
					basicsLoginRecordMapper.insertSelective(basicsLoginRecord1);
				}
				BasicsLoginRecord basicsLoginRecord1 = basicsLoginRecordMapper.selectByCustomerId(customerId);
				returnContent.put("loginTime", DateUtils.formatString(basicsLoginRecord1.getLogintime()));
				returnData.put("returnCode", "1");
				returnData.put("returnContent", returnContent);
				mapYanzhen.remove(mobileNum);
				return returnData;				
			}	
		} else {
			// 手机用户不存在
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "手机用户不存在或者验证码错误！");
			return returnData;
		}
	}
	/**
	 * 商户登录手机验证码验证
	 * 
	 * @param mobileNum
	 * @return
	 */
	public Map<String, Object> verifyLoginUserCodeSend(String mobileNum, String verifyCode) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BasicsUsers basicsUsers = basicsUsersMapper.selectByMobileNum(mobileNum);
		// Gekko 注：测试用
		if (basicsUsers != null) {
			if ("15957135002".equals(mobileNum) && "666666".equals(verifyCode)) {
				returnMap.put("userId", basicsUsers.getUserId() + "");
				returnMap.put("specialRoleNum", "4");// 众包
				returnData.put("returnCode", "1");
				returnData.put("returnContent", returnMap);
				return returnData;
			}
			if ("18657120825".equals(mobileNum) && "666666".equals(verifyCode)) {
				returnMap.put("userId", basicsUsers.getUserId() + "");
				returnMap.put("specialRoleNum", "3");// 商户
				returnData.put("returnCode", "1");
				returnData.put("returnContent", returnMap);
				return returnData;
			}
			if ("13108918396".equals(mobileNum) && "666666".equals(verifyCode)) {
				returnMap.put("userId", basicsUsers.getUserId() + "");
				returnMap.put("specialRoleNum", "1");// 地维
				returnData.put("returnCode", "1");
				returnData.put("returnContent", returnMap);
				return returnData;
			}
		}		

		
		boolean bflag = false;
		for (Date str : mapYanzhen.keySet()) {
	            Date key = str;
	            Map<String,String> valueMap = (Map<String, String>) mapYanzhen.get(key);
	            for(String keyVal:valueMap.keySet()){
	            	String keys=keyVal;
	            	String valus=valueMap.get(keys);
	            	 if(keys.equals(mobileNum) && valus.equals(verifyCode) ){
	            		 bflag=true;
	 	            	mapYanzhen.remove(key);
	 	            }
	            }	          
	        }			
		// 手机用户存在
		if (basicsUsers != null && bflag) {		
			//验证截止时间
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.MINUTE, identifyingTime);
			Date endTime = cal.getTime();						
			
			String CodeType = basicsUsers.getUserType().toString();
			//新增加用户验证码
			TblVerifyCode tblVerifyCode = new TblVerifyCode();
			tblVerifyCode.setCodeNum(verifyCode);
			tblVerifyCode.setCodeType(CodeType);
			tblVerifyCode.setMobileNum(mobileNum);
			tblVerifyCode.setCreateTime(new Date());
			tblVerifyCode.setClosingTime(endTime);
			// 添加之前删除已经发送过的验证码信息
			tblVerifyCodeMapper.deleteVerifyCodeByMobileAndType(mobileNum, CodeType);
			int isSuccessful = tblVerifyCodeMapper.insertSelective(tblVerifyCode);
			
			// 不是众包商从角色表找CodeType
			if (!"3".equals(CodeType)) {
				// 得到当前手机号商户类型
				CodeType = basicsUsersMapper.getSpecialRoleNumByUserMobileNum(basicsUsers.getUserMobile());
				if (CodeType == null) {
					returnData.put("returnCode", "0");
					returnData.put("returnContent", "该用户未分配角色");
					return returnData;
				}
			}
			// Gekko 注：测试用 不验证验证码
			TblVerifyCode verifyCode2 = tblVerifyCodeMapper.selectByMobileNumCode(mobileNum, verifyCode, CodeType);
			if (verifyCode2 == null) {
				// 手机号与验证码不匹配
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "验证码有误");

				return returnData;
			}
			// 验证码是否过期
			Date currentTime = new Date();
			if (currentTime.after(verifyCode2.getClosingTime())) {
				// 已过期
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "验证码过期");

				return returnData;
			}
			Map<String, Object> map = new HashMap<String, Object>();

			if ("3".equals(basicsUsers.getUserType().toString())) {
				map.put("specialRoleNum", "4");// userType=3,CodeType直接返回4
			} else {
				map.put("specialRoleNum", CodeType);// 用户类型
			}
			Date date = new Date();
			Long userId = basicsUsers.getUserId();
			map.put("userId", userId + "");
			BasicsLoginRecord basicsLoginRecord = basicsLoginRecordMapper.selectByUserId(userId);
			if (basicsLoginRecord != null) {
				basicsLoginRecord.setLogintime(date);
				basicsLoginRecordMapper.updateByPrimaryKeySelective(basicsLoginRecord);
			} else {
				BasicsLoginRecord basicsLoginRecord1 = new BasicsLoginRecord();
				basicsLoginRecord1.setUserId(userId);
				basicsLoginRecord1.setLogintime(date);
				basicsLoginRecordMapper.insertSelective(basicsLoginRecord1);
			}
			BasicsLoginRecord basicsLoginRecord1 = basicsLoginRecordMapper.selectByUserId(userId);
			map.put("loginTime", DateUtils.formatString(basicsLoginRecord1.getLogintime()));
			// 验证成功
			returnData.put("returnCode", "1");
			returnData.put("returnContent", map);
		} else {
			// 手机用户不存在
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "手机用户不存在或者验证码错误");
		}

		return returnData;
	}

	/**
	 * 设置密码（app）
	 * 
	 * @param customerId
	 *            用户Id
	 * @param oldPassword
	 *            原密码
	 * @param newPassword
	 *            新密码
	 * @return
	 */
	public Map<String, Object> updatePayPassword(String customerId, String oldPassword, String newPassword) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		TblCustomerVo tblCustomerVo = new TblCustomerVo();
		Long cutmid = Long.valueOf(customerId);

		int i = 0;

		if (!StringUtils.isEmpty(customerId) && !StringUtils.isEmpty(oldPassword)) {
			TblCustomer TblCustomer = tblCustomerMapper.selectByPrimaryKey(cutmid);
			if (!StringUtils.isEmpty(newPassword)) {// 修改密码

				// 判断前端传来的密码和数据库是否相等 (没有加密的和加密后的比较)
				if (TblCustomer != null && TblCustomer.getPayPassword().equals(oldPassword)) {

					tblCustomerVo.setCustomerId(cutmid);
					tblCustomerVo.setPayPassword(newPassword);
					i = tblCustomerMapper.update(tblCustomerVo);
				} else {
					returnData.put("returnCode", "0");
					returnData.put("returnContent", "原密码错误");
					return returnData;
				}

			} else {// 设置密码

				if (TblCustomer != null) {
					TblCustomerLock customerLock = tblCustomerLockMapper.selectByCustomerId(customerId);
					if (customerLock != null) {
						tblCustomerLockMapper.deleteByCustomerId(customerId);
					}
					tblCustomerVo.setCustomerId(cutmid);
					tblCustomerVo.setPayPassword(oldPassword);

					i = tblCustomerMapper.update(tblCustomerVo);
				}
			}

		}

		if (i > 0) {// 修改成功
			returnData.put("returnCode", "1");
			returnData.put("returnContent", "修改成功");
			return returnData;

		}

		returnData.put("returnCode", "0");
		returnData.put("returnContent", "修改失败");
		return returnData;

	}

	/**
	 * 发送手机验证码
	 * 
	 * @param endTime
	 * @param mobileNum
	 * @param identifyingCode
	 */
	private boolean sendMobileVerifyCode(Date endTime, String mobileNum, String identifyingCode) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String closingTime = sdf.format(endTime).toString();
		boolean flag=aliSmsService.sendVerifyCode(mobileNum, identifyingCode.toString(), closingTime);
		return flag;
	}

	/**
	 * 根据APP用户id返回用户详细信息
	 * 
	 * @param customerId
	 *            APP用户id
	 * @return
	 */
	public Map<String, Object> getCustomerDetail(String customerId) {

		Map<String, String> map = tblCustomerMapper.selectByPrimaryKeyMap(Long.valueOf(customerId));
		Map<String, String> tblCustomerInfoMap = tblCustomerInfoMapper.selectByPrimaryKeyMap(Long.valueOf(customerId));

		Map<String, Object> returnData = new HashMap<String, Object>();

		// 手机用户存在
		if (map != null) {
			Map<String, String> returnContent = new HashMap<String, String>();

			returnContent.put("customerId", map.get("customerId"));
			if ("".equals(map.get("customerName")) || map.get("customerName") == null) {
				returnContent.put("nickName", "匿名用户");
			} else {
				returnContent.put("nickName", map.get("customerName"));
			}
			returnContent.put("mobileNum", map.get("customerMobile"));
			returnContent.put("integral", map.get("customerIntegral"));
			returnContent.put("isNewUser", map.get("isNewUser"));

			if (tblCustomerInfoMap != null) {
				returnContent.put("sex", map.get("customerSex"));
				returnContent.put("place", tblCustomerInfoMap.get("nativePlace"));
				returnContent.put("universityName", tblCustomerInfoMap.get("universityName"));
				returnContent.put("grade", tblCustomerInfoMap.get("grade"));
				returnContent.put("dormitory", tblCustomerInfoMap.get("buildingName"));
				returnContent.put("studentCard", tblCustomerInfoMap.get("studentNum"));
			}

			returnData.put("returnCode", "1");
			returnData.put("returnContent", returnContent);

			return returnData;
		} else {
			// 手机用户不存在
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "手机用户不存在");
			return returnData;
		}
	}

	// 保存用户编辑后的用户信息
	public Map<String, Object> saveCustomerInfo(String customerId, String nickName, String mobileNum, String sex,
			String place, String schoolId, String grade, String dormitory, String studentCard) {
		int count = 0; // 判断用户信息是否完善标志位
		TblCustomer tblCustomer = tblCustomerMapper.selectByPrimaryKey(Long.valueOf(customerId));
		TblCustomerInfo tblCustomerInfo = tblCustomerInfoMapper.selectByPrimaryKey(Long.valueOf(customerId));
		Map<String, Object> returnData = new HashMap<String, Object>();
		// 更新Customer表
		if (tblCustomer != null) {
			if (nickName != null) {
				if (!"".equals(nickName)) {
					count++;
				}
				tblCustomer.setCustomerName(nickName);
			}
			tblCustomer.setCustomerMobile(mobileNum);
			if (sex != null) {
				if (!"".equals(sex)) {
					count++;
				}
				tblCustomer.setCustomerSex(sex);
			}
			tblCustomer.setModifyTime(new Date());
			tblCustomer.setModUserId(Long.valueOf(customerId));
			tblCustomer.setIsfirst("1");

			int result = tblCustomerMapper.updateByPrimaryKeySelective(tblCustomer);
			if (result <= 0) {
				// Customer表更新失败
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "更新失败");
				return returnData;
			} else {
				// 更新CustomerInfo表
				if (tblCustomerInfo != null) {
					if (grade != null) {
						if (!"".equals(grade)) {
							count++;
						}
						tblCustomerInfo.setGrade(grade);
					}
					if (place != null) {
						if (!"".equals(place)) {
							count++;
						}
						tblCustomerInfo.setNativePlace(place);
					}
					if (studentCard != null) {
						if (!"".equals(studentCard)) {
							count++;
						}
						tblCustomerInfo.setStudentNum(studentCard);
					}
					if (dormitory != null) {
						if (!"".equals(dormitory)) {
							count++;
						}
						tblCustomerInfo.setBuildingName(dormitory);
					}
					if (schoolId != null) {
						if (!"".equals(schoolId)) {
							count++;
						}
						tblCustomerInfo.setUniversityName(schoolId);
					}
					tblCustomerInfo.setModifyTime(new Date());
					tblCustomerInfo.setModUserId(Long.valueOf(customerId));

					// 若InfoCompleteSymbol已经是1，说明该用户已经过完善资料，否则判断这次修改信息是否完善全部资料
					if (!"1".equals(tblCustomer.getInfoCompleteSymbol())) {
						if (count == 7) {
							tblCustomer.setInfoCompleteSymbol("1");
							tblCustomerMapper.updateByPrimaryKeySelective(tblCustomer);// 先更新tblCustomer表内信息
						} else {
							tblCustomer.setInfoCompleteSymbol("0");
							tblCustomerMapper.updateByPrimaryKeySelective(tblCustomer);// 先更新tblCustomer表内信息
						}
					}
					// 再更新tblCustomerInfo表内信息
					int result2 = tblCustomerInfoMapper.updateByPrimaryKeySelective(tblCustomerInfo);
					if (result2 <= 0) {
						// Customer更新失败
						returnData.put("returnCode", "0");
						returnData.put("returnContent", "更新失败");
						return returnData;
					} else {
						// Customer更新成功
						returnData.put("returnCode", "1");
						returnData.put("returnContent", "");
					}
					return returnData;
				} else {
					// 手机用户不存在则新增
					TblCustomerInfo tblCustomerInfo2 = new TblCustomerInfo();
					tblCustomerInfo2.setCustomerId(Long.valueOf(customerId));
					if (grade != null) {
						if (!"".equals(grade)) {
							count++;
						}
						tblCustomerInfo2.setGrade(grade);
					}
					if (place != null) {
						if (!"".equals(place)) {
							count++;
						}
						tblCustomerInfo2.setNativePlace(place);
					}
					if (studentCard != null) {
						if (!"".equals(studentCard)) {
							count++;
						}
						tblCustomerInfo2.setStudentNum(studentCard);
					}
					if (dormitory != null) {
						if (!"".equals(dormitory)) {
							count++;
						}
						tblCustomerInfo2.setBuildingName(dormitory);
					}
					if (schoolId != null) {
						if (!"".equals(schoolId)) {
							count++;
						}
						tblCustomerInfo2.setUniversityName(schoolId);
					}
					tblCustomerInfo2.setCreateTime(new Date());
					tblCustomerInfo2.setModifyTime(new Date());
					tblCustomerInfo2.setCreateUserId(Long.valueOf(customerId));
					tblCustomerInfo2.setModUserId(Long.valueOf(customerId));

					if (!"1".equals(tblCustomer.getInfoCompleteSymbol())) {
						if (count == 7) {
							tblCustomer.setInfoCompleteSymbol("1");
							tblCustomerMapper.updateByPrimaryKeySelective(tblCustomer);
						} else {
							tblCustomer.setInfoCompleteSymbol("0");
							tblCustomerMapper.updateByPrimaryKeySelective(tblCustomer);
						}
					}

					int result2 = tblCustomerInfoMapper.insertSelective(tblCustomerInfo2);
					if (result2 <= 0) {
						// Customer添加失败
						returnData.put("returnCode", "0");
						returnData.put("returnContent", "添加失败");

						return returnData;
					} else {
						// Customer添加成功
						returnData.put("returnCode", "1");
						returnData.put("returnContent", "");
					}
					return returnData;
				}

			}
		} else {
			// customer不存在则新增
			TblCustomer tblCustomer2 = new TblCustomer();
			tblCustomer2.setCustomerId(Long.valueOf(customerId));
			if (nickName != null) {
				tblCustomer2.setCustomerName(nickName);
			}
			tblCustomer2.setCustomerMobile(mobileNum);
			if (sex != null) {
				tblCustomer2.setCustomerSex(sex);
			}
			tblCustomer2.setIsdel("N");
			tblCustomer2.setCreateTime(new Date());
			tblCustomer2.setModifyTime(new Date());
			tblCustomer2.setCreateUserId(Long.valueOf(customerId));
			tblCustomer2.setModUserId(Long.valueOf(customerId));
			tblCustomer2.setIsfirst("0");
			tblCustomer2.setDiscountUsedSymbol("0");
			tblCustomer2.setCustomerMoney(new BigDecimal(0));

			if (StringUtils.isNoneBlank(customerId, mobileNum, nickName, sex, place, schoolId, grade, dormitory,
					studentCard)) {
				tblCustomer2.setInfoCompleteSymbol("1");
			}
			int result = tblCustomerMapper.insertSelective(tblCustomer2);

			TblCustomerInfo tblCustomerInfo2 = new TblCustomerInfo();
			tblCustomerInfo2.setCustomerId(Long.valueOf(customerId));
			if (place != null) {
				tblCustomerInfo2.setNativePlace(place);
			}
			if (schoolId != null) {
				tblCustomerInfo2.setUniversityName(schoolId);
			}
			if (grade != null) {
				tblCustomerInfo2.setGrade(grade);
			}
			if (dormitory != null) {
				tblCustomerInfo2.setBuildingName(dormitory);
			}
			if (studentCard != null) {
				tblCustomerInfo2.setStudentNum(studentCard);
			}
			tblCustomerInfo2.setCreateTime(new Date());
			tblCustomerInfo2.setModifyTime(new Date());
			tblCustomerInfo2.setCreateUserId(Long.valueOf(customerId));
			tblCustomerInfo2.setModUserId(Long.valueOf(customerId));

			int result2 = tblCustomerInfoMapper.insertSelective(tblCustomerInfo2);
			if (result > 0 && result2 > 0) {
				// Customer和CustomerInfo添加成功
				returnData.put("returnCode", "1");
				returnData.put("returnContent", "");
			} else {
				// Customer和CustomerInfo添加失败
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "添加失败");
			}
			return returnData;
		}

	}

	// 发送短信方法
	public Map<String, Object> commitFeedBack(String mobileNum, String feedBackContent) {
		TblCustomer customer = tblCustomerMapper.selectByMobileNum(mobileNum);
		TblCustomerQuestion tblCustomerQuestion = new TblCustomerQuestion();
		Map<String, Object> returnData = new HashMap<String, Object>();
		// 新增
		if (customer != null) {
			tblCustomerQuestion.setCustomerId(customer.getCustomerId());
			tblCustomerQuestion.setMobile(mobileNum);
			tblCustomerQuestion.setQuestion(feedBackContent);
			tblCustomerQuestion.setCreateTime(new Date());
			tblCustomerQuestion.setModifyTime(new Date());
			tblCustomerQuestion.setModUserId(customer.getCustomerId());

			int result = tblCustomerQuestionMapper.insertSelective(tblCustomerQuestion);
			// 更新成功
			if (result > 0) {
				returnData.put("returnCode", "1");
				returnData.put("returnContent", "");
			} else {
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "提交失败");
			}
			return returnData;
		}
		// 未找到手机号
		else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "提交失败");
			return returnData;
		}
	}

	/**
	 * 发送用户短信找回密码
	 * 
	 * @param mobileNum
	 * @return
	 */
	public Map<String, Object> forgetPasswordSendMessage(String mobileNum, Long customerId) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag = false;
		Pattern regex = Pattern.compile("^1[3|4|5|7|8|9]\\d{9}$");
		Matcher matcher = regex.matcher(mobileNum);
		flag = matcher.matches();
		if (!flag) {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "手机号格式有误");
			return returnData;
		}

		TblCustomer tblCustomer = tblCustomerMapper.selectByPrimaryKey(customerId);
		if (tblCustomer != null) {
			if (!tblCustomer.getCustomerMobile().equals(mobileNum)) {
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "手机号码与用户绑定的手机号码不一致");
				return returnData;
			}
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "发送验证码失败，请稍后再试");
			return returnData;
		}
		Map<String, String> returnContent = new HashMap<String, String>();
		// 生成六位随机数
		String verifyCode = VerifyCode.createVerifyCode();
		// 验证截止时间
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MINUTE, identifyingTime);
		Date endTime = cal.getTime();
		// 获取当前时间
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());
		Date createtime = cal1.getTime();

		// 手机用户不存在，则新增用户
		TblVerifyCode tblVerifyCode = new TblVerifyCode();
		tblVerifyCode.setCodeNum(verifyCode);
		tblVerifyCode.setCodeType("3");
		tblVerifyCode.setMobileNum(mobileNum);
		tblVerifyCode.setCreateTime(createtime);
		tblVerifyCode.setClosingTime(endTime);
		// 添加信息时删除之前已经发送过的验证码信息
		tblVerifyCodeMapper.deleteByMobile(mobileNum);
		int isSuccessful = tblVerifyCodeMapper.insertSelective(tblVerifyCode);
		// 新增成功后发送手机验证码
		if (isSuccessful > 0) {
			forgetPasswordSendMessage(endTime, mobileNum, verifyCode);
		}
		returnContent.put("code", verifyCode);
		returnData.put("returnCode", "1");
		returnData.put("returnContent", returnContent);

		return returnData;
	}

	/**
	 * 发送手机验证码
	 * 
	 * @param endTime
	 * @param mobileNum
	 * @param identifyingCode
	 */
	private boolean forgetPasswordSendMessage(Date endTime, String mobileNum, String identifyingCode) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String closingTime = sdf.format(endTime).toString();
		boolean bool=aliSmsService.forgetPasswordSendMessage(mobileNum, identifyingCode.toString(), closingTime);
		return bool;
	}

	/**
	 * 忘记密码验证码验证接口（APP）
	 */
	public Map<String, Object> verifyForgetPasswordSendCode(String customerId, String code) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		/*
		 * if("15837340848".equals(mobileNum) && "666666".equals(verifyCode)){
		 * TblCustomer tblCustomer =
		 * tblCustomerMapper.selectByMobileNum(mobileNum);
		 * returnContent.put("customerId", tblCustomer.getCustomerId()+"");
		 * returnData.put("returnCode", "1"); returnData.put("returnContent",
		 * returnContent); return returnData; }
		 */
		TblCustomer tblCustomer = tblCustomerMapper.selectByPrimaryKey(Long.valueOf(customerId));

		// 用户存在
		if (tblCustomer != null) {

			TblVerifyCode tblVerifyCode = tblVerifyCodeMapper.selectByCodeMobileNum(tblCustomer.getCustomerMobile(),
					code);
			if (tblVerifyCode == null) {
				// 手机号与验证码不匹配
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "用户不存在或验证码有误");

				return returnData;
			}
			// 验证码是否过期
			Date currentTime = new Date();
			if (currentTime.after(tblVerifyCode.getClosingTime())) {
				// 已过期
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "验证码过期");

				return returnData;
			}
			// 验证成功
			returnData.put("returnCode", "1");
			returnData.put("returnContent", "");

			return returnData;
		} else {
			// 用户不存在
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "用户不存在");

			return returnData;
		}
	}

	/**
	 * 支付3次密码错误锁定计时接口（APP）
	 */
	public Map<String, Object> lockPassword(String customerId) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Date currentTime = new Date();
		String surplusTimes;
		TblCustomerLock customerLock = tblCustomerLockMapper.selectByCustomerId(customerId);

		if (customerLock == null) {
			Date lockStartTime = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(lockStartTime);
			c.add(Calendar.MINUTE, 60);
			Date lockEndTime = c.getTime();
			TblCustomerLock tblCustomerLock = new TblCustomerLock();
			tblCustomerLock.setCustomerId(Long.valueOf(customerId));
			tblCustomerLock.setLockStartTime(lockStartTime);
			tblCustomerLock.setLockEndTime(lockEndTime);
			int result = tblCustomerLockMapper.insertSelective(tblCustomerLock);
			surplusTimes = "60";
			if (result > 0) {
				returnData.put("returnCode", "1");
				returnData.put("returnContent", surplusTimes + "分");
			} else {
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "锁定失败");
			}
		} else {
			if (currentTime.after(customerLock.getLockEndTime())) {
				surplusTimes = "0";
				int result = tblCustomerLockMapper.deleteByCustomerId(customerId);
				if (result > 0) {
					returnData.put("returnCode", "1");
					returnData.put("returnContent", surplusTimes);
				} else {
					returnData.put("returnCode", "0");
					returnData.put("returnContent", "锁定失败");
				}
			} else {
				SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
				String nowtime = d.format(new Date());// 按以上格式 将当前时间转换成字符串
				String lockEndTime = d.format(customerLock.getLockEndTime()); // 锁定截至时间

				long result;
				try {
					result = (d.parse(lockEndTime).getTime() - d.parse(nowtime).getTime()) / 60000;
					if (result == 0) {
						result = (d.parse(lockEndTime).getTime() - d.parse(nowtime).getTime()) / 1000;
						returnData.put("returnCode", "1");
						returnData.put("returnContent", String.valueOf(result) + "秒");
					} else {
						returnData.put("returnCode", "1");
						returnData.put("returnContent", String.valueOf(result) + "分");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} // 当前时间减去测试时间 // 这个的除以1000得到秒，相应的60000得到分，3600000得到小时

			}
		}

		return returnData;

	}

	public Map<String, Object> pushMessage(JSONObject res) {
		Map<String, Object> map = new HashMap<String, Object>();
		String APPType = res.getString("appType");
		Date date = new Date();
		String appId = "";
		if (StringUtils.equals(APPType, "2")) {
			Long customerId = res.getLong("customerId");
			date = basicsLoginRecordMapper.selectByCustomerId(customerId).getLogintime();
			appId = customerId.toString();
		} else {
			Long userId = res.getLong("userId");
			date = basicsLoginRecordMapper.selectByUserId(userId).getLogintime();
			appId = userId.toString();
		}
		boolean flag = TimingPush.testSendPush7(Integer.parseInt(APPType), DateUtils.formatString(date), appId);		
		if (!flag) {
			map.put("returnCode", "0");
			map.put("returnContent", "发送推送信息失败");
			return map;
		}
		map.put("returnCode", "1");
		map.put("returnContent", "发送推送信息成功");
		return map;
	}
}
