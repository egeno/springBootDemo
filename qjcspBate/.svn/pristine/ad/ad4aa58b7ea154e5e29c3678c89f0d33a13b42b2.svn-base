package com.qjkj.qjcsp.service.area;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.mysql.fabric.xmlrpc.base.Array;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.BasicsMachineAreaModel;
import com.qjkj.qjcsp.entity.BasicsMachineAreaModelLog;
import com.qjkj.qjcsp.entity.TblMachineAreaModelMostSale;
import com.qjkj.qjcsp.entity.viewmodel.AreaModelVO;
import com.qjkj.qjcsp.entity.viewmodel.BasicsGoodsVo;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;

import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsGoodsMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineAreaModelLogMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineCellMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.TblItemDetailMapper;
import com.qjkj.qjcsp.mapper.TblMachineAreaModelMostSaleMapper;
import com.qjkj.qjcsp.service.goods.GoodsInfoService;
import com.qjkj.qjcsp.util.Constants;

/**
 * 设备关联分区方案维护业务层
 * 
 * @author carpeYe 2015-12-28
 *
 */
@Service
public class AreaAndModelService {
	private static final Logger logger = LoggerFactory.getLogger(GoodsInfoService.class);

	// 注入设备接口
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	// 注入分去接口

	@Autowired
	private BasicsMachineAreaModelMapper basicsMachineAreaModelMapper;
	@Autowired
	private BasicsMachineAreaModelLogMapper basicsMachineAreaModelLogMapper;
	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;
	@Autowired
	private BasicsGoodsMapper basicsGoodsMapper;
	@Autowired
	private TblItemDetailMapper tblItemDetailMapper;
	@Autowired
	private TblMachineAreaModelMostSaleMapper tblMachineAreaModelMostSaleMapper;
	@Autowired
	private BasicsMachineCellMapper basicsMachineCellMapper;

	/**
	 * 根据控制层传入的公司companyId查处所有该公司的设备
	 * 
	 * @param companyId
	 *            公司Id
	 * @return 设备的集合
	 */
	public List<BasicsMachine> findAllMachineByCompanyId(Long companyId, Map<String, Object> params) {
		params.put("companyId", companyId);
		return basicsMachineMapper.selectMachineByCompanyId(params);
	}

	/**
	 * 根据控制层传入的公司CompanyId查询 分区名称，格子数量，分区模型，商品名称，描述，状态 return page
	 */
	public Map<String, Object> findAllAreaAndModelByCompanyId(Long companyId, Map<String, Object> param, int pageNumber,
			int pageSize) {
		// return basicsAreaMapper.selectAreaAndModelByCompanyId(companyId,
		// pageSize, pageNumber);
		param.put("companyId", companyId);
		// System.out.println("machineId"+param.get("mchineId"));
		// 获取数据的总数
		Long total = basicsAreaModelMapper.findByCount(param);
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);
		List<AreaModelVO> list = new ArrayList<AreaModelVO>();
		if (total > 0) {
			list = basicsAreaModelMapper.selectAreaAndModelByCompanyId(param);
			// System.out.println("yejianhui"+list.get(0).getAreaName());
		}
		Map<String, Object> rows = new HashMap<String, Object>();
		rows.put("rows", list);
		rows.put("total", total);
		return rows;
	}

	public String findMachineIdByAreaId(Long areaModelId) {
		List<BasicsMachineAreaModel> list = basicsMachineAreaModelMapper.findByAreaModelId(areaModelId);
		String str = "";
		if (list.size() != 0) {
			for (BasicsMachineAreaModel long1 : list) {
				str += long1.getId() + "-" + long1.getMachineId() + ",";
			}
			return str.substring(0, str.length() - 1);
		} else {
			return "";
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public ReturnJson deleteAreaById(long pid) {
		ReturnJson json = new ReturnJson();
		// Integer companyId = Constants.getCurrendUser().getCompanyId();
		Integer userId = Constants.getCurrendUser().getUserId();
		// List<BasicsMachine> basicsMachines =
		// basicsMachineMapper.findBasicMachinesByAreaModelId((long) companyId,
		// pid);
		List<BasicsMachineAreaModel> basicsMachineAreaModels = basicsMachineAreaModelMapper.findByAreaModelId(pid);
		if (basicsMachineAreaModels != null && basicsMachineAreaModels.size() > 0) {
			json.setMessage("模型存在关联的设备不可删除!");
			json.setStatus(false);
		} else {
			BasicsAreaModel areaModel = new BasicsAreaModel();
			// 当单品表中不存在补货的记录时对
			if (tblItemDetailMapper.findItemDetailCountByAreaModelId(pid) == 0) {
				// 删除设备关联分区模型表中的数据
				/*
				 * List<BasicsMachineAreaModel> list =
				 * basicsMachineAreaModelMapper.findByAreaModelId(pid);
				 */
				/*
				 * for (BasicsMachineAreaModel basicsMachineAreaModel : list) {
				 * basicsMachineAreaModelMapper.deleteByPrimaryKey(
				 * basicsMachineAreaModel.getId()); BasicsMachineAreaModelLog
				 * record = new BasicsMachineAreaModelLog();
				 * record.setAreaModelId(basicsMachineAreaModel.getAreaModelId()
				 * );
				 * record.setMachineId(basicsMachineAreaModel.getMachineId());
				 * record.setOperation("0"); record.setOperationTime(new
				 * Date()); record.setOperationUserId((long) userId);
				 * basicsMachineAreaModelLogMapper.insert(record); }
				 */
				// 删除分区表中的数据
				// List<BasicsArea> basicsAreas =
				// basicsAreaMapper.findBasicsAreaByAreaModelId(pid);
				// for (BasicsArea basicsArea : basicsAreas) {
				// basicsArea.setIsdel("Y");
				// basicsArea.setModUserId((long) userId);
				// basicsArea.setLastModTime(new Date());
				// basicsAreaMapper.updateByPrimaryKeySelective(basicsArea);
				//
				// }

			}
			areaModel.setIsdel("Y");
			areaModel.setAreaModelId(pid);
			basicsAreaModelMapper.updateByPrimaryKeySelective(areaModel);
			json.setMessage("模型删除成功");
			json.setStatus(true);
		}
		return json;
	}

	public Map<String, Object> updateAreaById(BasicsAreaModel ba) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (basicsAreaModelMapper.getCountByCompanyIdAndAreaModelName(
					(long) Constants.getCurrendUser().getCompanyId(), ba.getAreaModelName(), ba.getAreaModelId()) == 0) {
				basicsAreaModelMapper.updateByPrimaryKeySelective(ba);
				map.put("message", "修改分区模型成功!");
				map.put("status", Boolean.TRUE);
			} else {
				map.put("message", "模型名称不能重复，无法修改!");
				map.put("status", Boolean.FALSE);
			}
		} catch (Exception e) {
			logger.error("修改分区模型发生异常!", e);
			map.put("message", "修改分区模型失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}
		return map;
	}

	@Transactional(rollbackFor = Exception.class)
	public ReturnJson saveMachineModel(String areaModel) {
		ReturnJson json = new ReturnJson();
		try {
			// 当前前台选中的产品Id
			String[] arr = areaModel.substring(0, areaModel.indexOf("_")).split(",");
			// 原始数据已选中的产品Id
			String[] arr1 = areaModel.substring(areaModel.indexOf("_") + 1, areaModel.length()).split(",");
			// System.out.println(Arrays.toString(arr1));
			// System.out.println(Arrays.toString(arr));
			int modelId = Integer.parseInt(arr[arr.length - 1]);
			// List<BasicsMachineAreaModel>
			// lists=basicsMachineAreaModelMapper.findByAreaModelId(modelId);
			for (int i = 0; i < arr.length - 1; i++) {
				for (int j = 0; j < arr1.length; j++) {
					if (arr[i].equals(arr1[j].substring(arr1[j].indexOf("-") + 1, arr1[j].length()))) {
						arr[i] = "no";
						arr1[j] = "no";
					}
				}
			}
			// System.out.println(Arrays.toString(arr1));
			// System.out.println(Arrays.toString(arr));
			// 从原始数据的数组中获取不为no的数据对进行删除操作
			BasicsMachineAreaModelLog record = null;
			Integer userId = Constants.getCurrendUser().getUserId();
			for (int i = 0; i < arr1.length; i++) {
				if (!arr1[i].equals("no") && !arr1[i].equals("no-no")) {
					if (basicsMachineMapper
							.selectByPrimaryKey(
									Long.valueOf(arr1[i].substring(arr1[i].indexOf("-") + 1, arr1[i].length())))
							.getAreaModelId() != Long.valueOf(modelId)) {
						BasicsMachineAreaModel basicsMachineAreaModel = basicsMachineAreaModelMapper
								.selectByPrimaryKey(Long.parseLong(arr1[i].substring(0, arr1[i].indexOf("-"))));
						/*TblMachineAreaModelMostSale tblMachineAreaModelMostSale = tblMachineAreaModelMostSaleMapper
								.getMostSaleByMachineIdAndAreaModelId(basicsMachineAreaModel.getMachineId(),
										basicsMachineAreaModel.getAreaModelId());
						if (tblMachineAreaModelMostSale != null) {
							json.setMessage("模型与设备的关联已被零售模板使用，无法取消关联！");
							json.setStatus(false);
							return json;
						}*/
						basicsMachineAreaModelMapper
								.deleteByPrimaryKey(Long.parseLong(arr1[i].substring(0, arr1[i].indexOf("-"))));
						record = new BasicsMachineAreaModelLog();
						record.setAreaModelId((long) modelId);
						record.setMachineId(
								Long.parseLong(arr1[i].substring(arr1[i].indexOf("-") + 1, arr1[i].length())));
						record.setOperation("0");
						record.setOperationTime(new Date());
						record.setOperationUserId((long) userId);
						basicsMachineAreaModelLogMapper.insert(record);
						tblMachineAreaModelMostSaleMapper.deleteByMachineIdAndAreaModelId(basicsMachineAreaModel.getMachineId(),
								basicsMachineAreaModel.getAreaModelId());
					} else {
						if (basicsMachineCellMapper.findCellCountByCellStatusIsNullByMachineId(
								Long.valueOf(arr1[i].substring(arr1[i].indexOf("-") + 1, arr1[i].length()))) == 0) {
							BasicsMachineAreaModel basicsMachineAreaModel = basicsMachineAreaModelMapper
									.selectByPrimaryKey(Long.parseLong(arr1[i].substring(0, arr1[i].indexOf("-"))));
							/*TblMachineAreaModelMostSale tblMachineAreaModelMostSale = tblMachineAreaModelMostSaleMapper
									.getMostSaleByMachineIdAndAreaModelId(basicsMachineAreaModel.getMachineId(),
											basicsMachineAreaModel.getAreaModelId());
							if (tblMachineAreaModelMostSale != null) {
								json.setMessage("模型与设备的关联已被零售模板使用，无法取消关联！");
								json.setStatus(false);
								return json;
							}*/
							basicsMachineAreaModelMapper
									.deleteByPrimaryKey(Long.parseLong(arr1[i].substring(0, arr1[i].indexOf("-"))));
							record = new BasicsMachineAreaModelLog();
							record.setAreaModelId((long) modelId);
							record.setMachineId(
									Long.parseLong(arr1[i].substring(arr1[i].indexOf("-") + 1, arr1[i].length())));
							record.setOperation("0");
							record.setOperationTime(new Date());
							record.setOperationUserId((long) userId);
							basicsMachineAreaModelLogMapper.insert(record);
							BasicsMachine basicsMachine = basicsMachineMapper.selectByPrimaryKey(
									Long.valueOf(arr1[i].substring(arr1[i].indexOf("-") + 1, arr1[i].length())));
							basicsMachine.setAreaModelId(null);
							// 将设备表中的当前模型Id字段设置为NULL
							basicsMachineMapper.updateByPrimaryKey(basicsMachine);
							tblMachineAreaModelMostSaleMapper.deleteByMachineIdAndAreaModelId(basicsMachineAreaModel.getMachineId(),
									basicsMachineAreaModel.getAreaModelId());
						}
					}
				}
			}

			// 从当前提交数组中获取不为no的数据进行添加或者修改操作
			for (int i = 0; i < arr.length - 1; i++) {
				if (!arr[i].equals("no")) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("machineId", arr[i]);
					map.put("modelId", modelId);
					BasicsMachineAreaModel bmad = basicsMachineAreaModelMapper.findByMachineId(map);
					if (bmad != null) {
						bmad.setAreaModelId(Long.valueOf(modelId));
						basicsMachineAreaModelMapper.updateByPrimaryKey(bmad);
					} else {
						BasicsMachineAreaModel bmam = new BasicsMachineAreaModel();
						bmam.setMachineId(Long.valueOf(arr[i]));
						bmam.setAreaModelId(Long.valueOf(modelId));
						basicsMachineAreaModelMapper.insert(bmam);
						record = new BasicsMachineAreaModelLog();
						record.setAreaModelId((long) modelId);
						record.setMachineId(Long.valueOf(arr[i]));
						record.setOperation("1");
						record.setOperationTime(new Date());
						record.setOperationUserId((long) userId);
						basicsMachineAreaModelLogMapper.insert(record);
					}
				}
			}
		} catch (Exception e) {
			logger.error("设备关联分区模型保存失败!", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			json.setMessage("设备关联分区模型保存失败!");
			json.setStatus(false);
			return json;
		}
		json.setMessage("设备关联分区模型保存成功!");
		json.setStatus(false);
		return json;
	}

	/**
	 * 增加分区模型
	 */
	public Map<String, Object> addModel(BasicsAreaModel bas) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			bas.setAreaModelStatus("1");
			bas.setIsdel("N");
			bas.setCreateUserId((long) Constants.getCurrendUser().getUserId());
			bas.setCreateTime(new Date());
			bas.setCompanyId((long) Constants.getCurrendUser().getCompanyId());
			bas.setLastModTime(new Date());
			bas.setModUserId((long) Constants.getCurrendUser().getUserId());
			if (basicsAreaModelMapper.findNumByCompanyIdAndTimeType(bas.getTimeType(),
					(long) Constants.getCurrendUser().getCompanyId(), bas.getAreaModelType()) == 0) {
				if (basicsAreaModelMapper.getCountByCompanyIdAndAreaModelName(
						(long) Constants.getCurrendUser().getCompanyId(), bas.getAreaModelName(), null) == 0) {
					basicsAreaModelMapper.insertSelective(bas);
					map.put("message", "添加分区模型成功!");
					map.put("status", Boolean.TRUE);
				} else {
					map.put("message", "模型名称不能重复，无法添加!");
					map.put("status", Boolean.FALSE);
				}
			} else {
				map.put("message", "该时间段或者模型名称的分区模型已存在不能重复添加！");
				map.put("status", Boolean.FALSE);
			}
		} catch (Exception e) {
			logger.error("添加分区模型发生异常!", e);
			map.put("message", "添加分区模型失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}
		return map;
	}

	public List<BasicsGoods> findAllGoodsBycompanyId(Long companyId) {
		return basicsGoodsMapper.findAllGoodsByCompanyId(companyId);
	}

	public List<BasicsAreaModel> findAllModelByCompanyId(Long companyId) {
		return basicsAreaModelMapper.findAllModelByCompanyId(companyId);
	}

	public BasicsAreaModel findAreaByAreaId(Long areaId) {
		return basicsAreaModelMapper.selectByPrimaryKey(areaId);
	}

}
