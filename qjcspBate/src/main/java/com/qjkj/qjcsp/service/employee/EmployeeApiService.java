package com.qjkj.qjcsp.service.employee;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsRole;
import com.qjkj.qjcsp.entity.BasicsSpecialRole;
import com.qjkj.qjcsp.entity.BasicsUserMachine;
import com.qjkj.qjcsp.entity.BasicsUserRole;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.TblVerifyCode;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsRoleMapper;
import com.qjkj.qjcsp.mapper.BasicsSpecialRoleMapper;
import com.qjkj.qjcsp.mapper.BasicsUserMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsUserRoleMapper;
import com.qjkj.qjcsp.mapper.BasicsUsersMapper;
import com.qjkj.qjcsp.mapper.TblVerifyCodeMapper;
import com.qjkj.qjcsp.service.alisms.AliSmsService;
import com.qjkj.qjcsp.util.DateFormat;
import com.qjkj.qjcsp.util.DateTimeUtil;
import com.qjkj.qjcsp.util.VerifyCode;

import net.sf.json.JSONObject;


@Component
@Transactional
public class EmployeeApiService {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeApiService.class);
	
	private static final int availableTime = 3;//有效时间为3分钟
	
	@Autowired
	private BasicsUserMachineMapper basicsUserMachineMapper;
	
	@Autowired
	private AliSmsService aliSmsService;
	
	@Autowired
	private TblVerifyCodeMapper tblVerifyCodeMapper;
	
	@Autowired
	private BasicsUsersMapper basicsUsersMapper;
	
	@Autowired
	private BasicsRoleMapper basicsRoleMapper;
	
	@Autowired
	private BasicsUserRoleMapper basicsUserRoleMapper;
	
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	/**
	 * 维修人员或补货员手机验证码发送
	 * @param res
	 * @return
	 */
	public Map<String, Object> verifyEmployeeCodeSend(JSONObject res){
		Map<String, Object> returnData = new HashMap<String, Object>();
		/*设备硬件id*/
		String deviceCode = res.getString("deviceCode");
		/*手机号*/
		String mobileNum = res.getString("mobileNum");
		
		if (StringUtils.isNoneBlank(deviceCode, mobileNum)){
			/*判断维修人员或补货员是否存在*/
			int count =  basicsUserMachineMapper.isExistbyDeviceCodeAndMobileNum(basicsMachineMapper.getMachineIdByDeviceCode(deviceCode), mobileNum);
			if (count > 0){
				sendSmsAndSaveVerifyCode(deviceCode, mobileNum);
				returnData.put("returnCode", "1");
				returnData.put("returnContent", "");
			}else {
				returnData.put("returnCode", "2");
				returnData.put("returnContent", "该手机号对应人员未绑定该设备");
			}
		}else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
		}
		
		return returnData;
	}
	
	private void sendSmsAndSaveVerifyCode(String deviceCode, String mobileNum){
		Date createTime = new Date();
		/*验证截止时间*/
		Date closingTime = DateTimeUtil.getPlusMinuteDate(createTime, availableTime);
		/*验证码*/
		String verifyCode = VerifyCode.createVerifyCode();
		/*发送验证码短信*/
//		aliSmsService.sendVerifyCode(mobileNum, verifyCode, DateFormat.dateFormatYMDHMS(closingTime));
		aliSmsService.sendMachineVerifyCode(mobileNum, verifyCode, DateFormat.dateFormatYMDHMS(closingTime));
		TblVerifyCode tblVerifyCode = new TblVerifyCode();
		tblVerifyCode.setCodeNum(verifyCode);
		tblVerifyCode.setMobileNum(mobileNum);
		tblVerifyCode.setCreateTime(createTime);
		tblVerifyCode.setClosingTime(closingTime);
		/*判断补货人员、维修人员、商户*/
		BasicsUserMachine userMachine= basicsUserMachineMapper.queryUserbyDeviceCodeAndMobile(basicsMachineMapper.getMachineIdByDeviceCode(deviceCode), mobileNum);
		if (StringUtils.equals(userMachine.getSpecialRoleNum(), "1")){
			/*维修人员*/
			tblVerifyCode.setCodeType("1");
			tblVerifyCodeMapper.deleteVerifyCodeByMobileAndType(mobileNum, "1");
		} else if (StringUtils.equals(userMachine.getSpecialRoleNum(), "2")){
			/*补货人员*/
			tblVerifyCode.setCodeType("2");
			tblVerifyCodeMapper.deleteVerifyCodeByMobileAndType(mobileNum, "2");
		} else if (StringUtils.equals(userMachine.getSpecialRoleNum(), "3")){
			/*商户*/
			tblVerifyCode.setCodeType("4");
			tblVerifyCodeMapper.deleteVerifyCodeByMobileAndType(mobileNum, "4");
		}

		tblVerifyCodeMapper.insertSelective(tblVerifyCode);
	}

	
	/**
	 * 维修人员/补货员验证
	 */
	public Map<String, Object> verifyEmployeeCode(String deviceCode, String mobileNum, String code) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> returnCode = new HashMap<String, Object>();
		TblVerifyCode tblVerifyCode = tblVerifyCodeMapper.getCountByCodeMobileNum(mobileNum,code);
		if(tblVerifyCode != null){
			Date currentTime = new Date();
			if (currentTime.after(tblVerifyCode.getClosingTime())){
				//已过期
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "验证码过期");
				return returnData;
			}
			
			//查询用户特殊角色标识（先判断是否是众包商）
			List<Map<String, Object>> roleList = basicsRoleMapper.findSpecialNumByMobile(mobileNum);
			for (Map<String, Object> map : roleList) {
				if("3".equals((String)map.get("specialRoleNum"))){
					returnCode.put("userId", String.valueOf(map.get("userId")));
					String specialRoleNum = "3";
					returnCode.put("specialRoleNum", specialRoleNum);
					returnData.put("returnContent", returnCode);
					returnData.put("returnCode", "1");
					return returnData;
				}
			}
			
			//从设备用户关联表内查询用户特殊角色
			List<BasicsUserMachine> list = basicsUserMachineMapper.findByMobileNumUserId(basicsMachineMapper.getMachineIdByDeviceCode(deviceCode),mobileNum);
			
			for (BasicsUserMachine basicsUserMachine : list) {
				if("1".equals(basicsUserMachine.getSpecialRoleNum())){
					returnCode.put("userId", String.valueOf(basicsUserMachine.getUserId()));
					String specialRoleNum = "1";
					returnCode.put("specialRoleNum", specialRoleNum);
					returnData.put("returnContent", returnCode);
					returnData.put("returnCode", "1");
					return returnData;
				}
				else if("2".equals(basicsUserMachine.getSpecialRoleNum())){
					returnCode.put("userId", String.valueOf(basicsUserMachine.getUserId()));
					String specialRoleNum = "2";
					returnCode.put("specialRoleNum", specialRoleNum);
					returnData.put("returnContent", returnCode);
					returnData.put("returnCode", "1");
				}
				/*else if("3".equals(basicsUserMachine.getSpecialRoleNum())){
					returnCode.put("userId", String.valueOf(basicsUserMachine.getUserId()));
					String specialRoleNum = "3";
					returnCode.put("specialRoleNum", specialRoleNum);
					returnData.put("returnContent", returnCode);
					returnData.put("returnCode", "1");
				}*/
				else{
					returnData.put("returnCode", "0");
					returnData.put("returnContent", "不是供货、维修、商户人员");
				}
				
			}
			/*for (BasicsUserMachine basicsUserMachine : list) {
				sb.append(basicsUserMachine.getSpecialRoleNum());
			}
			if(sb.toString().indexOf("1") != -1){
				returnCode.put("userId", String.valueOf(userId));
				String specialRoleNum = "1";
				returnCode.put("specialRoleNum", specialRoleNum);
				returnData.put("returnContent", returnCode);
				returnData.put("returnCode", "1");
			}
			else if(sb.toString().indexOf("2") != -1 && sb.toString().indexOf("1") == -1){
				returnCode.put("userId", String.valueOf(userId));
				String specialRoleNum = "2";
				returnCode.put("specialRoleNum", specialRoleNum);
				returnData.put("returnContent", returnCode);
				returnData.put("returnCode", "1");
			}
			else{
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "不是供货维修人员");
			}*/
			
		}
		else{
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "验证码有误");
			return returnData;
		}
		return returnData;
	}
	
}
	
	

