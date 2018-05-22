package com.demo.rsa.RSAUtils;

import com.demo.rsa.BigDataTest.MD5Utils;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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


    //md5批量加密
    public static  void testmd5(){
        String [] datas = {
                "441424198703083810",
                "61012419830111181X"
        };
        for(int i=0;i<datas.length;i++){
            String s = MD5Utils.MD5(datas[i]);
            System.out.println(s);
        }
    }

    //运营商
    public static void yys(){

        String [] datas = {
                "18666096718",
                "15889685430"
        };
        for(int i=0;i<datas.length;i++){
            //运营商匹配
            String YDregex = "(^1(3[4-9]|4[7]|5[0-27-9]|7[28]|8[2-478])\\d{8}$)|(^1(70[56]|34[0-8])|303\\d{7}$)";
            Pattern yd = Pattern.compile(YDregex);
            Matcher ydm = yd.matcher(datas[i]);
            boolean ydmatch = ydm.matches();
            if(ydmatch){
                System.out.println("移动");
            }

            String DXregex = "(^1(33|53|77|8[019]|49|73|99|)\\d{8}$)|(^1(70[012]|349)\\d{7}$)";
            Pattern dx = Pattern.compile(DXregex);
            Matcher dxm = dx.matcher(datas[i]);
            boolean dxmatch = dxm.matches();
            if(dxmatch){
                System.out.println("电信");
            }

            String LTregex = "(^1(3[0-2]|4[5]|5[56]|7[56]|8[56]|6[6])\\d{8}$)|(^1(70[4789]|71[356789])\\d{7}$)";
            Pattern lt = Pattern.compile(LTregex);
            Matcher ltm = lt.matcher(datas[i]);
            boolean ltmatch = ltm.matches();
            if(ltmatch){
                System.out.println("联通");
            }
        }
    }


    //运行
    public static void main(String args[]) {
        //批量解密RSA
        //testRsa();
        //md5
        //testmd5();
        //运营商
        yys();
    }
    /*
    *解密后结果:0---不一致;1---一致;2---身份证号码有误;
    */
}
