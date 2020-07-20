package com.zhang.components.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("jwt")
public class SecurityProperties {

    private String header;

    private String base64Secret;

    private Long expiration;

    private String tokenHead;

    private String codeKey;

    private Long codeExpiration;

}
