package com.zhang.components.security.security;

import com.zhang.constant.XaConstant;
import com.zhang.util.Base64Util;
import com.zhang.util.RedisUtils;
import com.zhang.exception.BaseException;
import com.zhang.components.security.config.SecurityProperties;
import com.zhang.components.security.entity.SelfUserDetail;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @Auther: zhangsl
 * @Date: 2020/5/28 16:52
 * @Description:
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final SecurityProperties properties;
    private final RedisUtils redisUtils;

    /**
     * 解析jwt
     *
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (ExpiredJwtException eje) {
            log.error("===== Token过期 =====", eje);
            throw new BaseException(XaConstant.Code.code_failure_token, "token过期" + eje.getLocalizedMessage());
        } catch (Exception e) {
            log.error("===== token解析异常 =====", e);
            throw new BaseException(XaConstant.Code.code_failure_parsing_token, "token解析异常" + e.getLocalizedMessage());
        }
    }

    /**
     * 构建jwt
     *
     * @param userDetail
     * @return
     */
    public String createToken(SelfUserDetail userDetail) {
        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(properties.getBase64Secret());
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                    // 可以将基本不重要的对象信息放到claims
                    .claim("role", userDetail.getRoles())
                    .claim("userId", userDetail.getUserId())
                    .setSubject(userDetail.getUsername())           // 代表这个JWT的主体，即它的所有人
                    .setIssuedAt(new Date())        // 是一个时间戳，代表这个JWT的签发时间；
                    .signWith(signatureAlgorithm, signingKey);
            //添加Token过期时间
            Long TTLMillis = properties.getExpiration();
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                        .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
            }
            //生成JWT
            return builder.compact();
        } catch (Exception e) {
            log.error("签名失败", e);
            throw new BaseException(XaConstant.Code.code_failure_sign_token, "token签名失败" + e.getLocalizedMessage());
        }
    }

    /**
     * 从token中获取用户名
     *
     * @param token
     * @param base64Security
     * @return
     */
    public String getUsername(String token, String base64Security) {
        return parseJWT(token, base64Security).getSubject();
    }

    /**
     * 从token中获取用户ID
     *
     * @param token
     * @param base64Security
     * @return
     */
    public String getUserId(String token, String base64Security) {
        String userId = parseJWT(token, base64Security).get("userId", String.class);
        return Base64Util.decode(userId);
    }

    /**
     * 是否已过期
     *
     * @param token
     * @param base64Security
     * @return
     */
    public boolean isExpiration(String token, String base64Security) {
        return parseJWT(token, base64Security).getExpiration().before(new Date());
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public Boolean validateToken(String token) {
        Object is = redisUtils.get(token);
        return is != null;
    }

    /**
     * 验证token和用户名
     *
     * @param token
     * @param base64Security
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, String base64Security, SelfUserDetail userDetails) {
        final String username = getUsername(token, base64Security);
        return (username.equals(userDetails.getUsername())
                && isExpiration(token, base64Security) == false);
    }

    /**
     * 删除token
     *
     * @param token
     */
    public void delToken(String token) {
        redisUtils.del(token);
    }
}
