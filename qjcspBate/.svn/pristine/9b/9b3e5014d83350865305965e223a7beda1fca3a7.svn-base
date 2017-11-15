package com.qjkj.qjcsp.service.join;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsJoininfo;
import com.qjkj.qjcsp.entity.BasicsJoininfoList;
import com.qjkj.qjcsp.mapper.BasicsJoininfoMapper;

@Service
@Transactional
public class JoinInfoService {
	@Autowired
	private BasicsJoininfoMapper basicsJoininfoMapper;
	/**
	 * 查询返回条数
	 * @param basicsJoininfo
	 * @return
	 */
	public int findJoinInfoCount(BasicsJoininfoList basicsJoininfo){
		return basicsJoininfoMapper.findJoinInfoCount(basicsJoininfo);
	};

	
	/**
	 * 查询列表
	 */
	 public List<BasicsJoininfo> findJoinInfoList(BasicsJoininfoList basicsJoininfo){			
			return basicsJoininfoMapper.findJoinInfoList(basicsJoininfo);
		}
}
