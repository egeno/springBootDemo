package com.qjkj.qjcsp.web.statistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.stacistics.MachineDayReserveService;
import com.qjkj.qjcsp.util.ExportExcelUtil;

/**
 * 设备日预定统计
 * @author wsk  2016年3月24日15:39:09
 */

@Controller
@RequestMapping("statistics")
public class MachineDayReserveController {
	@Autowired
	MachineDayReserveService machineDayReserveService;   
	/*
	 * 加载主页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listDayReserve")
	public String dayReserve(ServletRequest request,
			@RequestParam(value = "psdate", defaultValue = "") String psdate){
		
		request.setAttribute("psdate", getDate(psdate));
	    return "statistics/machineDayReserve/machineDayReserve";
    }
	
	/*
	 * 加载主页面数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findReserveList")
	@ResponseBody
	public Map<String, Object> findReserveList(
			@RequestParam(value = "psdate", defaultValue = "") String psdate,
			@RequestParam(value = "machinename", defaultValue = "") String machinename){
		//预定初始时间是当前时间
		psdate = getDate(psdate);
		return machineDayReserveService.findReserveList(machinename,psdate);
	}
	
    /*
	 * 导出功能
	 * @param request
	 * @return
	 */
    @RequestMapping("/machineReserveExport")
    @ResponseBody
    public void machineReserveExport(ServletRequest request,HttpServletResponse response,
                                @RequestParam(value = "psdate", defaultValue = "") String psdate,
                                @RequestParam(value = "machinename", defaultValue = "") String machinename){
        //Integer companyId=Constants.getCurrendUser().getCompanyId();
    	//获得要导出的数据
        Map<String,Object> machinesMap=
                machineDayReserveService.findReserveList(machinename, psdate);
        //要合并的列
        List<Integer> rowspana = null;
        //要合并的列
        List<Integer> rowspanb=new ArrayList<Integer>();
        List<List<Integer>> rows=new ArrayList<List<Integer>>();
        List<Map<String,Object>> list = null;
        if(machinesMap != null && machinesMap.size()>0){
            list=(List<Map<String,Object>>) machinesMap.get("rows");
            rowspana=(List<Integer>) machinesMap.get("rowspana");
            rowspanb=(List<Integer>) machinesMap.get("rowspanb");
            
            //0表示合并第一列
            rowspana.add(0);
            //1表示第二列
            rowspanb.add(1);
            rows.add(rowspana);
            rows.add(rowspanb);

        }
        String title = "设备日预定统计";
        String[] titles = {"设备名名称","模型名称","商品名称","份数"};
        Integer[] sheets = {150,100,150,100};
        String[] keys = {"machinename","areamodelname","goodsname","number"};
        ExportExcelUtil.exportExcel(response, title, sheets, titles, rows, keys, list);
    }
  //判断是否传来参数否则获取当前时间
    private String getDate(String psdate){
        if(psdate.equals("")){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            psdate = sdf.format(new Date());
        }
        return psdate;
    }

}
