package com.My.Reptile.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
public class YunMap {
    public  YunMap(){
        this._images = new ArrayList<>();
    }
    private Long _id;//企业id
    private String _location;//企业经纬度
    private String _name;//企业名
    private String _address;//企业地址
    private String _createtime;//创建时间
    private String _updatetime;//更新时间
    private String _province;//省份
    private String _city;//城市
    private String _districe;//地区
    private List<String> _images;//图片信息

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String get_location() {
        return _location;
    }

    public void set_location(String _location) {
        this._location = _location;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_createtime() {
        return _createtime;
    }

    public void set_createtime(String _createtime) {
        this._createtime = _createtime;
    }

    public String get_updatetime() {
        return _updatetime;
    }

    public void set_updatetime(String _updatetime) {
        this._updatetime = _updatetime;
    }

    public String get_province() {
        return _province;
    }

    public void set_province(String _province) {
        this._province = _province;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public String get_districe() {
        return _districe;
    }

    public void set_districe(String _districe) {
        this._districe = _districe;
    }

    public List<String> get_images() {
        return _images;
    }

    public void set_images(List<String> _images) {
        this._images = _images;
    }
}
