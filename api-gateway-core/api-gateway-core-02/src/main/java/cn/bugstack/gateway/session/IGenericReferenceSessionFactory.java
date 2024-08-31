package cn.bugstack.gateway.session;

import io.netty.channel.Channel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Author: chs
 * Description: 泛化调用会话工厂接口
 * CreateTime: 2024-08-30
 */
public interface IGenericReferenceSessionFactory {

    Future<Channel> openSession() throws ExecutionException, InterruptedException;

}
