package com.qjkj.qjcsp.service.join;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblWeixinLeague;
import com.qjkj.qjcsp.entity.TblWeixinLeagueList;
import com.qjkj.qjcsp.mapper.TblWeixinLeagueMapper;


@Service
public class JoinInfoWeixinService {
	
	@Autowired
	private TblWeixinLeagueMapper tblWeixinLeagueMapper;
	
	public int findWeixinLeagueCount(TblWeixinLeagueList tblWeixinLeague){
		return tblWeixinLeagueMapper.findWeixinLeagueCount(tblWeixinLeague);
	};	
	
    public List<TblWeixinLeague> findWeixinLeagueList(TblWeixinLeagueList tblWeixinLeague){	
		return tblWeixinLeagueMapper.findWeixinLeagueList(tblWeixinLeague);
	}
	
}
