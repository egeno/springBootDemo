package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;

@MyBatisRepository
public interface TblReplenishmentPrintMapper {

	Long findByCount(Map<String, Object> param);

	List<Map<String, Object>> findByList(Map<String, Object> param);

}
