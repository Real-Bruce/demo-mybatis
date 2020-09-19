package cn.bruce.dao;

import cn.bruce.dao.impl.UserDAOImpl;
import cn.bruce.domain.User;
import org.apache.ibatis.io.Resources;
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
 * @create 2020-09-14 9:30
 */
public class TestUserDao {

    private InputStream in;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        // 1.读取配置文件，生成字节流
        in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3.使用工厂创建dao对象
        userDao = new UserDAOImpl(factory);
    }

    @After
    public void destroy() throws IOException {
        in.close();
    }

    @Test
    public void testGetUsers() {
        List<User> users = userDao.getUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetUserBuyId() {
        User user = userDao.getUserById(50);
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
    public void testSaveUser(){
        User user = new User();
        user.setUsername("Jerry");
        user.setSex("女");
        user.setBirthday(new Date());
        user.setAddress("山东烟台");
        userDao.saveUser(user);
        System.out.println("保存成功");
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(56);
        user.setUsername("Tom");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("山东烟台");
        userDao.updateUser(user);
        System.out.println("修改成功");
    }

    @Test
    public void testDeleteUserById(){
        userDao.deleteUserById(58);
    }

    @Test
    public void testGetTotalUser(){
        int totalUser = userDao.getTotalUser();
        System.out.println("用户总数为："+totalUser);
    }

}
