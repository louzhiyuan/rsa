package com.demo.rsa.BigDataTest.httpclient;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class HttpUtils {
    public static void writeJsonStr(HttpServletResponse resp, String jsonStr) {
        if (resp != null) {
            resp.setCharacterEncoding("utf-8");
            resp.addHeader("Access-Control-Allow-Origin", "*");
            resp.setContentType("application/json;charset=utf-8");
            try {
                PrintWriter pw = resp.getWriter();
                pw.write(jsonStr);
                pw.flush();
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
