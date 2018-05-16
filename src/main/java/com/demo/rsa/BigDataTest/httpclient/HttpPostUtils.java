package com.demo.rsa.BigDataTest.httpclient;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lzy on 2017/12/1.
 */
public class HttpPostUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpPostUtils.class);

    /**
     * 获取响应的状态码
     * @param url
     * @param params
     */
    public static int getResponseStatusCode(String url, Map<String, String> params) {
        int statusCode = -1;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost post = new HttpPost(url);
            // 创建参数列表
            List<NameValuePair> list = new ArrayList<NameValuePair>();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            // url格式编码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
            post.setEntity(uefEntity);
            logger.debug("POST url is :" + post.getURI());
            // 执行请求
            CloseableHttpResponse httpResponse = httpclient.execute(post);
            try {
                StatusLine statusLine = httpResponse.getStatusLine();
                statusCode = statusLine.getStatusCode();
            } finally {
                httpResponse.close();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return statusCode;
    }

    //post请求的两种请求方式，表单提交和json提交  修改为json提交
    public static String getResponseContext(String url, Map<String, Object> params) {
        String context = null;
        CloseableHttpClient httpclient = HTTPClientUtils.getClient(false);
        try {
            HttpPost post = new HttpPost(url);
            /*post.addHeader("Content-type","application/json; charset=utf-8");
            post.setHeader("Accept", "application/json");*/

            // 创建参数列表
//            List<NameValuePair> list = new ArrayList<NameValuePair>();
//
//            for (Map.Entry<String, Object> entry : params.entrySet()) {
//                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
//            }
            // url格式编码
//            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
            StringEntity s = new StringEntity(JSON.toJSONString(params), ContentType.APPLICATION_JSON);
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");

            post.setEntity(s);
            logger.debug("POST url is :" + post.getURI());
            // 执行请求
            CloseableHttpResponse httpResponse = httpclient.execute(post);
            try {
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    context = EntityUtils.toString(entity);
                }
            } finally {
                httpResponse.close();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return context;
    }
}
