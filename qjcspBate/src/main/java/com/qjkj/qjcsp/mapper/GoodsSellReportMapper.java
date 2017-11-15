package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.CompanyPo;
import com.qjkj.qjcsp.entity.GoodsSellReportPo;
import com.qjkj.qjcsp.entity.GoodsSellReportSearch;
//import com.qjkj.qjcsp.entity.MachineGoodsSell;
@MyBatisRepository
public interface GoodsSellReportMapper {
	
	/*public int findAllGoodsSellCount(GoodsSellReportSearch goodsSellReportSearch);*/
	
	public List<GoodsSellReportPo> findAllGoodsSellReportList(GoodsSellReportSearch goodsSellReportSearch);
	
	public List<GoodsSellReportPo> findAllGoodsSellReportOrders(GoodsSellReportSearch goodsSellReportSearch);
	
	public List<Map<String,Object>> findAllGoodsSellReportListExport(GoodsSellReportSearch goodsSellReportSearch);
	public List<Map<String,Object>> findAllGoodsSellReportOrdersExport(GoodsSellReportSearch goodsSellReportSearch);
	public List<CompanyPo> getCompanyIdAndName(Map map);
	//查询每柜商品销售数据
	//public List<MachineGoodsSell> findListMachineGoodsSell(MachineGoodsSell machineGoodsSell);
	//public List<MachineGoodsSell> findAllMachineGoodsSellReportOrders(MachineGoodsSell machineGoodsSell);
	//public List<Map<String, Object>> findListMachineGoodsSellExcept(MachineGoodsSell machineGoodsSell);
	public int findPutGoodsCount(Map<String, Object> map);
}
