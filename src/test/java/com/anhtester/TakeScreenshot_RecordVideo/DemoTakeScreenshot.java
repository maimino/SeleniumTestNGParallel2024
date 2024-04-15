package com.anhtester.TakeScreenshot_RecordVideo;

import com.anhtester.common.BaseTest;
import com.anhtester.dataprovider.DataProviderFactoryLogin;
import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.ExcelHelper;
import com.anhtester.keywords.WebUI;
import com.anhtester.pages.LoginPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class DemoTakeScreenshot extends BaseTest {
    LoginPage loginPage;

    @Test
    public void testLoginSuccess() {
        System.out.println("===========testLoginSuccess=============");
        CaptureHelper.startRecord("testLoginSuccess");

        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/LoginData.xlsx", "Sheet1");

         loginPage.loginCRM(
                excelHelper.getCellData("email", 1),
                excelHelper.getCellData("password", 1)
        );

        //CaptureHelper.takeScreenshoot("testLoginSuccess");
        WebUI.sleep(3);

        //CaptureHelper.stopRecord();

        loginPage.verifyLoginSuccess();
    }

    @Test
    public void testLoginWithEmailInvalid(){
        CaptureHelper.startRecord("testLoginWithEmailInvalid");

        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/LoginData.xlsx", "Sheet1");

        loginPage.loginCRM(
                excelHelper.getCellData("email", 2),
                excelHelper.getCellData("password", 2)
        );

        WebUI.sleep(3);

        loginPage.verifyLoginFail();
    }
}
