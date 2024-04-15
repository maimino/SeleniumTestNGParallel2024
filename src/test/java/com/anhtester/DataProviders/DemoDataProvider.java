package com.anhtester.DataProviders;

import com.anhtester.dataprovider.DataProviderFactoryLogin;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class DemoDataProvider {

    @Test(dataProvider = "DataLoginSuccess", dataProviderClass = DataProviderFactoryLogin.class)
    public void testLoginSuccess(String email, String password){
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }

    @Test(dataProvider = "data_provider_login_excel_hashtable", dataProviderClass = DataProviderFactoryLogin.class)
    public void testLoginFromExcelMultipleRow(Hashtable<String, String> data){
        System.out.println("Email: " + data.get("email"));
        System.out.println("Password: " + data.get("password"));
    }
}
