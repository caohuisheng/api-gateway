package cn.bugstack.gateway.infrastucture.common;

import java.io.Serializable;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-11
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 10001L;
    private String code;
    private String info;
    private T data;

    public Result(String code, String info, T data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", info='" + info + '\'' +
                ", data=" + data +
                '}';
    }
}
