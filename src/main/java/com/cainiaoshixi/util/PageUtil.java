package com.cainiaoshixi.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageUtil<T> {
    private int pageSize;    //每页记录数
    private int pageNumber;    //当前页码
    private int totalPageCount; //总页数
    private int totalDataCount; //总记录条数
    private List<T> list;       //当前页数据

    //得到开始记录数
    public int getStartRow(){
        return (pageNumber-1)*pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    //当前页
    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    //总页数
    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount() {
        int totalP = totalDataCount % getPageSize() == 0 ? totalDataCount / getPageSize() :
                totalDataCount/ getPageSize() + 1;
        this.totalPageCount = totalP;
    }
    //总记录数
    public int getTotalDataCount() {
        return totalDataCount;
    }

    public void setTotalDataCount(int totalDataCount) {
        this.totalDataCount = totalDataCount;
        //设置总页数
        setTotalPageCount();
    }

    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
}
