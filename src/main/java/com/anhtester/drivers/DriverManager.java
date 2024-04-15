package com.anhtester.drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    //Thay the cho tat ca cac gia tri driver binh thuong trong project
    public static WebDriver getDriver() {
        return driver.get(); //thuoc ThreadLocal
    }

    //Dung tai BaseTest o vi tri @Before (can set gia tri driver truoc khi chay testcase)
    public static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver);
    }

    //Dung tai BaseTest o vi tri @After (de reset gia tri driver ve null va xoa luon gia tri driver do trong ThreadLocal sau moi testcase)
    public static void quit() {
        if (DriverManager.driver.get() != null) {
            DriverManager.driver.get().quit(); //thuộc selenium nhưng lấy giá trị từ ThreadLocal -> tắt luôn giá trị driver về null
            driver.remove(); //thuộc ThreadLocal dùng để xóa vị trí lưu trữ của driver đó trong ThreadLocal
        }
    }
}
