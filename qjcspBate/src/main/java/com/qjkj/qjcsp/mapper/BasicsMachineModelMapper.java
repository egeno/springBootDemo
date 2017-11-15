package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsMachineModel;
import com.qjkj.qjcsp.entity.viewmodel.BasicsMachineModelVo;

@MyBatisRepository
public interface BasicsMachineModelMapper {
    int deleteByPrimaryKey(Integer modelId);

    int insert(BasicsMachineModel record);

    int insertSelective(BasicsMachineModel record);

    BasicsMachineModel selectByPrimaryKey(Integer modelId);

    int updateByPrimaryKeySelective(BasicsMachineModel record);

    int updateByPrimaryKey(BasicsMachineModel record);
    
    Long findByCount(Map<String,Object> param) ;
    
    List<BasicsMachineModelVo> findByList(Map<String,Object> param) ;
    
    int deleteBasicsMachineModelbyId(Integer modelId);
    
    List<BasicsMachineModel> findAllListForCombotree();
    
    List<Map<String,Object>> findModelListByDeviceCode(String deviceCode);

	int findMachineCount(String modelId);
}