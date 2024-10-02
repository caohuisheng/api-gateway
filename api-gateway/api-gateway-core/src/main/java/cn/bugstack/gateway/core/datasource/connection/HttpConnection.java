package cn.bugstack.gateway.core.datasource.connection;

import cn.bugstack.gateway.core.datasource.Connection;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * Author: chs
 * Description: http连接
 * CreateTime: 2024-09-02
 */
public class HttpConnection implements Connection {

    private final HttpClient httpClient;
    private PostMethod postMethod;

    public HttpConnection(String uri){
        httpClient = new HttpClient();
        postMethod = new PostMethod(uri);
        postMethod.addRequestHeader("accept","*/*");
        postMethod.addRequestHeader("connection","Keep-Alive");
        postMethod.addRequestHeader("Content-Type","application/json;charset=UTF-8");
        postMethod.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36 Edg/128.0.0.0");
    }

    @Override
    public Object execute(String methodName, String[] parameterTypes, String[] parameterNames, Object[] args) {
        String res = "";
        try {
            int code = httpClient.executeMethod(postMethod);
            if(code == 200){
                res = postMethod.getResponseBodyAsString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
