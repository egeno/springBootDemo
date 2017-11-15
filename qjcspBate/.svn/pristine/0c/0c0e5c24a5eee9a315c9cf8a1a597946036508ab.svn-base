package com.qjkj.qjcsp.service.goods;

import java.util.ArrayList;
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
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.BasicsUserRole;
import com.qjkj.qjcsp.entity.BasicsUserRoleLog;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.MachineAreaPo;
import com.qjkj.qjcsp.entity.MachineAreaSearchVo;
import com.qjkj.qjcsp.entity.TblGoodsAreaModel;
import com.qjkj.qjcsp.entity.TblGoodsAreaModelLog;
import com.qjkj.qjcsp.entity.TblRetail;
import com.qjkj.qjcsp.entity.enums.DataStatusEnum;
import com.qjkj.qjcsp.entity.machinearea.MachineAreaCell;
import com.qjkj.qjcsp.entity.machinearea.MachineAreaModel;
import com.qjkj.qjcsp.entity.machinearea.MachineAreaModelRowAndCell;
import com.qjkj.qjcsp.entity.viewmodel.BasicsGoodsVo;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsGoodsMapper;
import com.qjkj.qjcsp.mapper.TblGoodsAreaModelLogMapper;
import com.qjkj.qjcsp.mapper.TblGoodsAreaModelMapper;
import com.qjkj.qjcsp.mapper.TblGoodsRetailNumMapper;
import com.qjkj.qjcsp.mapper.TblRetailMapper;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.DateFormat;
import com.qjkj.qjcsp.util.DateUtils;
import com.qjkj.qjcsp.util.MD5Util;
import com.qjkj.qjcsp.util.UserUtils;

/**
 * 设备分区方案维护
 * 
 * @author yehx
 * @date 2015年12月28日 下午2:35:11
 */
@Controller
public class GoodsModelService {
	
	@Autowired
	private BasicsGoodsMapper basicsGoodsMapper;
	
	@Autowired
	private TblGoodsAreaModelMapper tblGoodsAreaModelMapper;
	
	@Autowired
	private TblGoodsAreaModelLogMapper tblGoodsAreaModelLogMapper;
	
	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;
	
	@Autowired
	private TblRetailMapper tblRetailMapper;
	
	@Autowired
	private TblGoodsRetailNumMapper tblGoodsRetailNumMapper;
	

	/**
	 * 超级管理员和全家科技公司登录得到所有的设备信息
	 * 
	 * @author yehx
	 * @date 2015年12月29日
	 * @return
	 *
	 *//*
	public List<MachineAreaSearchVo> getAllMachineInfo() {
		return machineAreMapper.getAllMachineInfo();
	}

	*//**
	 * 根据公司id得到该公司下的子公司信息
	 *//*
	public List<Integer> getCompanySonId(int companyId) {
		return machineAreMapper.getCompanySonId(companyId);
	}

	public List<MachineAreaSearchVo> getMachineInfoIds(List ids) {
		return machineAreMapper.getAllMachineInfoIds(ids);
	}

	*//**
	 * 根据companyId得到该公司下的所有设备
	 * 
	 * @author yehx
	 * @date 2015年12月28日
	 * @param companyId
	 * @return
	 *
	 *//*
	public List<MachineAreaSearchVo> getMachineInfo(Map map) {
		// System.out.println(companyId);
		List<MachineAreaSearchVo> machineInfo = machineAreMapper.getMachineInfo(map);
		// System.out.println(machineInfo);
		return machineInfo;
	}*/

	/**
	 * 得到所有的分区信息
	 * 
	 * @author yehx
	 * @date 2015年12月28日
	 * @param map
	 * @return
	 *
	 */
	public List<MachineAreaPo> findAllareaList(Map map) {
		List<MachineAreaPo> machineAreaPoList = basicsGoodsMapper.findAllareaList(map);
		return machineAreaPoList;
	}

	/**
	 * 得到所有分区的总条数
	 * 
	 * @author yehx
	 * @date 2015年12月28日
	 * @param map
	 * @return
	 *
	 */
	public int findAllareaListCount(Map map) {
		int count = basicsGoodsMapper.findAllareaListCount(map);
		return count;
	}
	
	/**
	 * 查询所有商品信息
	 * @param param
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<BasicsGoodsVo> findGoodsPage(Map<String,Object> param, int pageNumber, int pageSize){
		param.put("state", DataStatusEnum.NORMAL.getValue()) ;
		
		UserUtils userUtils = new UserUtils(); 
		param.put("companyId", userUtils.getCompanyId());
		Long total = basicsGoodsMapper.findByCount(param) ;

		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		
		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);
		param.put("order","goods_id") ;
		param.put("sort",Sort.Direction.DESC) ;
		
		List<BasicsGoodsVo> list = new ArrayList<BasicsGoodsVo>() ;
		if (total != 0) {
			list =  basicsGoodsMapper.findByList(param) ;
		}
		Page<BasicsGoodsVo> page = new PageImpl<BasicsGoodsVo>(list,pageRequest, total);
		
		return page;
	}
	
	/**
	 * 查询所有商品信息
	 * @param param
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<BasicsGoodsVo> findNewGoodsPage(Map<String,Object> param, int pageNumber, int pageSize){
		param.put("state", DataStatusEnum.NORMAL.getValue()) ;
		
		UserUtils userUtils = new UserUtils(); 
		param.put("companyId", userUtils.getCompanyId());
		Long total = basicsGoodsMapper.findByCount(param) ;

		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		
		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("order","goods_id") ;
		
		List<BasicsGoodsVo> list = new ArrayList<BasicsGoodsVo>() ;
		if (total != 0) {
			list =  basicsGoodsMapper.findByList(param);
		}
		Page<BasicsGoodsVo> page = new PageImpl<BasicsGoodsVo>(list,pageRequest, total);
		
		return page;
	}
	
	/**
	 * @function 根据用户id获取用户对应的用户角色列表
	 * @param userId
	 * @return
	 */
	public List<TblGoodsAreaModel> findGoodsIdByModelId(String areaModelId) {
		return tblGoodsAreaModelMapper.findGoodsIdByModelId(Long.valueOf(areaModelId));
	}

	/**
	 * @function 保存商品模型分配
	 * @return
	 */
	public ReturnJson saveGoodsModelConfig(String areaModelId, String goodsId) {
		ReturnJson json = new ReturnJson();
		StringBuffer sb = new StringBuffer();
		List<String> goodsIdList=new ArrayList<String>();
		for (int i = 0; i < goodsId.split(",").length; i++) {
			goodsIdList.add(goodsId.split(",")[i]);
		}
		String[] goodsIdArray=goodsId.split(",");
		//判断模型与商品关联是否已有被使用
		List<Map<String, Object>> retailList = tblRetailMapper.judgeUsed(areaModelId, goodsId);
		List<Map<String, Object>> goodRetailList = tblGoodsRetailNumMapper.judgeUsed(areaModelId, goodsIdList,DateFormat.dateFormatYYYYMMDD());
		//检测商品是否被模板中使用
		int count=tblRetailMapper.checkGoodsIsUseInTemplate(areaModelId, goodsIdList);
		if(count == 0 && goodRetailList.size() == 0){
			/*//根据roleId把所有的log记录的operation置为0
			ReturnJson json = new ReturnJson();
			int operationUserId = Constants.getCurrendUser().getUserId();
			basicsUserRoleLogMapper.updateOperation0(userId,operationUserId);*/

			tblGoodsAreaModelMapper.deleteGoodsModelByAreaModelId(Long.valueOf(areaModelId));
			if (!"".equals(goodsId) && goodsId.length() != 0) {
				for (String goodId : goodsIdArray) {
					if (null != goodId && !"".equals(goodId) && !" ".equals(goodId)) {
						TblGoodsAreaModel tblGoodsAreaModel = new TblGoodsAreaModel();

						tblGoodsAreaModel.setAreaModelId(Long.valueOf(areaModelId));
						tblGoodsAreaModel.setGoodsId(Long.valueOf(goodId));
						if (tblGoodsAreaModelMapper.insert(tblGoodsAreaModel) > 0) {
							log(areaModelId, goodId, "0");
							json.setStatus(true);
							json.setMessage(Constants.POST_DATA_SUCCESS);
						}
					}
				}
			}
			else{
				json.setStatus(true);
				json.setMessage(Constants.POST_DATA_SUCCESS);
			}
		}
		else if(retailList.size() > 0){
			json.setStatus(false);
			for (Map<String, Object> map : retailList) {
				sb.append("“" + map.get("templateName") + "”，");
			}
			sb.deleteCharAt(sb.length()-1);
			json.setMessage("已有商品被零售商品模板设置下的" + sb.toString() + "使用");
		}
		else if (goodRetailList.size() > 0) {
			json.setStatus(false);
			for (Map<String, Object> map : goodRetailList) {
				sb.append("“" + map.get("machineName") + "”，");
			}
			sb.deleteCharAt(sb.length()-1);
			json.setMessage("已有商品被商品零售发布下的" + sb.toString() + "使用");
		}
		
		

		return json;
	}
	
	private void log(String areaModelId, String goodId, String operation) {
		long userId = Constants.getCurrendUser().getUserId();
		// 写入日志
		TblGoodsAreaModelLog tblGoodsAreaModelLog = new TblGoodsAreaModelLog();
		
		tblGoodsAreaModelLog.setAreaModelId(Long.valueOf(areaModelId));
		tblGoodsAreaModelLog.setGoodsId(Long.valueOf(goodId));
		tblGoodsAreaModelLog.setOperationUserId(userId);
		tblGoodsAreaModelLog.setOperation("0");
		tblGoodsAreaModelLog.setOperationTime(new Date());
		tblGoodsAreaModelLogMapper.insertSelective(tblGoodsAreaModelLog);
	}

	/**
	 * 模型添加、编辑
	 */
	public boolean persistenceGoodsModelEdit(BasicsAreaModel basicsAreaModel) {	
		long companyId = Constants.getCurrendUser().getCompanyId();
		long userId = Constants.getCurrendUser().getUserId();
		//添加
		if (null==basicsAreaModel.getAreaModelId() || "".equals(basicsAreaModel.getAreaModelId()))
		{
			basicsAreaModel.setCompanyId(companyId);
			basicsAreaModel.setAreaModelStatus("1");
			basicsAreaModel.setIsdel("N");
			basicsAreaModel.setCreateTime(new Date());
			basicsAreaModel.setLastModTime(new Date());
			basicsAreaModel.setCreateUserId(userId);
			basicsAreaModel.setModUserId(userId);
			int r = basicsAreaModelMapper.insert(basicsAreaModel);
			return r > 0;
		}
		//编辑
		else {
			basicsAreaModel.setLastModTime(new Date());
			basicsAreaModel.setModUserId(userId);
//			basicsUsers.setPassword(MD5Util.getMD5String(basicsUsers.getPassword()));
			int r = basicsAreaModelMapper.updateByPrimaryKeySelective(basicsAreaModel);
			return r > 0;
		}
	 }

	/**
	 * 删除模型
	 */
	public HashMap<String, Object> delRow(Long areaModelId){
		HashMap<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		//判断模型与商品关联是否已有被使用
		List<Map<String, Object>> retailList = tblRetailMapper.judgeUsedByAreaModelId(areaModelId);
		List<Map<String, Object>> goodRetailList = tblGoodsRetailNumMapper.judgeUsedByAreaModelId(areaModelId);
		if(retailList.size() == 0 && goodRetailList.size() == 0){
			//删除与商品的关联
			tblGoodsAreaModelMapper.deleteByPrimaryKey(areaModelId);
			int r = basicsAreaModelMapper.updateIsdel(areaModelId);
			if(r > 0){
				map.put("status", true);
				map.put("message", "数据更新成功！");
				return map;
			}
			else{
				map.put("message", "提交失败了！");
				return map;
			}
		}
		else if(retailList.size() > 0){
			for (Map<String, Object> retailListMap : retailList) {
				sb.append("“" + retailListMap.get("templateName") + "”，");
			}
			sb.deleteCharAt(sb.length()-1);
			map.put("status", true);
			map.put("message", "已有商品被零售商品模板设置下的" + sb.toString() + "使用");
		}
		else if(goodRetailList.size() > 0){
			for (Map<String, Object> map2 : goodRetailList) {
				sb.append("“" + map2.get("machineName") + "”，");
			}
			sb.deleteCharAt(sb.length()-1);
			map.put("status", true);
			map.put("message", "已有商品被商品零售发布下的" + sb.toString() + "使用");
		}
		return map;
		
		
		
	}
	
	/*
	*//**
	 * 得到公司下的分区模型
	 * 
	 * @author yehx
	 * @date 2015年12月29日
	 * @param companyId
	 * @return
	 *
	 *//*
	public List<MachineAreaPo> getAreaModel(int companyId) {
		List<MachineAreaPo> lists = machineAreMapper.getAreaModel(companyId);
		return lists;
	}
	
	public List<MachineAreaPo> getAreaModelAdd(Map map){
		return machineAreMapper.getAreaModelAdd(map);
	}

	*//**
	 * 得到公司下商品
	 * 
	 * @author yehx
	 * @date 2015年12月29日
	 * @param companyId
	 * @return
	 *
	 *//*
	public List<MachineAreaPo> getGoods(int companyId) {
		List<MachineAreaPo> lists = machineAreMapper.getGoods(companyId);
		return lists;
	}

	*//**
	 * 得到所有的公司
	 * 
	 * @author yehx
	 * @date 2015年12月30日
	 * @return
	 *
	 *//*
	public List<MachineAreaPo> getAllCompany() {
		List<MachineAreaPo> lists = machineAreMapper.getAllCompany();
		return lists;
	}

	*//**
	 * 新增分区
	 * 
	 * @author yehx
	 * @date 2015年12月29日
	 * @param machineAreaPo
	 * @return
	 *
	 *//*
	@Transactional
	public void addMachineAreaSave(MachineAreaPo machineAreaPo) {
		// 新增分区操作
		machineAreMapper.addMachineAreaSave(machineAreaPo);
	}

	*//**
	 * 修改分区
	 * 
	 * @author yehx
	 * @date 2015年12月30日
	 * @param machineAreaPo
	 * @return
	 *
	 *//*
	@Transactional
	public void editMachineAreaSave(MachineAreaPo machineAreaPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 修改分区操作
			machineAreMapper.editMachineAreaSave(machineAreaPo);
			map.put("message", "修改分区成功");
			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改分区失败：" + e.getMessage());
			map.put("message", "修改分区失败");
			map.put("status", Boolean.FALSE);
			throw new RuntimeException("出现SQL操作错误：" + e.getMessage());
		}

	}

	*//**
	 * 删除分区
	 * 
	 * @author yehx
	 * @date 2015年12月30日
	 * @param machineAreaPo
	 * @return
	 *
	 *//*
	@Transactional
	public void deleteMachineArea(MachineAreaPo machineAreaPo) {
		// 删除分区操作
		machineAreMapper.deleteMachineArea(machineAreaPo);
		Integer userId = Constants.getCurrendUser().getUserId();// 用户id
		// 首先得得到这个分区下的所有的格子
		List<MachineAreaCell> machineAreaCells = new ArrayList<MachineAreaCell>();
		// 通过分区id得到该分区下的格子
		machineAreaCells = machineAreMapper.getMachineAreaCellsForAreaIds(machineAreaPo.getAreaId());
		Map<String, Object> map = new HashMap<String, Object>();
		Date date = new Date();
		if (machineAreaCells != null && machineAreaCells.size() > 0) {
			map.put("machineAreaCells", machineAreaCells);
			map.put("userId", userId);
			map.put("operation", "0");// 删除操作
			map.put("operationTime", date);
			// 往分区和格子日志表中插数据
			machineAreMapper.insertDeleteMachineAreaCellforCellLog(map);
			// 删除这个分区下的格子,操作分区与格子关联表
			machineAreMapper.deleteMachineAreaCellforCell(machineAreaPo);
		}
//		//删除该分区与格子的关联信息
//		machineAreMapper.deleteAreaForCell(machineAreaPo);

	}

	*//**
	 * 得到这个分区所在的模型中的位置，以及格子位置
	 * 
	 * @author yehx
	 * @date 2015年12月30日
	 * @param machineAreaPo
	 * @return
	 *
	 *//*
	@Transactional
	public Map<String, Object> getMachineAreaToAreaModel(MachineAreaPo machineAreaPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<MachineAreaCell> gouxuan = new ArrayList<MachineAreaCell>();
			// 以分区的格子集合信息
			List<MachineAreaCell> yifenqu = new ArrayList<MachineAreaCell>();
			// 得到有多少设备的当前模型是该模型,得到是设备的id集合
			List<Integer> listMachineIds = checkModelisUse(machineAreaPo);
			if(listMachineIds==null||listMachineIds.size()==0){
				MachineAreaModelRowAndCell c = machineAreMapper.getAreaModelRowAndCelllast(machineAreaPo.getAreaModelId());
				//首先得到这个模型下的所有的分区id
				List<Integer> machineAreaPosAreaIdss=machineAreMapper.getmachineAreaPosAreaIdss(machineAreaPo.getAreaModelId());
				// 判断模型下是否有分区
				if (machineAreaPosAreaIdss != null && machineAreaPosAreaIdss.size() > 0) {
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("machineAreaPo", machineAreaPo);
					map1.put("machineAreaPosAreaIds", machineAreaPosAreaIdss);
					// 得到这些分区下的所有的格子
					List<MachineAreaCell> areaCellForAreas = machineAreMapper.getAreaCellForAreachange(map1);
					if(areaCellForAreas!=null && areaCellForAreas.size()>0){
						for(int i=0;i<areaCellForAreas.size();i++){
							MachineAreaCell areaCellForArea=areaCellForAreas.get(i);
							if(machineAreaPo.getAreaId().equals(areaCellForArea.getAreaId())){
								gouxuan.add(areaCellForArea);
							}else{
								yifenqu.add(areaCellForArea);
							}
						}
					}
				}
				map.put("gouxuan", gouxuan);
				map.put("yifenqu", yifenqu);
				map.put("status", Boolean.TRUE);
				map.put("table", c);
				return map;
			}
			
			/// 用来存放：得到所有设备下的所有的格子的行列，这些格子的状态是status为1的，就是这个格子中还有菜
			List<MachineAreaCell> listMap = new ArrayList<MachineAreaCell>();
	
			// 根据模型id得到该模型的层数与列数
			MachineAreaModel machineAreaModel = machineAreMapper.getAreaModelRowAndCell(machineAreaPo);
			// 得到模型
			// MachineAreaModel
			MachineAreaModelRowAndCell c = new MachineAreaModelRowAndCell();
			if (machineAreaModel != null) {
				c.setAreaModelRow(machineAreaModel.getAreaModelRow());
				c.setAreaModelCell(machineAreaModel.getAreaModelCell());
				// 得到模型的层数和列数
				map.put("table", c);
			} else {
				map.put("status", Boolean.FALSE);
				return map;

			}
			// 得到模型下的所有分区id
			
			// System.out.println(machineAreaPos);
			// MachineAreaPo machineAreaPott=machineAreaPos.get(0);
			// 勾选的格子集合信息
			
			List<Integer> machineAreaPosAreaIds = machineAreaModel.getMachineAreaPosAreaId();
			// 判断模型下是否有分区
			if (machineAreaPosAreaIds != null && machineAreaPosAreaIds.size() > 0) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("machineAreaPo", machineAreaPo);
				map1.put("machineAreaPosAreaIds", machineAreaPosAreaIds);
				// 得到这些分区下的所有的格子
				List<MachineAreaCell> areaCellForAreas = machineAreMapper.getAreaCellForAreachange(map1);
				List<MachineAreaCell> areaCellForAreaschange = machineAreMapper.getAreaCellForAreachangeTwo(map1);
				if (listMachineIds != null && listMachineIds.size() > 0) {
					// 得到该设备下的所有的格子的行列，这些格子的状态是status为1的，就是这个格子中还有菜
					listMap = machineAreMapper.getAreaCellInfo(listMachineIds);
					// 那些没被分区但是已经有菜了得格子信息
					// Set<MachineAreaCell> noAreaHasGoods=new
					// HashSet<MachineAreaCell>();
					if (areaCellForAreas != null && areaCellForAreas.size() > 0) {
						// 遍历所有的分区下的格子信息
						for (int i = 0; i < areaCellForAreas.size(); i++) {
							// 得到格子信息
							MachineAreaCell machineAreaCell = areaCellForAreas.get(i);
							MachineAreaCell machineAreaCellchange=areaCellForAreaschange.get(i);
							int rowNum = machineAreaCellchange.getRowNum();
							int colNum = machineAreaCellchange.getColumnNum();

							// 遍历设备下的所有的格子
							for (int j = 0; j < listMap.size(); j++) {
								MachineAreaCell machineAreaCell2 = listMap.get(j);
								// System.out.println(machineAreaCell2.getRowNum()+":"+machineAreaCell2.getColumnNum());
								// 判断那些被分区了的格子 是否已经被
								if (machineAreaCell2.getRowNum().equals(rowNum)
										&& machineAreaCell2.getColumnNum().equals(colNum)) {
									machineAreaCell.setCellStatus("1");
									// listMap.remove(j);
									break;
								}
							}
							// 判断是否是当前分区
							if (machineAreaPo.getAreaId().equals(machineAreaCell.getAreaId())) {
								gouxuan.add(machineAreaCell);
							} else {
								yifenqu.add(machineAreaCell);
							}

						}
						// map.put("gouxuan", gouxuan);
						// map.put("yifenqu", yifenqu);
						// map.put("noAreaHasGoods",listMap);
					}
				} else {
					if (areaCellForAreas != null && areaCellForAreas.size() > 0) {
						// 遍历所有的分区下的格子信息
						for (int i = 0; i < areaCellForAreas.size(); i++) {
							// 得到格子信息
							MachineAreaCell machineAreaCell = areaCellForAreas.get(i);
							// 判断是否是当前分区
							if (machineAreaPo.getAreaId().equals(machineAreaCell.getAreaId())) {
								gouxuan.add(machineAreaCell);
							} else {
								yifenqu.add(machineAreaCell);
							}

						}

					}

				}
				map.put("gouxuan", gouxuan);
				map.put("yifenqu", yifenqu);
				// map.put("noAreaHasGoods",listMap);
				map.put("status", Boolean.TRUE);
				// JSONObject obj = JSONObject.fromObject(map);
				// System.out.println(obj);

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据分区得到分区模型失败：" + e.getMessage());
			map.put("status", Boolean.FALSE);
			throw new RuntimeException("出现SQL操作错误：" + e.getMessage());
		}
		return map;

	}

	*//**
	 * 得到该分区的格子预警数
	 * 
	 * @author yehx
	 * @date 2015年12月30日
	 * @return
	 *
	 *//*
	public int getAreaMinNum(Long areaId) {
		int count = machineAreMapper.getAreaMinNum(areaId);
		return count;
	}

	*//**
	 * 删除这个分区下的格子和新增新的格子到这个分区下
	 * 
	 * @author yehx
	 * @date 2015年12月30日
	 * @param areaId
	 *
	 *//*
	@Transactional
	public void deleteMachineAreaCellforCell(MachineAreaPo machineAreaPo, List<Map> listMap) {
		Integer userId = Constants.getCurrendUser().getUserId();// 用户id
		// 首先得得到这个分区下的所有的格子
		List<MachineAreaCell> machineAreaCells = new ArrayList<MachineAreaCell>();
		// 通过分区id得到该分区下的格子
		machineAreaCells = machineAreMapper.getMachineAreaCellsForAreaIds(machineAreaPo.getAreaId());
		Map<String, Object> map = new HashMap<String, Object>();
		Date date = new Date();
		if (machineAreaCells != null && machineAreaCells.size() > 0) {
			map.put("machineAreaCells", machineAreaCells);
			map.put("userId", userId);
			map.put("operation", "0");// 删除操作
			map.put("operationTime", date);
			// 往分区和格子日志表中插数据
			machineAreMapper.insertDeleteMachineAreaCellforCellLog(map);
			// 删除这个分区下的格子,操作分区与格子关联表
			machineAreMapper.deleteMachineAreaCellforCell(machineAreaPo);
		}
		// 判断传过来的值这个分区是否有格子选中
		if (listMap != null && listMap.size() > 0) {
			// 新增这个分区下的格子
			machineAreMapper.addMachineAreaCellforCell(listMap);
			map.put("operation", "1");// 新增的操作
			map.put("operationTime", date);
			map.put("userId", userId);
			map.put("machineAreaCells", listMap);
			machineAreMapper.insertAddMachineAreaCellforCellLog(map);
		}
	}

	*//**
	 * 修改格子与分区的关联
	 * 
	 * @author yehx
	 * @date 2015年12月30日
	 * @return
	 *
	 *//*
	public Map<String, Object> updateAreaCell() {
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}

	*//**
	 * 得到有多少设备的当前模型是该模型
	 * 
	 * @author yehx
	 * @date 2016年1月7日
	 * @param machineAreaPo
	 * @return 设备id的集合
	 *
	 *//*
	public List<Integer> checkModelisUse(MachineAreaPo machineAreaPo) {
		// 首先得到该设备正在使用的模型
		List<Integer> list = machineAreMapper.getUseThisAreaModelMachineCount(machineAreaPo);
		return list;
	}

	// 根据设备id的集合得到这些设备下，格子中有菜的格子信息
	public List<MachineAreaCell> getCellInfoHasGoodsForMachineIds(List<Integer> machineIds) {
		List<MachineAreaCell> machineAreaCells = machineAreMapper.getAreaCellInfo(machineIds);
		return machineAreaCells;
	}

	*//**
	 * 判断该模型下是否有格子还有菜
	 * 
	 * @author yehx
	 * @date 2016年1月7日
	 * @param machineAreaPo
	 * @return
	 *
	 *//*
	// public Boolean checkThisModelhasGoods(MachineAreaPo machineAreaPo){
	// //判断这个模型是否有机器正在使用
	// //得到这个公司下有多少机器当前正在使用这个模型
	// int count=machineAreMapper.getMachineUserThisModel(machineAreaPo);
	//
	// }

	*//**
	 * 判断这个模型下的这个菜品是否还有菜
	 * 
	 * @author yehx
	 * @date 2016年1月9日
	 * @param machineAreaPo
	 * @return
	 *
	 *//*
	@Transactional
	public Boolean checkThisModelThisGoodsHasGoods(MachineAreaPo machineAreaPo) {
		Boolean flag = getMachineUseThisModelTheCellHasGoodsInfo(machineAreaPo);
		return flag;
	}

	*//**
	 * 判断当前模型使用的分区的菜品，格子中是否还有菜
	 * 
	 * @author yehx
	 * @date 2016年1月9日
	 * @return false 表明 还有菜， return true,表明没有菜了
	 * @param machineAreaPo
	 *            中有areaModelId 修改的模型id areaId:分区id companyId:公司id
	 *//*

	public Boolean getMachineUseThisModelTheCellHasGoodsInfo(MachineAreaPo machineAreaPo) {
		/// 用来存放：得到所有设备下的所有的格子的行列，这些格子的状态是status为1的，就是这个格子中还有菜
		List<MachineAreaCell> listMap = new ArrayList<MachineAreaCell>();
		// 得到有多少设备的当前模型是该模型,得到是设备的id集合
		List<Integer> listMachineIds = checkModelisUse(machineAreaPo);
		if (listMachineIds != null && listMachineIds.size() > 0) {
			// 得到该设备下的所有的格子的行列，这些格子的状态是status为1的，就是这个格子中还有菜
			listMap = machineAreMapper.getAreaCellInfo(listMachineIds);
			// 根据模型id和分区id得到该分区下的格子信息
			List<MachineAreaCell> machineAreaCells = machineAreMapper.getAreaCellInfoForModelAndAreaId(machineAreaPo);
			Boolean flag = true;
			if (machineAreaCells != null && machineAreaCells.size() > 0) {
				// 遍历所有的分区下的格子信息
				for (int i = 0; i < machineAreaCells.size(); i++) {
					if (flag) {
						// 得到格子信息
						MachineAreaCell machineAreaCell = machineAreaCells.get(i);
						int rowNum = machineAreaCell.getRowNum();
						int colNum = machineAreaCell.getColumnNum();
						if (listMap != null && listMap.size() > 0) {
							// 遍历设备下的所有的格子
							for (int j = 0; j < listMap.size(); j++) {
								MachineAreaCell machineAreaCell2 = listMap.get(j);
								// System.out.println(machineAreaCell2.getRowNum()+":"+machineAreaCell2.getColumnNum());
								// 判断那些被分区了的格子 是否还有菜
								if (machineAreaCell2.getRowNum().equals(rowNum)
										&& machineAreaCell2.getColumnNum().equals(colNum)) {
									// machineAreaCell.setCellStatus("1");
									// listMap.remove(j);
									flag = false;
									break;
								}
							}
						}
					} else {

						return false;

					}
				}

			} else {

				return true;
			}

			return flag;

		} else {
			return true;
		}
	}

	*//**
	 * 得到这个模型下还剩多少格子
	 * 
	 * @author yehx
	 * @date 2016年1月9日
	 * @param machineAreaPo
	 * @return
	 *
	 *//*
	public int getThisModelliveHowMuchCell(MachineAreaPo machineAreaPo) {

		// 根据模型id得到该模型的层数与列数
		MachineAreaModel machineAreaModel = machineAreMapper.getAreaModelRowAndCell(machineAreaPo);
		int modelRow = machineAreaModel.getAreaModelRow();// 得到行
		int modelColum = machineAreaModel.getAreaModelCell();// 得到列
		// 得到该模型下的分区
		List<Integer> machineAreaPosAreaIds = machineAreaModel.getMachineAreaPosAreaId();
		Map<String, Object> map1 = new HashMap<String, Object>();
		if (machineAreaPosAreaIds != null && machineAreaPosAreaIds.size() > 0) {
			map1.put("machineAreaPo", machineAreaPo);
			map1.put("machineAreaPosAreaIds", machineAreaPosAreaIds);
			// 得到这些分区下有多少格子
			List<MachineAreaCell> areaCellForAreas = machineAreMapper.getAreaCellForAreachange(map1);
			if (areaCellForAreas != null && areaCellForAreas.size() > 0) {
				int count = areaCellForAreas.size();// 得到格子数量
				return modelRow * modelColum - count;// 返回还剩多少个格子
			}

		}
		return modelRow * modelColum;
	}

	*//**
	 * 判断该模型下的分区名是否已经存在
	 * 
	 * @author yehx
	 * @date 2016年1月14日
	 * @return
	 *
	 *//*
	public int checkModelThisAreaNameExist(MachineAreaPo machineAreaPo) {

		return machineAreMapper.checkModelThisAreaNameExist(machineAreaPo);
	}*/

}
