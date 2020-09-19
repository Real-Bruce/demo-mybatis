package cn.bruce.dao.impl;

import cn.bruce.dao.IUserDao;
import cn.bruce.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-10 9:49
 */
public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> getUsers() {
        // 1.使用工厂创建SqlSession对象
        SqlSession session = factory.openSession();
        // 2.使用session执行查询方法
        List<User> users = session.selectList("cn.bruce.dao.IUserDao.getUsers");
        session.close();
        // 3.返回查询结果
        return users;
    }
}
