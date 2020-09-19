package cn.bruce.dao;

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
import java.util.Date;
import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-17 15:50
 */
public class AnnotationCRUDTest {
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
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUsername("test");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("北京市海淀区");
        userDao.saveUser(user);
    }

    @Test
    public void testDeleteUser(){
        userDao.deleteUserById(72);
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(68);
        user.setUsername("update");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京市海淀区");
        userDao.updateUser(user);
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
        User user = userDao.getUserById(68);
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
    public void testGetUserTotal(){
        int totalUser = userDao.getTotalUser();
        System.out.println("共有用户：" + totalUser);
    }
}
