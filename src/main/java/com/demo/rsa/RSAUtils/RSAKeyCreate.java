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
    公钥Base64编码:
    MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKo7x+FfFw90b7izTpSZTcyHLjWPC6N5
    RiKAPujgkW52qGqny06y8fINZaQ/mXd2pThF4gfzuMgs14BVYBl2n7sCAwEAAQ==
    私钥Base64编码:
    MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAqjvH4V8XD3RvuLNOl
    JlNzIcuNY8Lo3lGIoA+6OCRbnaoaqfLTrLx8g1lpD+Zd3alOEXiB/O4yCzXgFVgGX
    afuwIDAQABAkEAi6x5F8V5mkii0hzswKf4WXQgyfkQR5Tdorku5Mw9BzAhQDN/mQ4
    Bd6lInHpb223H0fuYwkHCnj6wEYgNzSjE4QIhAPZa+L4FjQ64RUYNMmllZdTglYNU
    b4SmpITC7tzM3ypdAiEAsOXmKhOg5VAC49LVY5zJ8CCF6MG/tcJY+GqF6yfrwPcCI
    QC4Kn/jhkDSLT1URg9WrT+4KYB8IkYP84fv2843cEyw3QIgXEOspqmSM8hlmXovvk
    LauJnd/iZ/3Uc+rGKy8pHhzsUCIDS50cv9NdsVB/yXKtBQFpiGlfpICV1n0q8IP8m
    R0D+O
    */
}
