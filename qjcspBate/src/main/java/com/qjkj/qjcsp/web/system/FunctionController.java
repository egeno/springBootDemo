package com.qjkj.qjcsp.web.system;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.BasicsMenu;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.entity.viewmodel.TreeGridModel;
import com.qjkj.qjcsp.entity.viewmodel.TreeModel;
import com.qjkj.qjcsp.service.system.FunctionService;
import com.qjkj.qjcsp.util.Constants;

/*
 * 类名:FunctionController
 * 创建者:yjg
 * 版本号：V1.0
 * 日期：2015-12-19
 * 系统菜单功能管理Controller
 */
@Controller
@RequestMapping(value = "/function")
public class FunctionController
{
	@Autowired
	private FunctionService functionService;

	/**
	 * 菜单管理跳转页
	 * 
	 */
	@RequestMapping(value = "/findFunction")
	public String findfunction() {
		return "/function/functionMain";
	}

	/**
	 * 菜单添加修改管理跳转页
	 * 
	 */
	@RequestMapping(value = "/editFunction")
	public String editfunction() {
		return "/function/functionEditDlg";
	}

	/**
	 * 查询所有菜单
	 */

	@RequestMapping(value = "/findAllFunctionList")
	@ResponseBody
	public List<TreeModel> findAllfunctionList() {
		return functionService.findAllFunctionList();
	}

	/**
	 * 按照节点查询菜单
	 * 
	 */
	@RequestMapping(value = "/findAllFunction")
	@ResponseBody
	public List<TreeGridModel> findAllFunction(@RequestParam(value = "id", required = false) String pid) {
		return functionService.findAllFunctionList(pid);
	}

	/**
	 * 逻辑删除菜单节点
	 */
	@RequestMapping(value = "/delFunction")
	@ResponseBody
	public ReturnJson delFunction(@RequestParam(value = "id", required = false) String pid) {
		ReturnJson json = new ReturnJson();
		if (functionService.updateFunction(pid)) {
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		} else {
			json.setMessage(Constants.POST_DATA_FAIL);
		}
		return json;
	}

	/**
	 * 删除菜单节点
	 */
	@RequestMapping(value = "/deleteFunction")
	@ResponseBody
	public ReturnJson deleteFunction(@RequestParam(value = "id", required = false) String pid) {
		ReturnJson json = new ReturnJson();
		if (functionService.delFunction(pid)) {
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		} else {
			json.setMessage(Constants.POST_DATA_FAIL);
		}
		return json;
	}

	/**
	 * 弹出框编辑
	 */
	@RequestMapping(value = "/persistenceFunctionDig")
	@ResponseBody
	public HashMap<String, Object> persistenceFunctionDig(BasicsMenu basicsMenu) {
		return Constants.getMessage(functionService.persistenceFunction(basicsMenu));
	}

}
