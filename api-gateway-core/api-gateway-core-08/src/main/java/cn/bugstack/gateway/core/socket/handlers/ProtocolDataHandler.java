package cn.bugstack.gateway.core.socket.handlers;

import cn.bugstack.gateway.core.bind.IGenericReference;
import cn.bugstack.gateway.core.executor.result.SessionResult;
import cn.bugstack.gateway.core.session.GatewaySession;
import cn.bugstack.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import cn.bugstack.gateway.core.socket.agreement.RequestParser;
import cn.bugstack.gateway.core.socket.BaseHandler;
import cn.bugstack.gateway.core.socket.agreement.AgreementConstants;
import cn.bugstack.gateway.core.socket.agreement.GatewayResultMessage;
import cn.bugstack.gateway.core.socket.agreement.ResponseParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Author: chs
 * Description: 协议数据处理器
 * CreateTime: 2024-09-08
 */
public class ProtocolDataHandler extends BaseHandler<FullHttpRequest> {

    private Logger log = LoggerFactory.getLogger(ProtocolDataHandler.class);
    private DefaultGatewaySessionFactory gatewaySessionFactory;

    public ProtocolDataHandler(DefaultGatewaySessionFactory gatewaySessionFactory){
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        log.info("网关接收请求【消息】 uri:{} method:{}", request.uri(), request.method());
        try {
            //1.解析请求参数
            RequestParser parser = new RequestParser(request);
            String uri = parser.getUri();
            if(null == uri) return;
            Map<String, Object> args = parser.parse();

            //2.调用会话服务
            GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
            IGenericReference mapper = gatewaySession.getMapper();
            SessionResult result = mapper.$invoke(args);

            //3.封装返回结果
            DefaultHttpResponse response = new ResponseParser().parse("0000".equals(result.getCode()) ?
                    GatewayResultMessage.buildSuccess(result) : GatewayResultMessage.buildError(AgreementConstants.ResponseCode._404.getCode(), "网关协议调用失败"));
            channel.writeAndFlush(response);
        } catch (Exception e) {
            e.printStackTrace();
            DefaultHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._502.getCode(), "网关协议调用失败"));
            channel.writeAndFlush(response);
        }
    }
}