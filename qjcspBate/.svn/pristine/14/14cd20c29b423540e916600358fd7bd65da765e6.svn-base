package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/23.
 */
@MyBatisRepository
public interface GoodsPublishReportMapper {
    List<Map<String,Object>> getGoodsPublishReport(Map<String,Object> map);
    Long getGoodsPublishReportTotal(Map<String,Object> map);

    List<Long> findGoodsIdByPsdate(@Param("psdate") String psdate, @Param("companyId") Integer companyId);

    List<Map<String,Object>> findReportBygoodsId(Map<String,Object> map);
}
