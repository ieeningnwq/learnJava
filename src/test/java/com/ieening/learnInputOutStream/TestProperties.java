package com.ieening.learnInputOutStream;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Test;

public class TestProperties {
    @Test
    public void testReadProperties() {
        Properties prop = new Properties();
        try (FileInputStream fStream = new FileInputStream("src\\main\\resources\\properties\\config.properties")) {
            prop.load(fStream);
            String host = prop.getProperty("db.host");
            assertEquals("192.168.10.100", host);
            int port = Integer.valueOf(prop.getProperty("db.port", "3306"));
            assertEquals(3306, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
