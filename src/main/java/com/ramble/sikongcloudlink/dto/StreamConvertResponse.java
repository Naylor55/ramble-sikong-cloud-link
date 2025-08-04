package com.ramble.sikongcloudlink.dto;

import lombok.Data;

import java.util.List;

/**
 * Project     ramble-sikong-cloud-link
 * Package     com.ramble.sikongcloudlink.dto
 * Class       StreamConvertResponse
 * date        2025/4/28 13:39
 * author      cml
 * Email       liangchen_beijing@163.com
 * Description
 */

@Data
public class StreamConvertResponse {


    private Integer total_count;

    private Integer cursor;

    public List<StreamConvertMember> members;

}
