package com.qjkj.qjcsp.service.machine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsMachineModel;
import com.qjkj.qjcsp.entity.enums.DataStatusEnum;
import com.qjkj.qjcsp.entity.viewmodel.BasicsMachineModelVo;
import com.qjkj.qjcsp.entity.viewmodel.TreeModel;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineModelMapper;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.UserUtils;

@Component
@Transactional
public class MachineModelService {

	private static final Logger logger = LoggerFactory.getLogger(MachineModelService.class);

	@Autowired
	private BasicsMachineModelMapper basicsMachineModelMapper;

	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
    
	
	/**
	 * 查询所有设备类型
	 * 
	 * @param param
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<BasicsMachineModelVo> findModelPage(Map<String, Object> param, int pageNumber, int pageSize) {
		param.put("state", DataStatusEnum.NORMAL.getValue());
		Long total = basicsMachineModelMapper.findByCount(param);

		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);

		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);
		param.put("order", "model_id");
		param.put("sort", Sort.Direction.DESC);

		List<BasicsMachineModelVo> list = new ArrayList<BasicsMachineModelVo>();
		if (total != 0) {
			list = basicsMachineModelMapper.findByList(param);
		}
		Page<BasicsMachineModelVo> page = new PageImpl<BasicsMachineModelVo>(list, pageRequest, total);

		return page;
	}

	/**
	 * 添加设备型号
	 * 
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	public Map<String, Object> addModel(ServletRequest request, BasicsMachineModel basicsMachineModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//basicsMachineModel.setcreateUserId(1L);
			UserUtils userUtils = new UserUtils();
			basicsMachineModel.setcreateUserId((long) userUtils.getUserId());
			basicsMachineModel.setcreateTime(new Date());
			basicsMachineModel.setModUserId((long) userUtils.getUserId());
			//basicsMachineModel.setModUserId(1L);
			basicsMachineModel.setLastModTime(new Date());
			basicsMachineModelMapper.insertSelective(basicsMachineModel);

			map.put("message", "添加设备型号成功!");
			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			logger.error("添加设备型号发生异常!", e);
			map.put("message", "添加设备型号失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}

		return map;
	}

	/**
	 * 编辑设备型号
	 * 
	 * @param request
	 * @param basicsMachineModel
	 * @return
	 */
	public Map<String, Object> editModel(ServletRequest request, BasicsMachineModel basicsMachineModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserUtils userUtils = new UserUtils();
			basicsMachineModel.setModUserId((long) userUtils.getUserId());
			//basicsMachineModel.setModUserId(3L);
			basicsMachineModel.setLastModTime(new Date());
			basicsMachineModelMapper.updateByPrimaryKeySelective(basicsMachineModel);

			map.put("message", "更新设备型号成功!");
			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			logger.error("更新设备型号发生异常!", e);
			map.put("message", "更新设备型号失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}

		return map;
	}

	/**
	 * 逻辑删除设备型号
	 * 
	 * @param request
	 * @param modelId
	 * @return
	 */
	public Map<String, Object> delModel(ServletRequest request, Integer modelId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (basicsMachineMapper.findMachineModelCountByModelId(modelId) == 0) {
				basicsMachineModelMapper.deleteBasicsMachineModelbyId(modelId);
				map.put("message", "删除设备型号成功!");
				map.put("status", Boolean.TRUE);
			} else {
				map.put("message", "删除失败，设备模型已被设备使用!");
				map.put("status", Boolean.FALSE);
			}
		} catch (Exception e) {
			logger.error("删除设备型号发生异常!", e);
			map.put("message", "删除设备型号失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}

		return map;
	}

	/**
	 * 根据设备型号id查找设备型号
	 * 
	 * @param request
	 * @param goodsId
	 * @return
	 */
	public BasicsMachineModel findMachineModelbyId(ServletRequest request, Integer modelId) {
		BasicsMachineModel bmm = basicsMachineModelMapper.selectByPrimaryKey(modelId);

		return bmm;
	}

	/**
	 * 查询当前可用所有设备型号
	 * 
	 * @return
	 */
	public List<TreeModel> findAllMachineModelList() {
		List<TreeModel> list = new ArrayList<TreeModel>();
		List<BasicsMachineModel> tempList = basicsMachineModelMapper.findAllListForCombotree();

		for (BasicsMachineModel bmm : tempList) {
			TreeModel treeModel = new TreeModel();
			treeModel.setId(bmm.getModelId() + Constants.NULL_STRING);
			treeModel.setName(bmm.getModelName());
			treeModel.setState(Constants.TREE_STATUS_OPEN);
			list.add(treeModel);
		}
		return list;
	}

	public List<Map<String, Object>> findModelListByDeviceCode(String deviceCode) {
		return basicsMachineModelMapper.findModelListByDeviceCode(deviceCode);
	}

	/**
	 * 查询未创建商户管理员的公司数量
	 */
	public int findMachineCount(String modelId) {
		int count = basicsMachineModelMapper.findMachineCount(modelId);
		return count;
	}
}
