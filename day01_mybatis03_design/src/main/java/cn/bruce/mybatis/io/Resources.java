package cn.bruce.mybatis.io;


import java.io.InputStream;

/**
 * @author Bruce
 * @create 2020-09-10 14:24
 * 实用类加载器读取配置文件
 */
public class Resources {

    public static InputStream getResourceAsStream(String filepath) {
        return Resources.class.getClassLoader().getResourceAsStream(filepath);
    }
}
