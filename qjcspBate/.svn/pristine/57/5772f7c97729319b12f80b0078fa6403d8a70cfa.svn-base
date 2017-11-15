package com.qjkj.qjcsp.service.stacistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qjkj.qjcsp.mapper.TblOperateRecordLogMapper;
/**
 * 
 * @author carpeYe 2016-01-22
 *  后台管理->数据统计->清货补货记录
 */
@Service
public class OperateRecordingService {

	@Autowired
	private TblOperateRecordLogMapper tblOperateRecordLogMapper;
	
	
	public Map<String,Object> operateRecording(Map<String,Object> params, int pageNumber, int pageSize) throws ParseException{
    	//System.out.println("machineId"+param.get("mchineId"));
    	//获取数据的总数
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(params.get("startTime")!=null&& !"".equals(params.get("startTime").toString())){
			params.put("startTime", sdf.parse(params.get("startTime").toString()));
		}else{
			params.put("startTime", null);
		}
		if(params.get("endTime")!=null && !"".equals(params.get("endTime").toString())){
			params.put("endTime", sdf.parse(params.get("endTime").toString()));
		} else{
			params.put("endTime", null);
		}
/*		if("".equals(params.get("userName"))){
			params.put("userName", null);
		}
		if("".equals(params.get("goodsName"))){
			params.put("goodsName", null);
		}*/
    	Long total = tblOperateRecordLogMapper.findOpetateRecordCount(params) ;
		params.put("offset", (pageNumber - 1) * pageSize);
		params.put("limit", pageSize);
		List<Map<String,Object>> list=tblOperateRecordLogMapper.findOpetateRecord(params);
		Map<String,Object> rows=new HashMap<String, Object>();
		rows.put("rows", list);
		rows.put("total", total);
		return rows;
	}
}
