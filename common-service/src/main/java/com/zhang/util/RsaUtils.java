package com.zhang.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Auther: zhangsl
 * @Date: 2020/5/28 16:52
 * @Description: RSA 加密工具类
 */
public class RsaUtils {

    private static final String SRC = "e10adc3949ba59abbe56e057f20f883e";

    public static void main(String[] args) throws Exception {
        RsaKeyPair rsaKeyPair = getInstanceKey();
//        String publicKey = rsaKeyPair.getPublicKey();
//        String privateKey = rsaKeyPair.getPrivateKey();
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCGx4kN8MxDtqTtI0sClb8y8Abxzv2Z5j6QcvUth+RxRRuyio3M05Mag3LgK1TOY6a78y8qdTUEOuOQkNa9diq1A4aTx9S4POFrunaI9mAye57t2bCDsUdmRCuMJSI1iRIs16hUeRWe1G+qJvcTHgn7NmrE2hEUrQn6BOsRvNKlrwIDAQAB";
        String privateKey = "MIICcwIBADANBgkqhkiG9w0BAQEFAASCAl0wggJZAgEAAoGBAIbHiQ3wzEO2pO0jSwKVvzLwBvHO/ZnmPpBy9S2H5HFFG7KKjczTkxqDcuArVM5jprvzLyp1NQQ645CQ1r12KrUDhpPH1Lg84Wu6doj2YDJ7nu3ZsIOxR2ZEK4wlIjWJEizXqFR5FZ7Ub6om9xMeCfs2asTaERStCfoE6xG80qWvAgMBAAECf10vmJMK6i/nGCDdRf+Dia6uRh7p/m1pw9gbo2jXjBrErWHEakY0AoFSYRqc2wXX4HASs4mngABstuo9OjvEd8GuEYJT7upxMnyMY74NL5v2PvXGMzNu/WYkWd2tUFmaMKxuy7PJ2l4cq5yEOh3kY4zIeksKH0MfJE85r0o/n8kCQQDIiPS5tHamB6xWMYb0WpHpMEgFmITU+/lRiJ9xujIHwQPDJ/D83XS9kd3hgv2c2f7uRALqPpQ86V6b2tKeAxOlAkEArA60LU4vsLiV0OxcfRA+AgJJO/9xpoEukKXOSZB4x2fnGClp3zZ+BubgP+bf0XNS0BDzz2cJfI9Zy2wQ7zTDwwJAekaQOn/gbOJ24eO3L13eZHrNH6RdMmUmI+ITnsvy36mAesnq9rSuDz1XNr+hlreR64TK5jwcNXvy9UObcz/bBQJAbFRpryjOMBdYBI39AaUrVXp4/7SqquolFl++zWSQFwTdZMIWbbrSiKfuw20quKZCmsxBpfE68NkU8VZeJy3ynwJAYMkBc6l/4S4wS5NjApW4IAaC/w4LVfWxhgTE3iil97krY4LabZ7qlPNzCt7pjANZu3CcvWqJ6WR3QbBtPWnm4w==";
        System.out.println("公钥:" + publicKey);
        System.out.println("私钥:" + privateKey);
        System.out.println("\n要加密的数据：" + SRC + "");
        System.out.println("\n###############公钥加密-私钥解密################");
        String encryptPublic = encryptByPublicKey(publicKey, SRC);
        System.out.println("加密的结果:" + encryptPublic);
        String decryptPrivate = decryptByPrivateKey(privateKey, encryptPublic);
        System.out.println("解密的结果:" + decryptPrivate);
        System.out.println("\n###############私钥加密-公钥解密################");
        String encryptPrivate = encryptByPrivateKey(privateKey, SRC);
        System.out.println("加密的结果:" + encryptPrivate);
        String decryptPublic = decryptByPublicKey(publicKey, encryptPrivate);
        System.out.println("解密的结果:" + decryptPublic);
    }


    /**
     * 公钥加密
     *
     * @param publicKeyString 公钥
     * @param text            要加密的信息
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String publicKeyString, String text) throws Exception {
        //密钥材料转换
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyString));
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //获取公钥
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }


    /**
     * 私钥解密
     *
     * @param privateKeyString 私钥
     * @param text             要解密的信息
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String privateKeyString, String text) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result, "UTF-8");
    }


    /**
     * 私钥加密
     *
     * @param privateKeyString 私钥
     * @param text             要加密的信息
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String privateKeyString, String text) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }


    /**
     * 公钥解密
     *
     * @param publicKeyString 公钥
     * @param text            要加密的信息
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String publicKeyString, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result, "UTF-8");
    }


    /**
     * 构建RSA密钥对
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static RsaKeyPair getInstanceKey() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        //随机数
        SecureRandom random = new SecureRandom();
        generator.initialize(1024, random);
        //密钥对
        KeyPair keyPair = generator.genKeyPair();
        //公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = Base64.encodeBase64String(publicKey.getEncoded());
        //私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        String privateKeyString = Base64.encodeBase64String(privateKey.getEncoded());
        return new RsaKeyPair(publicKeyString, privateKeyString);
    }

    public static class RsaKeyPair {
        private String publicKey;
        private String privateKey;

        public RsaKeyPair(String publicKey, String privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return this.publicKey;
        }

        public String getPrivateKey() {
            return this.privateKey;
        }
    }

}