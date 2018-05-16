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
}
