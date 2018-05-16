package com.demo.rsa.RSAUtils;

import org.apache.commons.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * 批量加密
 *
 *
 * */
public class RSATest {

    public static void testRsa() {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

            String strpk = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOCeyL9fEkpjDSWLUu9yAfpMPzM6x+q+" +
                    "YLPyRvhNZe+qTqWLQvDeLO47VXzzBiqsKTjl90kZMWkykBF9whK5OckCAwEAAQ==";
            String strprivk = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEA4J7Iv18SSmMNJYt" +
                    "S73IB+kw/MzrH6r5gs/JG+E1l76pOpYtC8N4s7jtVfPMGKqwpOOX3SRkxaTKQEX3CErk5yQID" +
                    "AQABAkEAt0hZPe5xmkkgnRYGYmOAwaAdocvA/QbDGZID3vAo0Vl6wfgX1orhfeK8IbzC47Zn4" +
                    "WanZzLpN5D756zGRVC9QQIhAPYmfHJAMTpFPHVuVcCuuhqHtT8D47KiWElMxDj5JFDVAiEA6Zu" +
                    "/jLzXV9au+hP4wuMh0P7nL0VolQjxlUGSTz3Q3yUCIQCeg5JIkQYinEue4/rdJqg6RYJ2ni6X7" +
                    "uwj6AfFGMxGMQIgHSIEQFvoixbF2YAWHM/QjszLLfT89uEXp6J2yZUO8FUCIQCvaslJCd6lnie" +
                    "fP5yTu05fXfPGCD5/G4iC2ROapdDqgA==";

            X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decodeBase64(strpk.getBytes()));
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(strprivk.getBytes()));

            KeyFactory keyf = KeyFactory.getInstance("RSA", "BC");
            PublicKey pubKey = keyf.generatePublic(pubX509);
            PrivateKey privKey = keyf.generatePrivate(priPKCS8);

            RSAUtil rsaUtil =  new RSAUtil();

            //String data = "0---不一致;1---一致;2---身份证号码有误;" ;
            String[] datas = {"傅萍,330623197706051068,13003615343,1;",
                    "朱生峰,330411199301275437,13067676667,1;",
                    "陈燕娜,330902198412134422,13666708768,1;",
                    "邵莹,330921198110300025,13735019078,0;"};
            for(int i=0;i<datas.length;i++){
                String encryptData = null;
                if (pubKey != null) {
                    encryptData = rsaUtil.encryptData(datas[i], pubKey);
                    System.out.println(encryptData);
                }
            }
            //System.out.println("加密前字符串:" + data);
            /*String encryptData = null;
            if (pubKey != null && (data != null && !data.equals(""))) {
                encryptData = rsaUtil.encryptData(data, pubKey);
                System.out.println("加密后字符串:" + encryptData);
            }*/
            /*String descryptData = null;
            if (privKey != null) {
                descryptData = rsaUtil.decryptData(encryptData, privKey);
                System.out.println("解密后字符串:" + descryptData);
            }*/
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) {
        testRsa();
    }
}
