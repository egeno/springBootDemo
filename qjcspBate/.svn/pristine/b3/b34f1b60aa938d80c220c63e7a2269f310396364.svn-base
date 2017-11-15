package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblWeixinAssist;
import com.qjkj.qjcsp.entity.viewmodel.BasicsGoodsVo;
@MyBatisRepository
public interface TblWeixinAssistMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblWeixinAssist record);

    int insertSelective(TblWeixinAssist record);

    TblWeixinAssist selectByPrimaryKey(Long id);
    TblWeixinAssist selectByCode(@Param("code")String code);

    int updateByPrimaryKeySelective(TblWeixinAssist record);

    int updateByPrimaryKeyWithBLOBs(TblWeixinAssist record);

    int updateByPrimaryKey(TblWeixinAssist record);
    //待完善
    Long findByCount(Map<String, Object> param);
    
	List<TblWeixinAssist> findByList(Map<String, Object> param);
	
	Integer checkAssistIsExisted(@Param("code") String code,@Param("id") Long id);
}