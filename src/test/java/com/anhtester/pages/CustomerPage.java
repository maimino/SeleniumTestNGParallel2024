package com.anhtester.pages;

import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class CustomerPage {
    private String headerText = "Customers Summary";

    private By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    private By headerCustomersPage = By.xpath("//span[normalize-space()='Customers Summary']");
    private By buttonAddNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By inputSearchCustomers = By.xpath("//div[@id='clients_filter']//input[@type='search']");
    private By buttonImportCustomers = By.xpath("//a[normalize-space()='Import Customers']");
    private By buttonContacts = By.xpath("//a[contains(@href,'clients/all_contacts')]");
    private By firstItemCustomerOnTable = By.xpath("//td[@class='sorting_1']/a");

    //Tao moi customer
    private By inputCompanyName = By.xpath("//input[@id='company']");
    private By inputVatNumber = By.xpath("//input[@id='vat']");
    private By inputPhone = By.xpath("//input[@id='phonenumber']");
    private By inputWebsite = By.xpath("//input[@id='website']");
    private By dropdownGroups = By.xpath("//button[@data-id='groups_in[]']");
    private By inputSearchGroup = By.xpath("//div[@app-field-wrapper='groups_in[]']//input[@type='search']");
    private By dropdownCurrency = By.xpath("//button[@data-id='default_currency']");
    private By inputSearchCurrency = By.xpath("//div[@app-field-wrapper='default_currency']//input[@type='search']");
    private By dropdownLanguage = By.xpath("//button[@data-id='default_language']");
    private By optionVietnamese = By.xpath("//span[normalize-space()='Vietnamese']");
    private By inputAddress = By.xpath("//textarea[@id='address']");
    private By inputCity = By.xpath("//input[@id='city']");
    private By inputState = By.xpath("//input[@id='state']");
    private By inptZipCode = By.xpath("//input[@id='zip']");
    private By dropdownCountry = By.xpath("//button[@data-id='country']");
    private By inputSearchCountry = By.xpath("//div[@app-field-wrapper='country']//input[@type='search']");
    private By buttonSaveAndCreateContact = By.xpath("//button[normalize-space()='Save and create contact']");
    private By buttonSaveCustomer = By.xpath("//div[@id='profile-save-section']//button[normalize-space()='Save']");
    private By pageTotal = By.xpath("//div[@id='clients_info']");


    public void verifyCustomerPage() {
        WebUI.waitForElementVisible(headerCustomersPage);
        Assert.assertTrue(WebUI.checkElementDisplay(headerCustomersPage), "Khong den duoc trang Customers");
        Assert.assertEquals(WebUI.getTextElement(headerCustomersPage), headerText, "Tieu de trang Customers khong dung");
    }

    public void clickButtonAddnew() {
        WebUI.waitForElementVisible(buttonAddNewCustomer);
        WebUI.clickElement(buttonAddNewCustomer);
    }


    public void inputFormData(String COMPANY_NAME) {
        //Tao moi customer
        WebUI.setText(inputCompanyName, COMPANY_NAME);
        WebUI.setText(inputVatNumber, "111");
        WebUI.setText(inputPhone, "0921998365");
        WebUI.setText(inputWebsite, "https://cty1tv.com");
        WebUI.clickElement(dropdownGroups);
        WebUI.sleep(1);
        WebUI.setText(inputSearchGroup, "Gold");
        WebUI.sleep(1);
        WebUI.setKey(inputSearchGroup, Keys.ENTER);
        WebUI.clickElement(dropdownGroups);
        WebUI.clickElement(dropdownCurrency);
        WebUI.sleep(1);
        WebUI.setText(inputSearchCurrency, "usd");
        WebUI.sleep(1);
        WebUI.setKey(inputSearchCurrency, Keys.ENTER);
        WebUI.clickElement(dropdownCurrency);
        WebUI.clickElement(dropdownLanguage);
        WebUI.sleep(1);
        WebUI.clickElement(optionVietnamese);
        WebUI.sleep(1);
        WebUI.clickElement(dropdownLanguage);
        WebUI.setText(inputAddress, "Gia Lam - Ha Noi");
        WebUI.setText(inputCity, "HA NOI");
        WebUI.sleep(1);
        WebUI.setText(inputState, "Ha Noi");
        WebUI.setText(inptZipCode, "123");
        WebUI.clickElement(dropdownCountry);
        WebUI.sleep(1);
        WebUI.setText(inputSearchCountry, "vietnam");
        WebUI.sleep(1);
        WebUI.setKey(inputSearchCountry, Keys.ENTER);
        WebUI.clickElement(buttonSaveCustomer);
        WebUI.sleep(3);
    }

    public void searchAndVerifyCustomer(String COMPANY_NAME) {
        //Tim kiem customer vua tao moi
        WebUI.clickElement(menuCustomers);
        WebUI.clickElement(inputSearchCustomers);
        WebUI.setText(inputSearchCustomers, COMPANY_NAME);
        WebUI.sleep(2);
        Assert.assertTrue(WebUI.checkElementExist(firstItemCustomerOnTable), "Khong tim thay customer");
    }

    public void searchCustomer(String COMPANY_NAME) {
        WebUI.clickElement(inputSearchCustomers);
        WebUI.setText(inputSearchCustomers, COMPANY_NAME);
        WebUI.sleep(2);
        WebUI.waitForPageLoaded();
    }

    public void verifyCustomerDetail(String COMPANY_NAME){
        //Kiem tra gia tri sau khi tao moi
        SoftAssert softAssert = new SoftAssert();
        WebUI.clickElement(firstItemCustomerOnTable);
        softAssert.assertEquals(WebUI.getAttributeElement(inputCompanyName,"value"), COMPANY_NAME, "Ten Comppany khong dung");
        softAssert.assertEquals(WebUI.getAttributeElement(inputVatNumber, "value"), "111", "VAT Number khong dung");
        softAssert.assertEquals(WebUI.getAttributeElement(inputPhone, "value"), "0921998365", "So dien thoai khong dung");
        softAssert.assertEquals(WebUI.getAttributeElement(inputWebsite, "value"), "https://cty1tv.com", "Website khong dung");
        softAssert.assertAll();
    }

    public void checkPageTotal(){
        String pageToTalText = WebUI.getTextElement(pageTotal);
        System.out.println(pageToTalText);
        String pageTotalNumber = pageToTalText.split(" ")[3];
        System.out.println("Check page total: " + pageTotalNumber);
    }

    public void checkPageTotal(int total){
        String pageToTalText = WebUI.getTextElement(pageTotal);
        System.out.println(pageToTalText);
        String pageTotalNumber = pageToTalText.split(" ")[3];
        System.out.println("Check page total: " + pageTotalNumber);

        Assert.assertEquals(Integer.parseInt(pageTotalNumber), total, "The page total not match");
    }

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

}