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

    private static final String SRC = "123456";

    public static void main(String[] args) throws Exception {
        RsaKeyPair rsaKeyPair = getInstanceKey();
        System.out.println("公钥:" + rsaKeyPair.getPublicKey());
        System.out.println("私钥:" + rsaKeyPair.getPrivateKey());
        System.out.println("\n要加密的数据：" + SRC + "");
        System.out.println("\n###############公钥加密-私钥解密################");
        String encryptPublic = encryptByPublicKey(rsaKeyPair.getPublicKey(), SRC);
        System.out.println("加密的结果:" + encryptPublic);
        String decryptPrivate = decryptByPrivateKey(rsaKeyPair.getPrivateKey(), encryptPublic);
        System.out.println("解密的结果:" + decryptPrivate);
        System.out.println("\n###############私钥加密-公钥解密################");
        String encryptPrivate = encryptByPrivateKey(rsaKeyPair.getPrivateKey(), SRC);
        System.out.println("加密的结果:" + encryptPrivate);
        String decryptPublic = decryptByPublicKey(rsaKeyPair.getPublicKey(), encryptPrivate);
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
        return   Base64.encodeBase64String(result);
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