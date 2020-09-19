package cn.bruce.mybatis.sqlsession;

import cn.bruce.mybatis.cfg.Configuration;
import cn.bruce.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import cn.bruce.mybatis.utils.XmlConfigBuilder;

import java.io.InputStream;

/**
 * @author Bruce
 * @create 2020-09-10 14:38
 * 用于创建SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream config) {
        Configuration cfg = XmlConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }

}
