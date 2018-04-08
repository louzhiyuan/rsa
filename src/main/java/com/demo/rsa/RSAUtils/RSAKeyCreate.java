package com.demo.rsa.RSAUtils;

import org.apache.commons.codec.binary.Base64;

import java.security.*;

public class RSAKeyCreate {

    /*
    *第一步：创建公钥，私钥
    *公钥用于加密
    *私钥用于解密
    */
    public static void createKeyPairs() {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
            generator.initialize(512, new SecureRandom());
            KeyPair pair = generator.generateKeyPair();
            PublicKey pubKey = pair.getPublic();
            PrivateKey privKey = pair.getPrivate();
            byte[] pk = pubKey.getEncoded();
            byte[] privk = privKey.getEncoded();
            String strpk = new String(Base64.encodeBase64(pk));
            String strprivk = new String(Base64.encodeBase64(privk));

            System.out.println("公钥Base64编码:" + strpk);
            System.out.println("私钥Base64编码:" + strprivk);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    //执行
    public static void main(String args[]){
        createKeyPairs();
    }

    /*
    *公钥Base64编码:
    *MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOCeyL9fEkpjDSWLUu9y
    *AfpMPzM6x+q+YLPyRvhNZe+qTqWLQvDeLO47VXzzBiqsKTjl90kZ
    *MWkykBF9whK5OckCAwEAAQ==
    *
    *私钥Base64编码
    *MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEA4J7Iv1
    *8SSmMNJYtS73IB+kw/MzrH6r5gs/JG+E1l76pOpYtC8N4s7jtVfPMG
    *KqwpOOX3SRkxaTKQEX3CErk5yQIDAQABAkEAt0hZPe5xmkkgnRYGYm
    *OAwaAdocvA/QbDGZID3vAo0Vl6wfgX1orhfeK8IbzC47Zn4WanZzLp
    *N5D756zGRVC9QQIhAPYmfHJAMTpFPHVuVcCuuhqHtT8D47KiWElMxD
    *j5JFDVAiEA6Zu/jLzXV9au+hP4wuMh0P7nL0VolQjxlUGSTz3Q3yUC
    *IQCeg5JIkQYinEue4/rdJqg6RYJ2ni6X7uwj6AfFGMxGMQIgHSIEQF
    *voixbF2YAWHM/QjszLLfT89uEXp6J2yZUO8FUCIQCvaslJCd6lnief
    *P5yTu05fXfPGCD5/G4iC2ROapdDqgA==
    */
}
