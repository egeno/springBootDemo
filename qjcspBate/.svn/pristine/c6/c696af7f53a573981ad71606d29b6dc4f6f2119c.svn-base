package com.qjkj.qjcsp.util;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

public class ExportExcelUtil {

	/**
	 * 统计 导出
	 * 
	 * @param response
	 * @param tablename
	 *            工作表名称
	 * @param sheets
	 *            创建表格设定宽度
	 * @param titles
	 *            标头名称
	 * @param rows
	 *            合并单元格，没有穿null
	 * @param keys
	 *            标头对应的键
	 * @param datas
	 *            表格数据
	 */
	public static void exportExcel(HttpServletResponse response, String tablename, Integer[] sheets, String[] titles,
			List<List<Integer>> rows, String[] keys, List<Map<String, Object>> datas) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(tablename);
		sheet.getPrintSetup().setLandscape(true);
		int i = 0;
		for (Integer str : sheets) {
			sheet.setColumnWidth(i, 40 * str);// 调整列宽
			i++;
		}
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		//HSSFDataFormat format = workbook.createDataFormat();
		HSSFRow row = sheet.createRow((int) 0);
		for (int index = 0; index < rows.size(); index++) {
			List<Integer> rowspan = rows.get(index);
			if (rowspan != null) {
				int num = 0;
				for (int newIndex = 0; newIndex< rowspan.size() - 1; newIndex++) {
					sheet.addMergedRegion(new Region(num + 1, (short) ((int) rowspan.get(rowspan.size() - 1)),
							num + rowspan.get(newIndex), (short) (int) rowspan.get(rowspan.size() - 1)));
					num += rowspan.get(newIndex);
				}
			}
		}

		/*
		 * HSSFRow row = sheet.createRow((int) 0); if (rows != null) {
		 * List<Integer> rowspan = rows.get(0); if (rowspan != null) { int num =
		 * 0; for (i = 0; i < rowspan.size() - 1; i++) {
		 * sheet.addMergedRegion(new Region(num + 1, (short) 0, num +
		 * rowspan.get(i), (short) 0)); num += rowspan.get(i); } } }
		 */

		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		HSSFFont font = wb.createFont();
		font.setColor(HSSFColor.BLACK.index);// HSSFColor.VIOLET.index //字体颜色
		font.setFontHeightInPoints((short) 12);
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //字体增粗
		// 把字体应用到当前的样式
		style.setFont(font);
		style.setWrapText(true);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		// 创建表格标题
		HSSFCell cell = null;
		i = 0;
		for (String str : titles) {
			cell = row.createCell(i);
			cell.setCellValue(str);
			cell.setCellStyle(style);
			i++;
		}

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		if (datas != null && datas.size() > 0) {
			int j = 0;
			String vstr = "-";
			Map<String, Object> dataMap = null;
			for (i = 0; i < datas.size(); i++) {
				dataMap = datas.get(i);
				if (dataMap != null && dataMap.size() > 0) {
					j = 0;
					row = sheet.createRow((int) (i + 1));
					for (String str : keys) {
						if (dataMap.get(str) != null && !dataMap.get(str).equals("null")) {
							vstr = dataMap.get(str).toString();
						} else {
							vstr = "-";
						}
						cell = row.createCell(j);
						cell.setCellValue(vstr);
						cell.setCellStyle(style);
						j++;
					}
				}
			}			
		}
		// 第六步，将文件存到指定位置
		try {
			i = (int) (Math.random() * 1000);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition",
					"attachment;filename=" + DateTimeUtil.getCurrentymd() + "-"+ i + ".xls");
			OutputStream ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			System.out.println("导出成功！");
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @param response
	 * @param tablename 工作表名称
	 * @param sheets    创建表格设定宽度
	 * @param titles    标头名称
	 * @param rows      合并单元格，没有穿null
	 * @param keys      标头对应的键
	 * @param datas     表格数据
	 * 前两个单元格合并方法
	 *
	 */
	public static void exportExcel2(HttpServletResponse response,
			String tablename,Integer[] sheets,String[] titles,List<Integer> rows,
			String[] keys,List<Map<String, Object>> datas){
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet(tablename);  
        sheet.getPrintSetup().setLandscape(true);
        int i = 0;
       /*  CellView cv = new CellView();
cv.setAutosize(true);
writableSheet.setColumnView(column, cv);      */
        for(Integer str : sheets){
        	sheet.setColumnWidth(i, 35 * str);//调整列宽
            i ++;
        }
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short(四个参数分别是：起始行，起始列，结束行，结束列)
        HSSFRow row = sheet.createRow((int) 0);
        if(rows!=null){
        	int num = 0;
            for (i = 0; i < rows.size(); i++) {
            	sheet.addMergedRegion(new Region(num + 1, (short) 0, num + rows.get(i), (short) 0));//1 0 4 0
            	num += rows.get(i);
    		}
            num = 0;
            for (i = 0; i < rows.size(); i++) {
            	sheet.addMergedRegion(new Region(num + 1, (short) 1, num + rows.get(i), (short) 1));//1 1 4 1
            	num += rows.get(i);
    		}
        }
        
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中  
        HSSFFont font=wb.createFont();
        font.setColor(HSSFColor.BLACK.index);//HSSFColor.VIOLET.index //字体颜色
        font.setFontHeightInPoints((short)12);
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);         //字体增粗
        //把字体应用到当前的样式
        style.setFont(font);
        style.setWrapText(true);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        
        //创建表格标题
        HSSFCell cell = null;
        i = 0;
        for(String str : titles){
        	cell = row.createCell(i);
        	cell.setCellValue(str);
            cell.setCellStyle(style);
            i ++;
        }
  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        if(datas!=null && datas.size()>0){
        	int j = 0;
        	String vstr = "-";
        	Map<String, Object> dataMap = null;
	        for (i = 0; i < datas.size(); i++) {
	        	dataMap = datas.get(i);
	        	if(dataMap!=null&&dataMap.size()>0){
	        		j = 0;
	        		row = sheet.createRow((int) (i + 1));
	        		for(String str : keys){
	        			if(dataMap.get(str) != null && !dataMap.get(str).equals("null")){
	        				vstr = dataMap.get(str).toString();
	        			}else{
	        				vstr = "-";
	        			}
	        			cell = row.createCell(j);
				        cell.setCellValue(vstr);  
				        cell.setCellStyle(style);
				        j ++;
	        		}
	        	}
			}
        }
        // 第六步，将文件存到指定位置  
        try {  
        	 i = (int) (Math.random()*1000);
        	 response.setContentType("application/vnd.ms-excel");    
             response.setHeader("Content-disposition", "attachment;filename="+ DateTimeUtil.getCurrentymd() +"-"+i+".xls");    
             OutputStream ouputStream = response.getOutputStream();  
             wb.write(ouputStream);    
             System.out.println("导出成功！");
             ouputStream.flush();    
             ouputStream.close();  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	
	public static void exportExcel3(HttpServletResponse response, String tablename, Integer[] sheets, String[] titles,
			List<List<Integer>> rows, String[] keys, List<Map<String, Object>> datas) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(tablename);
		sheet.getPrintSetup().setLandscape(true);
		int i = 0;
		for (Integer str : sheets) {
			sheet.setColumnWidth(i, 40 * str);// 调整列宽
			i++;
		}
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		//HSSFDataFormat format = workbook.createDataFormat();
		HSSFRow row = sheet.createRow((int) 0);
		for (int index = 0; index < rows.size(); index++) {
			List<Integer> rowspan = rows.get(index);
			if (rowspan != null) {
				int num = 0;
				for (int newIndex = 0; newIndex< rowspan.size() - 1; newIndex++) {
					sheet.addMergedRegion(new Region(num + 1, (short) ((int) rowspan.get(rowspan.size() - 1)),
							num + rowspan.get(newIndex), (short) (int) rowspan.get(rowspan.size() - 1)));
					num += rowspan.get(newIndex);
				}
			}
		}
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		HSSFFont font = wb.createFont();
		font.setColor(HSSFColor.BLACK.index);// HSSFColor.VIOLET.index //字体颜色
		font.setFontHeightInPoints((short) 12);
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //字体增粗
		// 把字体应用到当前的样式
		style.setFont(font);
		style.setWrapText(true);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		// 创建表格标题
		HSSFCell cell = null;
		i = 0;
		for (String str : titles) {
			cell = row.createCell(i);
			cell.setCellValue(str);
			cell.setCellStyle(style);
			i++;
		}

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		if (datas != null && datas.size() > 0) {
			int j = 0;
			String vstr = "-";
			Map<String, Object> dataMap = null;
			for (i = 0; i < datas.size(); i++) {
				dataMap = datas.get(i);
				if (dataMap != null && dataMap.size() > 0) {
					j = 0;
					row = sheet.createRow((int) (i + 1));
					for (String str : keys) {
						if (dataMap.get(str) != null && !dataMap.get(str).equals("null")) {
							vstr = dataMap.get(str).toString();
						} else {
							vstr = "-";
						}
						cell = row.createCell(j);
						cell.setCellValue(vstr);
						cell.setCellStyle(style);
						j++;
					}
				}
			}
			
			/**
			 * 设置每一列的宽度
			 */
			    int length = vstr.getBytes().length;
			    sheet.setColumnWidth((int)0,(int)(length*256));
			    sheet.setColumnWidth((int)1,(int)(length*256));
			    sheet.setColumnWidth((int)2,(int)(length*512));
			    sheet.setColumnWidth((int)3,(int)(length*512));
			    sheet.setColumnWidth((int)4,(int)(length*256));
			    sheet.setColumnWidth((int)5,(int)(length*256));
			    sheet.setColumnWidth((int)6,(int)(length*512));
			    sheet.setColumnWidth((int)7,(int)(length*256));
			    sheet.setColumnWidth((int)8,(int)(length*256));
			    sheet.setColumnWidth((int)9,(int)(length*256));
			    sheet.setColumnWidth((int)10,(int)(length*256));
			    sheet.setColumnWidth((int)11,(int)(length*512));
		}
		// 第六步，将文件存到指定位置
		try {
			i = (int) (Math.random() * 1000);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition",
					"attachment;filename=" + DateTimeUtil.getCurrentymd() + "-"+ i + ".xls");
			OutputStream ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			System.out.println("导出成功！");
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void exportExceld(HttpServletResponse response,
			String tablename,Integer[] sheets,String[] titles,List<Integer> rows,
			String[] keys,List<Map<String, Object>> datas){
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet(tablename);  
        sheet.getPrintSetup().setLandscape(true);
        int i = 0;
       /*  CellView cv = new CellView();
cv.setAutosize(true);
writableSheet.setColumnView(column, cv);      */
        for(Integer str : sheets){
        	sheet.setColumnWidth(i, 35 * str);//调整列宽
            i ++;
        }
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short(四个参数分别是：起始行，起始列，结束行，结束列)
        HSSFRow row = sheet.createRow((int) 0);
        if(rows!=null){
        	int num = 0;
            for (i = 0; i < rows.size(); i++) {
            	sheet.addMergedRegion(new Region(num + 1, (short) 0, num + rows.get(i), (short) 0));//1 0 4 0
            	num += rows.get(i);
    		}
            num = 0;
            for (i = 0; i < rows.size(); i++) {
            	sheet.addMergedRegion(new Region(num + 1, (short) 1, num + rows.get(i), (short) 1));//1 1 4 1
            	num += rows.get(i);
    		}
        }
        
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中  
        HSSFFont font=wb.createFont();
        font.setColor(HSSFColor.BLACK.index);//HSSFColor.VIOLET.index //字体颜色
        font.setFontHeightInPoints((short)10);
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);         //字体增粗
        //把字体应用到当前的样式
        style.setFont(font);
        style.setWrapText(true);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        
        //创建表格标题
        HSSFCell cell = null;
        i = 0;
        for(String str : titles){
        	cell = row.createCell(i);
        	cell.setCellValue(str);
            cell.setCellStyle(style);
            i ++;
        }
  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        if(datas!=null && datas.size()>0){
        	int j = 0;
        	String vstr = "-";
        	Map<String, Object> dataMap = null;
	        for (i = 0; i < datas.size(); i++) {
	        	dataMap = datas.get(i);
	        	if(dataMap!=null&&dataMap.size()>0){
	        		j = 0;
	        		row = sheet.createRow((int) (i + 1));
	        		for(String str : keys){
	        			if(dataMap.get(str) != null && !dataMap.get(str).equals("null")){
	        				vstr = dataMap.get(str).toString();
	        			}else{
	        				vstr = "-";
	        			}
	        			cell = row.createCell(j);
				        cell.setCellValue(vstr);  
				        cell.setCellStyle(style);
				        j ++;
	        		}
	        	}
			}
        }
        // 第六步，将文件存到指定位置  
        try {  
        	 i = (int) (Math.random()*1000);
        	 response.setContentType("application/vnd.ms-excel");    
             response.setHeader("Content-disposition", "attachment;filename="+ DateTimeUtil.getCurrentymd() +"-"+i+".xls");    
             OutputStream ouputStream = response.getOutputStream();  
             wb.write(ouputStream);    
             System.out.println("导出成功！");
             ouputStream.flush();    
             ouputStream.close();  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
}
