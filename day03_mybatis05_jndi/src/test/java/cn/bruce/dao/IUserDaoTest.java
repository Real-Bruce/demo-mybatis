package cn.bruce.dao;

import cn.bruce.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-23 14:41
 */
public class IUserDaoTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSession = new SqlSessionFactoryBuilder().build(in).openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
//        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    public void testGetUsers(){
        List<User> users = userDao.getUsers();
        for (User u : users) {
            System.out.println(u);
        }
    }
}
