package com.qjkj.qjcsp.service.machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsCompany;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.BasicsMachineCell;
import com.qjkj.qjcsp.entity.BasicsMachineModel;
import com.qjkj.qjcsp.entity.GMachineZone;
import com.qjkj.qjcsp.entity.TblDeviceOrderAmount;
import com.qjkj.qjcsp.entity.enums.DataStatusEnum;
import com.qjkj.qjcsp.entity.viewmodel.BasicsMachineVo;
import com.qjkj.qjcsp.mapper.BasicsCompanyMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineCellMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineModelMapper;
import com.qjkj.qjcsp.mapper.GMachineZoneMapper;
import com.qjkj.qjcsp.mapper.TblDeviceOrderAmountMapper;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.UserUtils;

@Service
@Transactional
public class MachineInfoService {

	@Autowired
	private BasicsMachineMapper basicsMachineMapper;

	@Autowired
	private BasicsMachineModelMapper basicsMachineModelMapper;

	@Autowired
	private BasicsMachineCellMapper basicsMachineCellMapper;

	@Autowired
	private TblDeviceOrderAmountMapper tblDeviceOrderAmountMapper;

	@Autowired
	private GMachineZoneMapper gMachineZoneMapper;
	@Autowired
	private BasicsCompanyMapper basicsCompanyMapper;

	/**
	 * 设备信息跳转页 判断当前登录用户是否属于全家科技公司
	 */
	public String listMachineInfoMain() {
		UserUtils userUtils = new UserUtils();
		if (userUtils.isUserofQJKJ() == true) {
			return "/machineInfo/listMachineInfoMainQJKJ";
		} else {
			return "/machineInfo/listMachineInfoMain";
		}
	}

	/**
	 * 设备信息添加跳转页 判断当前登录用户是否属于全家科技公司
	 */
	public String addInfoMain(ServletRequest request) {
		UserUtils userUtils = new UserUtils();
		if (userUtils.isUserofQJKJ() == true) {
			return "/machineInfo/addInfoMainQJKJ";
		} else {
			return "/machineInfo/addInfoMain";
		}
	}

	/**
	 * 设备信息编辑跳转页 判断当前登录用户是否属于全家科技公司
	 */
	public String editInfoMain(ServletRequest request, Long machineId) {
		BasicsMachine bm = findBasicsMachine(request, machineId);
		if (null == bm.getProvCode()) {
			bm.setProvCode("");
		}
		if (null == bm.getCityCode()) {
			bm.setCityCode("");
		}
		if (null == bm.getAreaCode()) {
			bm.setAreaCode("");
		}
		request.setAttribute("basicsMachine", bm);
		UserUtils userUtils = new UserUtils();
		if (userUtils.isUserofQJKJ() == true) {
			return "/machineInfo/editInfoMainQJKJ";
		} else {
			return "/machineInfo/editInfoMain";
		}
	}

	/**
	 * 查询所有设备信息
	 * 
	 * @param param
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<BasicsMachineVo> findInfoPage(Map<String, Object> param, int pageNumber, int pageSize) {
		param.put("state", DataStatusEnum.NORMAL.getValue());

		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);

		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);
		param.put("order", "machine_id");
		param.put("sort", Sort.Direction.DESC);

		List<BasicsMachineVo> list = new ArrayList<BasicsMachineVo>();
		/* 判断当前用户是否属于全家科技公司 */
		Long total = 0L;
		UserUtils userUtils = new UserUtils();
		if (userUtils.isUserofQJKJ() == true) {
			/* 属于 */
			if (param.get("companyName") != null) {
				param.put("companyName", StringUtils.trimToNull(param.get("companyName").toString()));
			}

			total = basicsMachineMapper.findByCount(param);
			if (total != 0) {
				list = basicsMachineMapper.findByList(param);
			}
		} else {
			/* 不属于 */
			param.put("companyId", userUtils.getCompanyId());
			total = basicsMachineMapper.findByCount(param);
			if (total != 0) {
				list = basicsMachineMapper.findByList(param);
			}
		}

		Page<BasicsMachineVo> page = new PageImpl<BasicsMachineVo>(list, pageRequest, total);

		return page;
	}

	/**
	 * 添加设备信息
	 * 
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> addInfo(ServletRequest request, BasicsMachine basicsMachine) {
		Map<String, Object> map = new HashMap<String, Object>();
		/* 判断设备是否存在 */
		if (basicsMachineMapper.selectByDeviceCode(basicsMachine.getDeviceCode()) != null) {
			map.put("message", "该设备已存在!");
			map.put("status", Boolean.FALSE);

			return map;
		}
		/* 根据设备型号id查找设备型号 */
		BasicsMachineModel bmm = basicsMachineModelMapper.selectByPrimaryKey(basicsMachine.getModelId());
		/* 新增设备信息 */
		UserUtils userUtils = new UserUtils();
		if (userUtils.isUserofQJKJ() == Boolean.FALSE) {
			basicsMachine.setCompanyId((long) userUtils.getCompanyId());
		}

		basicsMachine.setcreateUserId((long) userUtils.getUserId());
		basicsMachine.setcreateTime(new Date());
		basicsMachine.setModUserId((long) userUtils.getUserId());
		basicsMachine.setLastModTime(new Date());
		basicsMachine.setModelType(bmm.getModelType());
		basicsMachine.setMachineRow(bmm.getRowNum());
		basicsMachine.setMachineColumn(bmm.getColumnNum());
		basicsMachine.setTolerance(bmm.getTolerance());
		basicsMachine.setTargetTemperature(bmm.getTargetTemperature());

		basicsMachineMapper.insertSelective(basicsMachine);
		/* 新增设备格子信息 */
		List<BasicsMachineCell> basicsMachineCellList = new ArrayList<BasicsMachineCell>();
		/* 格子序号 */
		int cellNum = 1;
		/* 故障格子序号 */
		List<String> troubleCellList = Arrays.asList(Constants.troubleCellNum);
		for (Short i = 1; i <= bmm.getRowNum(); i++) {
			for (Short j = 1; j <= bmm.getColumnNum(); j++) {
				BasicsMachineCell basicsMachineCell = new BasicsMachineCell();
				basicsMachineCell.setCellId(UUID.randomUUID().toString());
				basicsMachineCell.setMachineId(basicsMachine.getMachineId());
				basicsMachineCell.setRowNum(i);
				basicsMachineCell.setColNum(j);
				basicsMachineCell.setCellNum(cellNum);
				if (troubleCellList.contains(cellNum+"")){
					basicsMachineCell.setCellStatus("2");
				} else {
					basicsMachineCell.setCellStatus("0");
				}

				basicsMachineCellList.add(basicsMachineCell);
				cellNum++;
			}
		}
		basicsMachineCellMapper.addMachineCellByList(basicsMachineCellList);
		/* 新增设备月售份数 */
		int max = 2000;
		int min = 1000;

		Random random = new Random();
		int result = random.nextInt(max) % (max - min + 1) + min;
		result = 0;
		TblDeviceOrderAmount tblDeviceOrderAmount = new TblDeviceOrderAmount();
		tblDeviceOrderAmount.setMachineName(basicsMachine.getMachineName());
		tblDeviceOrderAmount.setMachineId(basicsMachine.getMachineId());
		tblDeviceOrderAmount.setOrderAmount(result);

		tblDeviceOrderAmountMapper.insertSelective(tblDeviceOrderAmount);

		map.put("message", "添加设备信息成功!");
		map.put("status", Boolean.TRUE);

		return map;
	}

	/**
	 * 编辑设备信息
	 * 
	 * @param request
	 * @param basicsMachine
	 * @return
	 */
	public Map<String, Object> editInfo(ServletRequest request, BasicsMachine basicsMachine) {
		Map<String, Object> map = new HashMap<String, Object>();

		UserUtils userUtils = new UserUtils();
		basicsMachine.setModUserId((long) userUtils.getUserId());
		basicsMachine.setLastModTime(new Date());

		/* 根据设备型号id查找设备型号 */
		BasicsMachineModel bmm = basicsMachineModelMapper.selectByPrimaryKey(basicsMachine.getModelId());
		basicsMachine.setModelType(bmm.getModelType());
		basicsMachine.setMachineRow(bmm.getRowNum());
		basicsMachine.setMachineColumn(bmm.getColumnNum());

		Integer count = basicsMachineCellMapper
				.findCellCountByCellStatusIsNullByMachineId(basicsMachine.getMachineId());
		if (count > 0 && "0".equals(basicsMachine.getMachineStatus())) {
			map.put("message", "设备存在未清空的格子，无法停用设备!");
			map.put("status", Boolean.FALSE);
		} else {
			basicsMachineMapper.updateByPrimaryKeySelective(basicsMachine);
			map.put("message", "更新设备信息成功!");
			map.put("status", Boolean.TRUE);
		}

		return map;
	}

	/**
	 * 逻辑删除设备信息
	 * 
	 * @param request
	 * @param machineId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> delInfo(ServletRequest request, Long machineId) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询该设备的所有的格子是否空置状态,如果num为 0则可以删除，如果格num>0则提示不可以删除
		Integer num = basicsMachineCellMapper.findCellCountByCellStatusIsNullByMachineId(machineId);
		if (num > 0) {
			map.put("message", "该设备所在的格子存在清理的格子!");
			map.put("status", Boolean.FALSE);
		} else {
			/* 删除设备信息 */
			basicsMachineMapper.deleteBasicsMachinebyId(machineId);
			/* 删除设备格子 */
			basicsMachineCellMapper.delMachineCellByMachineId(machineId);
			/* 删除设备月售份数信息 */
			tblDeviceOrderAmountMapper.deleteByMachineId(machineId);

			map.put("message", "删除设备信息成功!");
			map.put("status", Boolean.TRUE);
		}

		return map;
	}

	/**
	 * 根据设备信息id查找设备信息
	 * 
	 * @param request
	 * @param goodsId
	 * @return
	 */
	public BasicsMachine findBasicsMachine(ServletRequest request, Long machineId) {
		BasicsMachine bm = basicsMachineMapper.selectByPrimaryKey(machineId);

		return bm;
	}

	/**
	 * 查找该登录用户的拥有设备
	 */
	public Page<BasicsMachine> findAllMachinesList(Map<String, Object> map, int pageNumber, int pageSize) {
		int companyId = Constants.getCurrendUser().getCompanyId();
		map.put("companyId", companyId);
		Long total = basicsMachineMapper.getCount(map);
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "id");
		map.put("offset", (pageNumber - 1) * pageSize);
		map.put("limit", pageSize);
		List<BasicsMachine> list = new ArrayList<BasicsMachine>();
		if (total != 0) {
			list = basicsMachineMapper.findAllMachinesList(map);
		}
		Page<BasicsMachine> page = new PageImpl<BasicsMachine>(list, pageRequest, total);
		return page;
	}

	/**
	 * 查找维修员列表分配所拥有的设备
	 */
	public Page<BasicsMachine> findMaintenanceMachinesList(Map<String, Object> map, int pageNumber, int pageSize) {
		Long total = basicsMachineMapper.getMaintenanceCount(map);
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "id");
		map.put("offset", (pageNumber - 1) * pageSize);
		map.put("limit", pageSize);
		List<BasicsMachine> list = new ArrayList<BasicsMachine>();
		if (total != 0) {
			list = basicsMachineMapper.findMaintenanceMachinesList(map);
		}
		Page<BasicsMachine> page = new PageImpl<BasicsMachine>(list, pageRequest, total);
		return page;
	}

	/**
	 * 查找该供货员、维修员所拥有设备
	 */
	public List<BasicsMachine> findEmployeeMachineList(Map<String, Object> map) {
		return basicsMachineMapper.findEmployeeMachineList(map);
	}

	public List<GMachineZone> findGMachineZoneByUniversityId(Long companyId) {
		/*Long companyId = (long) Constants.getCurrendUser().getCompanyId();
		return gMachineZoneMapper.findGMachineZoneByUniversityId(basicsCompanyMapper.getUniversityIdByCompanyId(companyId));*/
		if(companyId==null){
			 companyId = (long) Constants.getCurrendUser().getCompanyId();
		}
		//long	universityId=basicsCompanyMapper.getUniversityIdByCompanyId(companyId);

		return gMachineZoneMapper.findGMachineZoneByuniversityid(basicsCompanyMapper.getUniversityIdByCompanyId(companyId));
	}

	public Map<String, Object> addArea(String areaName) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Long companyId = (long) Constants.getCurrendUser().getCompanyId();
			BasicsCompany basicsCompany=basicsCompanyMapper.selectByPrimaryKey(companyId);
			GMachineZone gMachineZone = new GMachineZone();
			gMachineZone.setMachineZoneName(areaName);
			gMachineZone.setUniversityId(basicsCompany.getUniversityId());
			gMachineZoneMapper.insertSelective(gMachineZone);
			map.put("status", true);
			map.put("message", "添加区域成功！");
		} catch (Exception e) {
			map.put("status", true);
			map.put("message", "添加区域失败！");
		}
		return map;
	}
	public boolean getAreaModelCountByMachineId(Long machineId){
		try {
			Long count = basicsMachineMapper.getAreaModelCountByMachineId(machineId);
			if (count == null || count == 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
