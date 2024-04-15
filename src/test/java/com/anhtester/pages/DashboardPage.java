package com.anhtester.pages;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DashboardPage {

    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    private By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    private By dropdownProfile = By.xpath("//li[contains(@class,'icon header-user-profile')]");
    private By optionLogout = By.xpath("//ul[@class='dropdown-menu animated fadeIn']//li[@class='header-logout']//a[@href='#'][normalize-space()='Logout']");
    private By menuReports = By.xpath("//span[normalize-space()='Reports']");

    public void clickMenuDashboard(){
        WebUI.waitForElementVisible(menuDashboard);
        WebUI.clickElement(menuDashboard);
    }

    public CustomerPage clickMenuCustomers(){
        WebUI.waitForElementVisible(menuCustomers);
        WebUI.clickElement(menuCustomers);
        return new CustomerPage();
    }

    public void clickMenuProjects(){
        WebUI.waitForElementVisible(menuProjects);
        WebUI.clickElement(menuProjects);
    }

    public LoginPage logOut(){
        WebUI.clickElement(dropdownProfile);
        WebUI.waitForElementVisible(optionLogout);
        WebUI.clickElement(optionLogout);

        return new LoginPage();
    }

    public void verifyMenuReportDisplay() {
        WebUI.waitForPageLoaded();
        Assert.assertTrue(WebUI.checkElementExist(menuReports), "Menu Reports khong hien thi");
    }

    public void verifyMenuReportNotDisplay() {
        WebUI.waitForElementVisible(menuReports);
        Assert.assertFalse(WebUI.checkElementExist(menuReports), "Menu Reports duoc hien thi");
    }
}