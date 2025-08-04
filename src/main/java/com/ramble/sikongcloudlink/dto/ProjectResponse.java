package com.ramble.sikongcloudlink.dto;

import lombok.Data;

import java.util.List;

/**
 * Project     ramble-sikong-cloud-link
 * Package     com.ramble.sikongcloudlink.dto
 * Class       DeviceResponse
 * date        2025/4/28 11:21
 * author      cml
 * Email       liangchen_beijing@163.com
 * Description
 */

@Data
public class ProjectResponse {

    private BasePage pagination;

    private List<Project> list;

}
