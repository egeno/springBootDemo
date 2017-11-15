package com.qjkj.qjcsp.entity;

import java.util.Date;

public class BasicsUserMachineLog {
	private Long id;
	
	private String operation;

	private Long userId;

    private Long machineId;


    private Long operationUserId;

    private Date operationTime;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


    public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Long getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(Long operationUserId) {
        this.operationUserId = operationUserId;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }
}