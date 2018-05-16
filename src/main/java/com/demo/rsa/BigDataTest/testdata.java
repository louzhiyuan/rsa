package com.demo.rsa.BigDataTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.rsa.BigDataTest.httpclient.HttpGetUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class testdata {

    //数据宝：
    public static String phoneValidate(String idcard, String name, String mobile){
        String path = RequestUtils.server+"/allNetPhone3Elements";
        //将参数加密：参数按字母顺序+私有code
        String psign = MD5Utils.MD5("idcard"+idcard+"mobile"+mobile+"name"+name.replace(" ","")+"paccount"+RequestUtils.paccount+RequestUtils.scode);
        String params = "?name="+name.replace(" ","")+"&idcard="+idcard+"&mobile="+mobile+"&paccount="+RequestUtils.paccount+"&psign="+psign;
        //拼接请求路径
        String url = path + params;
        //get请求得到返回结果
        String Result = HttpGetUtils.getResponseContext(url);
        //对返回结果进行解析
        JSONObject thirdResJson = JSON.parseObject(Result);
        //System.out.println("三要素验证:"+thirdResJson.toString());
        //比对返回结果的状态值参考api：验证结果（1:验证一致，2:验证不一致，3:异常情况）
        String result = thirdResJson.getJSONObject("data").getJSONObject("result").getString("state");
        //System.out.println("结果:"+result);
        return result;
    }

    //读取excel内容
    public static List<List<String>> readXlsx(String path) throws Exception {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

        List<List<String>> result = new ArrayList<List<String>>();
        // 循环当前页，并处理当前循环页
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

        for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            int minColIx = xssfRow.getFirstCellNum();
            int maxColIx = xssfRow.getLastCellNum();
            List<String> rowList = new ArrayList<String>();
            for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                XSSFCell cell = xssfRow.getCell(colIx);
                if (cell == null) {
                    continue;
                }
                rowList.add(cell.toString());
            }
            result.add(rowList);
        }
        return result;
    }


    //批量测试接口脚本
    //测试时摘除4190 HUWEI
    public static void main(String[] args) throws Exception {
        //phoneValidate("371325198110037539","胡常伟","13426016388");

        //读取excel内容
        /*[1, 胡志刚, 132823197008032412, 15210888553]
          [2, 张秀荣, 110105195903050443, 18010066752]
          [3, 张海峰, 110221196610076412, 13661340838]
          [4, 胡常伟, 371325198110037539, 13426016388]
          [5, 杜红炎, 42900619820709427X, 13366876256]*/

        List list = readXlsx("/Users/louzhiyuan/Desktop/tt.xlsx");
        for(int i=0;i<list.size();i++){
            //System.out.println(list.get(i));
            List ll = (List) list.get(i);
            for(int m=0;m<ll.size();m++){
                //System.out.println(ll.get(m));
            }
            //验证结果
            String fr = null;
            String res = phoneValidate((String) ll.get(2),(String) ll.get(1),(String) ll.get(3));
            if(res.equals("1")){
                fr = "验证一致";
            }else if(res.equals("2")){
                fr = "验证不一致";
            }else{
                fr = "异常情况";
            }
            //String rrr = ll.get(0)+","+ll.get(1)+","+ll.get(2)+","+ll.get(3)+","+"验证结果:"+fr+";";
            String rrr = ll.get(0)+","+ll.get(1)+","+ll.get(2)+","+ll.get(3)+","+fr+";";
            System.out.println(rrr);
        }
    }
}
