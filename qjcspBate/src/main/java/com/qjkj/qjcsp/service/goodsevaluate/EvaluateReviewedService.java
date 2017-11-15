package com.qjkj.qjcsp.service.goodsevaluate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.EvaluateSearch;
import com.qjkj.qjcsp.mapper.TblGoodsEvaluateMapper;

@Service
@Transactional
public class EvaluateReviewedService {
	@Autowired
	private TblGoodsEvaluateMapper tblGoodsEvaluateMapper;

	public long selectCount(EvaluateSearch evaluateSearch) {
		 return tblGoodsEvaluateMapper.selectCount(evaluateSearch);
	}

	public List<Map<String, Object>> findEvaluateList(EvaluateSearch evaluateSearch) {
		 return tblGoodsEvaluateMapper.findEvaluateList(evaluateSearch);
	}

	public void evaluatereviewedPass(List<Integer> arrayObj) {
		tblGoodsEvaluateMapper.evaluatereviewedPass(arrayObj);
		
	}
	public void evaluatereviewedOut(List<Integer> arrayObj) {
		tblGoodsEvaluateMapper.evaluatereviewedOut(arrayObj);
		
	}
	public List<Map<String, Object>> evaluatereviewedExport(EvaluateSearch evaluateSearch) {

		return tblGoodsEvaluateMapper.evaluatereviewedExport(evaluateSearch);
	}

	

}
