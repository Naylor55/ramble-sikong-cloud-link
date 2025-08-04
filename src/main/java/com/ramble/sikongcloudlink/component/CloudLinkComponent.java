package com.ramble.sikongcloudlink.component;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.ramble.sikongcloudlink.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Project     ramble-sikong-cloud-link
 * Package     com.ramble.sikongcloudlink.component
 * Class       CloudLinkComponent
 * date        2025/4/28 11:05
 * author      cml
 * Email       liangchen_beijing@163.com
 * Description
 */


@Slf4j
@RequiredArgsConstructor
@Component
public class CloudLinkComponent {

    private final RestTemplate restTemplate;

    @Value("${sikong.orgToken}")
    private String sikongToken;

    @Value("${sikong.urlPrefix:https://es-flight-api-cn.djigate.com}")
    private String sikongUrlPrefix;

    @Value("${sikong.tokenKey:X-Organization-Key}")
    private String tokenKey;

    @Value("${sikong.jiyangProjectUuid:}")
    private String jiyangProjectUuid;


    /**
     * 获取请求头
     *
     * @return
     */
    private HttpHeaders getHeader() {
        HttpHeaders header = new HttpHeaders();
        header.add(tokenKey, sikongToken);
        return header;
    }


    /**
     * 获取组织下项目列表
     */
    public List<Project> findOrgProject() {
        List<Project> list = new ArrayList<>();
        String url = sikongUrlPrefix + "/manage/api/v1.0/projects";
        Map<String, String> param = new LinkedHashMap<>();
        param.put("page", "1");
        param.put("page_size", "99");
        String body = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(param, getHeader()), String.class).getBody();
        log.debug("获取组织下项目列表:{}", body);
        BaseResponse<ProjectResponse> response = JSON.parseObject(body, new TypeReference<BaseResponse<ProjectResponse>>() {
        });
        Integer code = response.getCode();
        if (code == 0) {
            list = Optional.ofNullable(response).map(BaseResponse::getData).map(ProjectResponse::getList).orElse(new ArrayList<>());
        }
        return list;
    }


    /**
     * 获取项目下设备列表
     *
     * @param projectUuid 项目 uuid ， 若不传 则为 jiyangProjectUuid
     * @return
     */
    public List<Device> findProjectDevice(String projectUuid) {
        List<Device> list = new ArrayList<>();
        if (!StringUtils.hasText(projectUuid)) {
            projectUuid = jiyangProjectUuid;
        }
        String url = sikongUrlPrefix + "/manage/api/v1.0/projects/" + projectUuid + "/devices";
        String body = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(getHeader()), String.class).getBody();
        log.debug("获取项目下设备列表:{}", body);
        BaseResponse<DeviceResponse> response = JSON.parseObject(body, new TypeReference<BaseResponse<DeviceResponse>>() {
        });
        Integer code = response.getCode();
        if (code == 0) {
            list = Optional.ofNullable(response).map(BaseResponse::getData).map(DeviceResponse::getList).orElse(new ArrayList<>());
        }
        return list;
    }


    /**
     * 启动流转换
     * <p>
     * 异常响应：
     * 示例1：400 Bad Request: "{"code":200101,"message":"Key: 'GetConversReq.Channel' Error:Field validation for 'Channel' failed on the 'required' tag"}"
     *
     * @param sn
     * @param cameraIndex
     */
    public void startStreamConvert(String sn, String cameraIndex) {
        String url = sikongUrlPrefix + "/manage/api/v1.0/stream-converters";
//        Map<String, String> param = new LinkedHashMap<>();
//        param.put("region", "cn");
////        param.put("converter_name", "stream_convertor_" + sn + IdUtil.fastSimpleUUID());
//        param.put("converter_name", "ddd");
//        param.put("sn", sn);
//        param.put("camera", cameraIndex);
//        param.put("video", "normal-0");
//        param.put("video_quality", "0");

        JSONObject param = new JSONObject();
        param.put("region", "cn");
//        param.put("converter_name", "stream_convertor_" + sn + IdUtil.fastSimpleUUID());
        param.put("converter_name", "ddd");
        param.put("sn", sn);
        param.put("camera", cameraIndex);
        param.put("video", "normal-0");
        param.put("video_quality", "0");

        HttpHeaders header = getHeader();
        header.add("Content-Type", "application/json");
        String body = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(param, header), String.class).getBody();
        log.debug("启动流转换:{}", body);
    }

    /**
     * 停止流转换
     */
    public void stopStreamConvert(String converterId) {
        String url = sikongUrlPrefix + "/manage/api/v1.0/stream-converters/" + converterId;
        String body = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(getHeader()), String.class).getBody();
        log.debug("停止流转换:{}", body);
    }


    /**
     * 获取流转换列表
     *
     * @param sn
     * @param cameraIndex
     * @return
     */
    public List<StreamConvertMember> getChannel(String sn, String cameraIndex) {
        List<StreamConvertMember> list = new ArrayList<>();
        String url = sikongUrlPrefix + "/manage/api/v1.0/stream-converters?channel=" + sn + "_" + cameraIndex;
        String body = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(getHeader()), String.class).getBody();
        log.debug("获取流转换列表:{}", body);
        BaseResponse<StreamConvertResponse> response = JSON.parseObject(body, new TypeReference<BaseResponse<StreamConvertResponse>>() {
        });
        Integer code = response.getCode();
        if (code == 0) {
            list = Optional.ofNullable(response).map(BaseResponse::getData).map(StreamConvertResponse::getMembers).orElse(new ArrayList<>());
        }
        return list;

    }

    public void test() {
        String url = sikongUrlPrefix + "/manage/api/v1.0/stream-converters?channel=1581F7FVC253900D1DWQ_88-0-0";
        String body = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(getHeader()), String.class).getBody();
        System.out.println(body);
    }


}
