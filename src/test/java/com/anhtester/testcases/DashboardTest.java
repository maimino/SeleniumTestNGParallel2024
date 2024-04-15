package com.anhtester.testcases;

import com.anhtester.pages.CustomerPage;
import com.anhtester.pages.DashboardPage;
import com.anhtester.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.constants.ConfigData;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;

    @Test
    public void testOpenCustomerPage(){
        //Login
        loginPage = new LoginPage();
        dashboardPage =loginPage.loginCRM(ConfigData.EMAIL, ConfigData.PASSWORD);
        loginPage.verifyLoginSuccess();
        //Click menu Customer
        customerPage = dashboardPage.clickMenuCustomers(); //Xay ra lien ke trang
        customerPage.verifyCustomerPage();
    }

    @Test
    public void testAdminRole(){
        loginPage = new LoginPage();
        dashboardPage =loginPage.loginCRM(ConfigData.EMAIL, ConfigData.PASSWORD);
        loginPage.verifyLoginSuccess();
        dashboardPage.verifyMenuReportDisplay();
    }
}