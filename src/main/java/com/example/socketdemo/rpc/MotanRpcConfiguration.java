package com.example.socketdemo.rpc;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description: motan rpc配置
 * @Author: god_ole
 * @Date: 2019/3/12 15:58i·
 */
@Configuration
@ImportResource(locations = {"classpath:spring-rpc.xml"})
public class MotanRpcConfiguration {
}
