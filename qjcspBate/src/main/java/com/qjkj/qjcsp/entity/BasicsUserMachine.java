package com.qjkj.qjcsp.entity;

public class BasicsUserMachine {
    private Long id;

    private String specialRoleNum;
    
    private Long userId;

    private Long machineId;


    public String getSpecialRoleNum() {
		return specialRoleNum;
	}

	public void setSpecialRoleNum(String specialRoleNum) {
		this.specialRoleNum = specialRoleNum;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}