package cn.bugstack.gateway.core.socket.handlers;

import cn.bugstack.gateway.core.session.Configuration;
import cn.bugstack.gateway.core.mapping.HttpStatement;
import cn.bugstack.gateway.core.socket.BaseHandler;
import cn.bugstack.gateway.core.socket.agreement.AgreementConstants;
import cn.bugstack.gateway.core.socket.agreement.GatewayResultMessage;
import cn.bugstack.gateway.core.socket.agreement.ResponseParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: chs
 * Description: 鉴权
 * CreateTime: 2024-09-08
 */
public class AuthorizationHandler extends BaseHandler<FullHttpRequest> {

    private final Logger log = LoggerFactory.getLogger(AuthorizationHandler.class);
    private Configuration configuration;

    public AuthorizationHandler(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        log.info("网关接收请求【鉴权】 uri:{} method:{}",request.uri(), request.method());

        try {
            HttpStatement httpStatement = channel.attr(AgreementConstants.HTTP_STATEMENT).get();
            //判断是否需要鉴权
            if(httpStatement.isAuth()){
                String uId = request.headers().get("uId");
                String token = request.headers().get("token");
                //token为空，直接返回
                if(token == null || token.equals("")){
                    DefaultFullHttpResponse response = new ResponseParser().parse(
                            GatewayResultMessage.buildError(AgreementConstants.ResponseCode._403.getCode(),"token不能为空"));
                    channel.writeAndFlush(response);
                }
                //执行鉴权
                boolean status = configuration.authValid(uId, token);
                if(status){
                    request.retain();
                    ctx.fireChannelRead(request);
                }else{
                    DefaultFullHttpResponse response = new ResponseParser().parse(
                            GatewayResultMessage.buildError(AgreementConstants.ResponseCode._403.getCode(),"您无权访问此接口"));
                    channel.writeAndFlush(response);
                }
            }else{
                //不需要鉴权，直接放行
                request.retain();
                ctx.fireChannelRead(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
            DefaultFullHttpResponse response = new ResponseParser().parse(
                    GatewayResultMessage.buildError(AgreementConstants.ResponseCode._500.getCode(),"服务器鉴权异常"));
            channel.writeAndFlush(response);
        }
    }
}
