package com.ramble.sikongcloudlink.dto;

import lombok.Data;

/**
 * Project     ramble-sikong-cloud-link
 * Package     com.ramble.sikongcloudlink.dto
 * Class       BasePage
 * date        2025/4/28 11:18
 * author      cml
 * Email       liangchen_beijing@163.com
 * Description
 */
@Data
public class BasePage {
    private Integer page;
    private Integer page_size;
    private String total;
}
