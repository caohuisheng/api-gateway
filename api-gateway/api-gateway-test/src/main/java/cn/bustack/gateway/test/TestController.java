package cn.bustack.gateway.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-28
 */
@RestController
public class TestController {

    @Value("${custom.msg}")
    private String msg;

    @GetMapping("/api01")
    public String method1(){
        System.out.println("call api01...");
        return this.msg;
    }

    @GetMapping("/api02")
    public String method2(){
        System.out.println("call api02...");
        return "hello,world~";
    }

}
