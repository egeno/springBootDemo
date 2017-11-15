package com.qjkj.qjcsp.web.areamodel;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.basicsareamodel.AreaModelService;


@Controller
@RequestMapping("areaModel")
public class AreaModelController {
	@Autowired
	private AreaModelService areaModelServicel;
	
	@RequestMapping("/getAreaModelList")
	@ResponseBody
	public List<Map<String, Object>> getAreaModelList(){
		return areaModelServicel.getAreaModelList();
	}
}
