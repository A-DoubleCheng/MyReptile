package com.buyal.mall.Param;

/**
 * Created by Administrator on 2017/3/20.
 */
public class AddOneEntParam {
    private String id;//数据ID
    private String name;//企业名字
    private String address;//企业地址
    private String loctype;//定位方式，1=经纬度，2=地址
    private String location;//坐标，经度,纬度，经纬度支持到小数点后6位
    private String coordtype;//坐标类型，1=gps，2=autonavi，3=baidu
    private String enterprise_telephone;//企业电话

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLoctype() {
        return loctype;
    }

    public void setLoctype(String loctype) {
        this.loctype = loctype;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCoordtype() {
        return coordtype;
    }

    public void setCoordtype(String coordtype) {
        this.coordtype = coordtype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterprise_telephone() {
        return enterprise_telephone;
    }

    public void setEnterprise_telephone(String enterprise_telephone) {
        this.enterprise_telephone = enterprise_telephone;
    }
}
