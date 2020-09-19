package cn.bruce.test;

import cn.bruce.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Bruce
 * @create 2020-09-18 11:08
 */
public class SecondLevelCacheTest {

    private InputStream in;
    private SqlSessionFactory factory;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    @After
    public void destroy() throws IOException {
        in.close();
    }
    
    @Test
    public void testGetUserById(){
        SqlSession sqlSession1 = factory.openSession();
        IUserDao userDao = sqlSession1.getMapper(IUserDao.class);
        User user1 = userDao.getUserById(41);
        System.out.println(user1);

        // 释放一级缓存
        sqlSession1.close();

        // 建立新的sqlSession
        SqlSession sqlSession2 = factory.openSession();
        IUserDao userDao1 = sqlSession2.getMapper(IUserDao.class);
        User user2 = userDao1.getUserById(41);
        System.out.println(user2);

        sqlSession2.close();

    }
}
