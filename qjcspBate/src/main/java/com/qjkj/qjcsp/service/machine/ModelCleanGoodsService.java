package com.qjkj.qjcsp.service.machine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.CompanyPo;
import com.qjkj.qjcsp.entity.ModelCleanGoodsVo;
import com.qjkj.qjcsp.mapper.ModelCleanGoodsMapper;
@Service
public class ModelCleanGoodsService {
	private static Logger logger = LoggerFactory.getLogger(ModelCleanGoodsService.class);
	@Autowired
	private ModelCleanGoodsMapper modelCleanGoodsMapper;
	
	public List<CompanyPo> getCompanyList(Map map){
		return modelCleanGoodsMapper.getCompanyList(map);
	}

	public List<ModelCleanGoodsVo> getAreaModel(int companyId){
		return modelCleanGoodsMapper.getAreaModel(companyId);
	};
	
	public int findAllModelCleanGoodsListCount(Map map){
		return modelCleanGoodsMapper.findAllModelCleanGoodsListCount(map);
	};
	
	public List<ModelCleanGoodsVo> findAllModelCleanGoodsList(Map map){
		return modelCleanGoodsMapper.findAllModelCleanGoodsList(map);
	}
	
	@Transactional
	public void addModelcleangoodsSave(ModelCleanGoodsVo modelCleanGoodsVo){
		modelCleanGoodsMapper.addModelcleangoodsSave(modelCleanGoodsVo);
	}
	@Transactional
	public boolean editModelcleangoodsSave(ModelCleanGoodsVo modelCleanGoodsVo,String areaModelId){
		boolean flag = Boolean.FALSE;
		int num = getOrderNum(areaModelId);
		
		/*当前模型没有下单的*/
		if(num == 0){
			modelCleanGoodsMapper.editModelcleangoodsSave(modelCleanGoodsVo);
			flag=Boolean.TRUE;
		}
		
		return flag;
	}
	@Transactional
	public Map<String,String>  deleteModelcleangoods(ModelCleanGoodsVo modelCleanGoodsVo,String areaModelId){
		Map<String, String> map = new HashMap<String, String>();
		int orderNum = getOrderNum(areaModelId);
		Long areaModelIdlong = Long.valueOf(areaModelId);
		/*当前模型没有下单的*/
		if(orderNum == 0){
			/*当前模型是不是设备当前模型*/
			int areaModeNum = modelCleanGoodsMapper.getAreaModeNum(areaModelIdlong);
			if(areaModeNum < 1){
				boolean flag = Boolean.TRUE;
				/*当前模型是不是设备的最后一个模型*/
				List<Integer> list = modelCleanGoodsMapper.getMachineModeNum(areaModelIdlong);
				for(int i = 0; i < list.size(); i++){
					if(list.get(i) == 1){
					   flag = Boolean.FALSE;
					   break;
					}
				}
				if(flag){
					modelCleanGoodsMapper.deleteModelcleangoods(modelCleanGoodsVo);
				}else{
					map.put("content", "该模型只关联了一个设备信息，不可删除");
				}
			}else{
				map.put("content", "设备当前模型不可删除");
			}
			
		}else{
			map.put("content", "当前模型已有客户下单");
		}
		return map;
	}
	/**
	 * 当前模型是否有
	 * @param areaModelId
	 * @return
	 */
	public int getOrderNum(String areaModelId){
		return  modelCleanGoodsMapper.getOrderNum(Long.valueOf(areaModelId));
	}
	
	public int getCount(ModelCleanGoodsVo modelCleanGoodsVo){
		return modelCleanGoodsMapper.getCount(modelCleanGoodsVo);
	}
	
	public int checkHasallDaygetCount(ModelCleanGoodsVo modelCleanGoodsVo){
		return modelCleanGoodsMapper.checkHasallDaygetCount(modelCleanGoodsVo);
	}
	
	public List<String> getdayDateStr(Long areaModelId){
//		List<String>liststr=modelCleanGoodsMapper.getdayDateStr(areaModelId);
//		StringBuffer sb=new StringBuffer();
//		if(liststr!=null && liststr.size()>0 ){
//			for(int i=0;i<liststr.size();i++){
//				String str=liststr.get(i);
//				sb.append(str);
//			}
//		}
		
		return modelCleanGoodsMapper.getdayDateStr(areaModelId);
	};
	
	public List<String> getEditDateStr(Long areaModelId,Long cleanId){
		return modelCleanGoodsMapper.getEditDateStr(areaModelId,cleanId);
	}
}
