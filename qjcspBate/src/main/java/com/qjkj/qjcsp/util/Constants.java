package com.qjkj.qjcsp.util;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.qjkj.qjcsp.core.shiro.ShiroUser;

/**
 * 类名:Constants 创建者:yjg 版本号：V1.0 日期：2015-12-29 常量(标志)定义类
 */
public class Constants {
	public static final String LOGIN_SESSION_DATANAME = "users";
	public static final String LOGIN_URL = "login";
	public static final String LOGIN_SUCCESS_URL = "index";
	public static final String LOGIN_LOGIN_OUT_URL = "loginout";
	public static final String LOGIN_MSG = "loginMsg";
	public static final String USERNAME_IS_NULL = "用户名为空!";
	public static final String LOGIN_IS_EXIST = "该用户已登录!";
	public static final String UNKNOWN_SESSION_EXCEPTION = "异常会话!";
	public static final String UNKNOWN_ACCOUNT_EXCEPTION = "账号错误!";
	public static final String INCORRECT_CREDENTIALS_EXCEPTION = "密码错误!";
	public static final String LOCKED_ACCOUNT_EXCEPTION = "账号已被锁定，请与系统管理员联系!";
	public static final String INCORRECT_CAPTCHA_EXCEPTION = "验证码错误!";
	public static final String AUTHENTICATION_EXCEPTION = "您没有授权!";
	public static final String UNKNOWN_EXCEPTION = "出现未知异常,请与系统管理员联系!";
	public static final String TREE_GRID_ADD_STATUS = "add";
	public static final String POST_DATA_SUCCESS = "数据更新成功!";
	public static final String POST_DATA_FAIL = "提交失败了!";
	public static final String GET_SQL_LIKE = "%";
	public static final String IS_FUNCTION = "F";
	public static final String PERSISTENCE_STATUS = "A";
	public static final String PERSISTENCE_DELETE_STATUS = "I";
	public static final String SYSTEM_ADMINISTRATOR = "admin";
	public static final String NULL_STRING = "";
	public static final String IS_DOT = ".";
	public static final String HQL_LIKE = "like";
	public static final String TEXT_TYPE_PLAIN = "text/plain";
	public static final String TEXT_TYPE_HTML = "text/html";
	public static final String FUNCTION_TYPE_O = "O";
	public static final String TREE_STATUS_OPEN = "open";
	public static final String TREE_STATUS_CLOSED = "closed";
	public static final String IS_EXT_SUBMENU = " 或可能包含菜单!";
	public static final String SHIRO_USER = "shiroUser";
	public static final String LOGS_INSERT = "insert:";
	public static final String LOGS_INSERT_TEXT = "插入:";
	public static final String LOGS_INSERT_NAME = "insertLogs";
	public static final String LOGS_UPDATE = "update:";
	public static final String LOGS_UPDATE_TEXT = "更新:";
	public static final String LOGS_UPDATE_NAME = "updateLogs";
	public static final String LOGS_DELETE = "delete:";
	public static final String LOGS_DELETE_TEXT = "删除:";
	public static final String LOGS_DELETE_NAME = "deleteLogs";
	public static final String LOGS_TB_NAME = "Log";
	public static final String FILE_SUFFIX_SQL = ".sql";
	public static final String FILE_SUFFIX_ZIP = ".zip";
	public static final String REMOTE_FILE_NOEXIST = "远程文件不存在";
	public static final String DOWNLOAD_NEW_SUCCESS = "下载文件成功";
	public static final String DOWNLOAD_NEW_FAILED = "下载文件失败";
	public static final String LOCAL_BIGGER_REMOTE = "本地文件大于远程文件";
	public static final String DOWNLOAD_FROM_BREAK_SUCCESS = "文件断点续传成功";
	public static final String DOWNLOAD_FROM_BREAK_FAILED = "文件断点续传失败";
	public static final String DOWNLOAD_BATCH_SUCCESS = "文件批量下载成功";
	public static final String DOWNLOAD_BATCH_FAILURE = "文件批量下载失败";
	public static final String DOWNLOAD_BATCH_FAILURE_SUCCESS = "文件批量下载不完全成功";

	public static final String CREATE_DIRECTORY_FAIL = "远程服务器相应目录创建失败";
	public static final String CREATE_DIRECTORY_SUCCESS = "远程服务器闯将目录成功";
	public static final String UPLOAD_NEW_FILE_SUCCESS = "上传新文件成功";
	public static final String UPLOAD_NEW_FILE_FAILED = "上传新文件失败";
	public static final String FILE_EXITS = "文件已经存在";
	public static final String REMOTE_BIGGER_LOCAL = "远程文件大于本地文件";
	public static final String UPLOAD_FROM_BREAK_SUCCESS = "断点续传成功";
	public static final String UPLOAD_FROM_BREAK_FAILED = "断点续传失败";
	public static final String DELETE_REMOTE_FAILD = "删除远程文件失败";

	public static final String PERSISTENCE_SYMBOL_Y = "Y";
	public static final String PERSISTENCE_SYMBOL_N = "N";

	public static final String MENU_CATEGORY_PLATFORM = "0";// 平台菜单
	public static final String MENU_CATEGORY_MERCHANT = "1";// 商户菜单

	public static final Short SYSTEM_USERTYPE_PLATFORM_ADMIN = 1;// 平台超级用户
	public static final Short SYSTEM_USERTYPE_PLATFORM_COMMON_USER = 2;// 平台普通用户
	public static final Short SYSTEM_USERTYPE_MERCHANT_ADMIN = 3;// 商户管理员
	public static final Short SYSTEM_USERTYPE_MERCHANT_COMMON_USER = 4;// 商户普通用户

	public static final int DEVICE_LAST_PAY_TIME_INTERVAL = 5;// 支付截止时间距离下单时间为5分钟

	// IOS测试标志位:1为测试状态，0为正常业务状态
	public static final String IOS_SYMBOL = "1";

	public static final String SYMBOL_SUCCESS = "SUCCESS";
	public static final String SYMBOL_FAILURE = "FAILURE";
	public static final String SYMBOL_WAIT = "WAIT";

	// 0：设备端提交订单，1：APP端提交订单
	public static final String COMMIT_ORDER_DEVICE = "0";
	public static final String COMMIT_ORDER_APP = "1";
	
	//虚拟月售份数是否开启 0：不开启 1：开启
	public static final String VIRTUAL_MONTH_SALE_AMOUNT_SYMBOL = "1";
	
	//故障格子序号(5,5) (5,6) (6,5) (6,6) (7,5) (7,6)
	public static final String[] troubleCellNum = new String[]{"29", "30", "35", "36", "41", "42"}; 
	
	@Autowired
	private static HttpServletRequest request;

	/**
	 * 函数功能说明 TODO:获取当前登录用户实体类 Administrator修改者名字 修改内容 @Title:
	 * getCurrendUser @Description: TODO: @param @return 设定文件 @return Users
	 * 返回类型 @throws
	 */
	public static ShiroUser getCurrendUser() {
		Subject subject = SecurityUtils.getSubject();
		return (ShiroUser) subject.getSession().getAttribute(SHIRO_USER);
	}

	/**
	 * @function 根据数据库更新标志返回更新提示
	 * @param flag
	 *            数据库更新标志
	 * @return
	 */
	public static HashMap<String, Object> getMessage(boolean flag) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (flag) {
			map.put("status", true);
			map.put("message", "数据更新成功！");
		} else {
			map.put("message", "提交失败了！");
		}
		return map;
	}
}
