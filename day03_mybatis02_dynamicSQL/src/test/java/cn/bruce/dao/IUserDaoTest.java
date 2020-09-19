package cn.bruce.dao;

import cn.bruce.domain.QueryVo;
import cn.bruce.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-15 10:49
 */
public class IUserDaoTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession(true);
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
            System.out.println(u);
        }
    }

    @Test
    public void testGetUserById(){
        User user = userDao.getUserById(60);
        System.out.println(user);
    }

    @Test
    public void testGetUserByName(){
        List<User> users = userDao.getUserByName("王");
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testGetUserByQueryVo(){
        User user = new User();
        user.setUserName("B");
        QueryVo vo = new QueryVo();
        vo.setUser(user);
        List<User> users = userDao.getUserByQueryVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testGetUserByCondition(){
        User user = new User();
        user.setUserName("王");
        user.setUserAddress("北京");
        user.setUserSex("男");
        List<User> users = userDao.getUserByCondition(user);
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testGetUserByIds(){
        QueryVo vo = new QueryVo();
        List<Integer> userList = new ArrayList<Integer>();
        userList.add(41);
        userList.add(48);
        userList.add(60);
        userList.add(67);
        vo.setIds(userList);
        List<User> users = userDao.getUserByIds(vo);
        for (User u : users) {
            System.out.println(u);
        }

    }
}
