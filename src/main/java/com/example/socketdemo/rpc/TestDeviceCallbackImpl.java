package com.example.socketdemo.rpc;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.geekplus.venus.api.entity.ApiResponse;
import com.geekplus.venus.api.entity.MapEntity;
import com.geekplus.venus.api.msg.CallbackHeader;
import com.geekplus.venus.device.api.call.RpcCallbackApi;

/**
 * @author 吴大伟
 * @version 0.0.1 初始化创建
 * @description
 * @date 2019-04-19 16:17
 */
@Slf4j
@Component
public class TestDeviceCallbackImpl implements RpcCallbackApi {

    @Override
    public ApiResponse<MapEntity> callback(CallbackHeader callbackHeader, MapEntity jsonObject) {
        log.info("TEST 收到请求callbackHeader:{},mapEntity:{}", callbackHeader, jsonObject);
        return createResponse(callbackHeader);
    }

    private ApiResponse<MapEntity> createResponse(CallbackHeader callbackHeader) {
        return ApiResponse.<MapEntity>builder()
                .responseId(callbackHeader.getRequestId())
                .code(0)
                .msg("")
                .build();
    }

}
