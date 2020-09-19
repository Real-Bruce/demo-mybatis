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
import java.util.Date;
import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-14 16:34
 */
public class IUserDAOTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        // 1.通过字节流读取配置文件
        in = Resources.getResourceAsStream("mysql-config.xml");
        // 2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3.获取SqlSession对象
        sqlSession = factory.openSession();
        // 4.获取dao代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        // 提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
        in.close();
    }

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUserName("张三");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        user.setUserAddress("浙江温州");
        userDao.saveUser(user);
        System.out.println("新增成功");
    }

    @Test
    public void testDeleteUserById(){
        userDao.deleteUserById(50);
        System.out.println("删除成功！");
    }
    
    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setUserId(60);
        user.setUserName("Big Mom");
        userDao.updateUser(user);
        System.out.println("修改成功！");
    }
    
    @Test
    public void testGetUsers(){
        List<User> users = userDao.getUsers();
        for (User user : users) {
            System.out.println(user);
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
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetUserByQueryVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("王");
        vo.setUser(user);
        List<User> users = userDao.getUserByQueryVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }
}
