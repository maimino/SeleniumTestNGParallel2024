package com.anhtester.handle_components;

import com.anhtester.common.BaseTest;
import com.anhtester.constants.ConfigData;
import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import com.anhtester.pages.CustomerPage;
import com.anhtester.pages.DashboardPage;
import com.anhtester.pages.LoginPage;
import com.anhtester.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class HandlePagination_Table extends BaseTest {

    public void checkSearchTableByColumn(int column, String value) {

        //Xác định số dòng của table sau khi search
        List<WebElement> row = DriverManager.getDriver().findElements(By.xpath("//table//tbody/tr"));
        int rowTotal = row.size(); //Lấy ra số dòng
        System.out.println("Số dòng tìm thấy: " + rowTotal);

        //Duyệt từng dòng
        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = DriverManager.getDriver().findElement(By.xpath("//table//tbody/tr[" + i + "]/td[" + column + "]"));

            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView(false);", elementCheck);

            System.out.print(value + " - "); //Expected
            System.out.println(elementCheck.getText()); //Actual
            Assert.assertTrue(elementCheck.getText().toUpperCase().contains(value.toUpperCase()), "Dòng số " + i + " không chứa giá trị tìm kiếm.");
        }
    }
        @Test
        public void testCheckPaginationOnTable() {

            WebUI.openURL("https://datatables.net/");

            //Data search read from Properties
            String searchValue = PropertiesHelper.getValue("searchValue");

            //Get item on One Page
            Select select = new Select(WebUI.getWebElement(By.xpath("//select[@name='example_length']")));

            LogUtils.info(select.getFirstSelectedOption().getText());

            int itemTotalOnePage = Integer.parseInt(select.getFirstSelectedOption().getText());
            System.out.println("Tổng số item / trang: " + itemTotalOnePage);

            //Set Text on Search input
            WebUI.setText(By.xpath("//input[@type='search']"), searchValue);

            //Get total item
            String strTotal = WebUI.getTextElement(By.xpath("//div[@id='example_info']"));
            ArrayList<String> list = new ArrayList<String>();

            for (String strItem : strTotal.split("\\s")) {
                list.add(strItem);
            }

            System.out.println(list);

            int itemTotal = Integer.parseInt(list.get(5));
            System.out.println("Tổng số item: " + itemTotal);
            int pageTotal = itemTotal / itemTotalOnePage;
            int sodu = itemTotal % itemTotalOnePage;
            System.out.println("Tổng số nguyên: " + pageTotal);
            System.out.println("Tổng số dư: " + sodu);

            if (sodu > 0) {
                pageTotal = pageTotal + 1;
            }

            System.out.println("Tổng số Page: " + pageTotal);

            for (int i = 1; i <= pageTotal; i++) {
                checkSearchTableByColumn(3, searchValue);

                //Nhấn nút Next để đến trang tiếp theo
                if (i < pageTotal) {
                    WebUI.clickElement(By.xpath("//a[@id='example_next']"));
                }
            }

        }

    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;

    @Test
    public void testCheckDataOnTable(){

        String searchValue = "Avepoint3";

        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM(ConfigData.EMAIL, ConfigData.PASSWORD);
        customerPage = dashboardPage.clickMenuCustomers();
        //customerPage.checkPageTotal(4);
        //customerPage.checkSearchTableByColumn(3, "cty"); //so sanh contains

        //Get item on One Page
        Select select = new Select(WebUI.getWebElement(By.xpath("//select[@name='clients_length']")));

        select.selectByVisibleText("10");
        WebUI.sleep(3);

        LogUtils.info(select.getFirstSelectedOption().getText());

        int itemTotalOnePage = Integer.parseInt(select.getFirstSelectedOption().getText());
        System.out.println("Tổng số item / trang: " + itemTotalOnePage);

        //Set Text on Search input
        //WebUI.setText(By.xpath("//input[@type='search']"), searchValue);
        customerPage.searchCustomer(searchValue); //tra ra 14 item

        //Get total item
        String strTotal = WebUI.getTextElement(By.xpath("//div[@id='clients_info']"));
        ArrayList<String> list = new ArrayList<String>();

        for (String strItem : strTotal.split("\\s")) {
            list.add(strItem);
        }

        System.out.println(list);

        int itemTotal = Integer.parseInt(list.get(5));
        System.out.println("Tổng số item: " + itemTotal);
        int pageTotal = itemTotal / itemTotalOnePage;
        int sodu = itemTotal % itemTotalOnePage;
        System.out.println("Tổng số nguyên: " + pageTotal);
        System.out.println("Tổng số dư: " + sodu);

        if (sodu > 0) {
            pageTotal = pageTotal + 1;
        }

        System.out.println("Tổng số Page: " + pageTotal);

        for (int i = 1; i <= pageTotal; i++) {
            checkSearchTableByColumn(3, searchValue);

            //Nhấn nút Next để đến trang tiếp theo
            if (i < pageTotal) {
                WebUI.clickElement(By.xpath("//a[normalize-space()='Next']"));
                WebUI.sleep(2);
            }
        }
    }
}
