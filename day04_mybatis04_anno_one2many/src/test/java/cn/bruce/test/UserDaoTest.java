package cn.bruce.test;

import cn.bruce.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-18 9:33
 */
public class UserDaoTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSession = new SqlSessionFactoryBuilder().build(in).openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.close();
        in.close();
    }

    @Test
    public void testGetUsers(){
        List<User> users = userDao.getUsers();
        for (User u : users) {
            System.out.println("-------------");
            System.out.println(u);
            System.out.println(u.getAccounts());
        }
    }
    
    @Test
    public void testGetUserById(){
        User user = userDao.getUserById(41);
        System.out.println(user);
    }

    @Test
    public void testGetUserByName(){
        List<User> users = userDao.getUserByName("王");
        for (User user : users) {
            System.out.println("---------------------");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }
}
