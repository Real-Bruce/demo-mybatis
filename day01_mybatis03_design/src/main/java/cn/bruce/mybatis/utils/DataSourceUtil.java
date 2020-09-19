package cn.bruce.mybatis.utils;

import cn.bruce.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Bruce
 * @create 2020-09-11 8:54
 * 用于创建数据源的工具类
 */
public class DataSourceUtil {

    public static Connection getConnection(Configuration cfg) {

        try {
            Class.forName(cfg.getDriver());
            return DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
