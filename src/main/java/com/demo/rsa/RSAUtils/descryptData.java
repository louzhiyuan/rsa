package com.demo.rsa.RSAUtils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;

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
            String strprivk = "";

            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(strprivk.getBytes()));

            KeyFactory keyf = KeyFactory.getInstance("RSA", "BC");
            PrivateKey privKey = keyf.generatePrivate(priPKCS8);

            /*
            * 加密后字符串
            * encryptData
            *
            * */
            String encryptData = "";

            String descryptData = null;
            if (privKey != null && (encryptData != null && !encryptData.equals(""))) {
                descryptData = decryptDatas(encryptData, privKey);
                System.out.println("解密后结果:" + descryptData);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //运行
    public static void main(String args[]) {
        testRsa();
    }
}
