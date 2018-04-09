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
            String strprivk = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEA4J7Iv18SSmMNJYt" +
                    "S73IB+kw/MzrH6r5gs/JG+E1l76pOpYtC8N4s7jtVfPMGKqwpOOX3SRkxaTKQEX3CErk5yQID" +
                    "AQABAkEAt0hZPe5xmkkgnRYGYmOAwaAdocvA/QbDGZID3vAo0Vl6wfgX1orhfeK8IbzC47Zn4" +
                    "WanZzLpN5D756zGRVC9QQIhAPYmfHJAMTpFPHVuVcCuuhqHtT8D47KiWElMxDj5JFDVAiEA6Zu" +
                    "/jLzXV9au+hP4wuMh0P7nL0VolQjxlUGSTz3Q3yUCIQCeg5JIkQYinEue4/rdJqg6RYJ2ni6X7" +
                    "uwj6AfFGMxGMQIgHSIEQFvoixbF2YAWHM/QjszLLfT89uEXp6J2yZUO8FUCIQCvaslJCd6lnie" +
                    "fP5yTu05fXfPGCD5/G4iC2ROapdDqgA==";

            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(strprivk.getBytes()));

            KeyFactory keyf = KeyFactory.getInstance("RSA", "BC");
            PrivateKey privKey = keyf.generatePrivate(priPKCS8);

            /*
            * 加密后字符串
            * encryptData
            *
            * */
            String encryptData = "Mf28x7u4oXbTNcJiDwgtgOPAbown80YZDC5OT4OlGfhpSHSRnVU4j0xijzyBDHAQxlkz+mybnfllwZ9bod7GRQ==";

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
    /*
    *解密后结果:0---不一致;1---一致;2---身份证号码有误;
    */
}
