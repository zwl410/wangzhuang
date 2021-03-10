package com.zhang.components.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("jwt")
public class SecurityProperties {

    /** Request Headers ： Authorization */
    private String header;

    /** 令牌加密密文 */
    private String base64Secret;

    /** 令牌过期时间 单位:毫秒 */
    private Long expiration;

    /** 令牌前缀，最后留个空格 Bearer */
    private String tokenHead;

    /** 缓存中用户令牌前缀 */
    private String onlineKey;

    /** 验证码前缀 */
    private String codeKey;

    /** 验证码过期时间 单位:毫秒 */
    private Long codeExpiration;

}
