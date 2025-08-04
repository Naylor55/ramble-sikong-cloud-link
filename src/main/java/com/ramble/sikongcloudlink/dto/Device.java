package com.ramble.sikongcloudlink.dto;

import lombok.Data;

/**
 * Project     ramble-sikong-cloud-link
 * Package     com.ramble.sikongcloudlink.dto
 * Class       Device
 * date        2025/4/28 13:22
 * author      cml
 * Email       liangchen_beijing@163.com
 * Description
 */
@Data
public class Device {

    private String sn;

    private String nickname;

    private Boolean is_org_device;

    private DeviceModel device_model;
}
