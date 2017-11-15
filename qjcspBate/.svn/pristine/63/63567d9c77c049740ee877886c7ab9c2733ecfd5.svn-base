package com.qjkj.qjcsp.service.employee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsUserMachine;
import com.qjkj.qjcsp.entity.BasicsUserMachineLog;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsUserMachineLogMapper;
import com.qjkj.qjcsp.mapper.BasicsUserMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsUserRoleMapper;
import com.qjkj.qjcsp.mapper.BasicsUsersMapper;
import com.qjkj.qjcsp.mapper.ShiroUserMapper;
import com.qjkj.qjcsp.util.Constants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
@Transactional
public class EmployeeService {
	public static final char  delFlag = '0'; //删除标识
	public static final char  succFlag = '1'; 
	private static final int identifyingTime = 3;	//验证码有效期限3分钟
	
	private static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	private BasicsUsersMapper basicsUsersMapper;
	@Autowired
	private BasicsUserMachineMapper basicsUserMachineMapper;
	@Autowired
	private BasicsUserMachineLogMapper basicsUserMachineLogMapper;
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	@Autowired
	private BasicsUserRoleMapper basicsUserRoleMapper;
	
	/*@Autowired
	private MachineBoardAreaMapper machineBoardAreaMapper;
	@Autowired
	private DishesMapper dishesMapper;
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private OrdersMapper ordersMapper;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Autowired
	private SellingMachinesMapper sellingMachinesMapper;
	
	@Autowired
	private ActivityLogService activityLogService;
	
	@Autowired
	private ActivityBargainMapper activityBargainMapper;*/
	
	/**
	 * 查找该登录用户的供货员
	 */
	public Page<Map<String,Object>> findAllEmployeeList(Map<String, Object> map , int pageNumber,int pageSize) {
		int companyId = Constants.getCurrendUser().getCompanyId();
		map.put("companyId", companyId);
		Long total = basicsUsersMapper.getCount(map);
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		map.put("offset", (pageNumber - 1) * pageSize);
		map.put("limit", pageSize);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if (total != 0) {
			list = basicsUsersMapper.findAllEmployeeList(map);
		}
		Page<Map<String,Object>> page = new PageImpl<Map<String,Object>>(list,pageRequest, total);
		return page;
	}
	
	/**
	 * 供货员保存设置
	 */
	public boolean saveEmployeeMachine(Long userId,String isCheckedIds)
	{ 	
		BasicsUserMachine basicsUserMachine = new BasicsUserMachine();
		String specialRoleNum = basicsUserRoleMapper.getSpecialRoleNumByUserId(userId);
		basicsUserMachineLogMapper.updateOperation0(userId);
		if(basicsUserMachineMapper.judgeExist(userId,specialRoleNum)>0){
			int result = basicsUserMachineMapper.delUserMachine(userId,specialRoleNum);
			if(result > 0){
				if("".equals(isCheckedIds)){
					return true;
				}
				else{
					String[] deviceCode = isCheckedIds.split(",");
					for(int i=0; i<deviceCode.length; i++){
						long machineId = basicsMachineMapper.getMachineIdByDeviceCode(deviceCode[i]);
						basicsUserMachine.setSpecialRoleNum(specialRoleNum);
						basicsUserMachine.setUserId(userId);
						basicsUserMachine.setMachineId(machineId);
						basicsUserMachineMapper.insertSelective(basicsUserMachine);
						log(basicsUserMachine);
					}
				}
				
			}else{
				return false;
			}
		}else{
			if("".equals(isCheckedIds)){
				return true;
			}
			else{
				String[] deviceCode = isCheckedIds.split(",");
				for(int i=0; i<deviceCode.length; i++){
					long machineId = basicsMachineMapper.getMachineIdByDeviceCode(deviceCode[i]);
					basicsUserMachine.setSpecialRoleNum(specialRoleNum);
					basicsUserMachine.setUserId(userId);
					basicsUserMachine.setMachineId(machineId);
					basicsUserMachineMapper.insertSelective(basicsUserMachine);
					log(basicsUserMachine);
				}
			}
		}
		return true;
	}
	
	private void log(BasicsUserMachine basicsUserMachine){
		BasicsUserMachineLog basicsUserMachineLog = new BasicsUserMachineLog();
		if(basicsUserMachineLogMapper.getCount(basicsUserMachine) > 0){
			basicsUserMachineLog.setUserId(basicsUserMachine.getUserId());
			//basicsUserMachineLog.setDeviceCode(basicsUserMachine.getDeviceCode());
			basicsUserMachineLog.setMachineId(basicsUserMachine.getMachineId());
			basicsUserMachineLog.setOperation("1");
			basicsUserMachineLog.setOperationTime(new Date());
			basicsUserMachineLog.setOperationUserId((long)Constants.getCurrendUser().getUserId());
			basicsUserMachineLogMapper.updateOperation1(basicsUserMachineLog);
		}
		else{
			basicsUserMachineLog.setUserId(basicsUserMachine.getUserId());
			//basicsUserMachineLog.setDeviceCode(basicsUserMachine.getDeviceCode());
			basicsUserMachineLog.setMachineId(basicsUserMachine.getMachineId());
			basicsUserMachineLog.setOperation("1");
			basicsUserMachineLog.setOperationTime(new Date());
			basicsUserMachineLog.setOperationUserId((long)Constants.getCurrendUser().getUserId());
			basicsUserMachineLogMapper.insert(basicsUserMachineLog);
		}
		
	}
	/**
	 * 查找该登录用户的维修员
	 */
	public Page<BasicsUsers> findAllMaintenanceList(Map<String, Object> map , int pageNumber,int pageSize) {
		int companyId = Constants.getCurrendUser().getCompanyId();
		map.put("companyId", companyId);
		Long total = basicsUsersMapper.getCountByMaintenance(map);
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		map.put("offset", (pageNumber - 1) * pageSize);
		map.put("limit", pageSize);
		List<BasicsUsers> list = new ArrayList<BasicsUsers>();
		if (total != 0) {
			list = basicsUsersMapper.findAllMaintenanceList(map);
		}
		Page<BasicsUsers> page = new PageImpl<BasicsUsers>(list,pageRequest, total);
		return page;
	}
	
	/**
	 * 维修员保存设置
	 */
	public boolean saveMaintenanceMachine(Long userId,String isCheckedIds)
	{ 	
		BasicsUserMachine basicsUserMachine = new BasicsUserMachine();
		String specialRoleNum = "1";
		/*basicsUserMachineLogMapper.updateOperation0(userId);*/
		if(basicsUserMachineMapper.judgeExist(userId,specialRoleNum)>0){
			int result = basicsUserMachineMapper.delUserMachine(userId,specialRoleNum);
			if("".equals(isCheckedIds)){
				return true;
			}
			if(result > 0){
				String[] deviceCode = isCheckedIds.split(",");
				for(int i=0; i<deviceCode.length; i++){
					long machineId = basicsMachineMapper.getMachineIdByDeviceCode(deviceCode[i]);
					basicsUserMachine.setSpecialRoleNum("1");
					basicsUserMachine.setUserId(userId);
					//basicsUserMachine.setDeviceCode(deviceCode[i]);
					basicsUserMachine.setMachineId(machineId);
					basicsUserMachineMapper.insertSelective(basicsUserMachine);
					log(basicsUserMachine);
				}
			}
			else{
				return false;
			}
		}
		else{
			if("".equals(isCheckedIds)){
				return true;
			}
			String[] deviceCode = isCheckedIds.split(",");
			for(int i=0; i<deviceCode.length; i++){
				long machineId = basicsMachineMapper.getMachineIdByDeviceCode(deviceCode[i]);
				basicsUserMachine.setSpecialRoleNum("1");
				basicsUserMachine.setUserId(userId);
				//basicsUserMachine.setDeviceCode(deviceCode[i]);
				basicsUserMachine.setMachineId(machineId);
				basicsUserMachineMapper.insertSelective(basicsUserMachine);
				log(basicsUserMachine);
			}
		}
		return true;
	}
	
}
