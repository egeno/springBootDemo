package com.qjkj.qjcsp.service.stacistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.mapper.MachineDayReserveMapper;
import com.qjkj.qjcsp.util.Constants;

/**
 * 设备日预定统计
 * @author wsk  2016年3月28日11:07:49
 *
 */
@Service
public class MachineDayReserveService {
	List<Integer> rowspanLista = null;
	List<Integer> rowspanListb = null;
	
	@Autowired
	MachineDayReserveMapper machineDayReserveMapper;
	
	/**
	 * 设备日预定统计方法
	 * 
	 * @param machinename
	 * @param psdate
	 * @return Map
	 */
    public Map<String,Object> findReserveList(String machinename,String psdate){
    	rowspanLista = new ArrayList<Integer>();
    	rowspanListb = new ArrayList<Integer>();
    	//当前公司id
    	int companyId = Constants.getCurrendUser().getCompanyId();
    	
    	Map<String, Object> par = new HashMap<String, Object>();
    	par.put("machinename", machinename);
    	par.put("companyId", companyId);
    	List<Map<String, Object>> returnlist = new ArrayList<Map<String,Object>>();
    	
    	//得到所有设备
    	List<Map<String, Object>> machines = machineDayReserveMapper.getMachineList(par);
    	for (Map<String, Object> map : machines) {
    		//得到当前设备下的所有模型
    		List<Map<String, Object>> Models = machineDayReserveMapper
    				.getModelsByMachineid(Long.valueOf(map.get("machineid").toString()));
    		/* 累计设备名需要合并的行数*/
        	int num = 0;
    		if(Models.size()>0){				
	    		for (Map<String, Object> map2 : Models) {
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("machineid", Long.valueOf(map.get("machineid").toString()));
					param.put("modelid", Long.valueOf(map2.get("areamodelid").toString()));
					param.put("psdate",psdate);
					//得到当前模型下的预定商品及数量
					List<Map<String, Object>> machineReserves = machineDayReserveMapper.findReserveList(param);
					if(machineReserves.size()>0){			
						num = num + machineReserves.size();
						rowspanListb.add(machineReserves.size());
						for (Map<String, Object> map3 : machineReserves) {
							map3.put("machinename", (String)map.get("machinename"));
							map3.put("areamodelname", (String)map2.get("areamodelname"));
							returnlist.add(map3);
						}
					}else{
						//当前模型下没有商品
						num = num+1;
						Map<String, Object> machinemodels = new HashMap<String, Object>();
						machinemodels.put("machinename", (String)map.get("machinename"));
						machinemodels.put("areamodelname", (String)map2.get("areamodelname"));
						machinemodels.put("goodsname", "");
						machinemodels.put("number", "");
						returnlist.add(machinemodels);
						rowspanListb.add(1);
					}
				}
	    		rowspanLista.add(num);
			}else{
				/* 设备下无模型*/
				Map<String, Object> machins = new HashMap<String, Object>();
				machins.put("machinename", (String)map.get("machinename"));
				machins.put("areamodelname", "");
				machins.put("goodsname", "");
				machins.put("number", "");
				returnlist.add(machins);
				rowspanLista.add(1);
				rowspanListb.add(1);
			}
		}
    	Map<String, Object> returnmap =new HashMap<String, Object>();
    	returnmap.put("rows", returnlist);
    	returnmap.put("rowspana", rowspanLista);
    	returnmap.put("rowspanb", rowspanListb);
		return returnmap;
	}
    
}
