package cn.bugstack.gateway.infrastucture.common;

import java.util.List;

/**
 * Author: chs
 * Description: 运营管理响应结果
 * CreateTime: 2024-09-25
 */
public class OperationResult<T> {

    private int total;
    private List<T> data;

    public OperationResult(){
    }

    public OperationResult(List<T> data, int total) {
        this.total = total;
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
