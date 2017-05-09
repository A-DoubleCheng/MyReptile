package com.My.Reptile.Param;

/**
 * Created by Administrator on 2017/4/27.
 */
public class PageParam {
    private int pageIndex;//当前页
    private int pageSize;//每页显示多少

    public PageParam(){
        this.pageIndex = 1;
        this.pageSize = 5;
    }
    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
