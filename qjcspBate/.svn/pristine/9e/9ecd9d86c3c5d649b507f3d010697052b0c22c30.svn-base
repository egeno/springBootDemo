package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.pick.OrderPickSearch;
import com.qjkj.qjcsp.entity.pick.PickPo;

/**
 * 取餐记录查询
 * @author yehx
 * @date 2016年1月20日  上午10:31:46
 */
@MyBatisRepository
public interface OrderPickSearchMapper {
	//得到设备的信息
	public List<OrderPickSearch>  getMachine(Map map);
	
	/**
	 * 得到总条数
	 * @author yehx
	 * @date 2016年1月20日
	 * @param orderPickSearch
	 * @return
	 *
	 */
	public int findAllPickCount(OrderPickSearch orderPickSearch);
	
	/**
	 * 取餐记录
	 * @author yehx
	 * @date 2016年1月20日
	 * @param orderPickSearch
	 * @return
	 *
	 */
	public List<PickPo> findAllpickList(OrderPickSearch orderPickSearch);
}
