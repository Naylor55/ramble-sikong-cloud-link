package com.ramble.sikongcloudlink.config;

/**
 * Project     ngh-aircraft
 * Package     com.nghsmart.nghaircraft.config
 * Class       SwaggerOpenApiConfig
 * date        2024/12/5 11:20
 * author      cml
 * Email       liangchen_beijing@163.com
 * Description
 */

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;

/** 这是一个普通的Swagger配置文档，其中不包含API接口的配置（API接口的配置推荐使用注解方式）
 * 访问地址： http://localhost:port/swagger-ui/index.html#
 * @author Anhui OuYang
 * @version 1.0
 **/
@SpringBootConfiguration
public class SwaggerOpenApiConfig {


    /***
     * 构建Swagger3.0文档说明
     * @return 返回 OpenAPI
     */
    @Bean
    public OpenAPI customOpenAPI() {


        // 联系人信息(contact)，构建API的联系人信息，用于描述API开发者的联系信息，包括名称、URL、邮箱等
        // name：文档的发布者名称 url：文档发布者的网站地址，一般为企业网站 email：文档发布者的电子邮箱
        Contact contact = new Contact()
                .name("MingliangChen")                             // 作者名称
                .email("liangchen_beijing@163.com")                   // 作者邮箱
                .url("https://www.cnblogs.com/Naylor")  // 介绍作者的URL地址
                .extensions(new HashMap<String, Object>()); // 使用Map配置信息（如key为"name","email","url"）


        // 授权许可信息(license)，用于描述API的授权许可信息，包括名称、URL等；假设当前的授权信息为Apache 2.0的开源标准
        License license = new License()
                .name("Apache 2.0")                         // 授权名称
                .url("https://www.apache.org/licenses/LICENSE-2.0.html")    // 授权信息
                .identifier("Apache-2.0")                   // 标识授权许可
                .extensions(new HashMap<String, Object>());// 使用Map配置信息（如key为"name","url","identifier"）


        //创建Api帮助文档的描述信息、联系人信息(contact)、授权许可信息(license)
        Info info = new Info()
                .title("无人机对接项目")      // Api接口文档标题（必填）
                .description("测试大疆司空2云端互联api")     // Api接口文档描述
                .version("0.0.1")                                  // Api接口版本
                .termsOfService("https://example.com/")            // Api接口的服务条款地址
                .license(license)                                  // 设置联系人信息
                .contact(contact);                                 // 授权许可信息
        // 返回信息
        return new OpenAPI()
                .openapi("3.0.1")  // Open API 3.0.1(默认)
                .info(info);       // 配置Swagger3.0描述信息
    }
}