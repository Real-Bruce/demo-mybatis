package cn.bruce.dao.impl;

import cn.bruce.dao.IUserDao;
import cn.bruce.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-14 9:01
 */
public class UserDAOImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDAOImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> getUsers() {
        // 1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 2.调用SqlSession方法
        List<User> users = sqlSession.selectList("cn.bruce.dao.IUserDao.getUsers");
        // 3.释放连接
        sqlSession.close();
        return users;
    }

    @Override
    public User getUserById(Integer userId) {
        // 1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 2.调用SqlSession方法
        User user = sqlSession.selectOne("cn.bruce.dao.IUserDao.getUserById", userId);
        // 3.释放连接
        sqlSession.close();
        return user;
    }

    @Override
    public List<User> getUserByName(String userName) {
        // 1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 2.调用SqlSession方法
        List<User> users = sqlSession.selectList("cn.bruce.dao.IUserDao.getUserByName", userName);
        // 3.释放连接
        return users;
    }

    @Override
    public void saveUser(User user) {
        // 1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 2.调用SqlSession方法
        sqlSession.insert("cn.bruce.dao.IUserDao.saveUser", user);
        // 3.提交事务
        sqlSession.commit();
        // 4.释放连接
        sqlSession.close();
    }

    @Override
    public void updateUser(User user) {
        // 1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 2.调用SqlSession方法
        sqlSession.update("cn.bruce.dao.IUserDao.updateUser", user);
        // 3.提交事务
        sqlSession.commit();
        // 4.释放连接
        sqlSession.close();

    }

    @Override
    public void deleteUserById(Integer userId) {
        // 1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 2.调用SqlSession方法
        sqlSession.delete("cn.bruce.dao.IUserDao.deleteUserById", userId);
        // 3.提交事务
        sqlSession.commit();
        // 3.释放连接
        sqlSession.close();
    }

    @Override
    public int getTotalUser() {
        // 1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 2.调用SqlSession方法
        int count = sqlSession.selectOne("cn.bruce.dao.IUserDao.getTotalUser");
        // 3.释放连接
        sqlSession.close();
        return count;
    }
}
