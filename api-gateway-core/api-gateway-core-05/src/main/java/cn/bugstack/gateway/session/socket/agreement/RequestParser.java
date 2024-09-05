package cn.bugstack.gateway.session.socket.agreement;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-05
 */
public class RequestParser {

    private final FullHttpRequest request;

    public RequestParser(FullHttpRequest request){
        this.request = request;
    }

    public Map<String, Object> parse(){
        HttpMethod method = request.getMethod();
        if(HttpMethod.GET == method){
            Map<String,Object> parameterMap = new HashMap<>();
            QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
            decoder.parameters().forEach((key,value) -> {
                parameterMap.put(key, value.get(0));
            });
            return parameterMap;
        }else if(HttpMethod.POST == method){
            String contentType = getContentType();
            switch(contentType){
                case "multipart/form-data":
                    Map<String,Object> parameterMap = new HashMap<>();
                    HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
                    decoder.offer(request);
                    decoder.getBodyHttpDatas().forEach(data -> {
                        Attribute attr = (Attribute) data;
                        try {
                            parameterMap.put(data.getName(), attr.getValue());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    return parameterMap;
                case "application/json":
                    ByteBuf byteBuf = request.content().copy();
                    if(byteBuf.isReadable()){
                        String content = byteBuf.toString(StandardCharsets.UTF_8);
                        return JSON.parseObject(content);
                    }
                    break;
                default:
                    throw new RuntimeException("未实现的协议类型 Content-Type:" + contentType);
            }
        }
        throw new RuntimeException("未实现的请求类型 HttpMethod:" + method);
    }

    private String getContentType(){
        Optional<Map.Entry<String, String>> header = request.headers().entries().stream()
                .filter(head -> head.getKey().equals("Content-Type")).findAny();
        Map.Entry<String, String> entry = header.orElse(null);
        assert entry != null;
        String contentType = entry.getValue();
        int idx = contentType.indexOf(";");
        return idx > 0?contentType.substring(0,idx):contentType;
    }
}
