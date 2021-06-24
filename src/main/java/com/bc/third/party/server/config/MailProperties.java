package com.bc.third.party.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.Charset;

/**
 * 邮件配置
 *
 * @author zhou
 */
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String protocol;
    private Charset defaultEncoding;

}