package com.qjkj.qjcsp.service.machine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.mapper.BasicsMachineAreaModelMapper;

@Service
@Transactional
public class MachineAreaModelService {
	@Autowired
	private BasicsMachineAreaModelMapper machineAreaModelMapper;
	/**
	 * 根据设备Id获取设备绑定的模型Id
	 * */
	public List<Long> getAreaModelIdByMachineId(Long machineId) {
		return machineAreaModelMapper.getAreaModelIdByMachineId(machineId);
	}
}