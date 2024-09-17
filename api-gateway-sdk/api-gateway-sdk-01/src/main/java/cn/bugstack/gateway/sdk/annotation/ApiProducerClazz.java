package cn.bugstack.gateway.sdk.annotation;

import java.lang.annotation.*;

/**
 * Author: chs
 * Description: 网关API生产者类自定义注解
 * CreateTime: 2024-09-17
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ApiProducerClazz {

    // 接口名称
    String interfaceName() default "";

    // 接口版本
    String interfaceVersion() default "";

}
