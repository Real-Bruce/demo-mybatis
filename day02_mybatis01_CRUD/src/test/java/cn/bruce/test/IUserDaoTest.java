package cn.bruce.test;

import cn.bruce.dao.IUserDao;
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
 * @create 2020-09-12 11:05
 */
public class IUserDaoTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    /**
     * 在测试执行方法执行前执行
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        // 1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3.获取SqlSession对象
        sqlSession = factory.openSession();
        // 4.获取dao对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    /**
     * 用于在测试方法执行后执行
     * @throws IOException
     */
    @After
    public void destroy() throws IOException {
        // 提交事务
        sqlSession.commit();
        // 释放连接
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有用户
     */
    @Test
    public void testGetUsers() {
        List<User> users = userDao.getUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试根据id获取用户
     */
    @Test
    public void testGetUserById() {
        User user = userDao.getUserById(41);
        System.out.println(user);

    }

    /**
     * 测试更新用户操作
     */
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUserId(48);
        user.setUserName("Bruce");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        user.setUserAddress("山东淄博");
        userDao.updateUserById(user);
    }

    /**
     * 测试保存账户
     */
    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUserName("Bruce");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        user.setUserAddress("山东济南");
        userDao.saveUser(user);
        System.out.println(new Date());
    }


    /**
     * 测试根据id删除用户
     */
    @Test
    public void testDeleteUserById() {
        userDao.deleteUserById(59);
    }

    /**
     * 测试根据名称查询用户
     */
    @Test
    public void testGetUserByName() {
        List<User> users = userDao.getUserByName("Bruce");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 获取用户总条目数
     */
    @Test
    public void testGetUserTotal() {
        int userTotal = userDao.getUserTotal();
        System.out.println("用户总数为：" + userTotal);
    }

    /**
     * 根据queryVo获取用户
     */
    @Test
    public void testGetUserByQueryVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("王");
        vo.setUser(user);
        // 执行查询方法
        List<User> users = userDao.getUserByQueryVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }
}
