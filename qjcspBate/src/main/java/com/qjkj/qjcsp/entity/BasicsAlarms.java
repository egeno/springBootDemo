package com.qjkj.qjcsp.entity;

public class BasicsAlarms {
    private String alarmCode;

    private String alarmName;

    private String alarmLevel;

    private String alarmOperation;

    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode == null ? null : alarmCode.trim();
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName == null ? null : alarmName.trim();
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel == null ? null : alarmLevel.trim();
    }

    public String getAlarmOperation() {
        return alarmOperation;
    }

    public void setAlarmOperation(String alarmOperation) {
        this.alarmOperation = alarmOperation == null ? null : alarmOperation.trim();
    }
}