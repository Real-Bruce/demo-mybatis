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
 * @create 2020-09-17 10:48
 */
public class IUserDaoTest {
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
    public void testFirstLevelCache(){
        User user1 = userDao.getUserById(60);
        System.out.println(user1);

        System.out.println("-------------------");
        // 此操作会清除缓存
        sqlSession.clearCache();

        User user2 = userDao.getUserById(60);
        // 开启缓存后不会再发送查询请求
        System.out.println(user2);

        System.out.println(user1 == user2);
    }

    @Test
    public void testSyncCache(){
        // 1.查询用户
        User user = userDao.getUserById(60);
        System.out.println(user);

        // 2.更新用户
        user.setUserName("Big M");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        user.setUserAddress("啥不懂");
        user.setUserId(60);
        userDao.updateUser(user);

        // 3.重新查询用户
        User user1 = userDao.getUserById(60);
        System.out.println(user1);

        System.out.println(user == user1);
    }
}
