package com.ramble.sikongcloudlink.dto;

import lombok.Data;

/**
 * Project     ramble-sikong-cloud-link
 * Package     com.ramble.sikongcloudlink.dto
 * Class       BaseResponse
 * date        2025/4/28 11:16
 * author      cml
 * Email       liangchen_beijing@163.com
 * Description
 */
@Data
public class BaseResponse<T> {
    private Integer code;
    private String message;
    private T data;

}
