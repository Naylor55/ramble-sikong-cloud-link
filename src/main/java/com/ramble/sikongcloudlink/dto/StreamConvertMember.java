package com.ramble.sikongcloudlink.dto;

import lombok.Data;

/**
 * Project     ramble-sikong-cloud-link
 * Package     com.ramble.sikongcloudlink.dto
 * Class       StreamConvertMember
 * date        2025/4/28 13:43
 * author      cml
 * Email       liangchen_beijing@163.com
 * Description
 */

@Data
public  class StreamConvertMember {


    private  String rtc_channel;

    private  String status;

    private  String converter_name;

    private  String update_ts;

    private  String rtmp_url;

    private  String converter_id;

    private  String idle_timeout;

    private  String state;

}