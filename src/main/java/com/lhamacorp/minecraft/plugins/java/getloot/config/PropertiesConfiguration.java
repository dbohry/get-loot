package com.lhamacorp.minecraft.plugins.java.getloot.config;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfiguration {

    public static final Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();

        try {
            ClassLoader classLoader = PropertiesConfiguration.class.getClassLoader();
            InputStream applicationPropertiesStream = classLoader.getResourceAsStream("application.properties");
            PROPERTIES.load(applicationPropertiesStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
