package cn.bugstack.gateway.test;

import com.alibaba.fastjson.JSON;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;
import org.objectweb.asm.Type;

import java.lang.reflect.Method;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-08-31
 */
public class CglibTest implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        return JSON.toJSONString(args);
    }

    @Test
    public void test_cglib() throws Exception {
        //定义接口
        InterfaceMaker interfaceMaker = new InterfaceMaker();
        interfaceMaker.add(new Signature("sayHi", Type.getType(String.class), new Type[]{Type.getType(String.class)}), null);
        Class<?> interfaceClass = interfaceMaker.create();

        //创建代理对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Object.class);
        enhancer.setInterfaces(new Class[]{interfaceClass});
        enhancer.setCallback(this);
        Object obj = enhancer.create();

        //调用方法
        Method method = obj.getClass().getMethod("sayHi", String.class);
        Object result = method.invoke(obj, "hi xiaofuge");

        System.out.println(result);
    }
}
