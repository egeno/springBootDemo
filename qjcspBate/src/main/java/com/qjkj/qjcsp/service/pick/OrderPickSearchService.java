package com.qjkj.qjcsp.service.pick;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.pick.OrderPickSearch;
import com.qjkj.qjcsp.entity.pick.PickPo;
import com.qjkj.qjcsp.mapper.OrderPickSearchMapper;

/**
 * 取餐记录查询
 * @author yehx
 * @date 2016年1月20日  上午10:11:19
 */
@Service
public class OrderPickSearchService {
	@Autowired
	private OrderPickSearchMapper orderPickSearchMapper;
	
	
	/**
	 * 得到这个公司下的所有的设备
	 * @author yehx
	 * @date 2016年1月20日
	 * @return
	 *
	 */
	public List<OrderPickSearch> getMachine(Map map){
		return orderPickSearchMapper.getMachine(map);
	}
	
	/**
	 * 得到取餐数量
	 * @author yehx
	 * @date 2016年1月21日
	 * @param orderPickSearch
	 * @return
	 *
	 */
	public int findAllPickCount(OrderPickSearch orderPickSearch){
		return orderPickSearchMapper.findAllPickCount(orderPickSearch);
	};
	
	/**
	 * 得到取餐记录
	 * @author yehx
	 * @date 2016年1月21日
	 * @param orderPickSearch
	 * @return
	 *
	 */
	public List<PickPo> findAllpickList(OrderPickSearch orderPickSearch){
		
		return orderPickSearchMapper.findAllpickList(orderPickSearch);
	}
	
	
	
}
