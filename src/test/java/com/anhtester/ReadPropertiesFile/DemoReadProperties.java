package com.anhtester.ReadPropertiesFile;

import com.anhtester.helpers.PropertiesHelper;
import org.testng.annotations.Test;

public class DemoReadProperties {
    @Test
    public void testReadPropertiesFile(){
        PropertiesHelper.loadAllFiles();

        System.out.println(PropertiesHelper.getValue("url"));
        System.out.println(PropertiesHelper.getValue("email"));
        System.out.println(PropertiesHelper.getValue("password"));

        //Set value in file - thuc te it khi dung ma set vao file excel
        PropertiesHelper.setFile("src/test/resources/configs/configs.properties");
        PropertiesHelper.setValue("message", "Day la noi dung message");
    }
}
