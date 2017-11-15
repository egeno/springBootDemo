package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblCustomerApplyAddress;
import com.qjkj.qjcsp.entity.TblCustomerApplyAddressList;
import com.qjkj.qjcsp.entity.TblWeixinLeague;
@MyBatisRepository
public interface TblWeixinLeagueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblWeixinLeague record);

    int insertSelective(TblWeixinLeague record);

    TblWeixinLeague selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblWeixinLeague record);

    int updateByPrimaryKey(TblWeixinLeague record);
    
    int selectByTelPhone(String mobileNum);
    
    List<Map<String,Object>> findWeiXinList(String name,String tblPhont);
    
    //查询返回的条数
    
    int findWeixinLeagueCount(TblWeixinLeague tblWeixinLeague);
    
    List<TblWeixinLeague> findWeixinLeagueList(TblWeixinLeague tblWeixinLeague);
}