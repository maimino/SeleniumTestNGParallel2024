package com.anhtester.Log4j2;

import com.anhtester.utils.LogUtils;
import org.testng.annotations.Test;

public class DemoWriteLog {
    @Test
    public void testWriteLogToFile(){
        LogUtils.info("Day la log demo");
    }
}
