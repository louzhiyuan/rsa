package com.demo.rsa.ExcelTest;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {

    public static List<List<String>> readXlsx(String path) throws Exception {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

        List<List<String>> result = new ArrayList<List<String>>();
        // 循环当前页，并处理当前循环页
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        // 循环每一页，并处理当前循环页
//        for (XSSFSheet xssfSheet : xssfWorkbook) {
//            String a = xssfSheet.getSheetName();
//            if (xssfSheet.gets(0) != "月度汇总") {
//                continue;
//            }
        // 处理当前页，循环读取每一行 rowNum:从第几行开始读取

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
//        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        List list = readXlsx("/Users/louzhiyuan/Desktop/tt.xlsx");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}
