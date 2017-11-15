package com.qjkj.qjcsp.service.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.mapper.TblTemporaryRetailMapper;

@Service
@Transactional
public class TemporaryRetailService {
	@Autowired
	private TblTemporaryRetailMapper tblTemporaryRetailMapper;

	public int getCount(Long issueId, Long goodsId) {
		return tblTemporaryRetailMapper.getCount(issueId, goodsId);
	}
}
