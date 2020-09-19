package cn.bruce.test;

import cn.bruce.dao.IUserDao;
import cn.bruce.domain.User;
import cn.bruce.mybatis.io.Resources;
import cn.bruce.mybatis.sqlsession.SqlSession;
import cn.bruce.mybatis.sqlsession.SqlSessionFactory;
import cn.bruce.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-11 11:13
 */
public class IUserTest {

    public static void main(String[] args) throws IOException {
        // 1.读取配置文件
        InputStream resource = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 2.创建SqlSessionFactory工厂
        /*SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(resource);*/
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resource);

        // 3.使用工厂生产SqlSession对象
        SqlSession sqlSession = factory.openSession();

        // 4.使用SqlSession创建Dao接口代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 5.使用代理对象执行方法
        List<User> users = userDao.getUsers();
        for (User usr : users) {
            System.out.println(usr);
        }

        // 6.释放资源
        sqlSession.close();
        resource.close();
    }
}
