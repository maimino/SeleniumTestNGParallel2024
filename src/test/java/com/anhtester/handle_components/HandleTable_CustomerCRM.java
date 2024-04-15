package com.anhtester.handle_components;

import com.anhtester.common.BaseTest;
import com.anhtester.constants.ConfigData;
import com.anhtester.pages.CustomerPage;
import com.anhtester.pages.DashboardPage;
import com.anhtester.pages.LoginPage;
import org.testng.annotations.Test;

public class HandleTable_CustomerCRM extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;

    @Test
    public void testCheckDataOnTable(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM(ConfigData.EMAIL, ConfigData.PASSWORD);
        customerPage = dashboardPage.clickMenuCustomers();
        customerPage.searchCustomer("cty 4 thanh vien"); //tra ra 4 record
        customerPage.checkPageTotal(4);
        customerPage.checkSearchTableByColumn(3, "cty"); //so sanh contains

    }

}
