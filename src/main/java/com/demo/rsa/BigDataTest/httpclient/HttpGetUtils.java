package com.demo.rsa.BigDataTest.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by lzy on 2017/12/1.
 */

public class HttpGetUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpGetUtils.class);

    public static String getResponseContext(String url) {
        String content = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpGet httpget = new HttpGet(url);
        try {
            CloseableHttpResponse httpResponse = httpclient.execute(httpget);
            // 获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            if(entity != null) {
                content = EntityUtils.toString(entity,"utf-8");
            }
            logger.debug("response content is : " + content);

            //打印请求参数
            //System.out.println("response content is : " + content);
            httpResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

}
