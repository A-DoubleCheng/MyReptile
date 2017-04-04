package com.My.Reptile.common;

import java.util.HashMap;
import java.util.Map;

public class SearchParams {

    private Map<String, Object> data;

    public SearchParams() {
        this.data = new HashMap<String, Object>();
    }

    public Map<String, Object> addParam(String key, Object value) {
        if (this.data == null) {
            this.data = new HashMap<String, Object>();
        }

        this.data.put(key, value);

        return this.data;
    }

    public Object getValue(String key) {
        return this.getData().get(key);
    }

    public void setValue(String key, Object value) {
        this.data.put(key, value);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void remove(String key) {
        if (this.data.containsKey(key)) {
            this.data.remove(key);
        }
    }
}
