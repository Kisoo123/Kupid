package com.kupid.common.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class JDBCDriverListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 애플리케이션 시작 시 추가할 작업이 있으면 여기에 작성
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 애플리케이션 종료 시 JDBC 드라이버 해제
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("Deregistered JDBC driver: " + driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
