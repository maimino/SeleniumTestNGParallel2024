package com.anhtester.dataprovider;

import com.anhtester.helpers.ExcelHelper;
import org.testng.annotations.DataProvider;

public class DataProviderFactoryLogin {
    //Khai bao nguon cung cap dl
    @DataProvider(name = "DataLoginSuccess", parallel = true)
    public Object[][] dataLoginSuccess(){
//        return new  Object[][]{
//                {"admin@example.com", "123456"}
//        };
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getExcelData("src/test/resources/testdata/LoginData.xlsx", "Sheet1");
        return  data;
    }

    @DataProvider(name = "DataLoginFail", parallel = true)
    public Object[][] dataLoginFail(){
        return new  Object[][]{
                {"customer@example.com", "123456"},
                {"staff@example.com", "123456"}
        };
    }

    @DataProvider(name = "data_provider_login_excel_hashtable")
    public Object[][] dataLoginFromExcelHashtable() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashTable("src/test/resources/testdata/LoginData.xlsx", "Sheet1", 1, 2);
        System.out.println("Login Data from Excel: " + data);
        return data;
    }
}
