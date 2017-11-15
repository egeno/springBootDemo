package com.qjkj.qjcsp.service.discountactivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblLotteryGradeActivity;
import com.qjkj.qjcsp.mapper.TblLotteryGradeActivityMapper;
import com.qjkj.qjcsp.mapper.TblLuckyMoneyMapper;

import net.sf.json.JSONObject;
/**
 * APP获取红包列表
 * @author carpeYe 2016-03-31
 *
 */
@Service
public class LuckyMoneyService {

	@Autowired
	private TblLuckyMoneyMapper tblLuckyMoneyMapper;
	@Autowired
	private TblLotteryGradeActivityMapper tblLotteryGradeActivityMapper;
	

	public Map<String, Object> getLuckMoney(JSONObject res) {
		Map<String, Object> content = new HashMap<String, Object>();
		String customerId = res.getString("customerId");
		if (StringUtils.isNoneBlank(customerId)) {
			List<Map<String, Object>> luckyMoneys = tblLuckyMoneyMapper
					.findLuckyMoneysByCustomerId(Long.valueOf(customerId));
			if(luckyMoneys!=null){
				content.put("returnContent",luckyMoneys);
			}else{
				content.put("returnContent","");
			}
			content.put("returnCode", "1");
		}else{
			content.put("returnCode", "0");
			content.put("returnContent", "请求参数异常！");
		}
		return content;
	}
	
	/**
	 * 查询红包设置记录
	 */
	public Page<TblLotteryGradeActivity> findAllLuckyMoneyActivity(Map<String, Object> param, int pageNumber, int pageSize){
		Long total = tblLuckyMoneyMapper.findByCount(param);
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);

		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);
		param.put("order", "ID");
		param.put("sort", Sort.Direction.DESC);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<TblLotteryGradeActivity> list = new ArrayList<TblLotteryGradeActivity>();
		if (total != 0) {
			list = tblLotteryGradeActivityMapper.findAllLuckyMoneyActivity(param);
			for (TblLotteryGradeActivity tblLotteryGradeActivity : list) {
				tblLotteryGradeActivity.setStartTime(tblLotteryGradeActivity.getStartTime().substring(0,19));
				tblLotteryGradeActivity.setEndTime(tblLotteryGradeActivity.getEndTime().substring(0,19));
				
				/*System.out.println(formatter.format(tblLotteryGradeActivity.getCreateTime()));
				try {
					System.out.println(formatter.parse(formatter.format(tblLotteryGradeActivity.getCreateTime())));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					tblLotteryGradeActivity.setCreateTime(formatter.parse(formatter.format(tblLotteryGradeActivity.getCreateTime())));
					tblLotteryGradeActivity.setModTime((formatter.parse(formatter.format(tblLotteryGradeActivity.getModTime()))));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} */
				
				tblLotteryGradeActivity.setCreateTimeStr(formatter.format(tblLotteryGradeActivity.getCreateTime()));
				tblLotteryGradeActivity.setModTimeStr(formatter.format(tblLotteryGradeActivity.getModTime()));
				
			}
		}
		Page<TblLotteryGradeActivity> page = new PageImpl<TblLotteryGradeActivity>(list, pageRequest, total);

		return page;
	}
	
	/**
	 * 判断是否有多条数据
	 */
	public Long getHasCount(){
		return tblLuckyMoneyMapper.findByCount();
	}
	
	/**
	 * 新增红包活动记录
	 */
	@Transactional
	public int insertSelective(TblLotteryGradeActivity tblLotteryGradeActivity){
		return tblLotteryGradeActivityMapper.insertSelective(tblLotteryGradeActivity);
	}

	/**
	 * 编辑
	 */
	@Transactional
	public int updateSelective(TblLotteryGradeActivity tblLotteryGradeActivity){
		return tblLotteryGradeActivityMapper.updateSelective(tblLotteryGradeActivity);
	}
	
	
}
