package com.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConn {

    public static Connection getConn() {
        String driver;
        String url;
        String name;
        String password;
        Connection conn = null;
        try {
            Properties pro = new Properties();
            InputStream in1 = DBConn.class.getResourceAsStream("/com/resources/DBConfig.properties");
            pro.load(in1);
            driver = pro.getProperty("SQLServerDriver");
            url = pro.getProperty("SQLServerURL");
            name = pro.getProperty("SQLServerUsername");
            password = pro.getProperty("SQLServerPassword");
            Class.forName(driver);
            conn = DriverManager.getConnection(url,name,password);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
