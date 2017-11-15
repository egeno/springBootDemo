package com.qjkj.qjcsp.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.mapper.BasicsUsersMapper;
import com.qjkj.qjcsp.util.weixinpay.util.MD5Util;

public class Usertest {
	@Autowired
	BasicsUsersMapper basicsUserMapper;
	public int test(){
		BasicsUsers user = new BasicsUsers();
		user.setUserAccount("system");
		user.setCompanyId((long) 1);
		user.setCompanyName("");
		user.setUserName("郑传艳");
		user.setUserCode("管理员");
		user.setcreateUserId((long) 1);
		user.setIsdel("N");
		user.setPassword("admin");
		user.setUserType((short) 1);
		user.setUserStatus("1");
		user.setUserMemo("1");
		user.setUserCode("1");
		int i= basicsUserMapper.insert(user);
		return i;
	}
	
	

}
