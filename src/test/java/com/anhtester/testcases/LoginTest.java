package com.anhtester.testcases;

import com.anhtester.dataprovider.DataProviderFactoryLogin;
import com.anhtester.helpers.ExcelHelper;
import com.anhtester.pages.DashboardPage;
import com.anhtester.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.keywords.WebUI;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;

//    @Test(dataProvider = "DataLoginSuccess", dataProviderClass = DataProviderFactoryLogin.class)
//    public void testLoginSuccess(String email, String password){
//        loginPage = new LoginPage();
//        dashboardPage = loginPage.loginCRM(email, password);
//        loginPage.verifyLoginSuccess();
//        WebUI.captureScreenImage("testLoginSuccess");
//        dashboardPage.logOut();
//    }

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
        dashboardPage.logOut();
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