package com.ramble.sikongcloudlink.controller;

import com.ramble.sikongcloudlink.component.CloudLinkComponent;
import com.ramble.sikongcloudlink.dto.Device;
import com.ramble.sikongcloudlink.dto.Project;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Project     ramble-sikong-cloud-link
 * Package     com.ramble.sikongcloudlink.controller
 * Class       TestController
 * date        2025/4/28 11:28
 * author      cml
 * Email       liangchen_beijing@163.com
 * Description
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {


    private final CloudLinkComponent cloudLinkComponent;


    @Operation(description = "获取组织下项目列表")
    @GetMapping("/orgProject")
    public List<Project> findOrgProject() {
        return cloudLinkComponent.findOrgProject();
    }

    @Operation(description = "获取项目下设备列表")
    @GetMapping("/projectDevice")
    public List<Device> findProjectDevice() {
        return cloudLinkComponent.findProjectDevice(null);
    }

    @Operation(description = "开始码流转发")
    @GetMapping("/startStreamConvert")
    public void startStreamConvert() {
//        cloudLinkComponent.startStreamConvert("1581F7FVC253900D1DWQ", "88-0-0");
        cloudLinkComponent.startStreamConvert("1ZNBHBS00C004G", "39-0-7");
    }

    @Operation(description = "停止码流转发")
    @GetMapping("/stopStreamConvert")
    public void stopStreamConvert() {
        cloudLinkComponent.stopStreamConvert("1234567890");
    }

    @Operation(description = "获取码流转发列表")
    @GetMapping("/convertList")
    public void convertList() {
        cloudLinkComponent.getChannel("1581F7FVC253900D1DWQ", "88-0-0");
    }

    @GetMapping("/test1")
    public void test1() {
        cloudLinkComponent.test();
    }


}
