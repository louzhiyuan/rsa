package com.demo.rsa.RSAUtils;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;


/**
 *
 * 批量解密
 *
 * */
public class descryptData {

    //RSA解密
    public static String decryptDatas(String data, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] descryptData = Base64.decodeBase64(data);
            byte[] descryptedData = cipher.doFinal(descryptData);
            String srcData = new String(descryptedData, "utf-8");
            return srcData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密
    public static void testRsa() {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

            /*
             * 私有化秘钥
             * strprivk
             *
             * */
            String strprivk = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAh2G7dxTDfirXUsDe+vUHlOfr3lF6Y" +
                    "eSWWvY8TEilR5v3yTzYIQcqOmr0pc37fH9tIEO43HbUBnxUbxGeCBJIXQIDAQABAkA/mYY8FyEP8IhfdARM2TfK" +
                    "mHmSY6yeuIh6CxugqrAax1TVNSiDLdsJ+yfQPXGJ5VfPC0eIndwEmWKJtknyQN+BAiEA4FTZucNY5jSVBPhRq5F" +
                    "HeYF6QmD5wT3eZPv0+322WAUCIQCaflC2MZeH6D/jAsDHdbAjWEAttgOah4QZh9vqE91WeQIhAMdVXioclM/phU" +
                    "jnRdrHutUS4go9lW/Kg+UDpUobCH3pAiA3elClfF170mzOqjhr59keeBxjzc6xeSR4vo68GfILkQIgVn/+eOUTA" +
                    "gUE8XfJUpnXa3Ydm5XzTDMz2yDeNJ8vscQ=";

            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(strprivk.getBytes()));

            KeyFactory keyf = KeyFactory.getInstance("RSA", "BC");
            PrivateKey privKey = keyf.generatePrivate(priPKCS8);

            /*
             * 加密后字符串
             * encryptData
             *
             * */
            //String encryptData = "uUfkKh+1cxLkG9g9kxHZPCqLoTP7xBrz1Y0RTNM0t1d7TH36H9l+4NjgQp78yjCSWjW2/yzfohFneMg9violQQ==";
           
            //批量解密
            String[] encryptData = {
                    "djz4N8iE2P8tu8w5uRAsjvZIqIAHmTwTVESgBIlDvmq4ORHRT1nugjWI/Tqhwm31jdPKRNvwL7QlW4WjZE8yMg==",
                    "djz4N8iE2P8tu8w5uRAsjvZIqIAHmTwTVESgBIlDvmq4ORHRT1nugjWI/Tqhwm31jdPKRNvwL7QlW4WjZE8yMg=="
            };
            for(int i=0;i<encryptData.length;i++){
                String descryptData = null;
                if (privKey != null && (encryptData != null && !encryptData.equals(""))) {
                    descryptData = decryptDatas(encryptData[i], privKey);
                    System.out.println("解密后结果:" + descryptData);
                }
            }
               
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //运行
    public static void main(String args[]) {
        testRsa();
    }
    /*
    *解密后结果:0---不一致;1---一致;2---身份证号码有误;
    */
}
