package com.anhtester.ReadExcelFile;

import com.anhtester.common.BaseTest;
import com.anhtester.constants.ConfigData;
import com.anhtester.helpers.ExcelHelper;
import com.anhtester.keywords.WebUI;
import com.anhtester.pages.DashboardPage;
import com.anhtester.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTestExcel extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void testLoginSuccess(){
        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/UserData.xlsx", "Sheet1");

        dashboardPage = loginPage.loginCRM(
                excelHelper.getCellData("email", 2),
                excelHelper.getCellData("password", 2)
        );
        loginPage.verifyLoginSuccess();
        //dashboardPage.logOut();
    }

    @Test
    public void testLoginWithEmailInvalid(){
        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/UserData.xlsx", "Sheet1");

        dashboardPage = loginPage.loginCRM(
                excelHelper.getCellData("email", 1),
                excelHelper.getCellData("password", 1)
        );
        loginPage.verifyLoginFail();
    }

    @Test
    public void testLoginWithPasswordInvalid(){
        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/UserData.xlsx", "Sheet1");

        dashboardPage = loginPage.loginCRM(
                excelHelper.getCellData("email", 2),
                excelHelper.getCellData("password", 1)
        );
        loginPage.verifyLoginFail();
    }
}