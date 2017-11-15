package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblAdPictures;

@MyBatisRepository
public interface TblAdPicturesMapper {
    int deleteByPrimaryKey(Long adPicId);

    int insert(TblAdPictures record);

    int insertSelective(TblAdPictures record);

    TblAdPictures selectByPrimaryKey(Long adPicId);

    int updateByPrimaryKeySelective(TblAdPictures record);

    int updateByPrimaryKey(TblAdPictures record);
    
    List<TblAdPictures> findAppAdPicturesByPicType(@Param("adPicType")String adPicType);
    
    Long findAdCount(Map<String,Object> map);

    List<TblAdPictures> findAdvertisementByParams(Map<String,Object> map);
}