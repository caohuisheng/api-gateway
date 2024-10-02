package cn.bugstack.gateway.sdk.annotation;

import java.lang.annotation.*;

/**
 * Author: chs
 * Description: 网关API生产者方法自定义注解
 * CreateTime: 2024-09-17
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ApiProducerMethod {

    // 方法名称
    String methodName() default "";

    // 访问路径
    String uri() default "";

    // 接口类型
    String httpCommandType() default "GET";

    // 是否认证
    int auth() default 0;

}
