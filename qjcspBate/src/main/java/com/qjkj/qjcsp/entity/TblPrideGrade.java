package com.qjkj.qjcsp.entity;

import java.math.BigDecimal;

public class TblPrideGrade {
    private Long id;

    private String prideName;

    private BigDecimal prideMoney;

    private Integer prideMount;

    private BigDecimal prideSingleMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrideName() {
        return prideName;
    }

    public void setPrideName(String prideName) {
        this.prideName = prideName == null ? null : prideName.trim();
    }

    public BigDecimal getPrideMoney() {
        return prideMoney;
    }

    public void setPrideMoney(BigDecimal prideMoney) {
        this.prideMoney = prideMoney;
    }

    public Integer getPrideMount() {
        return prideMount;
    }

    public void setPrideMount(Integer prideMount) {
        this.prideMount = prideMount;
    }

    public BigDecimal getPrideSingleMoney() {
        return prideSingleMoney;
    }

    public void setPrideSingleMoney(BigDecimal prideSingleMoney) {
        this.prideSingleMoney = prideSingleMoney;
    }
}