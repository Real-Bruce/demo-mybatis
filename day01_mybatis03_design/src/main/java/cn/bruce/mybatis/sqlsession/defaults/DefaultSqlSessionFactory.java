package cn.bruce.mybatis.sqlsession.defaults;

import cn.bruce.mybatis.cfg.Configuration;
import cn.bruce.mybatis.sqlsession.SqlSession;
import cn.bruce.mybatis.sqlsession.SqlSessionFactory;

/**
 * @author Bruce
 * @create 2020-09-11 9:07
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * 用于创建一个新的操作数据库的对象
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
