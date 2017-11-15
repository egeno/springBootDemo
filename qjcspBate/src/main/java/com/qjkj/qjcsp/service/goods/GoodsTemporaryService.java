package com.qjkj.qjcsp.service.goods;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.mapper.TblTemporaryRetailMapper;
import com.qjkj.qjcsp.util.Constants;

@Component
@Service
@Transactional
public class GoodsTemporaryService {
	@Autowired
	private TblTemporaryRetailMapper tblTemporaryRetailMapper;

	/**
	 * 查询出商品的零售数量，预订数量，总数
	 */
	public List<Map<String, Object>> selectGoodsSaleCount(String temporaryDate, Long machineId, Long areaModelId) {
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		List<Map<String, Object>> temporaryRetailList = tblTemporaryRetailMapper
				.queryTemporaryRetailSaleCount(temporaryDate, machineId, areaModelId, companyId.longValue());
		return temporaryRetailList;
	}

	/**
	 * 查询出商品的零售数量，预订数量，总数
	 */
	public List<Map<String, Object>> selectGoodsSaleCount1(String temporaryDate, Long machineId, Long areaModelId) {
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		List<Map<String, Object>> temporaryRetailList = tblTemporaryRetailMapper
				.queryTemporaryRetailSaleCount1(temporaryDate, machineId, areaModelId, companyId.longValue());
		return temporaryRetailList;
	}
}
