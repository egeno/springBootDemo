package com.qjkj.qjcsp.web.statistics;

import com.qjkj.qjcsp.service.stacistics.GoodsPublishReportService;
import com.qjkj.qjcsp.util.ExportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/23.
 */
@Controller
@RequestMapping("/collection")
public class GoodsPublishReportController {
    public final static String PAGE_SIZE="10";
    @Autowired
    private GoodsPublishReportService goodsPublishReportService;

    @RequestMapping("/goodsPublishReport")
    public String goodsPublishReport(ServletRequest request,
                                     @RequestParam(value = "psdate", defaultValue = "null") String psdate)
    {
        request.setAttribute("psdate", getDate(psdate));
        return "statistics/goodsPublishReport/goodsPublishReport";
    }
    //判断是否传来参数否则获取当前时间
    private String getDate(String psdate){
        if(psdate.equals("null")){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            psdate = sdf.format(new Date());
        }
        return psdate;
    }
    @RequestMapping("/findGoodsPublishReport")
    @ResponseBody
    public Map<String,Object> findGoodsPublishReport(ServletRequest request,
                                                    @RequestParam(value = "psdate", defaultValue = "null") String psdate)
    {

        return goodsPublishReportService.findGoodsPublishReportService(getDate(psdate));
    }
    @SuppressWarnings("unchecked")
    @RequestMapping("/goodsPublishExport")
    @ResponseBody
    public void goodsPublishExport(ServletRequest request,HttpServletResponse response,
                                @RequestParam(value = "psdate", defaultValue = "") String psdate){
        //Integer companyId=Constants.getCurrendUser().getCompanyId();
    	//获得要导出的数据
        Map<String,Object> goodsPublishMap=
                goodsPublishReportService.goodsPublishExport(psdate);
        //要合并的列
        List<Integer> rowsdate = null;
        //要合并的列
        List<Integer> rowsdate1=new ArrayList<Integer>();
        List<List<Integer>> rows=new ArrayList<List<Integer>>();
        List<Map<String,Object>> list = null;
        if(goodsPublishMap!=null&&goodsPublishMap.size()>0)
        {
            list=(List<Map<String,Object>>) goodsPublishMap.get("rows");
            rowsdate=(List<Integer>) goodsPublishMap.get("rowspan");
            //rowsdate1=(List<Integer>) goodsPublishMap.get("rowspan");
            //直接赋值会导致两个合并列的值指向同一个地址
            for (int i = 0; i < rowsdate.size(); i++) {
				rowsdate1.add(rowsdate.get(i));
			}
            //rowsdate的最后一位表示要合并的是那一列0表示第一列
            rowsdate.add(0);
            //同上，表示第二列
            rowsdate1.add(1);
            rows.add(rowsdate);
            rows.add(rowsdate1);

        }
        String title = "日发布商品统计";
        String[] titles = {"商品ID","商品名称","模型","发布数量","发布时间"};
        Integer[] sheets = {150,100,150,100,100};
        String[] keys = {"goodsId","goodsName","areaModelName","publishNum","publishTime"};
        ExportExcelUtil.exportExcel(response, title, sheets, titles, rows, keys, list);
    }

}
