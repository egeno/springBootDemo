package com.qjkj.qjcsp.entity;

public class GCity {
    private Long cityId;

    private String cityName;

    private String spellAll;

    private String spellShort;

    private Long provinceId;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getSpellAll() {
        return spellAll;
    }

    public void setSpellAll(String spellAll) {
        this.spellAll = spellAll == null ? null : spellAll.trim();
    }

    public String getSpellShort() {
        return spellShort;
    }

    public void setSpellShort(String spellShort) {
        this.spellShort = spellShort == null ? null : spellShort.trim();
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
}