package cn.bugstack.gateway.socket.handlers;

import cn.bugstack.gateway.bind.IGenericReference;
import cn.bugstack.gateway.mapping.HttpStatement;
import cn.bugstack.gateway.session.Configuration;
import cn.bugstack.gateway.socket.BaseHandler;
import cn.bugstack.gateway.session.GatewaySession;
import cn.bugstack.gateway.session.GatewaySessionFactory;
import cn.bugstack.gateway.socket.agreement.AgreementConstants;
import cn.bugstack.gateway.socket.agreement.GatewayResultMessage;
import cn.bugstack.gateway.socket.agreement.RequestParser;
import cn.bugstack.gateway.socket.agreement.ResponseParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author 小傅哥，微信：fustack
 * @description 会话服务处理器
 * @github github.com/fuzhengwei
 * @copyright 公众号：bugstack虫洞栈 | 博客：bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class GatewayServerHandler extends BaseHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(GatewayServerHandler.class);

    private Configuration configuration;

    public GatewayServerHandler(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    protected void session(ChannelHandlerContext ctx, final Channel channel, FullHttpRequest request) {
        logger.info("网关接收请求【全局】 uri：{} method：{}", request.uri(), request.method());
        try {
            // 1.解析请求参数
            RequestParser requestParser = new RequestParser(request);
            String uri = requestParser.getUri();

            // 2.保存信息
            HttpStatement httpStatement = configuration.getHttpStatement(uri);
            channel.attr(AgreementConstants.HTTP_STATEMENT).set(httpStatement);

            // 3.放行服务
            request.retain();
            ctx.fireChannelRead(request);
        } catch (Exception e) {
            e.printStackTrace();
            DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._500.getCode(),"网关协议调用失败"));
            channel.writeAndFlush(response);
        }
    }

}
