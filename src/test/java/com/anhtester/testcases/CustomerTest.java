package com.anhtester.testcases;

import com.anhtester.pages.CustomerPage;
import com.anhtester.pages.DashboardPage;
import com.anhtester.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.constants.ConfigData;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;

    @Test
    public void testAddNewCustomer(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM(ConfigData.EMAIL, ConfigData.PASSWORD);
        loginPage.verifyLoginSuccess();

        customerPage = dashboardPage.clickMenuCustomers();
        customerPage.verifyCustomerPage();

        customerPage.clickButtonAddnew();

        String CUSTOMER_NAME = "CTY 4 thanh vien";
        customerPage.inputFormData(CUSTOMER_NAME);
        customerPage.searchAndVerifyCustomer(CUSTOMER_NAME);
        customerPage.verifyCustomerDetail(CUSTOMER_NAME);
    }
}