package com.zhang.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class RsaProperties {

    public static String privateKey;

    @Value("${rsa.privateKey}")
    public static void setPrivateKey(String privateKey) {
        RsaProperties.privateKey = privateKey;
    }
}
