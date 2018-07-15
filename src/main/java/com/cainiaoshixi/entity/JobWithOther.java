package com.cainiaoshixi.entity;

/**
 * Created by Zxc on 2018/7/15.
 */
public class JobWithOther extends Job{
    private String name;

    private String abbr;

    private String province;

    private String city;

    private String district;

    private String address;

    private String introducation;

    private String officialWeb;

    private String logo;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    @Override
    public String getProvince() {
        return province;
    }

    @Override
    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getDistrict() {
        return district;
    }

    @Override
    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroducation() {
        return introducation;
    }

    public void setIntroducation(String introducation) {
        this.introducation = introducation;
    }

    public String getOfficialWeb() {
        return officialWeb;
    }

    public void setOfficialWeb(String officialWeb) {
        this.officialWeb = officialWeb;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
