package com.anhtester.pages;

import com.anhtester.constants.ConfigData;
import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.WebUI;
import com.anhtester.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {

    //Khai bao cac element dang doi tuong By(phuong thuc tim kiem)
    private By headerPage = By.xpath("//h1[normalize-space()='Login']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@name='password']");
    private By buttonLogin = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//div[@id='alerts']");


    private void setEmail(String email){
        //driver.findElement(inputEmail).sendKeys(email);
        //WebUI.getWebElement(inputEmail).sendKeys(email);
        WebUI.setText(inputEmail, email);
    }

    private void setPassword(String password){
        WebUI.setText(inputPassword,password);
    }

    private void clickLoginButton(){
        WebUI.clickElement(buttonLogin);
    }

    public void verifyLoginSuccess(){
        WebUI.waitForPageLoaded();
        LogUtils.info("Verify login success");
        Assert.assertFalse(DriverManager.getDriver().getCurrentUrl().contains("authentication"), "Van dang o trang login");
    }

    public void verifyLoginFail(){
        WebUI.waitForPageLoaded();
        LogUtils.info("Verify login fail");
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("authentication"), "Khong con o trang login");
        Assert.assertTrue(WebUI.checkElementDisplay(errorMessage), "Error message not displayed");
        Assert.assertEquals(WebUI.getTextElement(errorMessage), "Invalid email or password", "Noi dung error message khong dung");
    }

    //Cac ham xu ly cho chinh trang nay
    public DashboardPage loginCRM(String email, String password){
        WebUI.openURL(ConfigData.URL);
        WebUI.waitForPageLoaded();
        setEmail(email);
        setPassword(password);
        clickLoginButton();

        return new DashboardPage(); //tao lien ket giua cac trang
    }
}