package cn.bugstack.gateway.session.socket.handlers;

import cn.bugstack.gateway.bind.IGenericReference;
import cn.bugstack.gateway.session.socket.BaseHandler;
import cn.bugstack.gateway.session.GatewaySession;
import cn.bugstack.gateway.session.GatewaySessionFactory;
import cn.bugstack.gateway.session.socket.agreement.RequestParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author 小傅哥，微信：fustack
 * @description 会话服务处理器
 * @github github.com/fuzhengwei
 * @copyright 公众号：bugstack虫洞栈 | 博客：bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class GatewayServerHandler extends BaseHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(GatewayServerHandler.class);

    private GatewaySessionFactory gatewaySessionFactory;

    public GatewayServerHandler(GatewaySessionFactory gatewaySessionFactory){
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void session(ChannelHandlerContext ctx, final Channel channel, FullHttpRequest request) {
        logger.info("网关接收请求 uri：{} method：{}", request.uri(), request.method());
        //解析请求参数
        Map<String, Object> requestObj = new RequestParser(request).parse();

        //返回信息控制：简单处理
        String uri = request.uri();
        int idx = uri.indexOf("?");
        uri = idx > 0?uri.substring(0,idx):uri;
        if(uri.equals("/favicon")) return;

        // 服务泛化调用
        GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
        IGenericReference reference = gatewaySession.getMapper();
        String result = reference.$invoke(requestObj) + " " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        // 返回信息处理
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        // 设置响应内容
        response.content().writeBytes(JSON.toJSONBytes(result, SerializerFeature.PrettyFormat));
        // 头部信息设置
        HttpHeaders heads = response.headers();
        // 返回内容类型
        heads.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON + ";charset=UTF-8");
        // 响应体的长度
        heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        // 配置持久连接
        heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        // 配置跨域访问
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");

        channel.writeAndFlush(response);
        logger.info("local_addr:"+channel.localAddress() + " remote_addr:"+channel.remoteAddress());
    }

}
