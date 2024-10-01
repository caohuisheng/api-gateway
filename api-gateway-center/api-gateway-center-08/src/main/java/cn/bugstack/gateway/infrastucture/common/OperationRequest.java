package cn.bugstack.gateway.infrastucture.common;

import org.apache.commons.lang3.StringUtils;

/**
 * Author: chs
 * Description: 运营管理请求参数
 * CreateTime: 2024-09-25
 */
public class OperationRequest<T> {

    private int pageStart;
    private int pageIndex;
    private int pageSize;

    private T data;

    public OperationRequest(int pageIndex, int pageSize){
        this.pageIndex = pageIndex == 0?1:pageIndex;
        this.pageSize = pageSize == 0?10:pageSize;
        this.pageStart = (this.pageIndex - 1)*this.pageSize;
    }

    public OperationRequest(String pageIndex, String pageSize){
        this.pageIndex = StringUtils.isEmpty(pageIndex)?1:Integer.parseInt(pageIndex);
        this.pageSize = StringUtils.isEmpty(pageSize)?10:Integer.parseInt(pageSize);
        this.pageStart = (this.pageIndex - 1)*this.pageSize;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
