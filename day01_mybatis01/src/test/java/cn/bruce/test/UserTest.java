package cn.bruce.test;


import cn.bruce.dao.IUserDao;
import cn.bruce.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-09 16:55
 */
public class UserTest {

    public static void main(String[] args) throws Exception {

        // 1.读取配置文件
        InputStream resource = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 2.创建sqlSessionFactory工厂
        /*SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = sessionFactoryBuilder.build(resource);*/

        // 2.使用匿名对象创建sqlSessionFactory工厂
        SqlSessionFactory factory1 = new SqlSessionFactoryBuilder().build(resource);

        // 3.使用工厂生产sqlSession对象
        SqlSession sqlSession = factory1.openSession();

        // 4.使用sqlSession创建Dao接口的代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 5.使用代理对象执行方法
        List<User> users = userDao.getUsers();
        for (User user : users) {
            System.out.println(user);
        }

        // 6.释放资源
        sqlSession.close();
    }
}
