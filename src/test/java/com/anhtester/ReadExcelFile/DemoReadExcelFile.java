package com.anhtester.ReadExcelFile;

import com.anhtester.helpers.ExcelHelper;
import org.testng.annotations.Test;

public class DemoReadExcelFile {
    @Test
    public void testReadExcelFile(){
        //Khoi tao doi tuong class ExcelHelper
        ExcelHelper excelHelper = new ExcelHelper();

        //Goi ham setExcelFile
        excelHelper.setExcelFile("src/test/resources/testdata/UserData.xlsx", "Sheet1");

        //Goi ham getCellData de lay ra gia tri cua tung o trong file excel
        System.out.println(excelHelper.getCellData("email", 1));
        System.out.println(excelHelper.getCellData("password", 1));
        System.out.println(excelHelper.getCellData("role", 1));
    }

    @Test
    public void testSetDataToExcel(){
        ExcelHelper excelHelper = new ExcelHelper();

        excelHelper.setExcelFile("src/test/resources/testdata/UserData.xlsx", "Sheet1");

        excelHelper.setCellData("staff01@example.com", "email", 1);
        excelHelper.setCellData("112233", "password", 1);
        excelHelper.setCellData("staff", "role", 1);
    }
}
